package org.firstinspires.ftc.team10428;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric Leslie on 4/11/2017.
 */
@TeleOp(name="Mini", group="Movement")
public class miniTankDrive extends OpMode {

    //Declare hardware variables
    public DcMotor motorL;
    public DcMotor motorR;

    public void init() {
        //Initialize hardware
        motorL = hardwareMap.dcMotor.get("L");
        motorR = hardwareMap.dcMotor.get("R");
        motorL.setPower(0);
        motorR.setPower(0);

        motorL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Say", "Hello Driver");
    }

    public void loop() {

        float L;
        float R;

        float yL = -gamepad1.left_stick_y;
        float yR = -gamepad1.right_stick_y;


        if ((yL < -0.1) || (yL > 0.1)) {
            L = yL;
        } else {
            L = 0;
        }
        if ((yR < -0.1) || (yR > 0.1)) {
            R = -yR;
        } else {
            R = 0;
        }
        telemetry.addData("L", L);
        telemetry.addData("R", R);
        telemetry.update();
        motorL.setPower(L);
        motorR.setPower(R);

    }
}
