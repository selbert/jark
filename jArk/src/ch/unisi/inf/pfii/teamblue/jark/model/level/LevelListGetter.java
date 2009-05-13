package ch.unisi.inf.pfii.teamblue.jark.model.level;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class LevelListGetter {
	public static String[] getLevels() {
		File levelDirectory;
		
		try {
			URI filePath = LevelListGetter.class.getResource("levels/").toURI();
			String path = filePath.getPath().replaceAll("%20", " ");
			levelDirectory = new File(path);
			if (levelDirectory.isDirectory()) {
				File[] levelFiles = levelDirectory.listFiles();
				String[] levels =  new String[levelFiles.length];
				for (int i = 0; i < levelFiles.length; i++) {
					levels[i] = levelFiles[i].getName();
				}
				return levels;
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new String[0];
	}
}
