package utils;

public enum E_Levels {
	// -------------------------------------------------------------Values---------------------------------------------------------------------
	// levels of Rooms
	BEGINNERS(1), INTERMEDIATE(2), ADVANCED(3), PROFESSIONAL(4);
	// -------------------------------------------------------------Class Members----------------------------------------------------------------
	private final int level;

	// -------------------------------------------------------------Constructor------------------------------------------------------------------
	E_Levels(int level) {
		this.level = level;
	}

	// -------------------------------------------------------------Methods----------------------------------------------------------------------
	public int getLevel() {
		return level;
	}

	public static E_Levels returnLevel(int val) {
		switch (val) {
		case 0:
			return E_Levels.BEGINNERS;
		case 1:
			return E_Levels.BEGINNERS;
		case 2:
			return E_Levels.INTERMEDIATE;
		case 3:
			return E_Levels.ADVANCED;
		default:
			return E_Levels.PROFESSIONAL;
		}
	}
}
