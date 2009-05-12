package ch.unisi.inf.pfii.teamblue.jark.model.level;

import java.io.File;
import java.net.URL;

public class LevelListGetter {
	public static String[] getLevels() {
		File levelDirectory;
		final URL filePath = LevelListGetter.class.getResource("levels/");
		levelDirectory = new File(filePath.getPath());
		if (levelDirectory.isDirectory()) {
			File[] levelFiles = levelDirectory.listFiles();
			System.out.println(levelFiles.length);
			String[] levels =  new String[levelFiles.length];
			for (int i = 0; i < levelFiles.length; i++) {
				levels[i] = levelFiles[i].getName();
			}
			return levels;
		}
		return new String[0];
	}
}
