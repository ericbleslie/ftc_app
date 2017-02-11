package org.firstinspires.ftc.team10428;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric Leslie on 10/15/2016.
 */
@TeleOp(name="Basic - With Sweeper", group="Movement")
public class teleopBasicWithOtherMotors extends OpMode {

    //Declare hardware variables
    public DcMotor motorNW;
    public DcMotor motorNE;
    public DcMotor motorSW;
    public DcMotor motorSE;
    public DcMotor motorSweeper;

    public void init() {
        //Initialize hardware
        motorNW = hardwareMap.dcMotor.get("NW");
        motorNE = hardwareMap.dcMotor.get("NE");
        motorSW = hardwareMap.dcMotor.get("SW");
        motorSE = hardwareMap.dcMotor.get("SE");
        motorSweeper = hardwareMap.dcMotor.get("Sweeper");
        motorNW.setPower(0);
        motorNE.setPower(0);
        motorSW.setPower(0);
        motorSE.setPower(0);
        motorSweeper.setPower(0);
        motorNW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorNE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorSW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorSE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorSweeper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Say", "Hello Driver");
    }

    public void loop() {

        //Define initial motor velocity
        float NW = 0;
        float NE = 0;
        float SW = 0;
        float SE = 0;

        //Declare variables to controller stick inputs
        float xL = gamepad1.left_stick_x;
        float yL = -gamepad1.left_stick_y;
        float xR = gamepad1.right_stick_x;
        boolean LT = (gamepad1.left_trigger > 0.1);
        boolean RT = (gamepad1.right_trigger > 0.1);


        //Set motor power based on controller inputs
        if ((xL < -0.1) || (xL > 0.1)) {
            NW += xL;
            NE += xL;
            SW -= xL;
            SE -= xL;
        }
        if ((yL < -0.1) || (yL > 0.1)) {
            NW += yL;
            NE -= yL;
            SW += yL;
            SE -= yL;
        }
        if ((xR < -0.1) ||(xR > 0.1)) {
            NW += xR;
            NE += xR;
            SW += xR;
            SE += xR;
        }


        //Normalize motor speed values greater than 1.0
        float maxVal = Math.max(Math.max(Math.abs(NW),Math.abs(NE)),Math.max(Math.abs(SW),Math.abs(SE)));
        if(maxVal > 1)
        {
            NW /= maxVal;
            NE /= maxVal;
            SW /= maxVal;
            SE /= maxVal;
        }

        //Send N, E, S, W power to corresponding motors
        motorNW.setPower(NW);
        motorNE.setPower(NE);
        motorSW.setPower(SW);
        motorSE.setPower(SE);
        if (LT){motorSweeper.setPower(1.0);} else if (RT){motorSweeper.setPower(-1.0);}
        //motorNW.setPower(1);
        //motorNE.setPower(1);
        //motorSW.setPower(1);
        //motorSE.setPower(1);
    }
}