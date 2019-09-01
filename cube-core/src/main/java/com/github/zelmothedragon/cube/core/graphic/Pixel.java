package com.github.zelmothedragon.cube.core.graphic;

/**
 * Utilitaire pour la manipulation de pixel. Un pixel est représenté par le type
 * primitif <i>int</i>, généralement en hexadécimal sous la forme suivante:
 * <code>0xAARRGGBB</code>. Ou <i>AA</i> représente le canal alpha, idem pour
 * <i>RR</i> pour le rouge, <i>GG</i> pour le vert et <i>BB</i> pour le bleu.
 *
 * @author MOSELLE Maxime
 */
public final class Pixel {

    /**
     * Masque pour l'extraction d'un canal d'une couleur.
     */
    private static final int COLOR_MASK = 0xFF;

    /**
     * Ratio du canal alpha.
     */
    private static final float ALPHA_RATIO = 255f;

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
        return (color >> CHANNEL_ALPHA) & COLOR_MASK;
    }

    /**
     * Extraire le canal rouge d'une couleur.
     *
     * @param color Couleur unique
     * @return Le canal alpha de la couleur, la valeur est comprise entre 0 et
     * 255.
     */
    public static int getRed(final int color) {
        return (color >> CHANNEL_RED) & COLOR_MASK;
    }

    /**
     * Extraire le canal vert d'une couleur.
     *
     * @param color Couleur unique
     * @return Le canal alpha de la couleur, la valeur est comprise entre 0 et
     * 255.
     */
    public static int getGreen(final int color) {
        return (color >> CHANNEL_GREEN) & COLOR_MASK;
    }

    /**
     * Extraire le canal bleu d'une couleur.
     *
     * @param color Couleur unique
     * @return Le canal alpha de la couleur, la valeur est comprise entre 0 et
     * 255.
     */
    public static int getBlue(final int color) {
        return color & COLOR_MASK;
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
     * @param pixel Couleur du pixel initial en arrière plan
     * @param color Couleur en avant plan
     * @return Un pixel résultant du mélange des deux paramètres en combinant
     * les cannaux de couleurs les plus lumineux
     */
    public static int light(final int pixel, final int color) {

        var rr0 = Pixel.getRed(pixel);
        var gg0 = Pixel.getGreen(pixel);
        var bb0 = Pixel.getBlue(pixel);

        var rr1 = Pixel.getRed(color);
        var gg1 = Pixel.getGreen(color);
        var bb1 = Pixel.getBlue(color);

        return toPixel(
                0,
                Math.max(rr0, rr1),
                Math.max(gg0, gg1),
                Math.max(bb0, bb1)
        );

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
