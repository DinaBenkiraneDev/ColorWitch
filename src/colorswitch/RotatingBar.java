package colorswitch;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**Auteur: Alexandre Dufour, Dina Benkirane
 *
 */
public class RotatingBar extends Bar {
	
	private int timeSinceMove = 0;
	private Level level;
	private int vitesse = 3;
	private GraphicsContext context;
	Canvas canvas;

	/**Constructeur de la classe RotatingBar
	 *
	 */
	public RotatingBar(double x, double y, double largeur, double longueur, Level level, Player player) {
		super(x, y, largeur, longueur);
		this.level = level;
		
		this.renderer = new BarRenderer(this);
		canvas = new Canvas(largeur, longueur);
		context = canvas.getGraphicsContext2D();
		
	}

	public void tick(double dt) {
    	timeSinceMove += dt;
    	
    	context.translate(this.getX(), this.getY());
        context.rotate(-2);
        context.rotate(2);
        context.translate(this.getX(), this.getY());

    	if((this.x < (9*level.getScreenWidth()/10)) || (this.x > (level.getScreenWidth()/10))) {
    			 this.x = (this.x + this.vitesse);
    			 timeSinceMove = 0;
    	} 
    	
    	if((this.x > (9*level.getScreenWidth()/10)) || (this.x < (level.getScreenWidth()/10)))  {
    		 this.vitesse = -this.vitesse;

    	 }

}
}
