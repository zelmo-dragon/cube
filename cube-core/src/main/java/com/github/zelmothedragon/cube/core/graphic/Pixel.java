package com.github.zelmothedragon.cube.core.graphic;

/**
 * Utilitaire pour la manipulation de pixel. Un pixel est représenté par le type
 * primitif <i>int</i>, généralement en hexadécimal sous la forme suivante:
 * <code>0xAARRGGBB</code>. Où <i>AA</i> représente le canal alpha, idem pour
 * <i>RR</i> pour le rouge, <i>GG</i> pour le vert et <i>BB</i> pour le bleu.
 *
 * @author MOSELLE Maxime
 */
public final class Pixel {

    /**
     * Valeur de la transparence ou d'un pixel vide.
     */
    public static final int TRANSPARENT = 0;

    /**
     * Valeur de l'opcaité maximale. Utilisé également pour l'extraction des
     * cannaux de couleur d'un pixel, ou calculer le ratio du canal alpha.
     */
    public static final int OPAQUE = 255;

    /**
     * Index du canal alpha d'une couleur.
     */
    private static final int CHANNEL_ALPHA = 24;

    /**
     * Index du canal rouge d'une couleur.
     */
    private static final int CHANNEL_RED = 16;

    /**
     * Index du canal vert d'une couleur.
     */
    private static final int CHANNEL_GREEN = 8;

    /**
     * Index du canal bleu d'une couleur.
     */
    private static final int CHANNEL_BLUE = 0;

    /**
     * Constructeur interne. Pas d'instanciation.
     */
    private Pixel() {
        // RAS
    }

    /**
     * Extraire le canal alpha d'une couleur.
     *
     * @param color Couleur unique
     * @return Le canal alpha de la couleur, la valeur est comprise entre 0 et
     * 255.
     */
    public static int getAlpha(final int color) {
        return (color >> CHANNEL_ALPHA) & OPAQUE;
    }

    /**
     * Extraire le canal rouge d'une couleur.
     *
     * @param color Couleur unique
     * @return Le canal alpha de la couleur, la valeur est comprise entre 0 et
     * 255.
     */
    public static int getRed(final int color) {
        return (color >> CHANNEL_RED) & OPAQUE;
    }

    /**
     * Extraire le canal vert d'une couleur.
     *
     * @param color Couleur unique
     * @return Le canal alpha de la couleur, la valeur est comprise entre 0 et
     * 255.
     */
    public static int getGreen(final int color) {
        return (color >> CHANNEL_GREEN) & OPAQUE;
    }

    /**
     * Extraire le canal bleu d'une couleur.
     *
     * @param color Couleur unique
     * @return Le canal alpha de la couleur, la valeur est comprise entre 0 et
     * 255.
     */
    public static int getBlue(final int color) {
        return color & OPAQUE;
    }

    /**
     * Assembler les différents cannaux en un pixel.
     *
     * @param alpha Canal alpha
     * @param red Canal rouge
     * @param green Canal vert
     * @param blue Canal bleu
     * @return Le pixel assemblé
     */
    public static int toPixel(
            final int alpha,
            final int red,
            final int green,
            final int blue) {

        return alpha << CHANNEL_ALPHA
                | red << CHANNEL_RED
                | green << CHANNEL_GREEN
                | blue;
    }

    /**
     * Générer un pixel éclairer. Ce pixel se base en combinant les cannaux de
     * couleurs les plus lumineux en fonction de deux couleurs.
     *
     * @param pixel0 Couleur du pixel initial en arrière plan
     * @param pixel1 Couleur en avant plan
     * @return Un pixel résultant du mélange des deux paramètres en combinant
     * les cannaux de couleurs les plus lumineux
     */
    public static int light(final int pixel0, final int pixel1) {

        var aa0 = getAlpha(pixel0);
        var rr0 = getRed(pixel0);
        var gg0 = getGreen(pixel0);
        var bb0 = getBlue(pixel0);

        var aa1 = getAlpha(pixel1);
        var rr1 = getRed(pixel1);
        var gg1 = getGreen(pixel1);
        var bb1 = getBlue(pixel1);

        return toPixel(
                Math.max(aa0, aa1),
                Math.max(rr0, rr1),
                Math.max(gg0, gg1),
                Math.max(bb0, bb1)
        );
    }

