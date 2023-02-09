package View;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import resouces.Images;
import resouces.Musics;

/**
 * The cover of this game.
 *
 * @author Jingwei Zhang
 */
public class Cover extends Canvas {
  private int option;
  private GraphicsContext gc = this.getGraphicsContext2D();
  private Image title = new Image("file:images/title.png");
  private ArrayList<Image> fires = new ArrayList<>();
  private BorderPane pane;
  private MediaView mv;
  private Map game;
  private int x;
  private int y;
  private int tic = 0;
  private boolean insOpen;
  private Timeline timeline = new Timeline();
  private int page = 1;
  private String ins = "            The goal of SK TD is to protect the carrot from "
          + "losing all its health. \nTo do so, you must destroy all the monsters"
          + " before they reach it. This instructions \nmanual will teach you the tools "
          + "to do so. Starting a game: When you click on the \nNew Game option, "
          + "you will be presented with three options representing easy, medium,\n and hard."
          + " Choose your appropriate difficulty. On the map screen, the maps \navailable for"
          + " you to choose are represented by bright red circles. Hovering over those \ncircles"
          + " will show which map it is. Click on the icon for the map you choose \nto start "
          + "the game.\n\n            The Basics: On the right side is the menu bar, with 4 buttons "
          + "on the top \nbeing play, home, fast forward and save. Clicking play will start "
          + "the game. Clicking it \nagain will pause it. Home will take you back to the menu "
          + "screen. Fast forward will \nspeed up the game. Saving will save the state of the game "
          + "so you can load it \nfrom the main menu if you decide to take a break. When you"
          + " first start a game, it \nautomatically starts paused in order to give you enough "
          + "time to prepare for the \noncoming waves. The big chunk of the window is dedicated to "
          + "the map, which shows \nthe paths the enemies will take and let you place towers on it."
          + " The carrot is \nwhat you are trying to prevent the monsters from reaching. The carrot "
          + "starts with \n";
  private String ins2 = "100 health and will lose various amounts of health depending on the types "
          + "of enemy. \nClicking on an enemy on the map will show you the information about the enemy "
          + "in at \nthe bottom of the menu bar.Further down are the 5 towers. Hovering over the \ntowers "
          + "will display information about the towers at the bottom of the menu bar. This \ninformation"
          + " includes attack damage, attack speed, name, and cost. To buy a tower, \nmake sure you "
          + "have enough gold, then click on the tower you wish to purchase. Then \nmove your mouse "
          + "to the map and choose a valid location (the background of that \nlocation will turn green)."
          + " Click again on that square to place the turret down. \nYou can upgrade or remove "
          + "towers by clicking on a tower placed on the map. The \nnumber of stars shows how upgraded "
          + "the tower is, one being the most basic to three \nbeing the highest. Clicking on a tower "
          + "also shows the range of the tower. Left of \nthe tower is a red X, representing to scrap the"
          + " tower. Clicking the X will remove the \ntower from the map, but does not refund any gold. "
          + "Clicking the green arrow on the \nright will let you upgrade the tower, if you have enough "
          + "gold that is. You can check \nthat tower�s statistics at the bottom of the menu bar.";

  /**
   * Constructs a {@link Cover}.
   *
   * @param w width.
   * @param h height.
   * @param p the main pane.
   * @param m the start video.
   */
  public Cover(int w, int h, BorderPane p, MediaView m) {
    fires.add(new Image("file:images/fire1.png"));
    fires.add(new Image("file:images/fire2.png"));
    fires.add(new Image("file:images/fire5.png"));
    fires.add(new Image("file:images/fire3.png"));
    fires.add(new Image("file:images/fire4.png"));
    pane = p;
    this.setWidth(w);
    this.setHeight(h);
    MouseHandler mh = new MouseHandler();
    this.setOnMouseClicked(mh);
    mv = m;
    pane.getChildren().add(mv);
    gc.drawImage(title, 168, 0, 1050, 400, 200, 80, 700, 230);
    Font fontLarge = Font.font("Comic Sans MS", FontWeight.BOLD, 20);
    gc.setFont(fontLarge);
    gc.setFill(Color.WHITE);
    gc.fillText("START GAME", 470, 450);
    gc.fillText("LOAD GAME", 475, 500);
    gc.fillText("INSTRUCTIONS", 455, 550);
    gc.fillText("EXIT GAME", 475, 600);
    this.setOnMouseMoved(new DragHandler());
    timeline = new Timeline(new KeyFrame(Duration.millis(50), new AnimateStarter()));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    Musics.playBGM();
  }

