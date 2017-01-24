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
@Autonomous(name="Autonomous - Right Side - Blue", group="Autonomous")
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
        float whiteTRSH = 8;
        float redTRSH = 2;
        float blueTRSH = 2;
        String colorTeam = "Blue";
        boolean foundLine = false;
        boolean foundBeacon = false;
        boolean foundLeft = false;
        String colorLeft = "None";
        boolean foundRight = false;
        String colorRight = "None";
        boolean decidedBeacon = false;


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
        while (opModeIsActive() && (runtime.seconds() < 29.00) && !foundLine){
            motorE.setPower(0.25);
            motorW.setPower(-0.25);
            // Check if lineSensor sees a color with RGB greater than the threshold for detecting white
            if (lineSensor.alpha() > whiteTRSH){foundLine = true; telemetry.addData("Say", "Found Line");}
            telemetry.clear();
            telemetry.addData("Line", lineSensor.alpha());
            telemetry.addData("Color", colorSensor.alpha());
            telemetry.update();
        }

        //Capture and follow white line until beacon seen
        while (opModeIsActive() && (runtime.seconds() < 29.00) && !foundBeacon){
            foundLine = (lineSensor.alpha() > whiteTRSH);
            if (foundLine){
                motorE.setPower(0.65);
                motorW.setPower(-0.65);
            } else if (!foundLine){
                motorN.setPower(-0.35);
                motorE.setPower(-0.35);
                motorS.setPower(-0.35);
                motorW.setPower(-0.35);
            }
            if ((lineSensor.red() > redTRSH / 2) || (lineSensor.blue()> blueTRSH / 2)){foundBeacon = true; telemetry.addData("Say: ", "Found Beacon");}
        }

        //Detect which button to press on beacon
        while (opModeIsActive() && (runtime.seconds() < 29.00) && !decidedBeacon) {
            while (!foundLeft) {
                motorN.setPower(0.35);
                motorS.setPower(-0.35);
                if (colorSensor.red() > redTRSH){
                    colorLeft = "Red";
                    foundLeft = true;

                }
                if (colorSensor.blue() > blueTRSH){
                    colorLeft = "Blue";
                    foundLeft = true;
                }
                if (colorLeft.equals(colorTeam)){
                    decidedBeacon = true;
                }
            }

            while (!foundRight){
                motorN.setPower(-0.35);
                motorS.setPower(0.35);
                if ((colorSensor.red() > redTRSH) && (colorLeft.equals("Blue"))){
                    colorRight = "Red";
                    foundRight = true;
                }
                if ((colorSensor.blue() > blueTRSH) && (colorLeft.equals("Red"))){
                    colorRight = "Blue";
                    foundRight = true;
                }
                if (colorRight.equals(colorTeam)){
                    decidedBeacon = true;
                }
            }
        }

        //Should now be in front of correct beacon color so go forward
        motorE.setPower(0.35);
        motorW.setPower(-0.35);
        sleep(1000);
        motorE.setPower(-0.35);
        motorW.setPower(0.35);
        sleep(500);
        motorE.setPower(0);
        motorW.setPower(0);

        //Now find next beacon line and repeat
        foundLine = false;
        foundBeacon = false;
        foundLeft = false;
        foundRight = false;
        while (opModeIsActive() && (runtime.seconds() < 29.00) && !foundLine){
            motorN.setPower(0.65);
            motorS.setPower(-0.65);
            // Check if lineSensor sees a color with RGB greater than the threshold for detecting white
            if (lineSensor.alpha() > whiteTRSH){foundLine = true; telemetry.addData("Say", "Found Line");}
        }
        motorN.setPower(0);
        motorS.setPower(0);

        //Capture and follow white line until beacon seen
        while (opModeIsActive() && (runtime.seconds() < 29.00) && !foundBeacon){
            foundLine = (lineSensor.alpha() > whiteTRSH);
            if (foundLine){
                motorE.setPower(0.65);
                motorW.setPower(-0.65);
            } else if (!foundLine){
                motorN.setPower(-0.35);
                motorE.setPower(-0.35);
                motorS.setPower(-0.35);
                motorW.setPower(-0.35);
            }
            if ((lineSensor.red() > redTRSH / 2) || (lineSensor.blue()> blueTRSH / 2)){foundBeacon = true; telemetry.addData("Say: ", "Found Beacon");}
        }

        //Detect which button to press on beacon
        while (opModeIsActive() && (runtime.seconds() < 29.00) && !decidedBeacon) {
            while (!foundLeft) {
                motorN.setPower(0.35);
                motorS.setPower(-0.35);
                if (colorSensor.red() > redTRSH){
                    colorLeft = "Red";
                    foundLeft = true;

                }
                if (colorSensor.blue() > blueTRSH){
                    colorLeft = "Blue";
                    foundLeft = true;
                }
                if (colorLeft.equals(colorTeam)){
                    decidedBeacon = true;
                }
            }

            while (!foundRight){
                motorN.setPower(-0.35);
                motorS.setPower(0.35);
                if ((colorSensor.red() > redTRSH) && (colorLeft.equals("Blue"))){
                    colorRight = "Red";
                    foundRight = true;
                }
                if ((colorSensor.blue() > blueTRSH) && (colorLeft.equals("Red"))){
                    colorRight = "Blue";
                    foundRight = true;
                }
                if (colorRight.equals(colorTeam)){
                    decidedBeacon = true;
                }
            }
        }

        //Should now be in front of correct beacon color so go forward
        motorE.setPower(0.35);
        motorW.setPower(-0.35);
        sleep(1000);
        motorE.setPower(-0.35);
        motorW.setPower(0.35);
        sleep(500);
        motorE.setPower(0);
        motorW.setPower(0);
    }
}
