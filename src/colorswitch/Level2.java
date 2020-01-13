package colorswitch;

/**Auteurs:Alexandre Dufour, Dina Benkirane
 *Représente le deuxième niveau du jeu
 */
public class Level2 extends Level {

    /**
     * Constructeur de la classe, instancie les obstacles, les items, et le mushroom
     */
    public Level2(double screenWidth, double screenHeight, Player player) {
        super(screenWidth, screenHeight, player);

        double x = screenWidth / 2;

        // Création des obstacles
        VerticalBar obstacle1 = new VerticalBar(x, 0.75 * screenHeight, 30, 70, this, player);
        GrowingCircle obstacle2 = new GrowingCircle(x, 1.5 * screenHeight, 20, this);
        Square obstacle3 = new Square(x, 2.0 * screenHeight, 30);
        RotatingCircle obstacle4 = new RotatingCircle(x, 2.0 * screenHeight, 15, 6, this);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);

        // Création des items
        Potion potion1 = new Potion(x, 1.15 * screenHeight);

        items.add(potion1);

        //Ajout de la fin de niveau
        victoryMushroom = new Mushroom(screenWidth / 2, 2.5 * screenHeight);
    }
}
