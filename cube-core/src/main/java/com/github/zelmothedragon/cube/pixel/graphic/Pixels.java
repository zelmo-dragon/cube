package com.github.zelmothedragon.cube.pixel.graphic;

public final class Pixels {

    public static final int COLOR_TRANSPARENT = 0x00000000;

    public static final int COLOR_BLACK = 0xFF000000;

    public static final int COLOR_WHITE = 0xFFFFFFFF;

    public static final int COLOR_RED = 0xFFFF0000;

    public static final int COLOR_GREEN = 0xFF00FF00;

    public static final int COLOR_BLUE = 0xFF0000FF;

    public static final int COLOR_MAGENTA = 0xFFFF00FF;

    public static final int COLOR_YELLOW = 0xFFFFFF00;

    public static final int COLOR_CYAN = 0xFF00FFFF;

    private static final int CHANNEL_ALPHA = 24;

    private static final int CHANNEL_RED = 16;

    private static final int CHANNEL_GREEN = 8;

    private static final int CHANNEL_BLUE = 0;

    private static final int UNICOLOR_MAX_VALUE = 0xFF;

    private Pixels() {
    }

    public static int getAlpha(final int color) {
        return (color >> CHANNEL_ALPHA) & UNICOLOR_MAX_VALUE;
    }

    public static int getRed(final int color) {
        return (color >> CHANNEL_RED) & UNICOLOR_MAX_VALUE;
    }

    public static int getGreen(final int color) {
        return (color >> CHANNEL_GREEN) & UNICOLOR_MAX_VALUE;
    }

    public static int getBlue(final int color) {
        return color & UNICOLOR_MAX_VALUE;
    }

    public static boolean isInBound(final int position, final int size) {
        return position >= 0 && position < size;
    }

    public static int toColor(final int red, final int green, final int blue) {
        return UNICOLOR_MAX_VALUE << CHANNEL_ALPHA | red << CHANNEL_RED | green << CHANNEL_GREEN | blue;
    }

    public static int light(final int color0, final int color1) {

        var r0 = getRed(color0);
        var g0 = getGreen(color0);
        var b0 = getBlue(color0);

        var r1 = getRed(color1);
        var g1 = getGreen(color1);
        var b1 = getBlue(color1);

        return toColor(
                Math.max(r0, r1),
                Math.max(g0, g1),
                Math.max(b0, b1)
        );
    }

    public static int amplify(final int color, double power) {
        var red = getRed(color) * power;
        var green = getGreen(color) * power;
        var blue = getBlue(color) * power;
        return toColor((int) red, (int) green, (int) blue);
    }

    public static int[] scale(final int w, final int h, final int[] image, final int scale) {

        var scaledWidth = scale * w;
        var scaledHeight = scale * h;
        var buffer = new int[scaledWidth * scaledHeight];

        for (var y = 0; y < scaledHeight; y++) {
            var ya = y / scale;

            for (var x = 0; x < scaledWidth; x++) {
                var xa = x / scale;
                var color = image[xa + ya * w];
                buffer[x + y * scaledWidth] = color;
            }
        }
        return buffer;
    }

    public static int convertHSB(
            final float hue,
            final float saturation,
            final float brightness) {

        // By java.awt.Color.HSBtoRGB()
        var r = 0;
        var g = 0;
        var b = 0;

        if (saturation == 0) {
            r = g = b = (int) (brightness * 255.0f + 0.5f);
        } else {
            var h = (hue - (float) Math.floor(hue)) * 6.0f;
            var f = h - (float) Math.floor(h);
            var p = brightness * (1.0f - saturation);
            var q = brightness * (1.0f - saturation * f);
            var t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int) h) {
                case 0:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (t * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 1:
                    r = (int) (q * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 2:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (t * 255.0f + 0.5f);
                    break;
                case 3:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (q * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 4:
                    r = (int) (t * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 5:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (q * 255.0f + 0.5f);
                    break;
            }
        }
        return toColor(r, g, b);
    }

}
