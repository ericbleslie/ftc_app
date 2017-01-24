package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric Leslie on 10/15/2016.
 */
@TeleOp(name="Team 10428", group="Movement")
public class coreDriverControlled extends OpMode {

    //Declare hardware variables
    public DcMotor motorN;
    public DcMotor motorE;
    public DcMotor motorS;
    public DcMotor motorW;

    public void init() {
        //Initialize hardware
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
    }

    public void loop() {
        //Reverse motor directions to be forward positive
        boolean motorNReverse = false;
        boolean motorEReverse = true;
        boolean motorSReverse = true;
        boolean motorWReverse = false;

        //Define initial motor velocity
        float N = 0;
        float E = 0;
        float S = 0;
        float W = 0;

        //Declare variables to controller stick inputs
        float xL = gamepad1.left_stick_x;
        float yL = gamepad1.left_stick_y;
        float xR = gamepad1.right_stick_x;


        //Set motor power based on controller inputs
        if ((xL < -0.1) || (xL > 0.1)) {
            N += xL;
            S += xL;
        }
        if ((yL < -0.1) || (yL > 0.1)) {
            E += yL;
            W += yL;
        }
        if ((xR < -0.1) ||(xR > 0.1)) {
            N += xR / 2;
            E -= xR / 2;
            S -= xR / 2;
            W += xR / 2;
        }

        //Reverse motor outputs if motor is geared
        if (motorNReverse){ N = -N; }
        if (motorEReverse){ E = -E; }
        if (motorSReverse){ S = -S; }
        if (motorWReverse){ W = -W; }

        //Normalize motor speed values greater than 1.0
        float maxVal = Math.max(Math.max(Math.abs(N),Math.abs(E)),Math.max(Math.abs(S),Math.abs(W)));
        if(maxVal > 1)
        {
            N /= maxVal;
            E /= maxVal;
            S /= maxVal;
            W /= maxVal;
        }

        //Send N, E, S, W power to corresponding motors
        motorN.setPower(N);
        motorE.setPower(E);
        motorS.setPower(S);
        motorW.setPower(W);
    }
}

