package model;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * The unit.
 *
 * @author Jingwei Zhang
 */
public class Unit implements Serializable {
  private static final long serialVersionUID = -2309435554024558689L;

  private Point2D position;
  private String image;

  /**
   * Constructs a {@link Unit}.
   *
   * @param fileName the filename of the image of this user.
   * @param p        the position that this unit at.
   */
  public Unit(String fileName, Point2D p) {
    position = p;
    image = fileName;
  }

  /**
   * Gets the position that this unit at.
   *
   * @return the position.
   */
  public Point2D getPosition() {
    return position;
  }

  /**
   * Sets the position of this unit.
   *
   * @param p the position of this unit.
   */
  public void setPosition(Point2D p) {
    position = p;
  }

  /**
   * Gets the filename of the image of this unit.
   *
   * @return the filename of the image.
   */
  public String getImage() {
    return image;
  }
}
