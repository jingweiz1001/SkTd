package Enemy;

import java.awt.geom.Point2D;

import javafx.scene.canvas.GraphicsContext;
import model.Track;
import resouces.Images;

/**
 * The dragon enemy.
 *
 * @author Jingwei Zhang
 */
public class Dragon extends Enemy {
  private static final long serialVersionUID = -6196350074608265746L;

  private static String fn = "dragon";
  private static double h = 200;
  private static int a = 15;
  private static double s = 2.5;
  private static int v = 15;
  private static double d = 0.5;
  private int tic = -1;
  private double sx = 0;
  private double sy = 6;
  private double sw = 24;
  private double sh = 31;

  /**
   * Constructs a {@link Dragon} enemy.
   *
   * @param diff the increase rate of difficulty.
   * @param p    the position of this enemy.
   * @param t    the track of this enemy.
   */
  public Dragon(double diff, Point2D p, Track t) {
    super(fn, p, h * diff, a, s, t, d, v, "Dragon");
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
