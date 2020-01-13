package colorswitch;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

import static colorswitch.ColorsWitch.HEIGHT;
import static colorswitch.ColorsWitch.WIDTH;

/**
 *
 */
public class WitchDeath {

    private double x, y;
    private double r;
    //vitesse
    private double vx, vy;
    // Gravité
    private double ax = 0, ay = 1000;
    private boolean used = false;
    private double timeSinceDeath = 0;
    private double timeInvincible = 0;

    public WitchDeath(double x, double y, double r, double vx, double vy) {
        this.x = WIDTH / 2;
        this.y = HEIGHT / 2;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Met à jour la position et la vitesse de la balle
     *
     * @param dt Temps écoulé depuis le dernier update() en secondes
     */

    public void update(double dt) {

        //update la vitesse
        vx += dt * ax;
        vy += dt * ay;

        //update la position
        x += dt * vx;
        y += dt * vy;

        if (x + r > WIDTH || x - r < 0) {
            vx *= -0.9;
        }
        if (y + r > HEIGHT || y - r < 0) {
            vy *= -0.9;
        }

        x = Math.min(x, WIDTH - r);
        x = Math.max(x, r);

        y = Math.min(y, HEIGHT - r);
        y = Math.max(y, r);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return 2 * r;
    }

    public double getH() {
        return 2 * r;
    }

    /**à changer, maybe (ajouter circle renderer, e)
     * S'occuppe de l'animation de l'affichage de la mort de la sorcière
     * @param player
     */
    public void explosion(Player player, GraphicsContext context) {
        context.setFill(Color.BLUE);
        //instanciation des balles
        WitchDeath[] balls = new WitchDeath[100];
        Random random=new Random();
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new WitchDeath(240, 160, random.nextInt(4-2) + 2,
                    random.nextInt((300 + 300) + 1) - 300,random.nextInt((250 + 250) + 1) - 250);
        }
        //Début de l'animation
        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                double time = (now - lastTime) * 1e-9;
                context.clearRect(0, 0, WIDTH, HEIGHT);
                for (int i = 0; i < balls.length; i++) {
                    WitchDeath b = balls[i];
                    b.update(time);

                    context.fillOval(
                            b.getX() - b.getW() / 2,
                            b.getY() - b.getH() / 2,
                            b.getW(),
                            b.getH());
                }
                /**
                timeSinceDeath += time;
                timeInvincible += time;

                if(timeSinceDeath > 0.01) {
                    if (timeInvincible < 3) {
                        player.itIsInvincible(true);
                        timeSinceDeath = 0;
                    }
                }
                **/
                lastTime = now;
            }

        };
        timer.start();

    }
}
