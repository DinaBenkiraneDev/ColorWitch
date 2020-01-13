package colorswitch;

/**Auteurs: Alexandre Dufour, Dina Benkirane
 *But de la classe est d'avoir une classe qui nous permet d'avoir les composantes normales du cercle
 * s'occupe aussi des collisions d'obstacle
 */
public class Circle extends Obstacle {

    protected double diameter;
    protected double timeSinceColorChange = 0;

    /**
     * Constructeur de la classe cercle
     * @param x Position horizontale du cercle
     * @param y Postition verticale du cercle
     * @param diameter diamètre du cercle
     */
    public Circle(double x, double y, double diameter) {
        super(x, y);

        this.diameter = diameter;
        this.renderer = new CircleRenderer(this);

        this.color = (int) (Math.random() * 4);
    }

    @Override
    public double getWidth() {
        return diameter;
    }

    @Override
    public double getHeight() {
        return diameter;
    }

    /**
     * Permet de changer la couleur du cercle à chaque 2 secondes
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

    /**Regarde si le joueur rentre en contacte avec un objet Circle
     * @param player
     * @return Permet de calculer la distance entre les 2 obstacles, si la distance est plus petite que la distance
     *         minimale alors et que la couleur est similaire, alors cela va retourner true
     */
    @Override
    public boolean intersects(Player player) {

        double dx = player.getX() - x;
        double dy = player.getY() - y;

        double distance = Math.sqrt(dx * dx + dy * dy);
        double minDist = player.getWidth() / 2 + diameter / 2;

        return (distance < minDist && this.color != player.getColor());

    }
}