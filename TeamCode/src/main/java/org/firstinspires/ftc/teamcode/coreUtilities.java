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
    public void registerMotor(String pos, String name){
        if (pos == "N") {
            DcMotor motorN = hardwareMap.dcMotor.get(name);
        } else if (pos  == "E") {
            DcMotor motorE = hardwareMap.dcMotor.get(name);
        } else if (pos == "S") {
            DcMotor motorS = hardwareMap.dcMotor.get(name);
        } else if (pos == "W") {
            DcMotor motorW = hardwareMap.dcMotor.get(name);
        }
    }
}
