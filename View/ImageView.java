package View;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Enemy.Enemy;
import Tower.Archer;
import Tower.Canon;
import Tower.Freeze;
import Tower.Laser;
import Tower.Sun;
import Tower.Tower;
import controller.GUI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import map.Map;
import model.Unit;
import model.User;
import resouces.Images;
import resouces.Musics;

/**
 * The view of images.
 */
public class ImageView extends Canvas {
  private Map game;
  private int tic_hurt = 0;
  private int size = GUI.size;
  private GraphicsContext gc = this.getGraphicsContext2D();
  private Timeline timeline;
  private Timeline timeline2;
  private Unit selected;
  private int mouseX;
  private int mouseY;
  private Image selectedImage;
  private GraphicsContext g2;
  private boolean pause = false;
  private boolean speedy = false;
  private BorderPane pane;
  private boolean tryToBuild = false;
  private int buildType = 0;
  private MediaView mv;
  private User user;
  private long startTime;
  private long accuTime;
  private boolean storyEnd = false;
  private int tic = 0;
  private String ins = "                                    The Legend of the Mystical Carrot \n"
          + "            The mystical "
          + "carrot legend says that it came from the heavens \nabove"
          + ", nurtured by the first rays of sunlight. It is said that just one \nbite of "
          + "this carrot can cause a man to live for a thousand more years. \nThrough many "
          + "adventures and escapades, our heroes have finally \ndefeated the evil gardener "
          + "and took this mystical carrot! Currently, \nour band of heroes have just reached "
          + "Dauphin Island, whose \nvolcano is said to be the only thing that can cook the mystical "
          + "carrot. \nUnexpectedly though, the mystical carrot is attracting all the monsters \non "
          + "the island! Although the heroes are strong and magical, they need \nsomeone to organize "
          + "them. Help the heroes face off against these waves \nand they might just let you have a "
          + "bite!\n\n";
  private String manual = "Click On The Play-Button";
  private String manual2 = "to play the Game.";

  /**
   * Constructs a {@link ImageView}.
   *
   * @param g      the map.
   * @param ca     the canvas.
   * @param mapNum the number of the map.
   * @param diff   the difficulty rate.
   * @param a      the width.
   * @param b      the height.
   * @param p      the parent pane.
   * @param m      the start video.
   */
  public ImageView(Map g, Canvas ca, int mapNum, double diff, int a, int b, BorderPane p, MediaView m) {
    Musics.stopBGM();
    Musics.playMapBGM(g);
    g2 = ca.getGraphicsContext2D();
    game = g;
    ins = ins + game.getStory();
    this.setWidth(a);
    this.setHeight(b);
    pane = p;
    MouseHandler mh = new MouseHandler();
    DragHandler dh = new DragHandler();
    ca.setOnMouseClicked(mh);
    ca.setOnMouseMoved(dh);
    timeline2 = new Timeline(new KeyFrame(Duration.millis(50), new AnimateStarter2()));
    timeline2.setCycleCount(Animation.INDEFINITE);
    timeline = new Timeline(new KeyFrame(Duration.millis(50), new AnimateStarter()));
    timeline.setCycleCount(Animation.INDEFINITE);
    mv = m;
    user = game.getUser();
  }

  /**
   * Starts animation.
   */
  public void start() {
    pause = true;
    timeline2.play();
  }

