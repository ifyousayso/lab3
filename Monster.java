public abstract class Monster extends Creature {
	protected int experienceValue;
	protected int goldValue;

	protected Monster(String name, int maxHitPoints, int attack, int defense, int experienceValue, int goldValue) {
		super(name);
		this.maxHitPoints = maxHitPoints;
		this.hitPoints = maxHitPoints;
		this.attack = attack;
		this.defense = defense;
		this.experienceValue = experienceValue;
		this.goldValue = goldValue;
	}

	// Purpose: Return the monster's calculated damage. If the monster's damage is 10, this is meant to be in the range
	// of 5 to 15.
	// Arguments: -
	// Return: int
	public int generateDamage() {
		return this.attack + (int) Math.floor(Math.random() * (this.attack + 1) - this.attack * 0.5);
	}

	// Purpose: Return the monster's experience value.
	// Arguments: -
	// Return: int
	public int getExperienceValue() {
		return this.experienceValue;
	}

	// Purpose: Return the monster's calculated loot. If the monster's gold value is 10, this is meant to be in the range
	// of 5 to 15.
	// Arguments: -
	// Return: int
	public int generateGold() {
		return this.goldValue + (int) Math.floor(Math.random() * (this.goldValue + 1) - this.goldValue * 0.5);
	}

	// Purpose: Return the monster's reaction to damage.
	// Arguments: -
	// Return: String
	public abstract String painReaction();

	// Purpose: Reset the monster's hit points (so far) after being defeated.
	// Arguments: -
	// Return: -
	public void reset() {
		this.hitPoints = this.maxHitPoints;
	}
}

