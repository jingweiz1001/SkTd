package map;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import Enemy.Enemy;
import Tower.Tower;
import controller.GUI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Track;
import model.User;
import model.Wave;
import resouces.Images;

/**
 * The abstract map.
 *
 * @author Jingwei Zhang
 */
public abstract class Map implements Serializable {
  private static final long serialVersionUID = 3768063483640794417L;
  private boolean isHurt = false;
  private String background;
  private String background2;
  private int size = GUI.size;
  private int waveNo;
  private ArrayList<Track> tracks = new ArrayList<>();
  private ArrayList<Wave> waves;
  private ArrayList<Enemy> enemies = new ArrayList<>();
  private ArrayList<Tower> towers = new ArrayList<>();
  private ArrayList<Point2D> blocks = new ArrayList<>();
  private User user;
  private Point2D startPoint;
  private Point2D endPoint;
  private boolean warning = false;

  /**
   * Constructs a {@link Map}.
   *
   * @param s  the start point of this map.
   * @param e  the end point of this map.
   * @param w  the waves of this map.
   * @param b  the background image of this map.
   * @param b2 the upper cover background image of this map.
   * @param u  the user.
   * @param t  all tracks of this map.
   * @param bl the points that tower cannot construct at.
   */
  public Map(Point2D s, Point2D e, ArrayList<Wave> w, String b, String b2, User u,
             ArrayList<Track> t, ArrayList<Point2D> bl) {
    waveNo = 1;
    startPoint = s;
    endPoint = e;
    waves = w;
    background = b;
    background2 = b2;
    user = u;
    tracks = t;
    blocks = bl;
  }

  /**
   * Checks whether the given point is available.
   *
   * @param x the x coordinate of the point.
   * @param y the y coordinate of the point.
   * @return {@code true} if available; {@code false} otherwise.
   */
  public boolean isAvailable(double x, double y) {
    double x1;
    double x2;
    double y1;
    double y2;
    if( ((int) y)/30*30+size/2 == 16*size-size/2)
    	return false;
    for(Tower t : towers) {
    	if(t.getPosition().getX() == (((int) x) / size * size + size / 2) && t.getPosition().getY() == (((int) y) / size * size + size / 2)) {
    		return false;
    	}
    }
    for (Point2D p : blocks) {
      if (p.getX() == (((int) x) / size * size + size / 2) && p.getY() == (((int) y) / size * size + size / 2)) {
        return false;
      }
    }

    for (Track t : tracks) {
      double[] doublelist = t.getPath();
      x1 = startPoint.getX();
      y1 = startPoint.getY();
      x2 = startPoint.getX();
      y2 = startPoint.getY();
      for (int i = 0; i < doublelist.length; i += 2) {
        if (doublelist[i] == 1) {
          x2 += doublelist[i + 1];

        } else if (doublelist[i] == 2) {
          y2 += doublelist[i + 1];

        } else if (doublelist[i] == 3) {
          x2 -= doublelist[i + 1];

        } else if (doublelist[i] == 4) {
          y2 -= doublelist[i + 1];

        }
        if ((Math.max(x1, x2) + 25 > x && Math.min(x1, x2) - 25 < x && Math.max(y1, y2) + 25 > y
                && Math.min(y1, y2) - 25 < y)) {
          return false;
        }
        x1 = x2;
        y1 = y2;
      }
    }
    return true;
  }

  /**
   * Makes all enemies move once.
   */
  public void next() {
    for (int i = 0; i < enemies.size(); i++) {
      Enemy e = enemies.get(i);
      if (e.getPosition().equals(endPoint)) {
        user.getHurt(e.getAttack());
        isHurt = true;
        enemies.remove(e);
        i--;

      } else {
        e.move();
      }
    }

    if (waves.size() != 0) {
      Wave curWave = waves.get(0);
      if (curWave.isFinish() && enemies.size() == 0) {
        if (curWave.nextRound()) {
          waves.remove(0);
          if(waves.size()!=0)
        	  curWave = waves.get(0);
          if(waveNo != 5) {
        	  waveNo++;
          }
        }
      } else {
        if (curWave.isSendable()) {
        	warning = false;
          enemies.add(curWave.send());
        }else if(enemies.size() == 0 && curWave.shouldWarn()) {
        	warning = true; 
        }
      }
    }

  }

  /**
   * Draws all things on this map.
   *
   * @param gc the first graphics context.
   * @param g2 the second graphics context.
   */
  public void draw(GraphicsContext gc, GraphicsContext g2) {
    drawEnemy(gc, g2);
    for (Tower t : towers) {
      if (!t.isInAttack()) {
        t.isInRange(enemies);
      }
      t.attack(enemies, gc, user);
    }
    gc.drawImage(Images.getUser(user), endPoint.getX() - size/2+5, endPoint.getY() - size/2,
            size, size);
  }
  /**
   * Draws all enemies on this map.
   *
   * @param gc the first graphics context.
   * @param g2 the second graphics context.
   */
  public void drawEnemy(GraphicsContext gc, GraphicsContext g2) {
    for (Enemy e : enemies) {
      e.draw(gc);
      if (e.getEffect()[0] == 1) {
        gc.drawImage(Images.getEffect(e.getFE()), 23, 121, 53, 46,
                e.getPosition().getX() - size / 2, e.getPosition().getY(), 50, 20);
      }
      gc.setFill(Color.RED);
      gc.fillRect(e.getPosition().getX() - 11, e.getPosition().getY() - 20, 22 * e.getHPercent(), 3);
    }
  }

  /**
   * Constructs a tower on this map.
   *
   * @param t the tower that should be constructed.
   */
  public void addTower(Tower t) {
    for (Tower tow : towers) {
      if (tow.getPosition().equals(t.getPosition())) {
        return;
      }
    }
    if (user.getMoney() - t.getCost() >= 0) {
      user.getPaid(t.getCost());
      towers.add(t);
    }
  }

  /**
   * Initializes this map.
   */
  abstract public void init();

  /**
   * Return the String of story.
   */
  abstract public String getStory();

  /**
   * Indicates whether this game is over.
   *
   * @return {@code true} if game over; {@code false} otherwise.
   */
  public boolean isGameOver() {
    if (waves.size() == 0 && enemies.size() == 0) {
      return true;
    }else if(user.getHealth()<=0)
    		return true;
    return false;
  }

  /**
   * Gets enemies.
   *
   * @return the enemies
   */
  public ArrayList<Enemy> getEnemies() {
    return enemies;
  }

  /**
   * Gets towers.
   *
   * @return the towers
   */
  public ArrayList<Tower> getTowers() {
    return towers;
  }

  /**
   * Gets the background image of this map.
   *
   * @return the background image of this map.
   */
  public String getImage() {
    return background;
  }

  /**
   * Gets the upper cover background image of this map.
   *
   * @return the upper cover background image of this map.
   */
  public String getImage2() {
    return background2;
  }

  /**
   * Gets the user.
   *
   * @return the user.
   */
  public User getUser() {
    return user;
  }

  /**
   * Gets the current wave number.
   *
   * @return the current wave number.
   */
  public int getWaveNum() {
    return waveNo;
  }
  
  public Point2D getEndPoint() {
	  return endPoint;
  }
  
  public boolean getWarning() {
	  return warning;
  }
  
  public boolean getIsHurt() {
	  return isHurt;
  }
  
  public void changeIsHurt() {
	  isHurt = false;
  }
}