  /**
   * Sets the options.
   */
  public void setOption() {
    timeline.stop();
    switch (option) {
      case 1:
        pane.setCenter(new DifficultyView(1050, 750, pane, mv, fires));
        break;
      case 2:
        break;
      case 3:
      default:
    }
  }

  /**
   * Reads saved data.
   */
  private void readPer() {
    try {
      FileInputStream fileOutput = new FileInputStream("saveData");
      ObjectInputStream in = new ObjectInputStream(fileOutput);
      game = (Map) in.readObject();
      in.close();
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * The {@link EventHandler<ActionEvent>} of the start buttons.
   */
  private class AnimateStarter implements EventHandler<ActionEvent> {
    /**
     * Invoked when a specific event of the type for which this handler is registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
      tic++;
      gc.clearRect(0, 0, 1050, 750);
      gc.drawImage(title, 168, 0, 1050, 400, 200, 80, 700, 230);
      if (x > 470 && x < 605 && y > 434 && y < 452) {
        gc.drawImage(fires.get(tic / 3 % 5), 19, 150, 548, 280, 455, 428, 168, 30);
      } else if (x > 475 && x < 594 && y > 584 && y < 602) {
        gc.drawImage(fires.get(tic / 3 % 5), 19, 150, 548, 280, 460, 578, 150, 30);
      } else if (x > 474 && x < 599 && y > 485 && y < 501) {
        gc.drawImage(fires.get(tic / 3 % 5), 19, 150, 548, 280, 455, 478, 168, 30);
      } else if (x > 455 && x < 621 && y > 530 && y < 551) {
        gc.drawImage(fires.get(tic / 3 % 5), 19, 150, 548, 280, 440, 530, 200, 30);
      }
      Font fontSmall = Font.font("Comic Sans MS", FontWeight.BOLD, 15);
      gc.setFill(Color.WHITE);
      gc.setFont(fontSmall);
      gc.fillText("Video Copyright: Ori and the Blind Forest, Moon Studios.\nUsed for this project only.", 5, 720);
      Font fontLarge = Font.font("Comic Sans MS", FontWeight.BOLD, 20);
      gc.setFont(fontLarge);
      gc.setFill(Color.WHITE);
      gc.fillText("START GAME", 470, 450);
      gc.fillText("LOAD GAME", 475, 500);
      gc.fillText("INSTRUCTIONS", 455, 550);
      gc.fillText("EXIT GAME", 475, 600);
      if (tic == 20) {
        tic = 0;
      }
      if (insOpen) {
        if (page == 1) {
          gc.drawImage(Images.paper, 65, 50, 920, 620);
          gc.setFill(Color.BLACK);
          gc.fillText(ins, 100, 100);
        } else {
          gc.drawImage(Images.paper, 65, 50, 920, 620);
          gc.setFill(Color.BLACK);
          gc.fillText(ins2, 100, 100);
        }
        if (page == 1 && !(x > 875 && x < 933 && y > 617 && y < 633)) {
          gc.fillText("NEXT", 875, 635);
        } else if (page == 1) {
          Font f1 = Font.font("Comic Sans MS", FontWeight.BOLD, 15);
          gc.setFont(f1);
          gc.fillText("NEXT", 880, 635);
        }
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
     * @param e the event which occurred
     */
    @Override
    public void handle(MouseEvent e) {
      double x = e.getX();
      double y = e.getY();
      if (x > 470 && x < 605 && y > 434 && y < 452) {
        option = 1;
        insOpen = false;
      } else if (x > 475 && x < 594 && y > 584 && y < 602) {
        Platform.exit();
        insOpen = false;
      } else if (x > 474 && x < 599 && y > 485 && y < 501) {
        readPer();
        Canvas ca = new Canvas(1050, 750);
        ImageView view = new ImageView(game, ca, 1, 1, 900, 750, pane, mv);
        pane.getChildren().clear();
        pane.getChildren().add(view);
        pane.getChildren().add(ca);
        view.start();
        insOpen = false;
      } else if (insOpen && page == 1 && (x > 875 && x < 933 && y > 617 && y < 633)) {
        page = 2;
        insOpen = true;
      } else if (x > 455 && x < 621 && y > 530 && y < 551) {
        insOpen = true;
        page = 1;
      } else {
        insOpen = false;
      }
      if (option != 0) {
        setOption();
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
      x = (int) event.getX();
      y = (int) event.getY();
    }
  }
}
