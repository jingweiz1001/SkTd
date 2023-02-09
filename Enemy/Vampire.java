package Enemy;

import java.awt.geom.Point2D;

import javafx.scene.canvas.GraphicsContext;
import model.Track;
import resouces.Images;

/**
 * The vampire enemy.
 *
 * @author Jingwei Zhang
 */
public class Vampire extends Enemy {
  private static final long serialVersionUID = 709577054891664258L;

  private static String fn = "vampire";
  private static double h = 50;
  private static int a = 5;
  private static double s = 5;
  private static int v = 5;
  private static double d = 0.25;
  private int tic = -1;
  private double sx = 72;
  private double sy = 5;
  private double sw = 24;
  private double sh = 31;

  /**
   * Constructs a {@link Vampire} enemy.
   *
   * @param diff the increase rate of difficulty.
   * @param p    the position of this enemy.
   * @param t    the track of this enemy.
   */
  public Vampire(double diff, Point2D p, Track t) {
    super(fn, p, h * diff, a, s, t, d, v, "Vampire");
  }

  /**
   * Draws this enemy on the graphics context.
   *
   * @param gc the graphics context.
   */
  @Override
  public void draw(GraphicsContext gc) {
    tic++;
    gc.drawImage(Images.getMon(fn), sx + tic / 5 * sw, sy + (this.getDirection() % 4) * sh, sw, sh,
            this.getPosition().getX() - 15, this.getPosition().getY() - 15, 30, 30);
    if (tic == 10) {
      tic = -1;
    }
  }
}
