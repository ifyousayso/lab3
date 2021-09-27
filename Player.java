public class Player extends Creature {
	// When setting these, make sure that the levelUp() maxHitPoints never overflows (int).
	private final int MAX_LEVEL = 10;
	private final int EXP_PER_LEVEL = 100;
	private final int BASE_MAX_HP = 100;
	private final int BASE_ATTACK = 20;

	private int level = 1;
	private int experience = 0;
	private int gold = 0;

	private boolean[] equipment;

	// Purpose: This is the class' constructor.
	// Arguments: String name
	public Player(String name) {
		super(name);
		this.maxHitPoints = BASE_MAX_HP;
		this.hitPoints = BASE_MAX_HP;
		this.attack = BASE_ATTACK;
		equipment = new boolean[Equipment.size];
		// What's the max size of an enum? Is int enough?
		for (int i = 0; i < Equipment.size; i++) {
			equipment[i] = false;
		}
	}

	// Purpose: This attempts to heal the player.
	// Arguments: int hitPoints
	// Return: -
	public void heal(int hitPoints) {
		if (!this.isAlive() || hitPoints <= 0) {
			return;
		}

		long finalHitPoints = this.hitPoints + hitPoints;

		if (finalHitPoints > this.maxHitPoints) {
			this.hitPoints = this.maxHitPoints;
		} else {
			this.hitPoints = (int) finalHitPoints;
		}
	}

	// Purpose: Return the player's calculated damage. If the player's damage is 10, this is meant to be in the range
	// of 5 to 15.
	// Arguments: -
	// Return: int
	public int generateDamage() {
		return this.attack + (int) Math.floor(Math.random() * (this.attack + 1) - this.attack * 0.5);
	}

	private void levelUp() {
		if (this.level < MAX_LEVEL) {
			this.level++;
			this.maxHitPoints = BASE_MAX_HP + (BASE_MAX_HP * (this.level - 1)) / (2 * MAX_LEVEL);
			System.out.println("You leveled up, and are now level " + this.level + "!");
		}

		// This method will heal up the player every EXP_PER_LEVEL even if already at MAX_LEVEL.
		this.hitPoints = this.maxHitPoints;
	}

	// Purpose: Return the player's level.
	// Arguments: -
	// Return: int
	public int getLevel() {
		return this.level;
	}

	// Purpose: Returns true if the player's level is maxed out.
	// Arguments: -
	// Return: boolean
	public boolean isLevelMaxed() {
		return this.level == MAX_LEVEL;
	}

	// Purpose: Boost the player's experience and increase their level when appropriate.
	// Arguments: int experience
	// Return: -
	public void giveExperience(int experience) {
		boolean experienceLoop = true;

		if (this.isAlive() == false || experience < 1) {
			return;
		}

		do {
			// Let's say that the current experience is at 99 and a monster kill just gave 110. This would run twice and
			// leave the player with +2 levels and 9 experience.
			if (this.experience + experience >= EXP_PER_LEVEL) {
				this.levelUp();
				// Once max level has been reached, set the experience to 0 and return.
				if (this.level == MAX_LEVEL) {
					this.experience = 0;
					experienceLoop = false;
				}
				experience -= EXP_PER_LEVEL; // At this point, the experience left can be negative.
			} else {
				this.experience += experience;
				experienceLoop = false;
			}
		} while (experienceLoop);
	}

	// Purpose: Return the player's experience.
	// Arguments: -
	// Return: int
	public int getExperience() {
		return this.experience;
	}

	// Purpose: This changes the player's gold by a certain amount. The return value is whatever gold is left after
	// hitting 0 or Integer.MAX_VALUE. It should be 0.
	// Arguments: int gold
	// Return: int
	public int alterGold(int gold) {
		long result = this.gold + gold;

		if (result > Integer.MAX_VALUE) {
			this.gold = Integer.MAX_VALUE;
			return (int) (result - this.gold);
		}

		if (result < 0) {
			this.gold = 0;
			return (int) result;
		} else {
			this.gold = (int) result;
			return 0;
		}
	}

	// Purpose: Return the player's gold.
	// Arguments: -
	// Return: int
	public int getGold() {
		return this.gold;
	}

	// Purpose: Checks if the player owns a certain piece of equipment.
	// Arguments: Equipment equipment
	// Return: boolean
	public boolean hasEquipment(Equipment equipment) {
		return this.equipment[equipment.ordinal()];
	}

	// Purpose: Gives a piece of equipment. The return value indicates the success.
	// Arguments: Equipment equipment
	// Return: boolean
	public boolean giveEquipment(Equipment equipment) {
		if (this.equipment[equipment.ordinal()]) {
			return false;
		}

		this.equipment[equipment.ordinal()] = true;
		switch (equipment) {
			case AMULET_OF_POWER:
				this.attack += 4;
				break;
			case STONESKIN_RING:
				this.defense += 2;
				break;
		}
		return true;
	}

	// Purpose: Takes a piece of equipment. The return value indicates the success.
	// Arguments: Equipment equipment
	// Return: boolean
	public boolean takeEquipment(Equipment equipment) {
		if (!this.equipment[equipment.ordinal()]) {
			return false;
		}

		switch (equipment) {
			case AMULET_OF_POWER:
				this.attack -= 4;
				break;
			case STONESKIN_RING:
				this.defense -= 2;
				break;
		}
		this.equipment[equipment.ordinal()] = false;
		return true;
	}

	// Purpose: This prints information about the player.
	// Arguments: -
	// Return: -
	public void printDetails() {
		System.out.println("     Name: " + this.name);
		System.out.println("    Level: " + this.level);
		System.out.println("      Exp: " + this.experience + "/" + EXP_PER_LEVEL);
		System.out.println("   Health: " + this.hitPoints + "/" + this.maxHitPoints);
		System.out.println("   Attack: " + this.attack);
		System.out.println("  Defense: " + this.defense);
		System.out.println("     Gold: " + this.gold + "\n");
	}
}

