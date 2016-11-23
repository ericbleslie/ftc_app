package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Eric Leslie on 10/22/2016.
 */
@Autonomous(name="Autonomous (Right)", group="Autonomous")
public class coreAutonomousRight extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    coreLyrics Lyrics = new coreLyrics();

    public void runOpMode() {
        //Initialize Hardware
        DcMotor motorN = hardwareMap.dcMotor.get("North");
        DcMotor motorE = hardwareMap.dcMotor.get("East");
        DcMotor motorS = hardwareMap.dcMotor.get("South");
        DcMotor motorW = hardwareMap.dcMotor.get("West");
        ColorSensor colorSensor = hardwareMap.colorSensor.get("Color");
        ColorSensor lineSensor = hardwareMap.colorSensor.get("Line");
        boolean bLedOn = true;
        colorSensor.enableLed(bLedOn);
        lineSensor.enableLed(true);
        motorN.setPower(0);
        motorE.setPower(0);
        motorS.setPower(0);
        motorW.setPower(0);
        motorN.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorS.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Say", "Initialized");
        waitForStart();

        //Default Variables
        float whiteTRSH = 0.5F;
        float redTRSH = 0.5F;
        float blueTRSH = 0.5F;
        String color = "Blue";
        boolean foundLine = false;
        boolean foundBeacon = false;


        //Start Autonomous
        //Note: Convert to multithreaded program
        /** Flowchart
         *
         * Start
         * Move forward until found white line
         * Once found, "capture" the white line and follow
         * Color sensor detects significant color change to violet
         * moves left and right to check colors
         * hit button for correct color
         * check step
         * move left or right depending on color of alliance towards next beacon
         * capture white line
         * repeat
         */
        //Move until line is found
        while (opModeIsActive() && (runtime.seconds() < 30.00) && !foundLine){
            motorE.setPower(-0.65);
            motorW.setPower(0.65);
            // Check if lineSensor sees a color with RGB greater than the threshold for detecting white
            if ((lineSensor.red() > whiteTRSH) && (lineSensor.blue() > whiteTRSH) && (lineSensor.green() > whiteTRSH)){foundLine = true; telemetry.addData("Say", "Found Line");}
        }

        //Capture and follow white line until beacon seen
        while (opModeIsActive() && (runtime.seconds() < 30.00) && !foundBeacon){
            foundLine = ((lineSensor.red() > whiteTRSH) && (lineSensor.blue() > whiteTRSH) && (lineSensor.green() > whiteTRSH));
            if (foundLine){
                motorE.setPower(-0.65);
                motorW.setPower(0.65);
            } else if (!foundLine){
                motorN.setPower(0.35);
                motorE.setPower(0.35);
                motorS.setPower(0.35);
                motorW.setPower(0.35);
            }

            boolean condition = false; //Temporary for testing
            if (condition){foundBeacon = true;}
            if ((lineSensor.red() > redTRSH) || (lineSensor.blue()> blueTRSH)){foundBeacon = true; telemetry.addData("Say: ", "Found Beacon");}
        }

        //Detect which button to press on beacon
        boolean decidedBeacon = false; //Temporary
        while (opModeIsActive() && (runtime.seconds() < 30) && !decidedBeacon) {
            motorN.setPower(-0.35);
            motorS.setPower(0.35);
            
        }


        /** Sample program
         * motorN.setPower(0);
         * motorE.setPower(-.65);
         * motorS.setPower(0);
         * motorW.setPower(.65);
         * runtime.reset();
         * while (opModeIsActive() && (runtime.seconds() < 3.00)){
         *      telemetry.addData("Path", Lyrics.song("test", 1), runtime.seconds());
         *      telemetry.update();
         *  }
         *  motorN.setPower(1);
         *  motorE.setPower(1);
         *  motorS.setPower(1);
         *  motorW.setPower(1);
         *  while (opModeIsActive() && (runtime.seconds() < 33.0)) {
         *      telemetry.addData("Path", Lyrics.song("test", 2), runtime.seconds());
         *      telemetry.update();
         *  }
         *  sleep(10000);
         *  telemetry.addData("Talk", "Show me your moves!");
         *  motorN.setPower(0);
         *  motorE.setPower(0);
         *  motorS.setPower(0);
         *  motorW.setPower(0);
         */
    }
}
