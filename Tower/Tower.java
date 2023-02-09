package Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import Enemy.Enemy;
import javafx.scene.canvas.GraphicsContext;
import model.Unit;
import model.User;

/**
 * The tower.
 *
 * @author Jingwei Zhang
 */
public abstract class Tower extends Unit {
  private static final long serialVersionUID = -7162423460034773266L;

  private String name;
  private int cost;
  private double attackSpeed;
  private Point2D position;
  private ArrayList<Enemy> enemiesQueue;
  private double damage;
  private int level = 1;
  private double range;
  private String des;
  private boolean inAttack = false;

  /**
   * Constructs a {@link Tower}.
   *
   * @param n        the name of this tower.
   * @param fileName the filename of the image of this tower.
   * @param p        the position of this tower.
   * @param c        the cost for purchasing this tower.
   * @param as       the attack speed of this tower.
   * @param d        the damage per attacking of this tower.
   * @param r        the attack range of this tower.
   */
  public Tower(String n, String fileName, Point2D p, int c, double as, double d, double r, String s) {
    super(fileName, p);
    name = n;
    enemiesQueue = new ArrayList<Enemy>();
    cost = c;
    attackSpeed = as;
    position = p;
    damage = d;
    range = r;
    des = s;
  }

  /**
   * Adds all enemies which are in the attack range of this tower into the {@code enemiesQueue}.
   *
   * @param enemies the list of enemies.
   */
  public void isInRange(ArrayList<Enemy> enemies) {
    for (Enemy e : enemies) {
      if (position.distance(e.getPosition()) < range && !enemiesQueue.contains(e)) {
        enemiesQueue.add(e);
      } else if (position.distance(e.getPosition()) > range) {
        enemiesQueue.remove(e);
      }
    }
  }

  /**
   * Switches attack or not attack.
   *
   * @param t {@code true} if this tower should attack; {@code false} otherwise.
   */
  public void changeAttack(boolean t) {
    inAttack = t;
  }

  /**
   * Upgrades this tower.
   *
   * @param us the user.
   */
  public void upgrade(User us) {
    us.getPaid(cost / 2);
    level++;
    range = range * 1.2;
    damage = damage * 1.2;
  }

  /**
   * Finds an enemy and attacks it.
   *
   * @param enemies the list contains all enemies in the attack range of this tower.
   * @param gc      the graphics context.
   * @param us      the user.
   */
  public abstract void attack(ArrayList<Enemy> enemies, GraphicsContext gc, User us);

  /**
   * Draws this tower at the given position.
   *
   * @param gc the graphics context.
   * @param x  the x coordinate.
   * @param y  the y coordinate.
   */
  public abstract void drawImageAt(GraphicsContext gc, int x, int y);

  /**
   * Gets the queue of enemies.
   *
   * @return the queue of enemies.
   */
  public ArrayList<Enemy> getQueue() {
    return enemiesQueue;
  }

  /**
   * Gets the attack speed of this tower.
   *
   * @return the attack speed of this tower.
   */
  public double getAS() {
    return attackSpeed;
  }

  /**
   * Gets the damage of this tower.
   *
   * @return the damage.
   */
  public double getDamage() {
    return damage;
  }

  /**
   * Gets the cost of this tower.
   *
   * @return the cost.
   */
  public int getCost() {
    return cost;
  }

  /**
   * Gets the range of this tower.
   *
   * @return the range.
   */
  public double getRange() {
    return range;
  }

  /**
   * Indicates whether this tower is attacking.
   *
   * @return {@code true} if this tower is attacking; {@code false} otherwise.
   */
  public boolean isInAttack() {
    return inAttack;
  }

  /**
   * Gets the status of this tower.
   *
   * @return the string that contains all the status of this tower.
   */
  public String getStat() {
    return  name + "\nCost: " + cost  + "\nAttack Range: " + (int) range + "\nDamage: " + (int) damage
            + "\nAttack Speed: " + (int) (30 - attackSpeed)+"\n"+des;
  }

  /**
   * Gets the level of this tower.
   *
   * @return the level.
   */
  public int getLevel() {
    return level;
  }
}