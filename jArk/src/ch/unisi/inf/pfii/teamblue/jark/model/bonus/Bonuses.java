package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

/**
 * This enum returns a new instance of the given bonus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public enum Bonuses {

	STICKY(0, "neutral_stickyball", 5, new StickyBallBonus()), 
	SLOW(1,"neutral_slowball", 5, new SlowBallBonus()), 
	LONG(2,"bonus_longvaus", 5, new LongVausBonus()), 
	EXPLOSIVE(3,"bonus_explosionball", 4, new ExplosionBallBonus()), 
	DOUBLE(4,"bonus_doubleball", 4, new DoubleBallBonus()), 
	RIFLE(5,"bonus_riflevaus", 4, new RifleVausBonus()), 
	ULTRA(6,"bonus_ultraball", 3, new UltraBallBonus()), 
	DBL_RIFLE(7,"bonus_doubleriflevaus", 3, new DoubleRifleVausBonus()), 
	CANNON(8,"bonus_cannonvaus", 2, new CannonVausBonus()), 
	BOX(9, "bonus_box", 1, new TheBoxBonus()), 
	FAST(10, "neutral_fastball", 5, new FastBallBonus()), 
	SHORT(11, "malus_shortvaus", 5, new ShortVausBonus()), 
	GHOST(12, "malus_ghostball", 3, new GhostBallBonus()), 
	RESET(13, "neutral_resetstatus", 3, new ResetStatusBonus()), 
	FALSE(14, "malus_falseball", 2, new FalseBallsBonus()), 
	REM_LIFE(15, "malus_removelife", 1, new RemoveLifeBonus()), 
	ADD_LIFE(16, "bonus_addlife", 1, new AddLifeBonus()),
	LIGHT_OFF(17, "malus_lightoff", 1, new LightOffBonus());
	
	private final int probability;
	private final int bonusNumber;
	private final Bonus bonus;
	private final String bonusName;

	/**
	 * Constructor of the enum
	 * 
	 * @param bonusNumber
	 * @param bonusName
	 * @param prob
	 * @param bonus
	 */
	Bonuses(final int bonusNumber, final String bonusName, final int prob,
			final Bonus bonus) {
		probability = prob;
		this.bonusNumber = bonusNumber;
		this.bonus = bonus;
		this.bonusName = bonusName;
	}

	/**
	 * Get the probability that a bonus occurs (on a scale from 1 (rarest) to 5
	 * (common)
	 * 
	 * @param bonusNumber
	 */

	public final static int getProb(final int bonusNumber) {
		for (final Bonuses b : Bonuses.values()) {
			if (b.bonusNumber == bonusNumber) {
				return b.probability;
			}
		}
		return -1;
	}

	/**
	 * Get a bonus given its bonus number
	 * 
	 * @param bonusNumber
	 */

	public final static Bonus getBonus(final int bonusNumber) {
		for (final Bonuses b : Bonuses.values()) {
			if (b.bonusNumber == bonusNumber) {
				try {
					return b.bonus.getClass().newInstance();
				} catch (final InstantiationException e) {
					e.printStackTrace();
				} catch (final IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * Get a bonus given its name
	 * 
	 * @param bonusName
	 */

	public final static Bonus stringToBonus(final String bonusName) {
		for (final Bonuses b : Bonuses.values()) {
			if (b.bonusName.equals(bonusName)) {
				try {
					return b.bonus.getClass().newInstance();
				} catch (final InstantiationException e) {
					e.printStackTrace();
				} catch (final IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
