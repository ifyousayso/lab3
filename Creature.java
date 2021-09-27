public abstract class Creature {
	protected String name;
	protected int maxHitPoints;
	protected int hitPoints;
	protected int attack;
	protected int defense;

	protected Creature(String name) {
		this.name = name;
	}

	// Purpose: Return the creature's name.
	// Arguments: -
	// Return: String
	public String getName() {
		return this.name;
	}

	// Purpose: Return the creature's maximum hit points.
	// Arguments: -
	// Return: int
	public int getMaxHitPoints() {
		return this.maxHitPoints;
	}

	// Purpose: This attempts to hurt the creature. The returned value is the actual damage done, after defense.
	// Arguments: int damage
	// Return: int
	public int hurt(int damage) {
		// Long because the damage can be Integer.MIN_VALUE and even less after defense.
		long finalDamage = damage - this.defense;

		if (!this.isAlive() || finalDamage <= 0) {
			return 0;
		}

		damage = (int) finalDamage;
		// Yes, this can go below 0.
		this.hitPoints -= damage;
		return damage;
	}

	// Purpose: Return the creature's hit points.
	// Arguments: -
	// Return: int
	public int getHitPoints() {
		return this.hitPoints;
	}

	// Purpose: This is just a shortcut for hitPoints > 0 to check if the creature is alive.
	// Arguments: -
	// Return: boolean
	public boolean isAlive() {
		return this.hitPoints > 0;
	}

	// Purpose: Return the creature's attack.
	// Arguments: -
	// Return: int
	public int getAttack() {
		return this.attack;
	}

	// Purpose: Return the creature's defense.
	// Arguments: -
	// Return: int
	public int getDefense() {
		return this.defense;
	}

	// Purpose: Return the creature's calculated damage.
	// Arguments: -
	// Return: int
	public abstract int generateDamage();
}

