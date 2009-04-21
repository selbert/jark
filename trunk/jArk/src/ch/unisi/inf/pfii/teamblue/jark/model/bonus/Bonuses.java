package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

public enum Bonuses {
	
	STICKY (0, 5),
	SLOW (1, 5),
	LONG (2, 5),
	EXPLOSION (3, 4),
	DOUBLE (4, 4),
	LASER (5, 4),
	ULTRA (6, 3),
	DBL_LASER (7, 3),
	MISSILE (8, 2),
	BOX (9, 1),
	FAST (10, 5),
	SHORT (11, 5),
	GHOST (12, 4),
	FREEZE (13, 3),
	RESET (14, 3),
	FALSE (15, 2),
	DEATH (16, 1);
	
	private final int probability;
	private final int number;
	
	Bonuses(int num, int prob) {
		probability = prob;
		number = num;
	}
	
	public static int getProb(final int num) {
		for (Bonuses b : Bonuses.values()) {
			if (b.number == num) {
				return b.probability;
			}
		}
		return -1;
	}
}
