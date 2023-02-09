package map;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Random;

import Enemy.Demon;
import Enemy.Ghost;
import Enemy.Vampire;
import controller.GUI;
import model.Track;
import model.User;
import model.Wave;

/**
 * The grave map.
 *
 * @author Jingwei Zhang
 */
public class Grave extends Map {
  private static final long serialVersionUID = 2744783511490899661L;

  private static String background = "gr1";
  private static String background2 = "gr2";
  private static ArrayList<Track> tracks = new ArrayList<>();
  private static ArrayList<Wave> waves = new ArrayList<>();
  private static Point2D startPoint = new Double();
  private static Point2D endPoint = new Double();
  private static User user = new User();
  private static ArrayList<Point2D> blocks = new ArrayList<>();
  private double diff;
  private int size = GUI.size;
  private String story = "            From the remains of one of the monsters, our heroes found an \nold "
  		+ "worn-out map of the island. It shows a town in the center and a \npath from it leading "
  		+ "all the way to the volcano. They manage to reach the \narea shown on the map, but the town "
  		+ "has long disappeared. Only a \ngraveyard stands in that area. As our heroes are exploring "
  		+ "this strange \nplace, they hear those same noises from last night. As they look around, "
  		+ "\nthey realize they have been surrounded by monsters! Hold off the attack! ";

  /**
   * Constructs a {@link Grave} map.
   *
   * @param d the increase rate of the difficulty.
   */
  public Grave(double d) {
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
    startPoint.setLocation(25, 125);
    endPoint.setLocation(25, 575);

    tracks.add(new Track(new double[]{
            1, 16 * size, 2, 11 * size, 3, 6 * size, 4, 2 * size, 1, 3 * size, 4, 6 * size, 3, 3 * size, 2,
            3 * size, 3, 3 * size, 4, 3 * size, 3, 3 * size, 2, 8 * size, 3, 3 * size, 4, 2 * size, 3, size
    }));
    tracks.add(new Track(new double[]{
            1, 16 * size, 2, 11 * size, 3, 6 * size, 4, 2 * size, 1, 3 * size, 4, 6 * size, 3, 3 * size, 2,
            3 * size, 3, 3 * size, 4, 3 * size, 3, 3 * size, 2, 4 * size, 3, 3 * size, 2, 2 * size, 3, size
    }));

    blocks.add(new Double(1025, 25));
    blocks.add(new Double(275, 175));
    blocks.add(new Double(525, 175));
    blocks.add(new Double(575, 175));
    blocks.add(new Double(625, 175));
    blocks.add(new Double(725, 175));
    blocks.add(new Double(425, 225));
    blocks.add(new Double(475, 225));
    blocks.add(new Double(425, 275));
    blocks.add(new Double(475, 275));
    blocks.add(new Double(425, 325));
    blocks.add(new Double(775, 325));
    blocks.add(new Double(25, 325));
    blocks.add(new Double(425, 375));
    blocks.add(new Double(775, 375));
    blocks.add(new Double(625, 475));

    waves.add(new Wave());
    waves.add(new Wave());
    waves.add(new Wave());
    waves.add(new Wave());
    waves.add(new Wave());

    Random r = new Random();
    final double startX = startPoint.getX();
    final double startY = startPoint.getY();
    waves.get(0).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(0).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(1).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Ghost(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Vampire(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Demon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
  }
  
  public String getStory() {
	  return story;
  }
  
}
