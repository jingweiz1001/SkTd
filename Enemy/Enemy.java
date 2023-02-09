package Enemy;

import java.awt.geom.Point2D;

import javafx.scene.canvas.GraphicsContext;
import model.Track;
import model.Unit;

/**
 * The abstract enemy.
 *
 * @author Jingwei Zhang
 */
public abstract class Enemy extends Unit {
  private static final long serialVersionUID = -7900452772321152418L;

  private double health;
  private int attack;
  private double maxHealth;
  private double speed;
  private int direction;
  private double[] path;
  private double defense;
  private int value;
  private String name;
  private int[] effects = {0, 0};
  private String freezeE = "freeze";

  /**
   * Constructs a {@link Enemy} object.
   *
   * @param fn the filename of the image of this enemy.
   * @param p  the position that this enemy at.
   * @param h  the max health of this enemy.
   * @param a  the damage for dealing to the user when this enemy reaches the end point.
   * @param s  the speed of this enemy.
   * @param t  the track.
   * @param d  the defense of this enemy.
   * @param v  the gold amount carried by this enemy.
   * @param n  the name of this enemy.
   */
  public Enemy(String fn, Point2D p, double h, int a, double s, Track t, double d, int v, String n) {
    super(fn, p);
    health = h;
    maxHealth = h;
    attack = a;
    speed = s;
    path = t.getPath().clone();
    defense = d;
    value = v;
    name = n;
  }

  /**
   * Makes this enemy move once.
   */
  public void move() {
    checkEffect();

    int index = 1;
    Point2D p = this.getPosition();
    while (path[index] == 0) {
      if (path.length > index + 2) {
        index += 2;
      } else {
        break;
      }
    }
    direction = (int) path[index - 1];
    path[index] -= speed;
    if (direction == 4) {
      p.setLocation(p.getX(), p.getY() - speed);
    } else if (direction == 2) {
      p.setLocation(p.getX(), p.getY() + speed);
    } else if (direction == 1) {
      p.setLocation(p.getX() + speed, p.getY());
    } else if (direction == 3) {
      p.setLocation(p.getX() - speed, p.getY());
    }
  }

  /**
   * Checks the effect of this enemy.
   */
  public void checkEffect() {
    if (effects[0] == 1 && effects[1] != 0) {
      effects[1]--;
    } else if (effects[0] == 1 && effects[1] == 0) {
      effects[0] = 0;
      speed = speed * 2;
    }

  }

  /**
   * Deducts the HP of this enemy by {@code d}.
   *
   * @param d the deduction amount.
   */
  public void getHurt(double d) {
    health -= d;
  }

  /**
   * Affects the effect on this enemy.
   *
   * @param e the effect.
   */
  public void getEffected(String e) {
    if (e.equals("freeze")) {
      if (effects[0] == 0) {
        effects[0] = 1;
        effects[1] = 100;
        speed = speed / 2;
      }
    }
  }

  /**
   * Draws this enemy on the graphics context.
   *
   * @param gc the graphics context.
   */
  public abstract void draw(GraphicsContext gc);

  /**
   * Gets the damage amount of this enemy.
   *
   * @return the damage amount of this enemy.
   */
  public int getAttack() {
    return attack;
  }

  /**
   * Gets the current HP of this enemy.
   *
   * @return the current HP of this enemy.
   */
  public double getHealth() {
    return health;
  }

  /**
   * Gets the percent of the HP.
   *
   * @return the percent HP.
   */
  public double getHPercent() {
    return (health * 1.0) / (maxHealth * 1.0);
  }

  /**
   * Gets the direction that this enemy faces to.
   *
   * @return the direction that this enemy faces to.
   */
  public int getDirection() {
    return direction;
  }

  /**
   * Gets the status of this enemy.
   *
   * @return the status of this enemy.
   */
  public String getStat() {
    return  name + "\nHealth: " + (int) health + " / " + (int) maxHealth + "\nDefense: " +(int) (defense * 100) + "\nDamage: "
            + attack + "\nSpeed: " +(int) speed + "\nValue: " + value;
  }

  /**
   * Get the value of this enemy.
   *
   * @return the value of this enemy.
   */
  public int getValue() {
    return value;
  }

  /**
   * Gets the effect of this enemy.
   *
   * @return the effect of this enemy.
   */
  public int[] getEffect() {
    return effects;
  }

  /**
   * Gets the image of freeze effect.
   *
   * @return the image of freeze effect.
   */
  public String getFE() {
    return freezeE;
  }
}
