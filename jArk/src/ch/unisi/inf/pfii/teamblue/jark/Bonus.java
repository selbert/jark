package ch.unisi.inf.pfii.teamblue.jark;

import java.util.Random;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-04-13 17:18:53 +0200 (Mon, 13 Apr 2009) $
 * 
 */

public class Bonus {
	private static final int SPEED = 3;
	private static String bonusDistString = "";
	private int x;
	private int y;
	private int type;
	
	public Bonus() {
		x = 0;
		y = 0;
		if (bonusDistString.equals("")) {
			bonusDistString = getDistributionString();
		}
		this.type = createType();
	}
	

	// method that randomly selects a bonus type from a string of bonus indexes separated by ","
	
	
	private int createType() {
		Random rnd = new Random();
		String[] bonusArray = bonusDistString.split(",");
		return Integer.parseInt(bonusArray[rnd.nextInt(bonusArray.length)]);
	}
	
	// method that creates a string with the index of the bonus repeated the times defined in the Bonuses enum
	
	private String getDistributionString() {
		String bonusString = "";
		int numberOfBonus = Bonuses.values().length;
		for (int a = 0; a < numberOfBonus; a++) {
			int probability = Bonuses.getProb(a);
			for (int i = 0; i < probability; i++) {
				bonusString += "" + a + ",";
			}
		}
		return bonusString.substring(0, bonusString.length()-1);
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	public void move() {
		y = y + SPEED;
	}
	
	
}
