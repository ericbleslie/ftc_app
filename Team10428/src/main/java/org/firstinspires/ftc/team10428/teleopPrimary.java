package org.firstinspires.ftc.team10428;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric Leslie on 10/15/2016.
 */
@TeleOp(name="Primary - Untested", group="Movement")
public class teleopPrimary extends OpMode {
    core core = new core();

    //Declare hardware variables
    public DcMotor motorNW;
    public DcMotor motorNE;
    public DcMotor motorSW;
    public DcMotor motorSE;

    public void init(){
        core.init(hardwareMap);
    }


    public void loop() {
        //Send N, E, S, W power to corresponding motors
        double NW = core.vector2motor(core.getHeading(core.getStickInput("xL1"), core.getStickInput("yL1")), 0, "T")[0];
        double NE = core.vector2motor(core.getHeading(core.getStickInput("xL1"), core.getStickInput("yL1")), 0, "T")[1];
        double SW = core.vector2motor(core.getHeading(core.getStickInput("xL1"), core.getStickInput("yL1")), 0, "T")[2];
        double SE = core.vector2motor(core.getHeading(core.getStickInput("xL1"), core.getStickInput("yL1")), 0, "T")[3];

        double maxVal = Math.max(Math.max(Math.abs(NW),Math.abs(NE)),Math.max(Math.abs(SW),Math.abs(SE)));
        if(maxVal > 1)
        {
            NW /= maxVal;
            NE /= maxVal;
            SW /= maxVal;
            SE /= maxVal;
        }

        motorNW.setPower(NW);
        motorNE.setPower(NE);
        motorSW.setPower(SW);
        motorSE.setPower(SE);
    }
}