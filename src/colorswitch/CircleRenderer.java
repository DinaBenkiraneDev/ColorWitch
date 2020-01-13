package colorswitch;

import javafx.scene.canvas.GraphicsContext;

/**Auteurs: Alexandre Dufour, Dina Benkirane
 *Fait le rendu d'un circle en dessinant un circle coloré sur l'écran.
 */
public class CircleRenderer extends Renderer{

    private Circle cercle;

    /**
     * Constructeur de la classe CircleRenderer
     * @param cercle
     */
    public CircleRenderer(Circle cercle) {
        this.cercle = cercle;
    }

    /**
     * Dessine le cercle et donne la couleur au cercle selon sa position.
     * @param level Le level de l'objet
     * @param context
     */
	@Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, cercle.getY());

        context.setFill(Renderer.convertColor(cercle.getColor()));

        context.fillOval(
                cercle.getX() - cercle.getWidth()/2,
                canvasY - cercle.getWidth()/2,
                cercle.getWidth(),
                cercle.getWidth());
    }
}