/**                      Twilight is cutest smartest all around best pony.

                                     :os/.
                             `-:+syhdho:ohhhdhyo/-.
                         ./ohmmmmmmmmho-–:shdmmmmmmdy+-`
                     `-ohmmmmmmddddmmdh:-–-ohhddddmmmmmms-`
                   -odmmmmmmdddddmmmmdh+-–-+yshddmdmdmmmmmdo:`
                `/hmmmmdddddmddmmmmmmmhy-–/y/-+yhddddmddmmmmmmy/.
              .odmmmddddddmddmmmmmdddmdh+os:-–-:yhhdmdddddddddmmmh+.
            .ommmmddddddddmdmNmmddddmmdhy:-–-–-–hoyhddddddddddhyshmmh+-`
          `+dNmmdddddddddmmmmmddddddddmdh+-–-–-+y-:ydddddddhhsoooooshmmdo:.                  ```
         .yNmmmddmddddddmmNmmmmmddddddddhs-–-–+y//yhdddddhysoooooooshmmmNmdy+-`         `.:+ooos/`
        :dNmdddddmmddddmmNmddddddddddddmdh/-/ss+shddddddhsoooooooshmmmmmmmmmmmdy+:.` `-+sso+::-oho
       +mmmdmddmmmmdddmmmmmddddddddddddmdhyoo/ohddddddhsooooooooymmmdmNmddddmmmmmmmhsss+/-–-–-–-yh:
      +mmmddddmmNmdmddmmmdmdddddddddddddmhh/:shddddddhooooooooodmmddmNmdmdddmdddmddhs/-–-–-–-–-–ohs
     +mmmddddmNNmddmdmNmddddddddddddddddmdhyhdddddddyoooooooosmmmddmNmdmdddddmdmdhs/-–-–-–-–-–-–:hh.
    :mmmdddmmNNmmdddmNmmddddddddddddddmdmdhddddddddyoooooooosmmmmdmmmmdddddddmmdh+-–-–-–-–-–-–-–-yh/
   .dNmddmmmmhNmdddmNmddddddddddddddddddddddddddddyoooooooosmmddddmNmddddddddmmy/-–-–-–-–-–-–-–-–sh+
  `yNmddmdmm:dmddddmNmdddddddddddddddddddddddddddyoooooooosmmdddmmNmddddddddmNd:-–-–-–-–-–-–-–-–-sh+
  /Nmddddmm:+NmddmdmmmmmmmmmmmmmmmmmmdddddddddddhooooooooodNmddmdmNmddddddddmNs-–-–-–-–-–-–-–-–:-sh+
 .dmddddmm/ hNmmmmmNNNNmmmmmmNNmmmmmmmmmddddddddsoooooooosNmdddddmNdmdddddddmNo-–-–-–-–-–-–-–-o:-hh:
 yNmmmmmmy  yymhso++yNNho/shdmNmdyooosyhdmmmmmmdsoooooooodNmdddddNmdddddddmdmN+-–-–-–-–-–-–-–o+-/hh`
:mmdys+/-`   `md/-–-–:smMmy+smNMMMmo-–-–-::/oydmmmdysoooommdddddmNmdddddddmdmNo-–-–-–-–-–-–:o/-–sh+
-–.           ymNds:-–oms.:yhdNMMMMMd-–-–-–-–-–-/oymmmdysmmddddddNmdddddddddmNs-–-–-–-o:-:oo-–-+hy`
              /h+ohmmmm:      `+NMMMMd-–-–-–-–-–-–-–:+ymNNNmmddddmNdddddddddmNd:-–-:smh-oo:-–-:hh-
              `ys-–-dm-         /MMMMMy-–-–-–-–-–-–-–-–-/shmNNmmdmNmddddddddmNNNoshNNh++:-–-–:yh/
            /. -y/-sN:          -MMMMMM:-–-–-–-–-–-–-–-–-–-:dNmNNmNmdmdddddddmNNMds+/:-–-–-–:yh/
            -hdhmdyNs          `yMMMMMMs-–-–-–-–-–-–-–-–-–-sMy`:odNNmmmddmdddmNNMs-–-–-–:+/:yy:
              .:hdso.         `yMMMMMMMd-–-–-–-–-–-–-–-–-–+Md`   `hNNNNmmmmdmdmNNmo++oyddo+hs.
               `ys-.    `   .+mMMMMMMMMm-–-–-–-–-–-–-–-–-–mN-    +NMMMMNNNNmmddmNNmdhyo/:shy`
               .ho-`   .dyymNMMNMMMMMMMN:-–-–-–-–-–-–-–-–sMs   .yMMMMMMMNNNdmNmmmNms++/+hdmm.
               :h/-`    ydmMMh-`-mMMMMMN:-–-–-–-–-–-–-–-:mN-–/hNMMMMMMMMNNN.`:oymNNNNNNNNNNm.
               :h+-`    .sodM:  :NMMMMMN-–-–-–-–-–-–-–-–oMMNNMNysmMMMMMNmmy    `-/sdNmmmmmNy`
               -ho-.     -–-dNhdNMMMMMMm-–-–-–-–-–-–-–-–hMMMMM:  +MMMMdyhh.    `-–:dmdmmdmN+
               `ys-–      `odmNMMMMMMMMd-–-–-–-–-–-–-–-:mMMMMMy+sNMMm+/oo.     .-–/mmdddmNm.
                shoyo`     -shddmmmmmdNh-–-–-:::::-–-–-/NMMMMMMMMMNmy:..`     `-–-sNmdddmNo
                +my+yo`     `.:+oosoo/ms-–/ossssssso:-–/NmNNNNNNmmddho.      `-–-–dNmddmNd`
                .yhho/o/              d/-//::-–-–-:+o:-+doyhdddhhys/.`      .yy/-oNmddmNN/
                 +h+.sh:..``         `+-–-–-–-–-–-–-–-–:+ `.-–-..`       ``so-://mNmdmmNs
                 `sy:+:-–-–-–.......-–-–-+o:-–-–-–:/:-–-–``           ``-h+:y/-–hNmddmNm.
                  .yy:-–-–-–-–-–-–::-–-–-–:/-–-–-:++:-–-–-–..........-–-–/y.-–-yNmddmNN+
                   -yy:-–-–-–-–-–-:s/-–-–-–-–-–-–-–-–-–-–-//-–-–-–-–-–-–-–-–-:hNmmmmNNh`
                    -yy:-–-–-–-–-–-/s+-–-–-–-–-–-–-–-–-–/so/-–-–-–-–-–-–-–-–+dNmmmmNNm-
                     .oy/-–-–-–-–-–-:os+/::-–-–-–-–-–:/oo/-–-–:/-–-–-–-–-:odNmmmmmmmNs
                       /ys:-–-–-–-–-–-–/+osoooo+oooooo/:-–-–-–dmho+++osydmNmmmmmmmmNN-
                        `+yo:-–-–-–-–-–-–-–:::///::-–-–-–-–-–sNmmmmmmmmmmmmmmmmdddmNd`
                          .+yo/-–-–-–-–-–-–-–-–-–-–-–-–-–-–-+NNmmmmmmmmmmmmmmmmdmmNNs
                            `/ss+:-–-–-–-–-–-–-–-–-–-–-–-–-:dNmmmmmmmmmmmmmmNmdmmmmN+
                               ./sso/:-–-–-–-–-–-–-–-–-–-–-oNmddddddmmmmmmNNmmmNmymN/
                                  `-/syso++//:-–-–-–-–-–-–-hNmmmmmmmmmdyydNmmmNdsodN/
                                     :h:::/:::-–-–-–-–-–-–-:/+ossoo+//odNmmmNmdsosmN/
                                     /h-–-–-–-–-–-–-–-–-–-–-–-–-–-/ohmmmmmmNmhooohmNo
                                     +h-–-–-–-–-–-–-–-–-–-++oosyhmNmmmdmmNmdsooohddNd
*/