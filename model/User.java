package model;

import java.io.Serializable;

import Enemy.Enemy;

/**
 * The user controlled by the player.
 *
 * @author Jingwei Zhanhg
 */
public class User implements Serializable {
  private static final long serialVersionUID = -658344576345218497L;

  private int money;
  private int health;
  private int maxHealth;
  private int score;

  /**
   * Initializes this user.
   *
   * @param m the initial money of this user.
   * @param h the initial HP of this user.
   */
  public void init(int m, int h) {
    money = m;
    health = h;
    maxHealth = h;
  }

  /**
   * Deducts HP of this user.
   *
   * @param d the damage amount.
   */
  public void getHurt(int d) {
    health -= d;
  }

  /**
   * Deducts the money of this user.
   *
   * @param m the cost.
   */
  public void getPaid(int m) {
    money -= m;
  }

  /**
   * Gets rewards by killing an enemy.
   *
   * @param e the killed enemy.
   */
  public void killed(Enemy e) {
    money += e.getValue();
    score += e.getValue();
  }

  /**
   * Gets health.
   *
   * @return the health.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Gets the percent of HP.
   *
   * @return the the percent of HP.
   */
  public double getHPercent() {
    return (health * 1.0) / (maxHealth * 1.0);
  }

  /**
   * Gets score.
   *
   * @return the score
   */
  public int getScore() {
    return score;
  }

  /**
   * Gets money.
   *
   * @return the money
   */
  public int getMoney() {
    return money;
  }

}
