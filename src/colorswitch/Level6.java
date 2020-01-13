package colorswitch;

/**
 * Auteurs:Alexandre Dufour, Dina Benkirane
 * Représente le sixième niveau du jeu
 */
public class Level6 extends Level {

    /**
     * Constructeur de la classe, instancie les obstacles, les items, et le mushroom
     */
    public Level6(double screenWidth, double screenHeight, Player player) {
        super(screenWidth, screenHeight, player);

        double x = screenWidth / 2;

        
        // Création des obstacles
        RotatingBar obstacle1 = new RotatingBar(x, 0.75 * screenHeight, 15, 250, this, player);
        

        

        
        obstacles.add(obstacle1);
       

      

        // Création des items



        //Ajout de la fin de niveau
        victoryMushroom = new Mushroom(screenWidth / 2, 1.5 * screenHeight);
    }
}
