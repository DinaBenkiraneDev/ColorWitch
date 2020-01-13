package colorswitch;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**Auteurs: Alexandre Dufour, Dina Benkirane
 * Fait le rendu d'une Entity sur l'Ã©cran en affichant une image
 */
public class AnimationRenderer extends Renderer {
    //Attribut
    private Entity entity;
    private double framerate;
    private Image[] frames;
    private int frame;
    private double time;
    private long now;
    private long lastTime;
    double deltaTime;

    /**
     * Constructeur de la classe AnimationRenderer, permet d'instancier un animation par Frame et le
     * nombre d'image nécéssaire
     * @param prefix
     * @param nbrFrame
     * @param framerate
     * @param entity
     */
    public AnimationRenderer(String prefix, int nbrFrame, double framerate, Entity entity) {
        this.entity = entity;
        int number = nbrFrame;
        this.framerate = framerate;
        this.frames = new Image[number];
        this.frame = 0;
        this.now = System.nanoTime();
        this.deltaTime = 0;

        //Initialisation du tableau des images en animation.
        for(int i = 0; i < number; i++) {
        	int image = i+1;
        	frames[i] = new Image("/" + prefix + image + ".png");
        }
    }

    /**
     * Permet d'animer la séquence d'image
     * @param level
     * @param context
     */
    @Override
    public void draw(Level level, GraphicsContext context) {
    		
    		//Permet de déterminer le temps entre les appels de la méthode.
    		this.lastTime = this.now;
    		this.now = System.nanoTime();
    		
    		//Retrouver la position du champignon dans le niveau
    		double x = entity.getX();
    		double y = Renderer.computeScreenY(level, entity.getY());
    		
    		//Accumulation du temps depuis le début de l'animation
    		this.time = (this.now - this.lastTime)*1e-9;
    		this.deltaTime += time;
    		
    		//Détermine le frame auquel on est rendu en multipliant deltaTime avec la vitesse.
    		frame = (int) (deltaTime * this.framerate);
    		
    		//On retrouve la frame dans le tableau.
    		Image img = frames[frame % frames.length];

    		//On change l'ancienne image pour la nouvelle image.
    		context.clearRect( (x - entity.getWidth() / 2)+1, (y - entity.getHeight() / 2)+1,  entity.getWidth()-1,  entity.getHeight()-1.75);
    		context.setFill(Color.BLACK);
    		context.fillRect((x - entity.getWidth() / 2), (y - entity.getHeight() / 2),  entity.getWidth(),  entity.getHeight()+0.5);
    		context.drawImage(img, x - entity.getWidth() / 2, y - entity.getHeight() / 2, entity.getWidth(),  entity.getHeight());

    }

}