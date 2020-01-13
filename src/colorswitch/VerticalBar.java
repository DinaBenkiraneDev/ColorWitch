package colorswitch;

/**
 * Auteurs: Alexandre Dufour, Dina Benkirane
 * La classe permet de crée un obstacle en forme de barre verticale
 */
public class VerticalBar extends Bar {

    private Level level;
    private int vitesse = 150;

    /**
     * Constructeur de la classe VerticalBar
     * @param x Position horizontale
     * @param y Position verticale
     * @param largeur Largeur de l'obstacle
     * @param longueur Longueur de l'obstacle
     * @param level Le niveau de l'obstacle
     * @param player Le joueur
     */
    public VerticalBar(double x, double y, double largeur, double longueur, Level level, Player player) {
        super(x, y, largeur, longueur);

        this.renderer = new BarRenderer(this);

        //Empeche d'avoir la même couleur entre la bar et le joueur
        do {
            this.color = (int) (Math.random() * 4);
        } while (this.color == player.getColor());

        this.level = level;
    }

    /**
     *Permet de faire bouger l'obstacle
     * @param dt deltatime
     */
    @Override
    public void tick(double dt) {

        if ((this.x < (9 * level.getScreenWidth() / 10)) || (this.x > (level.getScreenWidth() / 10))) {
            this.x += this.vitesse * dt;
        }

        if (this.x > (9 * level.getScreenWidth() / 10)) {
            this.vitesse = -this.vitesse;
            this.x = (9 * level.getScreenWidth() / 10);

        } else if (this.x < (level.getScreenWidth() / 10)) {
            this.vitesse = -this.vitesse;
            this.x = (level.getScreenWidth() / 10);
        }


    }

}
