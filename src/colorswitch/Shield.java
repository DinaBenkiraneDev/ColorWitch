package colorswitch;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

/**
 * Auteurs: Alexandre Dufour, Dina Benkirane
 * Crée un un item qui permet de rendre la sorcière invincible pendant un certain temps
 */
public class Shield extends Item {

    private boolean used = false;
    private double timeSinceTouch = 0;
    private double timeInvincible = 0;
    private int width = 32;

    /**
     * Constructeur de la classe Shield
     *
     * @param x Position horizontale
     * @param y Position verticale
     */
    public Shield(double x, double y) {
        super(x, y);
        this.renderer = new ImageRenderer("shield", this);
    }

    @Override
    public void tick(double dt) {

    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.width;
    }

    /**
     * Si le joueur entre en contact avec le shield, le joueur va rentrer dans une période d'invincibilité
     * pendant un certain temps et change de couleur rapidement. (Prend aussi en compte de l'invincibilité manuel)
     *
     * @param player
     * @param game
     */
    @Override
    public void handleCollision(Player player, Game game) {
        used = true;
        this.renderer = new ImageRenderer("empty-shield", this);

        //Modification: change rapidement la couleur de la sorciere pendant trois secondes
        //pour un effet d'invincibilité.
        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();
            private boolean isItInvincible = player.isInvincible();
            private int color = player.getColor();

            @Override
            public void handle(long now) {
                double time = (now - lastTime) * 1e-9;

                timeSinceTouch += time;
                timeInvincible += time;

                //regarde le temps que la sorcière à été invincible
                if (timeSinceTouch > 0.01) {
                    if (timeInvincible < 3) {
                        player.randomizeColor();
                        player.itIsInvincible(true);
                        timeSinceTouch = 0;
                    } else {
                        //Si le joueur était déjà invincible (tab.typed) le joueur retourne invincible
                        player.itIsInvincible(isItInvincible);
                        //Retour à l'ancienne
                        player.setColor(color);
                    }
                }

                lastTime = now;
            }

        };
        timer.start();

    }

    /**
     * permet de vérifier si le joueur touche le shield
     *
     * @param player
     * @return un bouléen qui correspond si le joueur croise un obstacle
     */
    @Override
    public boolean intersects(Player player) {
        double dx = player.getX() - x;
        double dy = player.getY() - y;
        double distanceX = Math.sqrt(dx * dx);
        double distanceY = Math.sqrt(dy * dy);
        double minDistHeight = player.getWidth() / 2 + this.width / 2;
        double minDistWidth = player.getWidth() / 2 + this.width / 2;

        return (!used && distanceY < minDistHeight && distanceX < minDistWidth);
    }
}
