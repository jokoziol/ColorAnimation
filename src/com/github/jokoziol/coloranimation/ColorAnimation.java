package com.github.jokoziol.coloranimation;

import java.awt.*;
import java.util.Random;

public class ColorAnimation {

    private ColorAnimationCallback animatedBackgroundInterface;
    private Thread thread;

    private Color LAST_COLOR = null;

    private boolean isStopped = false;
    private boolean singleColorUpdate = false;

    public ColorAnimation(ColorAnimationCallback animatedBackgroundInterface){
        this.animatedBackgroundInterface = animatedBackgroundInterface;
    }

    public void startAnimation(Color ... colors){
        this.LAST_COLOR = generateRandomColor();
        this.isStopped = false;

        if(colors.length > 1){

            if(colors[0] == null || colors[1] == null){
                throw new RuntimeException("Color cannot be null");
            }

            this.singleColorUpdate = true;
            this.LAST_COLOR = colors[0];
        }

        thread = new Thread(() -> {
            while (!thread.isInterrupted() && !this.isStopped){
                try {
                    colorAnimation(this.singleColorUpdate ? colors[1] : generateRandomColor());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
    public void stopAnimation(){
        this.LAST_COLOR = null;
        this.isStopped = true;
    }

    private void colorAnimation(Color color) throws InterruptedException {

        final Color lastColor = this.LAST_COLOR;

        final int red = color.getRed() - lastColor.getRed();
        final int green = color.getGreen() - lastColor.getGreen();
        final int blue = color.getBlue() - lastColor.getBlue();

        if(red != 0 || green != 0 || blue != 0){
            for(int i = 0; i <= ColorAnimationSettings.getFadeSteps(); i++){
                final Color newColor = new Color(lastColor.getRed() + ((red * i) / ColorAnimationSettings.getFadeSteps()),
                        lastColor.getGreen() + ((green * i) / ColorAnimationSettings.getFadeSteps()),
                        lastColor.getBlue() + ((blue * i) / ColorAnimationSettings.getFadeSteps()));

                this.LAST_COLOR = newColor;

                animatedBackgroundInterface.onColorChanged(newColor);

                Thread.sleep(ColorAnimationSettings.getTimeBetweenColorSteps());
            }
        }
        Thread.sleep(ColorAnimationSettings.getTimeBetweenColors());

        if(this.singleColorUpdate){
            this.isStopped = true;
        }
    }

    private Color generateRandomColor(){
        return new Color(new Random().nextInt(ColorAnimationSettings.getMaxRedColor()), new Random().nextInt(ColorAnimationSettings.getMaxGreenColor()), new Random().nextInt(ColorAnimationSettings.getMaxBlueColor()));
    }

}
