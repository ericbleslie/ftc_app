package org.firstinspires.ftc.teamcode;

/**
 * Created by Eric on 10/22/2016.
 */
public class coreLyrics {
    public String song(String name, int line){
        String text;
        //Define Lyrics Arrays
        String test[] = {"line1", "line2", "line3", "line4", "line5"};


        if (name.equals("test")) {
            text = test[line - 1];
        } else {text = null;}
        return text;
    }
}
