package resouces;

import java.util.Random;

import javafx.scene.image.Image;
import model.User;

/**
 * The container of all images.
 *
 * @author Jingwei Zhang
 */
public class Images {
  private static Random r = new Random();
  private static int w = 0;
  // basic
  public static Image hurt = new Image("file:images/hurt.png", false);
  public static Image paper = new Image("file:images/paper.png", false);
  public static Image warning = new Image("file:images/warning.png", false);
  public static Image warning1 = new Image("file:images/warning1.png", false);
  public static Image statFrame = new Image("file:images/frame1.png", false);
  public static Image emptyFrame = new Image("file:images/emptyframe.png", false);
  public static Image timeBG = new Image("file:images/timeBG.png", false);
  public static Image save = new Image("file:images/save.png", false);
  public static Image upgrade1 = new Image("file:images/upgrade1.png", false);
  public static Image upgrade2 = new Image("file:images/upgrade2.png", false);
  public static Image cancel = new Image("file:images/cancel.png", false);
  public static Image resume = new Image("file:images/start1.png", false);
  public static Image stop = new Image("file:images/stop1.png", false);
  public static Image back = new Image("file:images/home.png", false);
  public static Image fast = new Image("file:images/fast.png", false);
  public static Image slow = new Image("file:images/slow.png", false);
  public static Image archer_button = new Image("file:images/archer_button.png", false);
  public static Image sun_button = new Image("file:images/sun_button.png", false);
  public static Image laser_button = new Image("file:images/laser_button.png", false);
  public static Image freeze_button = new Image("file:images/freeze_button.png", false);
  public static Image canon_button = new Image("file:images/canon_button.png", false);

  //character
  private static Image archer = new Image("file:images/human/char_p_castle01.png", false);
  private static Image sun = new Image("file:images/human/char_p_castle01.png", false);
  private static Image laser = new Image("file:images/human/char_p_castle01.png", false);
  private static Image freeze = new Image("file:images/human/char_p_magi01.png", false);
  private static Image canon = new Image("file:images/human/char_p_magi01.png", false);
  // map background
  private static Image jb1 = new Image("file:images/jungle1.png");
  private static Image jb2 = new Image("file:images/jungle2.png");
  private static Image la1 = new Image("file:images/New_lava.png");
  private static Image la2 = new Image("file:images/.png");
  private static Image gr1 = new Image("file:images/Grave map.png");
  private static Image gr2 = new Image("file:images/.png");

  // monsters
  private static Image demon = new Image("file:images/monster/char_m_devil01.png");
  private static Image dragon = new Image("file:images/monster/char_m_d01.png");
  private static Image firespirit = new Image("file:images/monster/char_m_fire01.png");
  private static Image ghost = new Image("file:images/monster/char_m_devil01.png");
  private static Image gslime = new Image("file:images/monster/char_m_sl_g.png");
  private static Image rslime = new Image("file:images/monster/char_m_sl_r.png");
  private static Image snake = new Image("file:images/monster/char_m_d01.png");
  private static Image spider = new Image("file:images/monster/char_m_fire01.png");
  private static Image vampire = new Image("file:images/monster/char_m_devil01.png");

  // towers' bullet
  private static Image bullet_archer = new Image("file:images/effect/b_bom01b.png");
  private static Image bullet_canon = new Image("file:images/effect/b_bom01b.png");
  private static Image bullet_freeze = new Image("file:images/effect/b_ice02.png");
  private static Image bullet_laser = new Image("file:images/effect/thunder.png", false);
  private static Image bullet_laser2 = new Image("file:images/effect/thunder2.png", false);
  private static Image bullet_laser3 = new Image("file:images/effect/thunder3.png", false);

  private static Image bullet_sun = new Image("file:images/effect/forsun.png", false);

  // tower's hit
  private static Image hit_canon = new Image("file:images/effect/b_bom01b.png");
  private static Image hit_freeze = new Image("file:images/effect/b_ice02.png");
  private static Image hit_laser = new Image("file:images/effect/b_fire01.png");
  private static Image hit_archer = new Image("file:images/effect/b_bom01b.png");

  // level
  private static Image star1 = new Image("file:images/star1.png", false);
  private static Image star2 = new Image("file:images/star2.png", false);
  private static Image star3 = new Image("file:images/star3.png", false);

  // effect
  private static Image freezeE = new Image("file:images/effect/b_ice01.png", false);

