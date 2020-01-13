package colorswitch;

/**Auteurs: Alexandre Dufour, Dina Benkirane
 * Obstacle simple : un carré qui change de couleur à toutes les 2 secondes.
 */
public class Bar extends Obstacle {

    protected double width;
    protected double height;
    protected double timeSinceColorChange = 0;

    /**
     * Constructeur de la classe
     * @param x position horizontale
     * @param y position verticale
     * @param largeur largeur de l'obstacle
     * @param longueur longueur de l'obstacle
     */
    public Bar(double x, double y, double largeur, double longueur) {
        super(x, y);

        this.width = largeur;
        this.height = longueur;
        this.renderer = new BarRenderer(this);

        this.color = (int) (Math.random() * 4);
    }

    //Getter
    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    /**
     * Permet de changer la couleur de la barre à chaque 2 secondes
     * @param dt Delta-Temps en secondes
     */
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
     * La classe permet de vérifier si le joueur touche l'obstacle
     * @param player
     * @return un bouléen qui correspond si le joueur croise un obstacle
     */
    @Override
    public boolean intersects(Player player) {

    	double dx = player.getX() - x;
        double dy = player.getY() - y;

        double distanceX = Math.sqrt(dx * dx);
        double distanceY = Math.sqrt(dy * dy);

        double minDistHeight = player.getHeight() / 2 + this.height / 2;
        double minDistWidth = player.getWidth() / 2 + this.width / 2;

        return (distanceY < minDistHeight && distanceX < minDistWidth && this.color != player.getColor());
    }
}
