package View;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaView;
import map.Grave;
import map.Jungle;
import map.Lava;
import map.Map;

/**
 * The view of choosing map.
 *
 * @author Jingwei Zhang
 */
public class MapChooseView extends Canvas {
  private double diff;
  private int mapNum;
  private Map game;
  private Image image = new Image("file:images/worldmap.png", false);
  private Image green = new Image("file:images/green.png", false);
  private Image red = new Image("file:images/red.png", false);
  private Image junglef = new Image("file:images/Jungle_s1.png", false);
  private Image gravef = new Image("file:images/Grave_s1.png", false);
  private Image lavaf = new Image("file:images/Lava_s1.png", false);

  private GraphicsContext gc = this.getGraphicsContext2D();
  private BorderPane pane;
  private MediaView mv;

  /**
   * Constructs a {@link MapChooseView}.
   *
   * @param w width
   * @param h height
   * @param p parent pane
   * @param d difficulty rate
   * @param m start video.
   */
  public MapChooseView(int w, int h, BorderPane p, double d, MediaView m) {
    this.setWidth(w);
    diff = d;
    this.setHeight(h);
    MouseHandler mh = new MouseHandler();
    this.setOnMouseClicked(mh);
    this.setOnMouseMoved(new DragHandler());
    gc.drawImage(image, 0, 0, 1050, 750);
    pane = p;
    mv = m;
    mapNum = 0;
    gc.drawImage(red, 523, 123, 30, 30);
    gc.drawImage(red, 784, 330, 30, 30);
    gc.drawImage(red, 620, 577, 30, 30);

  }

  /**
   * Sets options.
   */
  public void setOption() {
    switch (mapNum) {
      case 1:
        game = new Jungle(diff);
        break;
      case 2:
        game = new Grave(diff);
        break;
      default:
        game = new Lava(diff);
    }
    Canvas ca = new Canvas(1050, 760);
    ImageView view = new ImageView(game, ca, mapNum, diff, 900, 760, pane, mv);
    pane.getChildren().clear();
    pane.getChildren().add(view);
    pane.getChildren().add(ca);
    view.start();
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
      if (x > 620 && x < 650 & y > 577 && y < 607) {
        mapNum = 1;
      } else if (x > 784 && x < 814 & y > 330 && y < 360) {
        mapNum = 2;
      } else if (x > 523 && x < 553 & y > 123 && y < 153) {
        mapNum = 3;
      }
      if (mapNum != 0) {
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
      int x = (int) event.getX();
      int y = (int) event.getY();
      gc.clearRect(0, 0, 1050, 750);
      gc.drawImage(image, 0, 0, 1050, 750);
      gc.drawImage(red, 523, 123, 30, 30);
      gc.drawImage(red, 784, 330, 30, 30);
      gc.drawImage(red, 620, 577, 30, 30);

      if (x > 523 && x < 553 & y > 123 && y < 153) {
        gc.drawImage(green, 523, 123, 30, 30);
        gc.drawImage(lavaf, 10000, 100, 200, 125);
      } else if (x > 784 && x < 814 & y > 330 && y < 360) {
        gc.drawImage(green, 784, 330, 30, 30);
        gc.drawImage(gravef, 550, 250, 200, 125);
      } else if (x > 620 && x < 650 & y > 577 && y < 607) {
        gc.drawImage(green, 620, 577, 30, 30);
        gc.drawImage(junglef, 400, 460, 200, 125);
      }
    }
  }
}
