package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Eric on 10/22/2016.
 */
@Autonomous(name="Autonomous", group="Autonomous")
public class coreAutonomous extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    coreLyrics Lyrics = new coreLyrics();
    //Declare hardware variables
    public DcMotor motorN = null;
    public DcMotor motorE = null;
    public DcMotor motorS = null;
    public DcMotor motorW = null;

    public void runOpMode() {
        motorN = hardwareMap.dcMotor.get("North");
        motorE = hardwareMap.dcMotor.get("East");
        motorS = hardwareMap.dcMotor.get("South");
        motorW = hardwareMap.dcMotor.get("West");
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
        motorN.setPower(0);
        motorE.setPower(-.65);
        motorS.setPower(0);
        motorW.setPower(.65);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.00)){
            telemetry.addData("Path", Lyrics.song("test", 1), runtime.seconds());
            telemetry.update();
        }
        motorN.setPower(1);
        motorE.setPower(1);
        motorS.setPower(1);
        motorW.setPower(1);
        while (opModeIsActive() && (runtime.seconds() < 33.0)) {
            telemetry.addData("Path", Lyrics.song("test", 2), runtime.seconds());
            telemetry.update();
        }
        sleep(10000);
        telemetry.addData("Talk", "Show me your moves!");
        motorN.setPower(0);
        motorE.setPower(0);
        motorS.setPower(0);
        motorW.setPower(0);
    }
}
