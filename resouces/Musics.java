package resouces;

import java.io.File;

import Tower.Archer;
import Tower.Canon;
import Tower.Freeze;
import Tower.Laser;
import Tower.Sun;
import Tower.Tower;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import map.Grave;
import map.Jungle;
import map.Lava;
import map.Map;

/**
 * The container of all musics.
 *
 * @author Jingwei Zhang
 */
public class Musics {
  private static MediaPlayer bgm = new MediaPlayer(new Media(new File("sounds/main.mp3").toURI().toString()));
  private static MediaPlayer jungle = new MediaPlayer(new Media(new File("sounds/jungle.mp3").toURI().toString()));
  private static MediaPlayer volcano = new MediaPlayer(new Media(new File("sounds/volcano.mp3").toURI().toString()));
  private static MediaPlayer grave = new MediaPlayer(new Media(new File("sounds/grave.mp3").toURI().toString()));
  private static MediaPlayer laser = new MediaPlayer(new Media(new File("sounds/attack/laser.mp3").toURI().toString()));
  private static MediaPlayer archer =
          new MediaPlayer(new Media(new File("sounds/attack/archer.mp3").toURI().toString()));
  private static MediaPlayer canon = new MediaPlayer(new Media(new File("sounds/attack/canon.mp3").toURI().toString()));
  private static MediaPlayer sun = new MediaPlayer(new Media(new File("sounds/attack/sun.mp3").toURI().toString()));
  private static MediaPlayer freeze =
          new MediaPlayer(new Media(new File("sounds/attack/freeze.mp3").toURI().toString()));

  /**
   * Plays the BGM of starting game.
   */
  public static void playBGM() {
    bgm.play();
  }

  /**
   * Stops the BGM of starting game.
   */
  public static void stopBGM() {
    bgm.stop();
  }

  /**
   * Stops all SE of attacking.
   */
  public static void stopAllAttack() {
    laser.stop();
    sun.stop();
    archer.stop();
    canon.stop();
    freeze.stop();
  }

  /**
   * Plays the BGM of the map.
   *
   * @param m the selected map.
   */
  public static void playMapBGM(Map m) {
    if (m instanceof Jungle) {
      jungle.play();
    } else if (m instanceof Grave) {
      grave.play();
    } else if (m instanceof Lava) {
      volcano.play();
    }
  }

  /**
   * Stops the BGM of the map.
   *
   * @param m the selected map.
   */
  public static void stopMapBGM(Map m) {
    if (m instanceof Jungle) {
      jungle.stop();
    } else if (m instanceof Grave) {
      grave.stop();
    } else if (m instanceof Lava) {
      volcano.stop();
    }
    archer.stop();
    sun.stop();
    freeze.stop();
    canon.stop();
    laser.stop();
  }

  /**
   * Plays the SE of attacking.
   *
   * @param t the tower that attacks.
   */
  public static void playAttack1(Tower t) {
    if (t instanceof Freeze && !(freeze.getStatus() == Status.PLAYING)) {
      freeze.setCycleCount(Integer.MAX_VALUE);
      freeze.play();
    } else if (t instanceof Archer && !(archer.getStatus() == Status.PLAYING)) {
      archer.setCycleCount(Integer.MAX_VALUE);
      archer.play();
    } else if (t instanceof Sun && !(sun.getStatus() == Status.PLAYING)) {
      sun.setCycleCount(Integer.MAX_VALUE);
      sun.play();
    } else if (t instanceof Laser && !(laser.getStatus() == Status.PLAYING)) {
      laser.setCycleCount(Integer.MAX_VALUE);
      laser.play();
    } else if(t instanceof Canon && !(canon.getStatus() == Status.PLAYING)) {
    		canon.setCycleCount(Integer.MAX_VALUE);
    		canon.play();
    }

  }

  /**
   * Stops the SE of attacking.
   *
   * @param t the tower that attacks.
   */
  public static void stopAttack1(Tower t) {
    if (t instanceof Freeze) {
      freeze.stop();
    } else if (t instanceof Archer) {
      archer.stop();
    } else if (t instanceof Sun) {
      sun.stop();
    } else if (t instanceof Laser) {
      laser.stop();
    } else {
      canon.stop();
    }

  }

  public static void playAttack(Tower t) {

  }

  public static void stopAttack(Tower t) {

  }
}
