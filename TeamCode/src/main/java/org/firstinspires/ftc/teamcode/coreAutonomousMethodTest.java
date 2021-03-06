package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Eric on 10/22/2016.
 */
@Autonomous(name="Autonomous Basic Methods", group="Autonomous")
@Disabled
public class coreAutonomousMethodTest extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    coreLyrics Lyrics = new coreLyrics();
    core core = new core();

    public void runOpMode() {
        DcMotor motorN = hardwareMap.dcMotor.get("North");
        DcMotor motorE = hardwareMap.dcMotor.get("East");
        DcMotor motorS = hardwareMap.dcMotor.get("South");
        DcMotor motorW = hardwareMap.dcMotor.get("West");
        motorN.setPower(0);
        motorE.setPower(0);
        motorS.setPower(0);
        motorW.setPower(0);
        motorN.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorS.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Say", "Hello Driver");
        waitForStart();
        core.move("North", 1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.00)){
            telemetry.addData("Path", Lyrics.song("test", 1), runtime.seconds());
            telemetry.update();
        }
        motorN.setPower(0);
        motorE.setPower(0);
        motorS.setPower(0);
        motorW.setPower(0);
        telemetry.addData("Talk", "Show me your moves!");
    }
}