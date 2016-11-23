package org.firstinspires.ftc.team3963;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric on 11/21/2016.
 */
@TeleOp(name="Simple Drive (USE THIS)", group="Movement")
public class coreTeleOp extends OpMode {

    //Declare hardware variables
    public DcMotor motorE;
    public DcMotor motorW;
    public DcMotor motorS;
    public DcMotor motorShooter;

    public void init() {
        motorE = hardwareMap.dcMotor.get("East");
        motorW = hardwareMap.dcMotor.get("West");
        motorS = hardwareMap.dcMotor.get("South");
        motorShooter = hardwareMap.dcMotor.get("Shooter");
        motorE.setPower(0);
        motorW.setPower(0);
        motorS.setPower(0);
        motorShooter.setPower(0);
        motorE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorS.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Say", "Initialized");
    }
    public void loop(){
        // ===Variables that need input===
        boolean motorEReverse = true;
        boolean motorWReverse = false;
        boolean motorSReverse = false;
        boolean motorShooterReverse= false;
        //Define initial motor velocity
        float E = 0;
        float W = 0;
        float S = 0;
        float Shooter = 0;        //Define starting position of sticks
        float yL = gamepad1.left_stick_y;
        float xR = gamepad1.right_stick_x;
        float tL = gamepad1.left_trigger;
        float tR = gamepad1.right_trigger;

        if ((yL < -0.1) || (yL > 0.1)) {
            E -= yL;
            W -= yL;
        }
        if ((xR < -0.1) || (xR > 0.1)) {
            E += xR;
            W -= xR;
            S -= xR;
        }
        if ((tR > 0.1) && !(tL >0.1)) {
            Shooter += tR;
        }
        if ((tL > 0.1) && !(tR >0.1)) {
            Shooter += tL;
        }

        //Reverse motor outputs if motor is geared
        if (motorEReverse){ E = -E; }
        if (motorWReverse){ W = -W; }
        if (motorSReverse){ S = -S;}
        if (motorShooterReverse){Shooter = -Shooter;}

        //Normalize motor speed
        float maxVal = Math.max(Math.abs(E),Math.max(Math.abs(W),Math.abs(S)));
        if(maxVal > 1)
        {
            E /= maxVal;
            W /= maxVal;
            S /= maxVal;
        }

        motorE.setPower(E);
        motorW.setPower(W);
        motorS.setPower(S);
        motorShooter.setPower(Shooter);
        telemetry.clearAll();
        telemetry.addData("E: ", E);
        telemetry.addData("W: ", W);
        telemetry.addData("S: ", S);
        telemetry.addData("Shooter: ", Shooter);
    }
}
