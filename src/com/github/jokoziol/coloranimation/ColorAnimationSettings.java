package com.github.jokoziol.coloranimation;

public class ColorAnimationSettings {

    private static int MAX_RED_COLOR = 50;
    private static int MAX_GREEN_COLOR = 50;
    private static int MAX_BLUE_COLOR = 50;

    private static int TIME_BETWEEN_COLORS = 250;
    private static int TIME_BETWEEN_COLOR_STEPS = 75;

    private static int FADE_STEPS = 25;

    public static void setRgbColor(int red, int green, int blue){
        if(red <= 0 || red > 255){
            throw new RuntimeException("The red value must be between 0 and 255");
        }
        if(green <= 0 || green > 255){
            throw new RuntimeException("The green value must be between 0 and 255");
        }
        if(blue <= 0 || blue > 255){
            throw new RuntimeException("The blue value must be between 0 and 255");
        }

        MAX_RED_COLOR = red;
        MAX_GREEN_COLOR = green;
        MAX_BLUE_COLOR = blue;
    }

    public static int getMaxRedColor() {
        return MAX_RED_COLOR;
    }

    public static void setMaxRedColor(int maxRedColor) {
        if(maxRedColor <= 0 || maxRedColor > 255){
            throw new RuntimeException("The red value must be between 0 and 255");
        }

        MAX_RED_COLOR = maxRedColor;
    }

    public static int getMaxGreenColor() {
        return MAX_GREEN_COLOR;
    }

    public static void setMaxGreenColor(int maxGreenColor) {
        if(maxGreenColor <= 0 || maxGreenColor > 255){
            throw new RuntimeException("The green value must be between 0 and 255");
        }

        MAX_GREEN_COLOR = maxGreenColor;
    }

    public static int getMaxBlueColor() {
        return MAX_BLUE_COLOR;
    }

    public static void setMaxBlueColor(int maxBlueColor) {
        if(maxBlueColor <= 0 || maxBlueColor > 255){
            throw new RuntimeException("The blue value must be between 0 and 255");
        }

        MAX_BLUE_COLOR = maxBlueColor;
    }

    public static int getTimeBetweenColors() {
        return TIME_BETWEEN_COLORS;
    }

    public static void setTimeBetweenColors(int timeBetweenColors) {
        TIME_BETWEEN_COLORS = timeBetweenColors;
    }

    public static int getTimeBetweenColorSteps() {
        return TIME_BETWEEN_COLOR_STEPS;
    }

    public static void setTimeBetweenColorSteps(int timeBetweenColorSteps) {
        TIME_BETWEEN_COLOR_STEPS = timeBetweenColorSteps;
    }

    public static int getFadeSteps() {
        return FADE_STEPS;
    }

    public static void setFadeSteps(int fadeSteps) {
        FADE_STEPS = fadeSteps;
    }

    public static void filterRed(){
        if(getMaxRedColor() <= 1){
            throw new RuntimeException("The red value is too low");
        }
        setMaxRedColor(Math.round((float) (getMaxRedColor() * 0.125)));
    }

    public static void filterGreen(){
        if(getMaxGreenColor() <= 1){
            throw new RuntimeException("The green value is too low");
        }
        setMaxGreenColor(Math.round((float) (getMaxGreenColor() * 0.2)));
    }

    public static void filterBlue(){
        if(getMaxBlueColor() <= 1){
            throw new RuntimeException("The blue value is too low");
        }
        setMaxBlueColor(Math.round((float) (getMaxBlueColor() * 0.125)));
    }

}