  // user
  private static Image hp100_1 = new Image("file:images/carrot/100-1.png", false);
  private static Image hp100_2 = new Image("file:images/carrot/100-2.png", false);
  private static Image hp100_3 = new Image("file:images/carrot/100-3.png", false);
  private static Image hp100_4 = new Image("file:images/carrot/100-4.png", false);
  private static Image hp75 = new Image("file:images/carrot/75.png", false);
  private static Image hp50 = new Image("file:images/carrot/50.png", false);
  private static Image hp25 = new Image("file:images/carrot/25.png", false);
  private static int tic = 0;

  /**
   * Gets map's background by the given string.
   *
   * @param s the string that refers a map's background.
   * @return the background of the relative map.
   */
  public static Image getWarn() {
	  if(w == 8) {
		  w = 0;
	  }
	  if(w<4) {
		  w++;
		  return warning;
	  }else {
		  w++;
		  return warning1;
	  }
  }
  public static Image getMap(String s) {
    switch (s) {
      case "jb1":
        return jb1;
      case "jb2":
        return jb2;
      case "la1":
        return la1;
      case "la2":
        return la2;
      case "gr1":
        return gr1;
      case "gr2":
        return gr2;
      default:
    }
    return upgrade1;
  }

  /**
   * Gets the image that refers the level of the tower.
   *
   * @param s the level between 1 to 3 (inclusive).
   * @return the image for showing the level of the tower.
   */
  public static Image getLevel(int s) {
    switch (s) {
      case 1:
        return star1;
      case 2:
        return star2;
      case 3:
        return star3;
      default:
    }
    return upgrade1;
  }

  /**
   * Gets the image of a monster by the given string.
   *
   * @param s the name of the monster.
   * @return the image of the monster.
   */
  public static Image getMon(String s) {
    switch (s) {
      case "demon":
        return demon;
      case "dragon":
        return dragon;
      case "firespirit":
        return firespirit;
      case "ghost":
        return ghost;
      case "gslime":
        return gslime;
      case "rslime":
        return rslime;
      case "snake":
        return snake;
      case "spider":
        return spider;
      case "vampire":
        return vampire;
      default:
    }
    return upgrade1;
  }

  /**
   * Gets the image of the tower by the given string.
   *
   * @param s the name of the tower.
   * @return the image of the tower.
   */
  public static Image getTower(String s) {
    switch (s) {
      case "archer":
        return archer;
      case "canon":
        return canon;
      case "freeze":
        return freeze;
      case "laser":
        return laser;
      case "sun":
        return sun;
      default:
    }
    return upgrade1;
  }

  /**
   * Gets the bullet image by the given string.
   *
   * @param s the name of the tower that will launch this bullet.
   * @return the image of the bullet.
   */
  public static Image getBullet(String s) {
    switch (s) {
      case "archer":
        return bullet_archer;
      case "canon":
        return bullet_canon;
      case "freeze":
        return bullet_freeze;
      case "laser":
    	  int rn = r.nextInt(3);
    	  switch(rn) {
    	  case 0:
    	    return bullet_laser;
    	  case 1:
  	        return bullet_laser2;
    	  default:
    		return bullet_laser3;
    	  }
      case "sun":
        return bullet_sun;
      default:
    }
    return upgrade1;
  }

  /**
   * Gets the image when the bullet hits a enemy.
   *
   * @param s the string of the tower that will launch this bullet.
   * @return the hitting image.
   */
  public static Image getHit(String s) {
    switch (s) {
      case "archer":
        return hit_archer;
      case "canon":
        return hit_canon;
      case "freeze":
        return hit_freeze;
      case "laser":
        return hit_laser;
      case "sun":
        return sun;
      default:
    }
    return upgrade1;
  }

  /**
   * Gets the image of the user.
   *
   * @param s .
   * @return the image of the user.
   */
  public static Image getUser(User us) {
    if(us.getHPercent() >=0.75) {
    	tic++;
    	if(tic<20) {
    		return hp100_1;
    	}else if(tic <24) {
    		return hp100_2;
    	}else if(tic<28) {
    		return hp100_3;
    	}else if(tic<=32) {
    		if(tic == 32)
    			tic = 0;
    		return hp100_4;
    	}else
    		return hp100_1;
    }else if(us.getHPercent()<0.75 && us.getHPercent()>=0.5) {
    	tic = 0;
    	return hp75;
    }else if(us.getHPercent()<0.5 && us.getHPercent()>0.25) {
    	tic = 0;
    	return hp50;
    }else {
    	tic = 0;
    	return hp25;
    }
  }

  /**
   * Gets the image of the effect when it affect on the enemy.
   *
   * @param s the name of the effect.
   * @return the image of the effect.
   */
  public static Image getEffect(String s) {
    switch (s) {
      case "freeze":
        return freezeE;
      default:
    }
    return upgrade1;
  }
}
