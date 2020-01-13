package colorswitch;

/**
 * Auteurs:Alexandre Dufour, Dina Benkirane
 * Représente le quatrième niveau du jeu
 */
public class Level4 extends Level {

    /**
     * Constructeur de la classe, instancie les obstacles, les items, et le mushroom
     */
    public Level4(double screenWidth, double screenHeight, Player player) {
        super(screenWidth, screenHeight, player);

        double x = screenWidth / 2;


        // Création des obstacles
        Square obstacle1 = new Square(x, 0.70 * screenHeight, 90);
        RotatingCircle obstacle2 = new RotatingCircle(x, 1 * screenHeight, 30, 5, this);
        VerticalBar obstacle3 = new VerticalBar(x, 1.5 * screenHeight, 15, 120, this, player);
        RotatingCircle obstacle4 = new RotatingCircle(x, 2.35 * screenHeight, 15, 4, this);
        RotatingCircle obstacle5 = new RotatingCircle(x, 2.75 * screenHeight, 25, 8, this);
        GrowingCircle obstacle6 = new GrowingCircle(x, 3.25 * screenHeight, 20, this);


        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        obstacles.add(obstacle5);
        obstacles.add(obstacle6);


        // Création des items
        Potion potion1 = new Potion(x, 2 * screenHeight);
        Shield shield = new Shield(x, 2.35 * screenHeight);

        items.add(potion1);
        items.add(shield);

        //Ajout de la fin de niveau
        victoryMushroom = new Mushroom(screenWidth / 2, 3.75 * screenHeight);
    }
}
