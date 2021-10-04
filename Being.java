public abstract class Being {
	protected String name;
	protected int maxHitPoints;
	protected int hitPoints;
	protected int attack;
	protected int defense;

	protected Being(String name) {
		this.name = name;
	}

	// Purpose: Return the being's name.
	// Arguments: -
	// Return: String
	public final String getName() {
		return this.name;
	}

	// Purpose: Return the being's maximum hit points.
	// Arguments: -
	// Return: int
	public final int getMaxHitPoints() {
		return this.maxHitPoints;
	}

	// Purpose: This attempts to heal the being.
	// Arguments: int hitPoints
	// Return: -
	public final void heal(int hitPoints) {
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

	// Purpose: This attempts to hurt the being. The returned value is the actual damage done, after defense.
	// Arguments: int damage
	// Return: int
	public final int hurt(int damage) {
		// Long because the damage can be Integer.MIN_VALUE and even less after defense.
		long finalDamage = damage - this.defense;

		if (!this.isAlive() || damage <= 0 || finalDamage <= 0) {
			return 0;
		}

		damage = (int) finalDamage;
		// Yes, this can go below 0.
		this.hitPoints -= damage;
		return damage;
	}

	// Purpose: Return the being's hit points.
	// Arguments: -
	// Return: int
	public final int getHitPoints() {
		return this.hitPoints;
	}

	// Purpose: This is just a shortcut for hitPoints > 0 to check if the being is alive.
	// Arguments: -
	// Return: boolean
	public final boolean isAlive() {
		return this.hitPoints > 0;
	}

	// Purpose: Alter the being's attack.
	// Arguments: int attack
	// Return: -
	public final void alterAttack(int attack) {
		long finalAttack = this.attack + attack;

		if (finalAttack > Integer.MAX_VALUE) {
			this.attack = Integer.MAX_VALUE;
		} else if (finalAttack < 0) {
			this.attack = 0;
		} else {
			this.attack = (int) finalAttack;
		}
	}

	// Purpose: Return the being's attack.
	// Arguments: -
	// Return: int
	public final int getAttack() {
		return this.attack;
	}

	// Purpose: Alter the being's defense.
	// Arguments: int defense
	// Return: -
	public final void alterDefense(int defense) {
		long finalDefense = this.defense + defense;

		if (finalDefense > Integer.MAX_VALUE) {
			this.defense = Integer.MAX_VALUE;
		} else if (finalDefense < 0) {
			this.defense = 0;
		} else {
			this.defense = (int) finalDefense;
		}
	}

	// Purpose: Return the being's defense.
	// Arguments: -
	// Return: int
	public final int getDefense() {
		return this.defense;
	}

	// Purpose: Return the being's calculated damage. (damage ± 50%)
	// Arguments: -
	// Return: int
	public int generateDamage() {
		return this.attack + (int) Math.floor(Math.random() * (this.attack + 1) - this.attack * 0.5);
	}
}

