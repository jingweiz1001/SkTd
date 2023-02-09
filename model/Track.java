package model;

import java.io.Serializable;

/**
 * The track.
 *
 * @author Jingwei Zhang
 */
public class Track implements Serializable {
  private static final long serialVersionUID = -312644920514916167L;

  private double[] path;

  /**
   * Constructs a {@link Track}.
   *
   * @param p the path of this track.
   */
  public Track(double[] p) {
    path = p;
  }

  /**
   * Get the path.
   *
   * @return the the path.
   */
  public double[] getPath() {
    return path;
  }
}
