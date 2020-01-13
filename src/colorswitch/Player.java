package colorswitch;

/**
 * Auteurs: Alexandre Dufour, Dina Benkirane
 * Classe représentant l'entité de la personne qui joue (aka, la sorcière).
 * <p>
 * La sorcière est représentée par un cercle.
 */
public class Player extends Entity {

    public double radius;
    //vitesse
    private double vy;
    private double ay;
    private int color = 1;
    private boolean invincible;

    /**
     * Constructeur de la classe Player
     *
     * @param x position horizontale du joueur
     * @param y Position verticale du joueur
     * @param r C'est le rayon du cercle
     */
    public Player(double x, double y, double r) {
        super(x, y);

        this.radius = r;

        this.vy = 0;
        this.ay = -400;

        this.renderer = new PlayerRenderer(this);
        this.invincible = false;
    }

    //Rend le joueur invincible
    public boolean isInvincible() {
        return this.invincible;
    }

    //Regarde si le joueur est présentement invincible
    public void itIsInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité de la vitesse du joueur
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {
        // Mise à jour de la vitesse
        vy += dt * ay;

        // Mise à jour de la position
        y += dt * vy;

        // Clip la vitesse pour rester entre -300 et 300
        vy = Math.min(vy, 300);
        vy = Math.max(vy, -300);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Remplace la couleur actuelle par une nouvelle couleur aléatoire
     */
    public void randomizeColor() {
        int newColor;

        do {
            newColor = (int) (Math.random() * 4);
        } while (newColor == this.color);

        this.color = newColor;
    }

    /**
     * Fait sauter la sorcière
     */
    public void jump() {
        vy = Math.max(vy, 0);
        vy += 200;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getWidth() {
        return this.getRadius() * 2;
    }

    @Override
    public double getHeight() {
        return this.getRadius() * 2;
    }
}
