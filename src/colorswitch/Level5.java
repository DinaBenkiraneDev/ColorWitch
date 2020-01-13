package colorswitch;

/**
 * Auteurs:Alexandre Dufour, Dina Benkirane
 * Représente le cinquième niveau du jeu
 */
public class Level5 extends Level {

    /**
     * Constructeur de la classe, instancie les obstacles, les items, et le mushroom
     */
    public Level5(double screenWidth, double screenHeight, Player player) {
        super(screenWidth, screenHeight, player);

        double x = screenWidth / 2;

        // Création des obstacles
        VerticalBar obstacle1 = new VerticalBar(x, 0.75 * screenHeight, 15, 250, this, player);
        RotatingCircle obstacle2 = new RotatingCircle(x, 1.5 * screenHeight, 30, 8, this);
        RotatingCircle obstacle3 = new RotatingCircle(x, 1.5 * screenHeight, 15, 4, this);
        RotatingCircle obstacle4 = new RotatingCircle(x, 1.5 * screenHeight, 15, 6, this);

        Square obstacle5 = new Square(x, 2.25 * screenHeight, 120);
        Square obstacle6 = new Square(x, 2.70 * screenHeight, 50);
        Square obstacle7 = new Square(x, 3.15 * screenHeight, 180);

        GrowingCircle obstacle8 = new GrowingCircle(x, 4 * screenHeight, 20, this);
        RotatingCircle obstacle9 = new RotatingCircle(x, 4 * screenHeight, 20, 9, this);
        RotatingCircle obstacle10 = new RotatingCircle(x, 4.80 * screenHeight, 30, 8, this);
        RotatingCircle obstacle11 = new RotatingCircle(x, 4.80 * screenHeight, 10, 6, this);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        obstacles.add(obstacle5);
        obstacles.add(obstacle6);
        obstacles.add(obstacle7);
        obstacles.add(obstacle8);
        obstacles.add(obstacle9);
        obstacles.add(obstacle10);
        obstacles.add(obstacle11);


        // Création des items
        Potion potion1 = new Potion(x, 2.5 * screenHeight);
        Shield shield = new Shield(x, 1.10 * screenHeight);

        items.add(potion1);
        items.add(shield);

        //Ajout de la fin de niveau
        victoryMushroom = new Mushroom(screenWidth / 2, 4.8 * screenHeight);
    }
}
