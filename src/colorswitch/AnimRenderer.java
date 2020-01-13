package colorswitch;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Auteurs: Alexandre Dufour, Dina Benkirane
 * La fonction n'est pas utilisé?
 */
public class AnimRenderer extends Renderer {
    private String prefix;
    private double framerate;
    private Entity entity;
    private int suffixe;
    private boolean augmente;
    private int count;
    private double frame;

    public AnimRenderer(String prefix,  double frame, double framerate, Entity entity){
        this.prefix = prefix;
        this.framerate = framerate;
        this.entity = entity;
        this.suffixe = 1;
        this.augmente = true;
        this.frame = frame;
    }
    
    @Override
    public void draw(Level level, GraphicsContext context){
        double x = entity.getX();
        double y = Renderer.computeScreenY(level, entity.getY());

        context.drawImage(new Image("/" + prefix + "" + suffixe + ".png"), x - entity.getWidth() / 2, y - entity.getHeight() / 2, entity.getWidth(), entity.getHeight());
        if(augmente){
            suffixe++;
            if(suffixe >= this.frame){
                augmente = false;
            }
        }else{
            suffixe--;
            if(suffixe <= 1){
                augmente = true;
            }
        }


    }

}
