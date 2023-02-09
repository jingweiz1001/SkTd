
package Enemy;

import java.awt.geom.Point2D;

import javafx.scene.canvas.GraphicsContext;
import model.Track;
import resouces.Images;

/**
 * The ghost enemy.
 *
 * @author Jingwei Zhang
 */
public class Ghost extends Enemy {
  private static final long serialVersionUID = -3254602391763646663L;

  private static String fn = "ghost";
  private static double h = 100;
  private static int a = 10;
  private static double s = 2;
  private static int v = 10;
  private static double d = 0.25;
  private int tic = -1;
  private double sx = 0;
  private double sy = 132;
  private double sw = 24;
  private double sh = 31;

  /**
   * Constructs a {@link Ghost} enemy.
   *
   * @param diff the increase rate of difficulty.
   * @param p    the position of this enemy.
   * @param t    the track of this enemy.
   */
  public Ghost(double diff, Point2D p, Track t) {
    super(fn, p, h * diff, a, s, t, d, v, "Ghost");
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
            this.getPosition().getX() - 25, this.getPosition().getY() - 25, 50, 50);
    if (tic == 10) {
      tic = -1;
    }
  }
}
