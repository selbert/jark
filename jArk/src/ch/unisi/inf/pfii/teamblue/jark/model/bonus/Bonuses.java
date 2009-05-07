package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

/**
 * This enum returns a new instance of the given bonus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public enum Bonuses {
	
	STICKY (0, 5, new StickyBallBonus()),
	SLOW (1, 5, new SlowBallBonus()),
	LONG (2, 5, new LongVausBonus()),
	EXPLOSIVE (3, 4, new ExplosionBallBonus()),
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
	private final int bonusNumber;
	private final Bonus bonus;
	
	/*
	 * Constructor of the enum
	 * 
	 * @param bonusNumber
	 * @param prob
	 * @param bonus
	 */
	Bonuses(final int bonusNumber, final int prob, final Bonus bonus) {
		probability = prob;
		this.bonusNumber = bonusNumber;
		this.bonus = bonus;
	}
	
	/*
	 * Get the probability that a bonus occurs (on a scale from 1 (rarest) to 5 (common)
	 * 
	 * @param bonusNumber
	 */
	
	public final static int getProb(final int bonusNumber) {
		for (Bonuses b : Bonuses.values()) {
			if (b.bonusNumber == bonusNumber) {
				return b.probability;
			}
		}
		return -1;
	}
	
	/*
	 * Get a bonus given its bonus number
	 * 
	 * @param bonusNumber
	 */
	
	public final static Bonus getBonus(final int bonusNumber) {
		for (Bonuses b : Bonuses.values()) {
			if (b.bonusNumber == bonusNumber) {
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
