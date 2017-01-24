package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Eric on 12/13/2016.
 */

public class testClass extends OpMode{

    public DcMotor Left;
    public DcMotor Right;

    public void init(){
        Left = hardwareMap.dcMotor.get("Left");
        Right = hardwareMap.dcMotor.get("Right");
        Left.setPower(0);
        Right.setPower(0);
        Left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void loop(){
        //double xL = gamepad1.left_stick_x;
        double yL = -gamepad1.left_stick_y;
        double xR = gamepad1.right_stick_x;
        //double yR = -gamepad1.right_stick_y;
        double leftPower = 0;
        double rightPower = 0;

        if ((yL > 0.1) || (yL < -0.1)){
            leftPower += yL;
            rightPower -= yL;
        }
        if ((xR > 0.1) || (xR < -0.1)){
            leftPower += xR;
            rightPower += xR;
        }




        Left.setPower(leftPower);
        Right.setPower(rightPower);

    }


}
