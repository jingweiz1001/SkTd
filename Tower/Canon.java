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
 * The canon tower.
 *
 * @author Jingwei Zhang
 */
public class Canon extends Tower {
  private static final long serialVersionUID = -5724512529424033627L;

  private static String name = "Gunner";
  private static String fileName = "canon";
  private static int c = 250;
  private static double as = 20;
  private static int d = 40;
  private static int r = 3 * GUI.size;
  private int attackProgress;
  private int size;
  private String bullet = "canon";
  private String hit = "canon";
  private double sx = 72;
  private double sy = 6;
  private double sw = 24;
  private double sh = 31;
  private int direction = 2;
  private static String des = "Partial AOE attack";


  /**
   * Constructs a {@link Canon} tower.
   *
   * @param p the position of this tower.
   */
  public Canon(Point2D p) {
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
      if (as - attackProgress == 5) {
        Musics.playAttack(this);
      }
      gc.drawImage(Images.getTower(fileName), sx + Math.round((attackProgress / (as / 2.4) % 3)) * sw,
              sy + sh * direction, sw, sh, this.getPosition().getX() - size / 2, this.getPosition().getY() - size / 2,
              size, size);
      if (first.getHealth() > 0 && attackProgress == as) {
        first.getHurt(d);
        this.changeAttack(false);
        for (Enemy e : enemysQueue) {
          if (e != first && e.getPosition().distance(first.getPosition()) < 50) {
            e.getHurt(d);
          }
        }

      } else if (attackProgress > 14 && attackProgress <= 16) {
        gc.drawImage(Images.getHit(hit), 200, 6, 95, 79, first.getPosition().getX() - 50,
                first.getPosition().getY() - 50, 100, 100);
      } else if (attackProgress > 16 && attackProgress <= 18) {
        gc.drawImage(Images.getHit(hit), 290, 6, 95, 79, first.getPosition().getX() - 50,
                first.getPosition().getY() - 50, 100, 100);
      } else if (attackProgress > 18 && attackProgress <= 20) {
        gc.drawImage(Images.getHit(hit), 386, 6, 95, 79, first.getPosition().getX() - 50,
                first.getPosition().getY() - 50, 100, 100);
      } else {
        if (attackProgress <= 14) {
          gc.drawImage(Images.getBullet(bullet), 118, 24, 52, 52,
                  (first.getPosition().getX() - this.getPosition().getX()) * (attackProgress / as)
                          + this.getPosition().getX() - 25,
                  (first.getPosition().getY() - this.getPosition().getY()) * (attackProgress / as)
                          + this.getPosition().getY() - 25, 50, 50);
        }
      }
    } else {
      Musics.stopAttack(this);
      this.changeAttack(false);
      gc.drawImage(Images.getTower(fileName), sx + (attackProgress % 3) * sw, sy + sh * 2, sw, sh,
              this.getPosition().getX() - size / 2, this.getPosition().getY() - size / 2, size, size);
      attackProgress = 0;
    }
    for (int i = 0; i < enemysQueue.size(); i++) {
      Enemy e = enemysQueue.get(i);
      if (e.getHealth() <= 0) {
        if (enemies.contains(e) && enemysQueue.contains(e)) {
          us.killed(e);
        }
        enemysQueue.remove(e);
        enemies.remove(e);
        i--;
      }
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
