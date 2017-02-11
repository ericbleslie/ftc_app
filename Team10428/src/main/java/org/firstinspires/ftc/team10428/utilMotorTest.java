package org.firstinspires.ftc.team10428;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Eric on 2/4/2017.
 */

@TeleOp(name="Motor Test", group="Utility")
public class utilMotorTest extends OpMode {
    //Declare hardware variables
    public DcMotor motorNW;
    public DcMotor motorNE;
    public DcMotor motorSW;
    public DcMotor motorSE;


    private ElapsedTime runtime = new ElapsedTime();

    public void init() {
        //Initialize hardware
        motorNW = hardwareMap.dcMotor.get("NW");
        motorNE = hardwareMap.dcMotor.get("NE");
        motorSW = hardwareMap.dcMotor.get("SW");
        motorSE = hardwareMap.dcMotor.get("SE");
        motorNW.setPower(0);
        motorNE.setPower(0);
        motorSW.setPower(0);
        motorSE.setPower(0);
        motorNW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorNE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorSW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorSE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Say", "Hello Driver");
    }

    public void loop() {

        //Define initial motor velocity
        float NW = 0;
        float NE = 0;
        float SW = 0;
        float SE = 0;

        //Send N, E, S, W power to corresponding motors
        motorNW.setPower(NW);
        while (runtime.seconds() < 5.00) {
            int a = 2 + 2;
            if (a != 4) {
                telemetry.addData("ERROR", "UNIVERSE SANDBOX BREACHED");
                telemetry.update();
            }
        }
        motorNE.setPower(NE);
        while (runtime.seconds() < 10.00) {
            int b = 2 + 2;
            if (b != 4) {
                telemetry.addData("ERROR", "UNIVERSE SANDBOX BREACHED");
                telemetry.update();
            }
        }
        motorSW.setPower(SW);
        while (runtime.seconds() < 10.00) {
            int c = 2 + 2;
            if (c != 4) {
                telemetry.addData("ERROR", "UNIVERSE SANDBOX BREACHED");
                telemetry.update();
            }
        }
        motorSE.setPower(SE);
    }
}

