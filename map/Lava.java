package map;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Random;

import Enemy.Dragon;
import Enemy.FireSpirit;
import Enemy.RedSlime;
import controller.GUI;
import model.Track;
import model.User;
import model.Wave;

/**
 * The lava map.
 *
 * @author Jingwei Zhang
 */
public class Lava extends Map {
  private static final long serialVersionUID = 7396610034640818016L;
  private static String background = "la1";
  private static String background2 = "la2";
  private static ArrayList<Track> tracks = new ArrayList<>();
  private static ArrayList<Wave> waves = new ArrayList<>();
  private static Point2D startPoint = new Double();
  private static Point2D endPoint = new Double();
  private static User user = new User();
  private static ArrayList<Point2D> blocks = new ArrayList<>();
  private double diff;
  private int size = GUI.size;
  private String story = "            The heroes have finally made it to the volcano. Hot steaming lava \nflows within this area"
			+ "In the centermost room, our heroes have started \nto cook the carrot in the hottest pool of lava."
			+ "Halfway through the \ncooking, our heroes hear some splashing noises... Thoes darn monsters!\nStop"
			+ "this last wave at all costs! ";

  /**
   * Constructs a {@link Lava} map.
   *
   * @param d the increase rate of the difficulty.
   */
  public Lava(double d) {
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
    startPoint.setLocation(25, 75);
    endPoint.setLocation(475, 725);

    tracks.add(new Track(new double[]{1, 15 * size, 2, 11 * size, 3, 6 * size, 2, 2 * size}));
    tracks.add(new Track(new double[]{1, 15 * size, 2, 6 * size, 3, 13 * size, 2, 5 * size, 1, 7 * size, 2, 2 * size}));
    tracks.add(new Track(new double[]{1, 2 * size, 2, 11 * size, 1, 7 * size, 2, 2 * size}));
    tracks.add(new Track(new double[]{1, 2 * size, 2, 6 * size, 1, 13 * size, 2, 5 * size, 3, 6 * size, 2, 2 * size}));
    
    blocks.add(new Double(75,25));
    blocks.add(new Double(125,25));
    blocks.add(new Double(175,25));
    blocks.add(new Double(275,25));
    blocks.add(new Double(325,25));
    blocks.add(new Double(375,25));
    blocks.add(new Double(475,25));
    blocks.add(new Double(525,25));
    blocks.add(new Double(575,25));
    blocks.add(new Double(675,25));
    blocks.add(new Double(725,25));
    blocks.add(new Double(775,25));
    blocks.add(new Double(875,25));
    blocks.add(new Double(825,75));
    blocks.add(new Double(875,75));
    blocks.add(new Double(375,125));
    blocks.add(new Double(675,225));
    blocks.add(new Double(175,325));
    blocks.add(new Double(225,325));
    blocks.add(new Double(275,325));
    blocks.add(new Double(325,325));
    blocks.add(new Double(375,325));
    blocks.add(new Double(425,325));
    blocks.add(new Double(475,325));
    blocks.add(new Double(525,325));
    blocks.add(new Double(575,325));
    blocks.add(new Double(625,325));
    blocks.add(new Double(675,325));
    blocks.add(new Double(725,325));
    blocks.add(new Double(25,425));
    blocks.add(new Double(175,575));
    blocks.add(new Double(225,575));
    blocks.add(new Double(275,575));
    blocks.add(new Double(325,575));
    blocks.add(new Double(375,575));
    blocks.add(new Double(425,575));
    blocks.add(new Double(475,575));
    blocks.add(new Double(525,575));
    blocks.add(new Double(575,575));
    blocks.add(new Double(625,575));
    blocks.add(new Double(675,575));
    blocks.add(new Double(725,575));
    blocks.add(new Double(125,675));



    
    waves.add(new Wave());
    waves.add(new Wave());
    waves.add(new Wave());
    waves.add(new Wave());
    waves.add(new Wave());

    Random r = new Random();
    final double startX = startPoint.getX();
    final double startY = startPoint.getY();
    waves.get(0).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(0).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(1).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(1).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(2).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(2).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(3).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(3).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));

    waves.get(4).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new RedSlime(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new FireSpirit(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
    waves.get(4).add(new Dragon(diff, new Double(startX, startY), tracks.get(r.nextInt(tracks.size()))));
  }
  
  public String getStory() {
	  return story;
  }
}
