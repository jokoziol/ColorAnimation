# ColorAnimation
Animate color transitions.

## How to use
1. Create a new `ColorAnimationCallback`
```java
ColorAnimationCallback animationCallback = new ColorAnimationCallback() {
    @Override
    public void onColorChanged(Color color) {
        //
    }
};
```
2. Create a new `ColorAnimation`
```java
ColorAnimation colorAnimation = new ColorAnimation(colorAnimationCallback);
```
3. Call `colorAnimation.start()` to animate color transitions with random colors.
4. If you want to use custom colors, call `colorAnimation.start(startColor, endColor)`.

## Preferences
**NOTE: `ColorAnimationSettings` must be called before `colorAnimation.start()`**
### Change maximum colors
Default values:
- red: 50
- green: 50
- blue: 50

```java
ColorAnimationSettings.setMaxRedColor(100);
ColorAnimationSettings.setMaxGreenColor(100);
ColorAnimationSettings.setMaxBlueColor(100);
```

### Fade steps
Default value: 75

The value is specified in milliseconds.
```java
ColorAnimationSettings.setFadeSteps(75);
```

### Time between each color
Default value: 250

The value is specified in milliseconds.
```java
ColorAnimationSettings.setTimeBetweenColorSteps(250);
```

### Filter colors
**NOTE: Call the filter methods after setting the maximum colors**
```java
ColorAnimationSettings.filterRed();
ColorAnimationSettings.filterGreen();
ColorAnimationSettings.filterBlue();
```

## Example code
```java
    private static Timer timer = new Timer();

    public static void main(String[] args){

        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] graphicsDevices = graphicsEnvironment.getScreenDevices();

        JFrame jFrame = new JFrame("");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage bufferedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(bufferedImage, new Point(0, 0), "blank_cursor");
        jFrame.getContentPane().setCursor(cursor);

        JLabel jLabelTime = new JLabel("", SwingConstants.CENTER);
        jLabelTime.setForeground(Color.WHITE);
        jLabelTime.setFont(new Font("Yu Gothic UI", Font.PLAIN, 200));
        jFrame.add(jLabelTime);

        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setUndecorated(true);
        jFrame.setVisible(true);

        jFrame.setAlwaysOnTop(true);
        jFrame.setSize(graphicsDevices[1].getDefaultConfiguration().getBounds().getSize());
        jFrame.setLocation(graphicsDevices[1].getDefaultConfiguration().getBounds().getLocation());

        ColorAnimationCallback animatedBackgroundCallback = new ColorAnimationCallback() {
            @Override
            public void onColorChanged(Color color) {
                jFrame.getContentPane().setBackground(color);
            }
        };

        ColorAnimationSettings.filterGreen();

        ColorAnimation animatedBackground = new ColorAnimation(animatedBackgroundCallback);
        animatedBackground.startAnimation();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jLabelTime.setText(new SimpleDateFormat("HH : mm : ss").format(new Date()));
            }
        }, 0, 1000);
    }
```
