package map;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Random;

import Enemy.GreenSlime;
import Enemy.Snake;
import Enemy.Spider;
import controller.GUI;
import model.Track;
import model.User;
import model.Wave;

/**
 * The jungle map.
 *
 * @author Jingwei Zhang
 */
public class Jungle extends Map {
  private static final long serialVersionUID = -4121117526661656518L;

  private static String background = "jb1";
  private static String background2 = "jb2";
  private static ArrayList<Track> tracks = new ArrayList<>();
  private static ArrayList<Wave> waves = new ArrayList<>();
  private static Point2D startPoint = new Double();
  private static Point2D endPoint = new Double();
  private static User user = new User();
  private static ArrayList<Point2D> blocks = new ArrayList<>();
  private double diff;
  private int size = GUI.size;
  private String story = "            After walking from the beach, our heroes have entered deep into \nan insidious jungle. After"
			+ "wandering around for hours, they reach a \nclearing in the undergrowth, where they decide to"
			+ "camp for night. \nDuring the night, they hear shuffling and other suspiciouis noises, \nputting them"
			+ "on guard. At the break of creatures start attacking our \nheros! Organize a defense to fend off"
			+ "these monsters!";

  /**
   * Constructs a {@link Jungle} map.
   *
   * @param d the increase rate of the difficulty.
   */
  public Jungle(double d) {
    super(startPoint, endPoint, waves, background, background2, user, tracks, blocks);
    diff = d;
    init();
  }

  /**
   * Initializes this map.
   */
  @Override
  public void init() {
    waves.clear();
    tracks.clear();
    int gold = (int) (1000/diff);
    user.init(gold, 100);
    startPoint.setLocation(875, 25);
    endPoint.setLocation(25, 675);

    tracks.add(new Track(new double[]{
            3, size * 2, 2, size * 14, 3, size * 2, 4, size * 13, 3, size * 2, 2, size * 13, 3, size * 3, 4, size * 9,
            3, size * 2, 2, size * 9, 3, size * 3, 4, size * 6, 3, size * 2, 2, size * 5, 3, size
    }));

    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 9; x++) {
        blocks.add(new Double(25 + size * x, 25 + size * y));
      }
    }
    blocks.add(new Double(25, 225));
    blocks.add(new Double(75, 225));
    blocks.add(new Double(125, 225));
    blocks.add(new Double(175, 225));
    blocks.add(new Double(225, 225));
    blocks.add(new Double(25, 275));
    blocks.add(new Double(75, 275));
    blocks.add(new Double(125, 275));
    blocks.add(new Double(175, 275));
    blocks.add(new Double(225, 275));

    waves.add(new Wave());
    waves.add(new Wave());
    waves.add(new Wave());
    waves.add(new Wave());
    waves.add(new Wave());

    Random r = new Random();
    final double startX = startPoint.getX();
    final double startY = startPoint.getY();
    waves.get(0).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(1).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(2).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(3).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(4).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new GreenSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Spider(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Snake(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
  }
  
  public String getStory() {
	  return story;
  }
}
