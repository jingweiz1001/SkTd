package View;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * The view of difficulty.
 *
 * @author Jingwei Zhang
 */
public class DifficultyView extends Canvas {
  private double diff;
  private GraphicsContext gc = this.getGraphicsContext2D();
  private BorderPane pane;
  private MediaView mv;
  private int x;
  private int y;
  private int tic;
  private ArrayList<Image> fires = new ArrayList<>();
  private Timeline timeline = new Timeline();

  /**
   * Constructs a {@link DifficultyView}.
   *
   * @param w width
   * @param h height
   * @param p parent node
   * @param m start video
   * @param f the background effect of words.
   */
  public DifficultyView(int w, int h, BorderPane p, MediaView m, ArrayList<Image> f) {
    fires = f;
    this.setWidth(w);
    this.setHeight(h);
    MouseHandler mh = new MouseHandler();
    this.setOnMouseClicked(mh);
    Font fontLarger = Font.font("Comic Sans MS", FontWeight.BOLD, 40);
    gc.setFont(fontLarger);
    gc.setFill(Color.BLACK);
    gc.fillText("Select your Difficulty", 310, 350);
    gc.setFill(Color.WHITE);
    gc.fillText("Back", 10, 10);
    Font fontLarge = Font.font("Comic Sans MS", FontWeight.BOLD, 20);
    gc.setFont(fontLarge);
    gc.fillText("Give Me a Ease", 450, 450);
    gc.fillText("Give Me a Balanced Experience", 380, 500);
    gc.fillText("Give Me a Challenge", 430, 550);
    Font fontSmall = Font.font("Comic Sans MS", FontWeight.BOLD, 15);
    gc.setFont(fontSmall);
    gc.fillText("Video Copyright: Ori and the Blind Forest, Moon Studios.\nUsed for this project only.", 5, 720);
    pane = p;
    mv = m;
    this.setOnKeyPressed(getOnKeyPressed());
    this.setOnMouseMoved(new DragHandler());
    timeline = new Timeline(new KeyFrame(Duration.millis(50), new AnimateStarter()));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }

  /**
   * Sets the options.
   */
  public void setOption() {
    pane.setCenter(new MapChooseView(1050, 750, pane, diff, mv));
  }

  /**
   * The {@link EventHandler<ActionEvent>} for the animation of buttons.
   */
  private class AnimateStarter implements EventHandler<ActionEvent> {
    /**
     * Invoked when a specific event of the type for which this handler is registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
      tic++;
      gc.clearRect(0, 0, 1050, 750);
      if (x > 450 && x < 601 && y > 434 && y < 450) {
        gc.drawImage(fires.get(tic / 3 % 5), 19, 150, 550, 280, 420, 429, 210, 30);
      } else if (x > 380 && x < 684 && y > 483 && y < 500) {
        gc.drawImage(fires.get(tic / 3 % 5), 19, 150, 550, 270, 340, 478, 390, 30);
      } else if (x > 430 && x < 627 && y > 533 && y < 549) {
        gc.drawImage(fires.get(tic / 3 % 5), 19, 150, 550, 280, 400, 528, 240, 30);
      } else if (x > 0 && x < 102 && y > 0 && y < 31) {
        gc.drawImage(fires.get(tic / 3 % 5), 19, 150, 550, 280, -15, -5, 140, 50);
      }
      Font fontLarger = Font.font("Comic Sans MS", FontWeight.BOLD, 40);
      gc.setFont(fontLarger);
      gc.setFill(Color.BLACK);
      gc.fillText("Select your Difficulty", 310, 350);
      gc.setFill(Color.WHITE);
      gc.fillText("Back", 10, 30);
      Font fontLarge = Font.font("Comic Sans MS", FontWeight.BOLD, 20);
      gc.setFont(fontLarge);
      gc.fillText("Give Me a Ease", 450, 450);
      gc.fillText("Give Me a Balanced Experience", 380, 500);
      gc.fillText("Give Me a Challenge", 430, 550);
      Font fontSmall = Font.font("Comic Sans MS", FontWeight.BOLD, 15);
      gc.setFont(fontSmall);
      gc.fillText("Video Copyright: Ori and the Blind Forest, Moon Studios.\nUsed for this project only.", 5, 720);
      if (tic == 20) {
        tic = 0;
      }
    }
  }

  /**
   * The {@link EventHandler<MouseEvent>} for the user's mouse.
   */
  private class MouseHandler implements EventHandler<MouseEvent> {
    /**
     * Invoked when a specific event of the type for which this handler is registered happens.
     *
     * @param e the event which occurred
     */
    @Override
    public void handle(MouseEvent e) {
      double x = e.getX();
      double y = e.getY();
      if (x > 450 && x < 601 && y > 434 && y < 450) {
        diff = 1;
      } else if (x > 380 && x < 684 && y > 483 && y < 500) {
        diff = 1.25;
      } else if (x > 430 && x < 627 && y > 533 && y < 549) {
        diff = 1.5;
      } else if (x > 0 && x < 102 && y > 0 && y < 31) {
        pane.getChildren().clear();
        pane.setCenter(new Cover(1050, 750, pane, mv));
      }
      if (diff != 0) {
        setOption();
      }
    }
  }

  /**
   * The {@link EventHandler<MouseEvent>} for user when he drags something.
   */
  private class DragHandler implements EventHandler<MouseEvent> {
    /**
     * Invoked when a specific event of the type for which this handler is registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(MouseEvent event) {
      x = (int) event.getX();
      y = (int) event.getY();
    }
  }
}
