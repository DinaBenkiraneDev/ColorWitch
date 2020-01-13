package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**Auteurs: Alexandre Dufour, Dina Benkirane
 * Fait le rendu d'un Square en dessinant un carré coloré sur l'écran.
 */
public class SquareRenderer extends Renderer {
    //Attribut
    private Square carre;

    /**
     * Contructeur de la classe, permet d'instancier le carré
     * @param c
     */
    public SquareRenderer(Square c) {
        this.carre = c;
    }

    /**
     *Permet de dessiner le carré
     * @param level
     * @param context
     */
    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, carre.getY());
        context.setFill(Renderer.convertColor(carre.getColor()));

        context.fillRect(
                carre.getX() - carre.getWidth() / 2,
                canvasY - carre.getWidth() / 2,
                carre.getWidth(),
                carre.getWidth());
    }
}
