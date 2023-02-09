package Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import Enemy.Enemy;
import controller.GUI;
import javafx.scene.canvas.GraphicsContext;
import model.User;
import resouces.Images;
import resouces.Musics;

/**
 * The freeze tower.
 *
 * @author Jingwei Zhang
 */
public class Freeze extends Tower {
  private static final long serialVersionUID = -7081012026777231307L;

  private static String name = "Ice Magician";
  private static String fileName = "freeze";
  private static int c = 150;
  private static double as = 6;
  private static int d = 6;
  private static int r = 3 * GUI.size;
  private int attackProgress;
  private int size;
  private String bullet = "freeze";
  private String hit = "freeze";
  private double sx = 216;
  private double sy = 6;
  private double sw = 24;
  private double sh = 31;
  private int direction = 2;
  private static String des = "Single attack with \nfreezing effect";

  /**
   * Constructs a {@link Freeze} tower.
   *
   * @param p the position of this tower.
   */
  public Freeze(Point2D p) {
    super(name, fileName, p, c, as, d, r, des);
    size = GUI.size;
  }

  /**
   * Finds an enemy and attacks it.
   *
   * @param enemies the list contains all enemies in the attack range of this tower.
   * @param gc      the graphics context.
   * @param us      the user.
   */
  @Override
  public void attack(ArrayList<Enemy> enemies, GraphicsContext gc, User us) {
    gc.drawImage(Images.getLevel(this.getLevel()), this.getPosition().getX() - size / 2,
            this.getPosition().getY() - size / 2 - 15, size, 15);
    ArrayList<Enemy> enemysQueue = super.getQueue();
    attackProgress++;
    this.changeAttack(true);
    if (!enemysQueue.isEmpty()) {
      Enemy first = enemysQueue.get(0);
      for (int i = 0; i < enemysQueue.size(); i++) {
        Enemy e3 = enemysQueue.get(i);
        first = e3;
        if (e3.getEffect()[0] == 0) {
          break;
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
      gc.drawImage(Images.getTower(fileName), sx + Math.round((attackProgress / (as / 2.4) % 3)) * sw,
              sy + sh * direction, sw, sh, this.getPosition().getX() - size / 2, this.getPosition().getY() - size / 2,
              size, size);
      if (as - attackProgress == 0) {
        gc.drawImage(Images.getHit(hit), 389, 195, 84, 84, first.getPosition().getX() - size * 0.4,
                first.getPosition().getY() - size * 0.4, size * 0.8, size * 0.8);
      }
      if (first.getHealth() <= 0) {
        if (enemies.contains(first) && enemysQueue.contains(first)) {
          us.killed(first);
        }
        enemysQueue.remove(first);
        enemies.remove(first);
      } else if (first.getHealth() > 0 && attackProgress == as) {
        first.getHurt(d);
        Musics.playAttack(this);
        this.changeAttack(false);
        first.getEffected("freeze");
      } else {
        gc.drawImage(Images.getBullet(bullet), 215, 208, 53, 53,
                (first.getPosition().getX() - this.getPosition().getX()) * (attackProgress / as)
                        + this.getPosition().getX(),
                (first.getPosition().getY() - this.getPosition().getY()) * (attackProgress / as)
                        + this.getPosition().getY(), size * 0.6, size * 0.6);
      }
    } else {
      Musics.stopAttack(this);
      this.changeAttack(false);
      gc.drawImage(Images.getTower(fileName), sx + (attackProgress % 3) * sw, sy + sh * 2, sw, sh,
              this.getPosition().getX() - size / 2, this.getPosition().getY() - size / 2, size, size);
      attackProgress = 0;
    }
    if (attackProgress == as) {
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
}
