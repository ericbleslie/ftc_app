package org.firstinspires.ftc.team10428;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Eric on 1/23/2017.
 */

public class core{
    //Public OpMode members
    public DcMotor motorNE = null;
    public DcMotor motorNW = null;
    public DcMotor motorSW = null;
    public DcMotor motorSE = null;
    HardwareMap hw = null;
    private ElapsedTime runtime = new ElapsedTime();
    OpMode opmode = null;

    static final double encCountsPerRev = 1440;    // eg: TETRIX Motor Encoder
    static final double encGearReduction = 0.5;     // This is < 1.0 if geared UP
    static final double encWheelDiameter = 4.0;     // For figuring circumference
    static final double encWheelSlant = Math.sqrt(2);   //Slant of the wheel so that the distances actually match the distance traveled
    static final double encCountsPerInch = (encCountsPerRev * encGearReduction) / (encWheelDiameter * Math.PI);
    static final double encCountsPerInchSlant = (encCountsPerRev * encGearReduction * encWheelSlant) / (encWheelDiameter * Math.PI); //Modified counts per inch to account for the slant.

    //Constructor
    public core(){

    }

    public void init(HardwareMap ahwMap){
        //Save reference to hardware map
        hw = ahwMap;

        //Define and initialize motors
        motorNE = hw.dcMotor.get("NE");
        motorNW = hw.dcMotor.get("NW");
        motorSW = hw.dcMotor.get("SW");
        motorSE = hw.dcMotor.get("SE");

        //Zero motors
        motorNE.setPower(0);
        motorNW.setPower(0);
        motorSW.setPower(0);
        motorSE.setPower(0);

        //Set motors to run with encoders
        motorNE.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorNW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorSW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorSE.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public double getStickInput(String axis){
        //Y-axes are inverted as to correct to positive up
        switch(axis){
            case "xL1":return opmode.gamepad1.left_stick_x;
            case "yL1":return -opmode.gamepad1.left_stick_y;
            case "xR1":return opmode.gamepad1.right_stick_x;
            case "yR1":return -opmode.gamepad1.right_stick_y;
            case "xL2":return opmode.gamepad2.left_stick_x;
            case "yL2":return -opmode.gamepad2.left_stick_y;
            case "xR2":return opmode.gamepad2.right_stick_x;
            case "yR2":return -opmode.gamepad2.right_stick_y;
        }

        return 0;
    }

    public double getHeading(double x, double y){
        return Math.atan(y/x);
    }



    public double[] vector2motor(double heading, double distance, String opmode){

        double NW;
        double NE;
        double SW;
        double SE;

        switch (opmode){


            case "T":
                //Determined empirically - a hack
                //For a walkthrough see either the engineering notebook or the attached LaTeX formatted document.
                NW = ((3/(2 * Math.sqrt(2))) * Math.cos(heading - 1 * Math.PI / 4)) - ((3/(2 * Math.sqrt(2))) - 1 / Math.sqrt(2)) * Math.cos(3 * (heading - 1 * Math.PI / 4)) *  //The "1 *" was for readability
                        Math.sqrt(Math.pow(getStickInput("xL1"), 2) + Math.pow(getStickInput("yL1"), 2));
                NE = ((3/(2 * Math.sqrt(2))) * Math.cos(heading - 7 * Math.PI / 4)) - ((3/(2 * Math.sqrt(2))) - 1 / Math.sqrt(2)) * Math.cos(3 * (heading - 7 * Math.PI / 4)) *
                        Math.sqrt(Math.pow(getStickInput("xL1"), 2) + Math.pow(getStickInput("yL1"), 2));
                SW = ((3/(2 * Math.sqrt(2))) * Math.cos(heading - 3 * Math.PI / 4)) - ((3/(2 * Math.sqrt(2))) - 1 / Math.sqrt(2)) * Math.cos(3 * (heading - 3 * Math.PI / 4)) *
                        Math.sqrt(Math.pow(getStickInput("xL1"), 2) + Math.pow(getStickInput("yL1"), 2));
                SE = ((3/(2 * Math.sqrt(2))) * Math.cos(heading - 5 * Math.PI / 4)) - ((3/(2 * Math.sqrt(2))) - 1 / Math.sqrt(2)) * Math.cos(3 * (heading - 5 * Math.PI / 4)) *
                        Math.sqrt(Math.pow(getStickInput("xL1"), 2) + Math.pow(getStickInput("yL1"), 2));

                //I don't think you can return the array in one line so I'm doing this.
                double[] tele = {NW, NE, SW, SE};
                return tele;
            case "A":
                NW = ((3/(2 * Math.sqrt(2))) * Math.cos(heading - 1 * Math.PI / 4)) - ((3/(2 * Math.sqrt(2))) - 1 / Math.sqrt(2)) * Math.cos(3 * (heading - 1 * Math.PI / 4)); //The "1 *" was for readability
                NE = ((3/(2 * Math.sqrt(2))) * Math.cos(heading - 7 * Math.PI / 4)) - ((3/(2 * Math.sqrt(2))) - 1 / Math.sqrt(2)) * Math.cos(3 * (heading - 7 * Math.PI / 4));
                SW = ((3/(2 * Math.sqrt(2))) * Math.cos(heading - 3 * Math.PI / 4)) - ((3/(2 * Math.sqrt(2))) - 1 / Math.sqrt(2)) * Math.cos(3 * (heading - 3 * Math.PI / 4));
                SE = ((3/(2 * Math.sqrt(2))) * Math.cos(heading - 5 * Math.PI / 4)) - ((3/(2 * Math.sqrt(2))) - 1 / Math.sqrt(2)) * Math.cos(3 * (heading - 5 * Math.PI / 4));

                //I don't think you can return the array in one line so I'm doing this.
                double[] auto = {NW, NE, SW, SE};
                return auto;
        }

        double[] a = {0,0,0,0};
        return a;
    }

    public double[] turn(String direction, String opmode){

        //Android studio didn't like not setting the variables to something here
        double NW = 0;
        double NE = 0;
        double SW = 0;
        double SE = 0;

        switch(opmode){
            case "T":
                NW = Math.sqrt(2) / 2 * getStickInput("xR1");
                NE = Math.sqrt(2) / 2 * getStickInput("xR1");
                SW = Math.sqrt(2) / 2 * getStickInput("xR1");
                SE = Math.sqrt(2) / 2 * getStickInput("xR1");

                double[] turnT = {NW, NE, SW, SE};
                return turnT;

            case "A":
                switch(direction){
                    case "CW":
                        double[] turnACW = {Math.sqrt(2) / 2,Math.sqrt(2) / 2,Math.sqrt(2) / 2,Math.sqrt(2) / 2};
                        return turnACW;
                    case "CCW":
                        double[] turnACCW = {-Math.sqrt(2) / 2,-Math.sqrt(2) / 2,-Math.sqrt(2) / 2,-Math.sqrt(2) / 2};
                        return turnACCW;
                }
                double[] a = {0,0,0,0};
                return a;
        }

        double[] b = {0, 0, 0, 0};
        return b;
    }
}
