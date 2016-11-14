package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric Leslie  on 10/15/2016.
 */
@TeleOp(name="Team 6476", group="Movement")
@Disabled
public class Spencer extends OpMode {
    //Declare hardware variables
    public DcMotor motorN;
    public DcMotor motorE;
    public DcMotor motorS;
    public DcMotor motorW;
    //public DcMotor motorArm;

    public void init() {
        motorN = hardwareMap.dcMotor.get("North");
        motorE = hardwareMap.dcMotor.get("East");
        motorS = hardwareMap.dcMotor.get("South");
        motorW = hardwareMap.dcMotor.get("West");
        //motorArm = hardwareMap.dcMotor.get("Arm");
        motorN.setPower(0);
        motorE.setPower(0);
        motorS.setPower(0);
        motorW.setPower(0);
        //motorArm.setPower(0);
        motorN.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorS.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //motorArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Say", "Hello Driver");
    }
    public void loop() {
        // ===Variables that need input===
        boolean motorNReverse = false;
        boolean motorEReverse = true;
        boolean motorSReverse = true;
        boolean motorWReverse = false;
        //boolean motorArmReverse = false;
        //Define initial motor velocity
        float N = 0;
        float E = 0;
        float S = 0;
        float W = 0;
        //float A = 0;
        //Define starting position of sticks
        float xL = gamepad1.left_stick_x;
        float yL = gamepad1.left_stick_y;
        float xR = gamepad1.right_stick_x; //yR is useless for movement so it is not defined
        //float xL2 = gamepad2.left_stick_x;
        //float yL2 = gamepad2.left_stick_x;
        //float xR2 = gamepad2.right_stick_x;
        //float yR2 = gamepad2.right_stick_y;
        //Internal Variables
        float maxVal;

        //Convert X and Y coordinates of both sticks to motor outputs
        if (xL < -.1) {
            N -= xL;
            S -= xL;
        } else if (xL > .1) {
            N -= xL;
            S -= xL;
        }
        if (yL < -.1) {
            E += yL;
            W += yL;
        } else if (yL > .1) {
            E += yL;
            W += yL;
        }
        if (xR < -.1) { //Bug: South and West wheel turn wrong way whether +/- when turning w/ right stick
            N += xR / 2;
            E -= xR / 2;
            S -= xR / 2;
            W += xR / 2;
        } else if (xR > .1){
            N += xR / 2;
            E -= xR / 2;
            S -= xR / 2;
            W += xR / 2;
        }

        //Reverse motor outputs if motor is geared
        if (motorNReverse){ N = -N; }
        if (motorEReverse){ E = -E; }
        if (motorSReverse){ S = -S; }
        if (motorWReverse){ W = -W; }

        //Normalize motor speed
        maxVal = Math.max(Math.max(Math.abs(N),Math.abs(E)),Math.max(Math.abs(S),Math.abs(W)));
        if(maxVal > 1)
        {
            N /= maxVal;
            E /= maxVal;
            S /= maxVal;
            W /= maxVal;
        }

        motorN.setPower(N);
        motorE.setPower(E);
        motorS.setPower(S);
        motorW.setPower(W);
        telemetry.addData("Say", N);
        telemetry.addData("Say", E);
    }
}