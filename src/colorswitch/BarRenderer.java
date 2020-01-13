package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**Auteurs: Alexandre Dufour, Dina Benkirane
 * Fait le rendu d'un Square en dessinant un carré coloré sur l'écran.
 */
public class BarRenderer extends Renderer {

    private Bar barre;

    /**
     * Constructeur de la classe BarRenderer
     */
    public BarRenderer(Bar b) {
        this.barre = b;
    }

    /**
     * Permet de dessiner la bar verticale selon la longueur
     * @param level Le niveau ou se trouve l'obstacle
     * @param context
     */
    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, barre.getY());

        context.setFill(Renderer.convertColor(barre.getColor()));

        context.fillRect(
                barre.getX() - barre.getWidth() / 2,
                canvasY - barre.getHeight() /2,
                barre.getWidth(),
                barre.getHeight());

    }
}
