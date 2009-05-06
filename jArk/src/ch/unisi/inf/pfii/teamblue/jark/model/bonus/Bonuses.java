package ch.unisi.inf.pfii.teamblue.jark.model.bonus;


public enum Bonuses {
	
	STICKY (0, 5, new StickyBallBonus()),
	SLOW (1, 5, new SlowBallBonus()),
	LONG (2, 5, new LongVausBonus()),
	EXPLOSIVE (3, 4, new ExplosiveBallBonus()),
	DOUBLE (4, 4, new DoubleBallBonus()),
	LASER (5, 4, new LaserVausBonus()),
	ULTRA (6, 3, new UltraBallBonus()),
	DBL_LASER (7, 3, new DoubleLaserVausBonus()),
	MISSILE (8, 2, new MissileVausBonus()),
	BOX (9, 1, new TheBoxBonus()),
	FAST (10, 5, new FastBallBonus()),
	SHORT (11, 5, new ShortVausBonus()),
	FREEZE (12, 3, new RubberBallBonus()),
	RESET (13, 3, new ResetStatusBonus()),
	FALSE (14, 2, new FalseBallsBonus()),
	REM_LIFE (15, 1, new RemoveLifeBonus()),
	ADD_LIFE (16, 1, new AddLifeBonus());
	
	private final int probability;
	private final int number;
	private final Bonus bonus;
	
	Bonuses(int num, int prob, Bonus bonus) {
		probability = prob;
		number = num;
		this.bonus = bonus;
	}
	
	public static int getProb(final int num) {
		for (Bonuses b : Bonuses.values()) {
			if (b.number == num) {
				return b.probability;
			}
		}
		return -1;
	}
	
	public static Bonus getBonus(final int num) {
		for (Bonuses b : Bonuses.values()) {
			if (b.number == num) {
				try {
					return b.bonus.getClass().newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
