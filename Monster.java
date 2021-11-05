public abstract class Monster extends Being {
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

	// Purpose: Return the monster's experience value.
	// Arguments: -
	// Return: int
	public int getExperienceValue() {
		return this.experienceValue;
	}

	// Purpose: Return the monster's calculated loot. (gold ± 25%)
	// Arguments: -
	// Return: int
	public int generateGold() {
		return this.goldValue + (int) Math.floor(Math.random() * ((this.goldValue >> 1) + 1) - (this.goldValue >> 2));
	}

	// Purpose: Return the monster's reaction to damage.
	// Arguments: -
	// Return: String
	public String painReaction() {
		return "- Meep!";
	}

	// Purpose: Reset the monster's hit points (so far) after being defeated.
	// Arguments: -
	// Return: -
	public void reset() {
		this.hitPoints = this.maxHitPoints;
	}
}

