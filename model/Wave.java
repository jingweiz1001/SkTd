package model;

import java.io.Serializable;
import java.util.ArrayList;

import Enemy.Enemy;

/**
 * The wave.
 *
 * @author Jingwei Zhang
 */
public class Wave implements Serializable {
  private static final long serialVersionUID = -2656249749759138444L;

  private ArrayList<Enemy> eList = new ArrayList<>();
  private int tic;
  private boolean finish;
  private int maxTic = 150;
  private int diffSend = 8;

  /**
   * Constructs a {@link Wave}.
   */
  public Wave() {
    tic = 0;
    finish = false;
  }

  /**
   * Adds an enemy into this wave.
   *
   * @param e the enemy.
   */
  public void add(Enemy e) {
    eList.add(e);
  }

  /**
   * Sends an enemy to the map.
   *
   * @return the enemy.
   */
  public Enemy send() {
    Enemy e = eList.get(0);
    eList.remove(e);
    return e;

  }

  /**
   * Goes to next round.
   *
   * @return {@code true} if this wave is finished; {@code false} otherwise.
   */
  public boolean nextRound() {
    if (tic == maxTic && finish) {
      return true;
    } else {
      tic++;
      return false;
    }
  }

  /**
   * Indicates whether this wave could be sent.
   *
   * @return {@code true} if it is able to send enemy; {@code false} otherwise.
   */
  public boolean isSendable() {
    if (!finish) {
      tic++;
    }
    if (tic == maxTic && eList.size() != 0) {
      tic = maxTic-diffSend;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Indicates whether this wave is finished.
   *
   * @return {@code true} if finished; {@code false} otherwise.
   */
  public boolean isFinish() {
    if (eList.size() == 0) {
      finish = true;
      return true;
    } else {
      return finish;
    }
  }
  
  public boolean shouldWarn() {
	  return (tic*0.1)/(maxTic*0.1)>0.8 && eList.size()!=0;
  }
}
