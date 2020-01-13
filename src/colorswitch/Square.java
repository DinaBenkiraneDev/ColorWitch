package colorswitch;

/**Auteurs: Alexandre Dufour, Dina Benkirane
 * Obstacle simple : un carré qui change de couleur à toutes les 2 secondes.
 */
public class Square extends Obstacle {

    protected double width;
    protected double timeSinceColorChange = 0;

    /**
     * Constructeur de la classe Square
     * @param x Position horizontale du carré
     * @param y position verticale du carré
     * @param largeur largeur du carré
     */
    public Square(double x, double y, double largeur) {
        super(x, y);

        this.width = largeur;
        this.renderer = new SquareRenderer(this);
        this.color = (int) (Math.random() * 4);
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return width;
    }

    @Override
    public void tick(double dt) {
        timeSinceColorChange += dt;

        if (timeSinceColorChange > 2) {
            color = (color + 1) % 4;
            timeSinceColorChange = 0;
        }
    }

    public int getColor() {
        return color;
    }

    /**
     * Permet de savoir si le joueur touche l'obstacle.
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

        double minDistHeight = player.getWidth() / 2 + width / 2;
        double minDistWidth = player.getWidth() / 2 + width / 2;

        return (distanceY < minDistHeight && distanceX < minDistWidth && this.color != player.getColor());
    }
}
