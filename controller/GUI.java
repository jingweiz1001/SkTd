package controller;

import java.io.File;

import View.Cover;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * The javafx application of the tower defense game.
 *
 * @author Jingwei Zhang
 */
public class GUI extends Application {
  public static int size = 50;

  /**
   * The main method of launch this application.
   *
   * @param args no arg needed.
   */
  public static void main(String[] args) {
    launch(args);
  }

  private BorderPane pane;

  /**
   * The main entry point for all JavaFX applications. The start method is called after the init method has returned,
   * and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param stage the primary stage for this application, onto which the application scene can be set. The primary stage
   *              will be embedded in the browser if the application was launched as an applet. Applications may create
   *              other stages, if needed, but they will not be primary stages and will not be embedded in the browser.
   */
  @Override
  public void start(Stage stage) {
    pane = new BorderPane();

    File f = new File("bg.mp4");
    Media m = new Media(f.toURI().toString());
    MediaPlayer mp = new MediaPlayer(m);
    MediaView mv = new MediaView(mp);
    mv.setScaleY(1.58);
    mv.setScaleX(1.55);
    mv.setFitWidth(1050);
    mv.setFitHeight(750);
    mp.play();
    mp.setCycleCount(Integer.MAX_VALUE);

    pane.setCenter(new Cover(1050, 750, pane, mv));
    Scene scene = new Scene(pane, 1050, 750);
    stage.setTitle("TD");
    stage.setResizable(true);
    stage.setScene(scene);
    stage.show();
  }
}
