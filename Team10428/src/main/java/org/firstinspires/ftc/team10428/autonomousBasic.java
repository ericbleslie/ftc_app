package org.firstinspires.ftc.team10428;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Eric on 2/4/2017.
 */

@Autonomous(name="Autonomous Basic (When in doubt use this)", group="Autonomous")
public class autonomousBasic extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {
        DcMotor motorNW = hardwareMap.dcMotor.get("NW");
        DcMotor motorNE = hardwareMap.dcMotor.get("NE");
        DcMotor motorSW = hardwareMap.dcMotor.get("SW");
        DcMotor motorSE = hardwareMap.dcMotor.get("SE");
        motorNW.setPower(0);
        motorNE.setPower(0);
        motorSW.setPower(0);
        motorSE.setPower(0);
        motorNW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorNE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorSW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorSE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Say", "Hello Driver");
        waitForStart();
        motorNW.setPower(.65);
        motorNE.setPower(-.65);
        motorSW.setPower(.65);
        motorSE.setPower(-.65);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.00)) {
            telemetry.addData("Path", "test", runtime.seconds());
            telemetry.update();
        }
        motorNW.setPower(0);
        motorNE.setPower(0);
        motorSW.setPower(0);
        motorSE.setPower(0);
        telemetry.addData("Talk", "Show me your moves!");
        telemetry.update();
    }
}
