package colorswitch;

/**
 * Auteurs:Alexandre Dufour, Dina Benkirane
 * Représente le troisième niveau du jeu
 */
public class Level3 extends Level {

    /**
     * Constructeur de la classe, instancie les obstacles, les items, et le mushroom
     */
    public Level3(double screenWidth, double screenHeight, Player player) {
        super(screenWidth, screenHeight, player);

        double x = screenWidth / 2;

        // Création des obstacles
        // Création des obstacles
        RotatingCircle obstacle1 = new RotatingCircle(x, 0.75 * screenHeight, 70, 1, this);
        VerticalBar obstacle2 = new VerticalBar(x, 1.5 * screenHeight, 80, 120, this, player);
        GrowingCircle obstacle3 = new GrowingCircle(x, 2 * screenHeight, 20, this);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);


        // Création des items
        Potion potion1 = new Potion(x, 1.75 * screenHeight);
        Shield shield = new Shield(x, 1 * screenHeight);

        items.add(potion1);
        items.add(shield);

        //Ajout de la fin de niveau
        victoryMushroom = new Mushroom(screenWidth / 2, 2.5 * screenHeight);
    }
}
