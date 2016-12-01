package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Eric on 11/21/2016.
 */

public class coreUtilities{
    HardwareMap hardwareMap = null;
            DcMotor motorN = hardwareMap.dcMotor.get("North");
            DcMotor motorE = hardwareMap.dcMotor.get("East");
            DcMotor motorS = hardwareMap.dcMotor.get("South");
            DcMotor motorW = hardwareMap.dcMotor.get("West");

    public void move(String direction, float speed){
        if (direction.toUpperCase().startsWith("N")){
            motorE.setPower(-speed);
            motorW.setPower(speed);
        }
        if (direction.toUpperCase().startsWith("S")){
            motorE.setPower(speed);
            motorW.setPower(-speed);
        }
        if (direction.toUpperCase().startsWith("E")){
            motorN.setPower(speed);
            motorS.setPower(-speed);
        }
        if (direction.toUpperCase().startsWith("W")){
            motorN.setPower(-speed);
            motorS.setPower(speed);
        }
    }
}
