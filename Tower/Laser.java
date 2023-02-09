package Tower;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import Enemy.Enemy;
import controller.GUI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import model.User;
import resouces.Images;
import resouces.Musics;

/**
 * The laser tower.
 *
 * @author Jingwei Zhang
 */
public class Laser extends Tower {
  private static final long serialVersionUID = 2667940371255702624L;

  private static String name = "Lighting Shaman";
  private static String fileName = "laser";
  private static int c = 350;
  private static double as = 1;
  private static int d = 1;
  private static int r = GUI.size * 3;
  private int size = GUI.size;
  private int attackProgress;
  private String bullet = "laser";
  private String hit = "laser";
  private double tic = 18;
  private double sx = 216;
  private double sy = 6;
  private double sw = 24;
  private double sh = 31;
  private int direction = 2;
  private static String des = "Constant partial \nAOE attack";

  /**
   * Constructs a {@link Laser} tower.
   *
   * @param p the position of this tower.
   */
  public Laser(Point2D p) {
    super(name, fileName, p, c, as, d, r, des);
  }

  /**
   * Finds an enemy and attacks it.
   *
   * @param enemies the list contains all enemies in the attack range of this tower.
   * @param gc      the graphics context.
   * @param us      the user.
   */
  @Override
  public void attack(ArrayList<Enemy> enemys, GraphicsContext gc, User us) {
    gc.drawImage(Images.getLevel(this.getLevel()), this.getPosition().getX() - size / 2,
            this.getPosition().getY() - size / 2 - 15, size, 15);
    ArrayList<Enemy> enemysQueue = super.getQueue();
    attackProgress++;
    if (attackProgress == 18) {
      attackProgress = 0;
    }
    this.changeAttack(true);
    if (!enemysQueue.isEmpty()) {

      Enemy first = enemysQueue.get(0);
      double xd = first.getPosition().getX() - this.getPosition().getX();
      double yd = first.getPosition().getY() - this.getPosition().getY();

      drawBullet(first, gc);
      Musics.playAttack(this);
      boolean canAttack = false;

      for (int a = 0; a < enemysQueue.size(); a++) {
        Enemy e1 = enemysQueue.get(a);
        for (int b = 10; b < 55; b++) {
          if (e1.getPosition().distance(new Double(this.getPosition().getX() + b * xd / size - size * 0.3,
                  this.getPosition().getY() + b * yd / size - size * 0.3)) < size) {
            canAttack = true;
            break;
          }
        }
        if (canAttack) {
          this.changeAttack(false);
          e1.getHurt(this.getDamage());
          gc.drawImage(Images.getHit(hit), 0 + 96 * Math.round((attackProgress / (tic / 3.4) % 4)), 292, 88, 71,
                  e1.getPosition().getX() - size * 0.5, e1.getPosition().getY() - size * 0.2, size, size * 0.6);
          canAttack = false;
        }
      }


      if (Math.abs(first.getPosition().getX() - this.getPosition().getX()) > Math.abs(
              first.getPosition().getY() - this.getPosition().getY())) {
        if (first.getPosition().getX() - this.getPosition().getX() > 0) {
          direction = 1;
        } else {
          direction = 3;
        }
      } else if (Math.abs(first.getPosition().getX() - this.getPosition().getX()) < Math.abs(
              first.getPosition().getY() - this.getPosition().getY())) {
        if (first.getPosition().getY() - this.getPosition().getY() > 0) {
          direction = 2;
        } else {
          direction = 0;

        }
      }

      gc.drawImage(Images.getTower(fileName), sx + Math.round((attackProgress / (tic / 2.4) % 3)) * sw, sy + sh * direction, sw,
              sh, this.getPosition().getX() - size / 2, this.getPosition().getY() - size / 2, size, size);
      for (int c = 0; c < enemysQueue.size(); c++) {
        Enemy e2 = enemysQueue.get(c);
        if (e2.getHealth() <= 0) {
          if (enemys.contains(e2) && enemysQueue.contains(e2)) {
            us.killed(e2);
          }
          c--;
          enemysQueue.remove(e2);
          enemys.remove(e2);
        }
      }
    } else {
      Musics.stopAttack(this);
      this.changeAttack(false);
      gc.drawImage(Images.getTower(fileName), sx + (attackProgress % 3) * sw, sy + sh * 2, sw, sh,
              this.getPosition().getX() - size / 2, this.getPosition().getY() - size / 2, size, size);
      attackProgress = 0;
    }


  }

  /**
   * Draws this tower at the given position.
   *
   * @param gc the graphics context.
   * @param x  the x coordinate.
   * @param y  the y coordinate.
   */
  @Override
  public void drawImageAt(GraphicsContext gc, int x, int y) {
    gc.drawImage(Images.getLevel(this.getLevel()), x, y - 15, size, 15);
    gc.drawImage(Images.getTower(fileName), sx + sw, sy + 2 * sh, sw, sh, x, y, size, size);
  }

  /**
   * Draws the bullet of this tower.
   *
   * @param e  the target enemy.
   * @param gc the graphics context.
   */
  private void drawBullet(Enemy e, GraphicsContext gc) {
    Point2D ep = e.getPosition();
    Point2D tp = this.getPosition();
    gc.save();
    double length = Math.sqrt(Math.abs(ep.getX() - tp.getX()) * Math.abs(ep.getX() - tp.getX())
            + Math.abs(ep.getY() - tp.getY()) * Math.abs(ep.getY() - tp.getY()));
    double midX = (ep.getX() + tp.getX()) / 2;
    double midY = (ep.getY() + tp.getY()) / 2;
    Rotate r = new Rotate(-Math.toDegrees(Math.atan((ep.getX() - tp.getX()) / (ep.getY() - tp.getY()))), midX, midY);
    gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    gc.drawImage(Images.getBullet(bullet), midX - 50, midY - length / 2, 100, length);
    gc.restore();
  }
}
