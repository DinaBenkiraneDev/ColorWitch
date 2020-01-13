package colorswitch;

import java.util.ArrayList;
import java.util.List;

/**
 * Auteurs: Alexandre Dufour, Dina Benkirane
 * Classe qui instancie le jeu et les niveaux, s'occupe de progresser dans l'affichage du niveau
 */
public class Game {

    private Level level;
    private Player player;
    /**
     * Dimensions de l'écran
     */
    private double screenWidth, screenHeight;

    /**
     * Indique si la partie est terminée/gagnée
     */
    private boolean gameOver = false;
    private boolean hasWon = false;

    private String winOrLoose;

    /**
     * Crée une partie dans le niveau levelNumber.
     * Constructeur de la classe Game
     *
     * @param screenWidth  largeur de l'écran
     * @param screenHeight hauteur de l'écran
     * @param levelNumber  numéro du niveau
     */
    public Game(double screenWidth, double screenHeight, int levelNumber) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        player = new Player(screenWidth / 2, 200, 15);

        //Instancie le niveau selon le chiffre
        switch (levelNumber) {
            case 1:
                level = new Level1(screenWidth, screenHeight, player);
                break;
            case 2:
                level = new Level2(screenWidth, screenHeight, player);
                break;
            case 3:
                level = new Level3(screenWidth, screenHeight, player);
                break;
            case 4:
                level = new Level4(screenWidth, screenHeight, player);
                break;
            case 5:
                level = new Level5(screenWidth, screenHeight, player);
                break;
            default:
                throw new IllegalArgumentException("Niveau inconnu");
        }
    }

    /**
     * Fonction appelée à chaque frame
     *
     * @param dt Delta-Temps (en secondes)
     */
    public void tick(double dt) {
        level.tick(dt);
        player.tick(dt);

        if (player.getY() - player.getRadius() < level.getScroll()) {
            // Empêche la balle de sortir de l'écran
            player.setY(level.getScroll() + player.getRadius());
        } else if (player.getY() - level.getScroll() > screenHeight / 2) {
            // Scroll le level verticalement si nécessaire
            level.incrementScroll(player.getY() - level.getScroll() - screenHeight / 2);
        }

        for (LevelElement element : level.getEntities()) {
            if (element.intersects(player)) {
                element.handleCollision(player, this);
                break;
            }
        }

    }

    /**
     * @return les entités à afficher à l'écran
     */
    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>();

        entities.addAll(level.getEntities());
        entities.add(player);

        return entities;
    }

    public Level getLevel() {
        return level;
    }

    // fait sauter la sorciere
    public void jump() {
        player.jump();
    }

    //Donne un string si on perd la partie
    public void loose() {
        if (!player.isInvincible()) {
            winOrLoose = "You loose... Too bad !";
            this.gameOver = true;
        }
    }

    // Donne un string si on gagne la partie
    public void win() {
        winOrLoose = "You win !";
        this.hasWon = true;
        this.gameOver = true;
    }

    // retourne un string pour l'affichage de la phrase de fin de partie
    public String winOrLoose() {
        return winOrLoose;
    }

    /**
     * Indique si la partie est gagnée
     *
     * @return un bouléen
     */
    public boolean hasWon() {
        return hasWon;
    }

    /**
     * Indique si la partie est terminée
     *
     * @return un bouléen
     */
    public boolean isGameOver() {
        return gameOver;
    }

    // Retourne un bouléen pour savoir si le joueur est invincible ou pas
    public boolean isItInvincible() {
        return this.player.isInvincible();
    }

    //rend le joueur invincible
    public void IsInvincible(boolean invincible) {
        player.itIsInvincible(invincible);
    }
}
