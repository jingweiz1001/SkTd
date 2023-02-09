package Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import Enemy.Enemy;
import controller.GUI;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.User;
import resouces.Images;
import resouces.Musics;

/**
 * The sun tower.
 *
 * @author Jingwei Zhang
 */
public class Sun extends Tower {
  private static final long serialVersionUID = 2600381719649700193L;

  private static String name = "Archmage";
  private static String fileName = "sun";
  private static int c = 300;
  private static double as = 18;
  private static double d = 12;
  private static int r = 5 * GUI.size;
  private int size = GUI.size;
  private double attackProgress;
  private String bullet = "sun";
  private double sx = 0;
  private double sy = 6;
  private double sw = 24;
  private double sh = 31;
  private int direction = 2;
  private static String des = "Circular AOE attack";

  /**
   * Constructs a {@link Sun} tower.
   *
   * @param p the position of this tower.
   */
  public Sun(Point2D p) {
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
  public void attack(ArrayList<Enemy> enemies, GraphicsContext gc, User us) {
    gc.drawImage(Images.getLevel(this.getLevel()), this.getPosition().getX() - size / 2,
            this.getPosition().getY() - size / 2 - 15, size, 15);
    ArrayList<Enemy> enemysQueue = super.getQueue();
    double damage = super.getDamage();
    attackProgress++;
    this.changeAttack(true);
    boolean ifAttack = false;
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
      for (Enemy e : enemysQueue) {
        if (e.getHealth() >= 0 && attackProgress == as) {
          e.getHurt(damage);
          ifAttack = true;
          this.changeAttack(false);
        } else {
          ImageView iv = new ImageView(Images.getBullet(bullet));
          iv.setRotate(180 * (attackProgress / as));
          SnapshotParameters params = new SnapshotParameters();
          params.setFill(Color.TRANSPARENT);
          Image rotatedImage = iv.snapshot(params, null);
          gc.drawImage(rotatedImage, this.getPosition().getX() - (r * 2.4 * ((attackProgress + 1) / as)) / 2,
                  this.getPosition().getY() - (r * 2.4 * ((attackProgress + 1) / as)) / 2,
                  r * 2.4 * ((attackProgress + 1) / as), r * 2.4 * ((attackProgress + 1) / as));
        }
      }
      for (int x = 0; x < enemysQueue.size(); x++) {
        Enemy e = enemysQueue.get(x);
        if (e.getHealth() <= 0) {
          if (enemies.contains(e) && enemysQueue.contains(e)) {
            us.killed(e);
          }
          enemies.remove(e);
          enemysQueue.remove(e);
          x--;
        }
      }

      if (ifAttack) {
        attackProgress = 0;
        ifAttack = false;
      }
    } else {
      Musics.stopAttack(this);
      this.changeAttack(false);
      attackProgress = 0;
      gc.drawImage(Images.getTower(fileName), sx + (attackProgress % (as / 3)) * sw, sy + sh * 2, sw, sh,
              this.getPosition().getX() - size / 2, this.getPosition().getY() - size / 2, size, size);
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
