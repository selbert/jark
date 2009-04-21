package ch.unisi.inf.pfii.teamblue.jark.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-04-13 17:30:41 +0200 (Mon, 13 Apr 2009) $
 * 
 */
public class Level {
	private final int dimX = 20;
	private final int dimY = 8;
	
	private Brick[][] grid;
	
	
	public Level(final int numOfBonus) {
		grid = new Brick[dimY][dimX];
		createLevel(0);
		addBonus(numOfBonus);
		System.out.println(toString());
	}
	
	private void createLevel(final int levelNumber) {
		try{
			FileInputStream fis = new FileInputStream("src/ch/unisi/inf/pfii/teamblue/jark/model/levels/"+levelNumber);
			BufferedReader myInput = new BufferedReader(new InputStreamReader(fis));
			String thisLine = "";
		
			while ((thisLine = myInput.readLine()) != null) {
				
				String[] brickInfo = thisLine.split("\\.");
				if (brickInfo.length > 1) {
					int row = Integer.parseInt(brickInfo[0]);
					int col = Integer.parseInt(brickInfo[1]);
					int type = Integer.parseInt(brickInfo[2]);
				
					grid[row][col] = new Brick(type);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private String getBrick(final int x, final int y) {
		Brick tempBrick = grid[x][y];
		if (tempBrick != null) {
			Bonus tempBonus = tempBrick.getBonus();
			if (tempBonus != null) {
				return tempBonus.getType()+" ";
			}
		}
		return "  ";
	}
	
	public String toString() {
		String tab = "\n";

		for (int row = 0; row<dimY; row++) {
			tab += "  ";
			for (int col = 0; col<dimX; col++) {
				tab += getBrick(row,col)+"|";
			}
			tab = tab.substring(0, tab.length()-1);

			if (row != dimY-1) {
				tab += "\n  ";
				for (int col = 0; col<dimX; col++) {
					tab += "--+";
				}
				tab = tab.substring(0, tab.length()-1) + "\n";
			}

		}

		return tab+"\n\n\n             ð";
	}

	
	private void addBonus(final int numOfBonus) {
		ArrayList<Brick> listOfBricks = new ArrayList<Brick>();
		Random rnd = new Random();
		
		for(Brick[] rowOfBricks : grid) {
			for (Brick brick : rowOfBricks) {
				if (brick != null) {
					listOfBricks.add(brick);
				}
			}
		}
		
		for (int i = 0; i < numOfBonus; i++) {
			int index = rnd.nextInt(listOfBricks.size());
			listOfBricks.remove(index).setBonus();
		}	
	}
	
}

