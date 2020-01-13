package colorswitch;

import java.util.List;

/**
 * Auteurs: Alexandre Dufour, Dina Benkirane
 * Contrôleur pour le jeu : fait le pont entre la vue et les modèles.
 */
public class Controller {

    //Attribut de classe
    private Game game;
    private int level;
    private boolean finDuJeu=false;

    /**
     * Controlleur de la classe Controller
     *
     * @param level Niveau courant du jeu
     */
    public Controller(int level) {
        this.level = level;
        //Instancie le jeu selon le niveau mis en paramètre
        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
    }

    public List<Entity> getEntities() {
        return this.game.getEntities();
    }

    /**
     * Vérifie si le jeu est fini (perdu ou gagné)
     *
     * @param dt Delta-temps exprimé en secondes
     */
    public void tick(double dt) {

        if (this.game.isGameOver()) {
            //Change le niveau
            if (this.game.hasWon()) {
                level++;
            }

            tabTyped(true);

            //Si le niveau est plus grand que 5 on recommence le jeu
            if (level > 5) {
                this.level = 1;
                this.finDuJeu = true;
            } else {
                this.finDuJeu = false;
            }

            //Recommence le niveau
            this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
        } else {
            this.game.tick(dt);
        }
    }

    public Level getCurrentLevel() {
        return this.game.getLevel();
    }

    /**
     * Fonction appelée lorsque la barre espace est enfoncée, pour faire bouger la sorcière constamment
     */
    public void spaceTyped() {
        this.game.jump();
    }

    /**
     * Rend le joueur invincible si la touche tab est enfoncée
     *
     * @param invincible
     */
    public void tabTyped(boolean invincible) {
        this.game.IsInvincible(invincible);
    }

    // Permet de savoir si le joueur est invincible
    public boolean isItInvincible() {
        return this.game.isItInvincible();
    }


    // regarde si le jeu est fini (perdu ou gagné)
    public boolean Done() {
        return game.isGameOver();
    }

    //Regarde si le dernier niveau est fini
    public boolean Fin(){
        return this.finDuJeu;
    }

    //Reçoit la phrase de fin de jeu, soit celle ou on perd une partie ou quand on gagne
    public String getPhraseEvenement() {
        return game.winOrLoose();
    }
}
