package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric on 10/29/2016.
 */
@TeleOp(name="Team 3963", group="Movement")
@Disabled
public class JTDC extends OpMode {
    //Declare hardware variables
    public DcMotor motorE;
    public DcMotor motorS;
    public DcMotor motorW;
    public DcMotor motorShooter;

    public void init() {
        motorE = hardwareMap.dcMotor.get("East");
        motorS = hardwareMap.dcMotor.get("South");
        motorW = hardwareMap.dcMotor.get("West");
        motorShooter = hardwareMap.dcMotor.get("Shooter");
        motorE.setPower(0);
        motorS.setPower(0);
        motorW.setPower(0);
        motorShooter.setPower(0);
        motorE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorS.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Say", "Hello Driver");
    }
    public void loop() {
        // ===Variables that need input===
        boolean motorEReverse = true;
        boolean motorSReverse = true;
        boolean motorWReverse = false;
        boolean motorShooterReverse = false;
        //Define initial motor velocity
        float E = 0;
        float S = 0;
        float W = 0;
        //Define starting position of sticks
        float yL = gamepad1.left_stick_y;
        float xR = gamepad1.right_stick_x; //yR is useless for movement so it is not defined

        //Internal Variables
        float maxVal;

        //Convert X and Y coordinates of both sticks to motor outputs
        if (yL < -.1) {
            E += yL;
            W += yL;
        } else if (yL > .1) {
            E += yL;
            W += yL;
        }
        if (xR < -.1) {
            E -= yL;
            W += yL;
            S -= yL;

        } else if (xR > .1){
            E -= yL;
            W += yL;
            S -= yL;
        }

        //Reverse motor outputs if motor is geared
        if (motorEReverse){ E = -E; }
        if (motorSReverse){ S = -S; }
        if (motorWReverse){ W = -W; }

        //Normalize motor speed
        maxVal = Math.max(Math.abs(E),Math.max(Math.abs(S),Math.abs(W)));
        if(maxVal > 1)
        {
            E /= maxVal;
            S /= maxVal;
            W /= maxVal;
        }

        motorE.setPower(E);
        motorS.setPower(S);
        motorW.setPower(W);
        if (gamepad1.right_trigger > .1) {motorShooter.setPower(gamepad1.right_trigger);}
        if (gamepad1.left_trigger > .1) {motorShooter.setPower(-gamepad1.left_trigger);}
        if (gamepad1.right_trigger > .1 && gamepad1.left_trigger > .1) {motorShooter.setPower(0);}
    }
}