    /**
     * Générer un pixel éclairer.Ce pixel se base en combinant les cannaux de
     * couleurs les plus lumineux en fonction de deux couleurs.
     *
     * @param aa0 Canal alpha du pixel initial en arrière plan
     * @param rr0 Canal rouge du pixel initial en arrière plan
     * @param gg0 Canal vert du pixel initial en arrière plan
     * @param bb0 Canal bleu du pixel initial en arrière plan
     * @param pixel1 Couleur en avant plan
     * @return Un pixel résultant du mélange des deux paramètres en combinant
     * les cannaux de couleurs les plus lumineux
     */
    public static int light(
            final int aa0,
            final int rr0,
            final int gg0,
            final int bb0,
            final int pixel1) {

        var aa1 = getAlpha(pixel1);
        var rr1 = getRed(pixel1);
        var gg1 = getGreen(pixel1);
        var bb1 = getBlue(pixel1);

        return toPixel(
                Math.max(aa0, aa1),
                Math.max(rr0, rr1),
                Math.max(gg0, gg1),
                Math.max(bb0, bb1)
        );
    }

    /**
     * Calculer une couleur de pixel résultant de deux autres pixels.
     *
     * @param sourceColor Couleur du pixel source
     * @param destinationColor Couleur du pixel de destination
     * @return Un pixel issu du mélange
     */
    public static int blend(final int sourceColor, final int destinationColor) {

        int pixel;

        var aa0 = getAlpha(sourceColor);

        if (aa0 == TRANSPARENT) {
            pixel = TRANSPARENT;
        } else if (aa0 == OPAQUE) {
            pixel = sourceColor;
        } else {

            var rr0 = getRed(sourceColor);
            var gg0 = getGreen(sourceColor);
            var bb0 = getBlue(sourceColor);

            var aa1 = getAlpha(destinationColor);
            var rr1 = getRed(destinationColor);
            var gg1 = getGreen(destinationColor);
            var bb1 = getBlue(destinationColor);

            var alpha = aa0 - (aa0 - aa1) * aa0 / (float) OPAQUE;
            var red = rr0 - (rr0 - rr1) * aa0 / (float) OPAQUE;
            var green = gg0 - (gg0 - gg1) * aa0 / (float) OPAQUE;
            var blue = bb0 - (bb0 - bb1) * aa0 / (float) OPAQUE;

            pixel = toPixel(
                    (int) alpha,
                    (int) red,
                    (int) green,
                    (int) blue
            );
        }

        return pixel;
    }

    /**
     * Mettre à l'échelle une image.
     *
     * @param image Image initiale
     * @param scale Coéfficient de mise à l'échelle, strictement suppérieur à 0.
     * @return Une nouvelle image agrandi
     */
    public static Sprite scale(final Sprite image, final int scale) {

        var scaleWidth = scale * image.width;
        var scaleHeight = scale * image.height;
        var buffer = new int[scaleWidth * scaleHeight];
        int ya;
        int xa;

        for (var y = 0; y < scaleHeight; y++) {
            ya = y / scale;

            for (var x = 0; x < scaleWidth; x++) {
                xa = x / scale;
                var pixel = image.getPixel(xa, ya);
                buffer[x + y * scaleWidth] = pixel;
            }
        }
        return new Sprite(scaleWidth, scaleHeight, buffer);
    }

    /**
     * Vérifier qu'une position est comprise dans une taille.
     *
     * @param position Position
     * @param size Taille
     * @return La valeur <code>true</code> si la position est comprise dans la
     * taille sinon <code>false</code>
     */
    public static boolean isInBound(final int position, final int size) {

        return position >= 0 && position < size;
    }

}
