package colorswitch;

/**
 * Auteurs: Alexandre Dufour, Dina Benkirane
 * Item : champignon.
 * Ramasser un champignon permet de gagner le niveau actuel
 */
public class Mushroom extends Item {

    private int width = 64;

    /**
     * Contructeur de la classe mushroom, fait appel à AnimationRenderer pour animer le champignon
     *
     * @param x Position horizontale du champignon
     * @param y Position verticale du champignon
     */
    public Mushroom(double x, double y) {
        super(x, y);

        this.renderer = new AnimationRenderer("mushroom_animation", 26, 24, this);
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
     * Quand le joueur rentre en contact avec le champignon, le niveau est fini.
     *
     * @param player
     * @param game
     */
    @Override
    public void handleCollision(Player player, Game game) {
        game.win();
    }

    /**
     * Regarde si le joueur rentre en contacte avec le champignon
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

        return (distanceY < minDistHeight && distanceX < minDistWidth);
    }
}
