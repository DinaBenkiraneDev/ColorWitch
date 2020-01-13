package colorswitch;

/**
 * Auteurs: Alexandre Dufour, Dina Benkirane
 * Item : Potion magique.
 * <p>
 * Fait changer la sorcière de couleur
 */
public class Potion extends Item {

    //variable pour voir si l'objet à été utilisé
    private boolean used = false;
    private int width = 48;

    /**
     * Constructeur de la classe Potion,
     *
     * @param x Position horizontale
     * @param y Position verticale
     */
    public Potion(double x, double y) {
        super(x, y);

        this.renderer = new ImageRenderer("potion", this);
    }

    @Override
    public void tick(double dt) {
        // Rien à faire
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
     * Rentrer en collision fais en sorte que le joueur change de couleur aléatoirement et change l'image de la potion
     *
     * @param player
     * @param game
     */
    @Override
    public void handleCollision(Player player, Game game) {
        used = true;
        this.renderer = new ImageRenderer("empty-potion", this);
        player.randomizeColor();
    }

    /**
     * Regarde si le joueur rentre en contacte avec la potion
     *
     * @param player
     * @return Permet de calculer la distance entre les 2 obstacles, si la distance est plus petite que la distance
     * minimale alors et que la couleur est similaire, alors cela va retourner true
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
