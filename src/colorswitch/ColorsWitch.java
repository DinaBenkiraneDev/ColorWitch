package colorswitch;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

/**Auteurs: Dina Benkriane, Alexandre Dufour
 * Classe principale. DÃ©finit la vue.
 */
public class ColorsWitch extends Application {

    public static final double WIDTH = 320, HEIGHT = 480;

    private Controller controller;
    private GraphicsContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        controller = new Controller(1);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        //Scene affichant le muenu de choix du niveau
        VBox rootMenu = new VBox();
        Scene sceneMenu = new Scene(rootMenu, WIDTH, HEIGHT);
        rootMenu.setAlignment(Pos.CENTER);
        rootMenu.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY)));

        //Texte
        Text message = new Text("Veuillez Choisir le niveau");
        message.setFont(Font.font("serif", 20));
        message.setFill(Color.WHITE);

        //Boutton menu
        Button niveau1 = new Button("niveau 1");
        Button niveau2 = new Button("niveau 2");
        Button niveau3 = new Button("niveau 3");
        Button niveau4 = new Button("niveau 4");
        Button niveau5 = new Button("niveau 5");

        rootMenu.setSpacing(10);
        rootMenu.getChildren().addAll(message, niveau1, niveau2, niveau3, niveau4, niveau5);


        //Scene affichant le jeu
        Pane rootJeu = new Pane(canvas);
        Scene sceneJeu = new Scene(rootJeu, WIDTH, HEIGHT);

        //Scene affichant "You win!" ou "You loose" à la fin de chaque niveau
        VBox rootFin = new VBox();
        rootFin.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY)));
        Scene sceneFin =  new Scene(rootFin, WIDTH, HEIGHT);

        //Text sceneFin
        Text messageFin = new Text();
        Text messageFin2 = new Text("Press ENTER to continu");

        messageFin.setFont(Font.font("serif", 16));
        messageFin.setFont(Font.font("serif", 20));
        messageFin.setFill(Color.WHITE);
        messageFin2.setFill(Color.WHITE);

        rootFin.getChildren().addAll(messageFin, messageFin2);
        rootFin.setAlignment(Pos.CENTER);

        context = canvas.getGraphicsContext2D();
        //Timer utilisé pour les animation du jeu
        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                //Fait l'affichage de la sceneFin
                if (controller.Done()) {

                    messageFin.setText(controller.getPhraseEvenement());
                    context.clearRect(0, 0, WIDTH, HEIGHT);

                    primaryStage.setScene(sceneFin);
                }

                controller.tick((now - lastTime) * 1e-9);

                context.setFill(Color.BLACK);
                context.fillRect(0, 0, WIDTH, HEIGHT);

                List<Entity> entities = controller.getEntities();

                for (Entity e : entities) {
                    e.getRepresentation().draw(controller.getCurrentLevel(), context);

                }

                lastTime = now;
            }
        };
        timer.start();

        //Action relié au choix du niveau dans le menu
        niveau1.setOnAction((event) -> {
            controller = new Controller(1);
            primaryStage.setScene(sceneJeu);
        });

        niveau2.setOnAction((event) -> {
            controller = new Controller(2);
            primaryStage.setScene(sceneJeu);

        });

        niveau3.setOnAction((event) -> {
            controller = new Controller(3);
            primaryStage.setScene(sceneJeu);

        });

        niveau4.setOnAction((event) -> {
            controller = new Controller(4);
            primaryStage.setScene(sceneJeu);

        });

        niveau5.setOnAction((event) -> {
            controller = new Controller(5);
            primaryStage.setScene(sceneJeu);

        });

        //Action relié au action dans le jeu (retour au menu, saut, invincibilité)
        sceneJeu.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.SPACE) {
                controller.spaceTyped();
            } else if (event.getCode() == KeyCode.TAB) {
                if (controller.isItInvincible() == false)
                    controller.tabTyped(true);
                else
                    controller.tabTyped(false);
            } else if (event.getCode() == KeyCode.ESCAPE) {
                primaryStage.setScene(sceneMenu);
            }
        });


        //Action relié à la scène du message de fin de partie (passage au prochain niveau ou refaire le niveau)
        sceneFin.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (controller.Fin())
                    primaryStage.setScene(sceneJeu);
                else
                    primaryStage.setScene(sceneJeu);
            } else if (event.getCode() == KeyCode.ESCAPE) {
                primaryStage.setScene(sceneMenu);
            }
        });

        primaryStage.setTitle("Colors Witch");
        primaryStage.setScene(sceneMenu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
