package colorswitch;

/**
 * Auteur: Alexandre Dufour, Dina Benkirane
 * Permet de crée un cercle qui tourne en rond
 */
public class RotatingCircle extends Circle {

    private double timeSinceMove = 0;
    private Level level;
    private double angle;
    private double hauteur;
    private int multiplicateur;
    private int vitesse = 130;

    /**
     * Constructeur de la classe RotatingCircle
     *
     * @param x
     * @param y
     * @param diameter       diamètre du cercle.
     * @param multiplicateur
     * @param level
     */
    public RotatingCircle(double x, double y, double diameter, int multiplicateur, Level level) {
        super(x, y, diameter);

        this.renderer = new CircleRenderer(this);

        this.color = (int) (Math.random() * 4);

        this.level = level;

        this.hauteur = y;

        this.multiplicateur = multiplicateur;

        //Donne un angle aléatoire de départ pour l'obstacle
        this.angle = Math.random() * 360 + 1;

    }

    /**
     * Permet de changer la couleur du cercle après 2 secondes et de bouger le cercle en rond.
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {
        timeSinceColorChange += dt;

        double rayon = multiplicateur * level.getScreenWidth() / 10;

        //Change la couleur du cercle
        if (timeSinceColorChange > 2) {
            this.color = (this.color + 1) % 4;
            timeSinceColorChange = 0;
        }

        //Bouge le cercle
        angle += vitesse * dt;
        if (this.angle < 360) {
            this.x = (Math.cos(Math.toRadians(angle)) * (rayon / 2)) + level.getScreenWidth() / 2;
            this.y = (Math.sin(Math.toRadians(angle)) * (rayon / 2)) + this.hauteur;
        } else
            this.angle = 0;

    }
}