  /**
   * Saves data.
   */
  public void writePer() {
    try {
      FileOutputStream fileOutput = new FileOutputStream("saveData");
      ObjectOutputStream out = new ObjectOutputStream(fileOutput);
      out.writeObject(game);

      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Draws all elements.
   */
  private void drawAll() {
    gc.drawImage(Images.getMap(game.getImage()), 0, 0, 900, 760);
    game.draw(gc, g2);
    gc.drawImage(Images.getMap(game.getImage2()), 0, 0, 900, 760);
    drawForG2();
  }

  /**
   * Draws the information at side.
   */
  private void drawForG2() {
	g2.clearRect(0, 0, 1000, 1000);
    drawButton();
    printStat();
    if (selected instanceof Enemy) {
      Enemy en = (Enemy) selected;
      g2.setFill(Color.GOLD);
      g2.fillText(en.getStat(), 903, 600, 144);
    } else if (selected instanceof Tower) {
      Tower to = (Tower) selected;
      g2.setFill(Color.GOLD);
      g2.fillText(to.getStat(), 903, 585, 144);
      g2.setStroke(Color.YELLOW);
      g2.strokeOval(to.getPosition().getX() - to.getRange(),
              to.getPosition().getY() - to.getRange(), to.getRange() * 2, to.getRange() * 2);
      g2.setStroke(Color.BLACK);
      g2.setGlobalAlpha(0.1);
      g2.setFill(Color.YELLOW);
      g2.fillOval(to.getPosition().getX() - to.getRange(),
              to.getPosition().getY() - to.getRange(), to.getRange() * 2, to.getRange() * 2);
      g2.setGlobalAlpha(1);
      //if satisfy upgrade
      if (to.getLevel() < 3 && user.getMoney() > to.getCost() / 2) {
        g2.drawImage(Images.upgrade1, to.getPosition().getX() + size, to.getPosition().getY() - size / 2, size, size);
      } else {
        g2.drawImage(Images.upgrade2, to.getPosition().getX() + size, to.getPosition().getY() - size / 2, size, size);
      }
      g2.drawImage(Images.cancel, to.getPosition().getX() - size * 2, to.getPosition().getY() - size / 2, size, size);
      g2.setFill(Color.BLACK);
    }
    if (tryToBuild) {
      Tower t;
      if (selectedImage.equals(Images.archer_button)) {
        t = new Archer(new Double());
      } else if (selectedImage.equals(Images.sun_button)) {
        t = new Sun(new Double());
      } else if (selectedImage.equals(Images.laser_button)) {
        t = new Laser(new Double());
      } else if (selectedImage.equals(Images.freeze_button)) {
        t = new Freeze(new Double());
      } else {
        t = new Canon(new Double());
      }
      if (game.isAvailable(mouseX, mouseY) && mouseX < 900 && storyEnd) {
        g2.setStroke(Color.YELLOW);
        g2.strokeOval(Math.round(mouseX) / size * size - t.getRange() + 0.5 * size,
                Math.round(mouseY) / size * size - t.getRange() + 0.5 * size, t.getRange() * 2, t.getRange() * 2);
        g2.setStroke(Color.BLACK);
        g2.setGlobalAlpha(0.1);
        g2.setFill(Color.YELLOW);
        g2.fillOval(Math.round(mouseX) / size * size - t.getRange() + 0.5 * size,
                Math.round(mouseY) / size * size - t.getRange() + 0.5 * size, t.getRange() * 2, t.getRange() * 2);
        g2.setGlobalAlpha(0.3);
        g2.setFill(Color.GREEN);
        g2.fillRect(Math.round(mouseX) / size * size, Math.round(mouseY) / size * size, size, size);
        g2.setGlobalAlpha(1);
        t.drawImageAt(g2, Math.round(mouseX) / size * size, Math.round(mouseY) / size * size);
        g2.setFill(Color.BLACK);
      } else if (mouseX < 900 && storyEnd) {
        t.drawImageAt(g2, mouseX - size / 2, mouseY - size / 2);
      } else {
        g2.setFill(Color.GOLD);
        g2.fillText(t.getStat(), 903, 585, 144);
      }
    }

  }

  /**
   * Draws buttons.
   */
  private void drawButton() {
    g2.drawImage(Images.statFrame, 898, 0, 153, 760);
    g2.drawImage(Images.emptyFrame, 897, 550, 151, 200);
    g2.drawImage(Images.emptyFrame, 903, 120, 143, 145);
    g2.drawImage(Images.timeBG, 903, 480, 125, 50);
    if (!pause) {
      if (mouseX > 910 && mouseX < 960 && mouseY > 10 && mouseY < 60) {
        g2.drawImage(Images.stop, 915, 15, 40, 40);
      } else {
        g2.drawImage(Images.stop, 910, 10, 50, 50);
      }
    } else {
      if (mouseX > 910 && mouseX < 960 && mouseY > 10 && mouseY < 60) {
        g2.drawImage(Images.resume, 915, 15, 40, 40);
      } else {
        g2.drawImage(Images.resume, 910, 5, 50, 53);
      }
    }
    if (!speedy) {
      if (mouseX > 910 && mouseX < 960 && mouseY > 65 && mouseY < 115) {
        g2.drawImage(Images.fast, 915, 70, 40, 40);
      } else {
        g2.drawImage(Images.fast, 910, 65, 50, 50);
      }
    } else {
      if (mouseX > 910 && mouseX < 960 && mouseY > 65 && mouseY < 115) {
        g2.drawImage(Images.slow, 915, 70, 40, 40);
      } else {
        g2.drawImage(Images.slow, 910, 65, 50, 50);
      }
    }

    if (mouseX > 970 && mouseX < 1020 && mouseY > 65 && mouseY < 115) {
      g2.drawImage(Images.save, 975, 70, 40, 40);
    } else {
      g2.drawImage(Images.save, 970, 65, 50, 50);
    }
    if (mouseX > 970 && mouseX < 1020 && mouseY > 7 && mouseY < 57) {
      g2.drawImage(Images.back, 975, 14, 40, 40);
    } else {
      g2.drawImage(Images.back, 970, 9, 50, 50);
    }

    if (mouseX > 910 && mouseX < 960 && mouseY > 270 && mouseY < 320) {
      g2.drawImage(Images.archer_button, 915, 275, 40, 40);
    } else {
      g2.drawImage(Images.archer_button, 910, 270, 50, 50);
    }
    if (mouseX > 970 && mouseX < 1020 && mouseY > 270 && mouseY < 320) {
      g2.drawImage(Images.sun_button, 975, 275, 40, 40);
    } else {
      g2.drawImage(Images.sun_button, 970, 270, 50, 50);
    }
    if (mouseX > 910 && mouseX < 960 && mouseY > 340 && mouseY < 390) {
      g2.drawImage(Images.laser_button, 915, 345, 40, 40);
    } else {
      g2.drawImage(Images.laser_button, 910, 340, 50, 50);
    }
    if (mouseX > 970 && mouseX < 1020 && mouseY > 340 && mouseY < 390) {
      g2.drawImage(Images.freeze_button, 975, 345, 40, 40);
    } else {
      g2.drawImage(Images.freeze_button, 970, 340, 50, 50);
    }
    if (mouseX > 910 && mouseX < 960 && mouseY > 410 && mouseY < 460) {
      g2.drawImage(Images.canon_button, 915, 415, 40, 40);
    } else {
      g2.drawImage(Images.canon_button, 910, 410, 50, 50);
    }
  }

  /**
   * Draws status.
   */
  public void printStat() {
    String text = "     WAVE\n      " + game.getWaveNum() + " / 5\nScore: " + user.getScore() + "\nMoney: "
            + user.getMoney() + "\nHealth: " + (int) (user.getHPercent() * 100);
    Font fontSmall = Font.font("Cooper Black", FontWeight.BOLD, 20);
    g2.setFont(fontSmall);
    g2.setFill(Color.GOLD);
    g2.fillText(text, 910, 150, 140);
  }

  /**
   * The {@link EventHandler<ActionEvent>}.
   */
  private class AnimateStarter implements EventHandler<ActionEvent> {
    /**
     * Invoked when a specific event of the type for which this handler is registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
      if (game.isGameOver()) {
        pause = true;
        timeline.pause();
        timeline2.play();
      } else {
        if (!pause) {
          game.next();
        }
        drawAll();
        if (game.getWarning()) {
          gc.drawImage(Images.getWarn(), 180, 200, 600, 250);
        }
        long endTime = System.currentTimeMillis();
        long curTime = endTime - startTime;
        int time = Math.round((accuTime + curTime) / 1000);
        int m = time / 60;
        g2.setFill(Color.GOLD);
        g2.fillText("Time: " + m + " : " + (time - 60 * m), 910, 510);
        if(game.getIsHurt()) {
        	tic_hurt++;
            g2.drawImage(Images.hurt, game.getEndPoint().getX()-size/2, game.getEndPoint().getY()-size/2, size, size);
            if(tic_hurt == 8) {
            	game.changeIsHurt();
            	tic_hurt = 0;
            }
        }

      }
    }
  }

  /**
   * The {@link EventHandler<ActionEvent>}.
   */
  private class AnimateStarter2 implements EventHandler<ActionEvent> {
    /**
     * Invoked when a specific event of the type for which this handler is registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
      drawForG2();
      Point2D ep = game.getEndPoint();
      gc.drawImage(Images.getMap(game.getImage()), 0, 0, 900, 760);
      game.drawEnemy(gc, g2);
      gc.drawImage(Images.getMap(game.getImage2()), 0, 0, 900, 760);
      gc.drawImage(Images.getUser(user), ep.getX() - size/2+5, ep.getY() - size/2,
              size, size);
      int time = Math.round((accuTime) / 1000);
      int m = time / 60;
      g2.setFill(Color.GOLD);
      g2.fillText("Time: " + m + " : " + (time - 60 * m), 910, 510);
      for (Tower t1 : game.getTowers()) {
        t1.drawImageAt(g2, (int) t1.getPosition().getX() - size / 2, (int) t1.getPosition().getY() - size / 2);
      }
      if (!storyEnd) {
        g2.setGlobalAlpha(0.9);
        g2.setFill(Color.BLACK);
        g2.fillRect(0, 0, 900, 770);
        g2.setGlobalAlpha(1);
        g2.setFill(Color.WHITE);
        if (tic * 4 >= ins.length() - 4) {
          g2.fillText(ins, 100, 100);
          Font fontLarge = Font.font("Comic Sans MS", FontWeight.BOLD, 25);
          g2.setFont(fontLarge);
          g2.setFill(Color.GOLD);
          g2.fillText(manual, 100, 600);
          g2.drawImage(Images.resume, 425, 565, 40, 40);
          g2.fillText(manual2, 480, 600);
        } else {
          tic++;
          g2.fillText(ins.substring(0, tic * 2), 100, 100);
        }

      }
      if (game.isGameOver()) {
        Font fontLarge = Font.font("Comic Sans MS", FontWeight.BOLD, 100);
        gc.setFont(fontLarge);
        gc.setFill(Color.WHITE);
        if (user.getHealth() >= 0) {
          gc.setFill(Color.YELLOW);
          gc.fillText("You Win!", 300, 300);
        } else {
          gc.setFill(Color.GOLD);
          gc.fillText("You Lose.", 300, 300);
        }
      }
      if(game.getIsHurt()) {
          g2.drawImage(Images.hurt, game.getEndPoint().getX()-size/2, game.getEndPoint().getY()-size/2, size, size);
      }
    }
  }

  /**
   * The {@link EventHandler<MouseEvent>} for the user's mouse.
   */
  private class MouseHandler implements EventHandler<MouseEvent> {
    /**
     * Invoked when a specific event of the type for which this handler is registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(MouseEvent event) {
      int x = (int) event.getX();
      int y = (int) event.getY();
      if (selected instanceof Tower) {
        Tower t = (Tower) selected;
        if (x > t.getPosition().getX() + size && x < t.getPosition().getX() + size * 2
                && y > t.getPosition().getY() - size / 2 && y < t.getPosition().getY() + size / 2) {
          if (t.getLevel() < 3 && user.getMoney() > t.getCost() / 2) {
            t.upgrade(game.getUser());
          }
        } else if (x > t.getPosition().getX() - size * 2 && x < t.getPosition().getX() - size
                && y > t.getPosition().getY() - size / 2 && y < t.getPosition().getY() + size / 2) {
          game.getTowers().remove(t);
        }
      }
      selected = null;
      if (x > 900) {
        if (x > 910 && x < 960 && y > 10 && y < 60) {
          if (!pause) {
            timeline.pause();
            Musics.stopAllAttack();
            timeline2.play();
            pause = true;
            accuTime = accuTime + System.currentTimeMillis() - startTime;
          } else {
            storyEnd = true;
            startTime = System.currentTimeMillis();
            timeline.play();
            timeline2.pause();
            pause = false;
          }
        } else if (x > 910 && x < 960 && y > 65 && y < 115 && storyEnd) {
          if (!speedy) {
            timeline.stop();
            timeline.getKeyFrames().setAll(new KeyFrame(Duration.millis(25), new AnimateStarter()));
            speedy = true;
          } else {
            timeline.stop();
            timeline.getKeyFrames().setAll(new KeyFrame(Duration.millis(50), new AnimateStarter()));
            speedy = false;
          }
          if (!pause) {
            timeline.play();
          }
        } else if (mouseX > 970 && mouseX < 1020 && mouseY > 65 && mouseY < 115 && storyEnd) {
          writePer();
        } else if (x > 970 && x < 1020 && y > 7 && y < 57) {
          timeline.stop();
          Musics.stopMapBGM(game);
          pane.setCenter(new Cover(1050, 760, pane, mv));
        } else if (x > 910 && x < 960 && y > 270 && y < 320) {
          tryToBuild = true;
          buildType = 1;
          selectedImage = Images.archer_button;
        } else if (x > 970 && x < 1020 && y > 270 && y < 320) {
          tryToBuild = true;
          buildType = 2;
          selectedImage = Images.sun_button;

        } else if (x > 910 && x < 960 && y > 340 && y < 390) {
          tryToBuild = true;
          buildType = 3;
          selectedImage = Images.laser_button;

        } else if (x > 970 && x < 1020 && y > 340 && y < 390) {
          tryToBuild = true;
          buildType = 4;
          selectedImage = Images.freeze_button;

        } else if (x > 910 && x < 960 && y > 410 && y < 460) {
          tryToBuild = true;
          buildType = 5;
          selectedImage = Images.canon_button;
        } else {
          tryToBuild = false;
        }
      } else if (x < 900 && storyEnd) {
        if (tryToBuild) {
          Tower tow;
          if (game.isAvailable(x, y) && tryToBuild) {
            tryToBuild = false;
            switch (buildType) {
              case 1:
                tow = new Archer(new Double(x / size * size + size / 2, y / size * size + size / 2));
                game.addTower(tow);
                break;
              case 2:
                tow = new Sun(new Double(x / size * size + size / 2, y / size * size + size / 2));
                game.addTower(tow);
                break;
              case 3:
                tow = new Laser(new Double(x / size * size + size / 2, y / size * size + size / 2));
                game.addTower(tow);
                break;
              case 4:
                tow = new Freeze(new Double(x / size * size + size / 2, y / size * size + size / 2));
                game.addTower(tow);
                break;
              case 5:
                tow = new Canon(new Double(x / size * size + size / 2, y / size * size + size / 2));
                game.addTower(tow);
              default:
            }

          } else {
            tryToBuild = false;
          }
        } else {
          Point2D m = new Double(x, y);
          if (game.getEnemies().size() != 0 || game.getTowers().size() != 0) {
            Unit cur;
            if (game.getEnemies().size() != 0) {
              cur = game.getEnemies().get(0);
            } else {
              cur = game.getTowers().get(0);
            }
            for (Enemy enemy : game.getEnemies()) {
              if (cur.getPosition().distance(m) > enemy.getPosition().distance(m)) {
                cur = enemy;
              }
            }
            for (Tower t : game.getTowers()) {
              if (cur.getPosition().distance(m) > t.getPosition().distance(m)) {
                cur = t;
              }
            }
            if (m.distance(cur.getPosition()) < 30) {
              selected = cur;
            }
            drawForG2();
          }
        }
      }
    }
  }

  /**
   * The {@link EventHandler<MouseEvent>} for user when he drags something.
   */
  private class DragHandler implements EventHandler<MouseEvent> {
    /**
     * Invoked when a specific event of the type for which this handler is registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(MouseEvent event) {
      mouseX = (int) event.getX();
      mouseY = (int) event.getY();
    }
  }

}
