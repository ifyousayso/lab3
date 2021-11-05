import java.util.ArrayList;

public class Player extends Being {
	// When setting these, make sure that the levelUp() maxHitPoints never overflows (int).
	private final int MAX_LEVEL = 10;
	private final int EXP_PER_LEVEL = 100;
	private final int BASE_MAX_HP = 100;
	private final int BASE_ATTACK = 16;
	private final int BASE_DEFENSE = 0;

	private int level = 1;
	private int experience = 0;
	private int experienceBonus = 0;
	private int gold = 0;
	private ArrayList<Product> products = new ArrayList<Product>();

	// Purpose: This is the class' constructor.
	// Arguments: String name
	public Player(String name) {
		super(name);
		this.maxHitPoints = BASE_MAX_HP;
		this.hitPoints = BASE_MAX_HP;
		this.attack = BASE_ATTACK;
		this.defense = BASE_DEFENSE;
	}

	private void levelUp() {
		if (this.level < MAX_LEVEL) {
			// A level-up gives some max hp, +1 attack and +1 defense.
			this.level++;
			this.maxHitPoints = BASE_MAX_HP + Math.round((BASE_MAX_HP * (this.level - 1)) / (MAX_LEVEL << 1));
			this.attack++;
			this.defense++;
			System.out.println("Enough experience has piled up and you are now level " + this.level + "!");
		}
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

	// Purpose: Boost the player's experience and increase their level when appropriate. The return value indicates the
	// modified experience after bonus.
	// Arguments: int experience
	// Return: int
	public int giveExperience(int experience) {
		if (this.isAlive() == false || experience < 1) {
			return 0;
		}

		boolean experienceLoop = true;
		int bonusedExperience;

		bonusedExperience = experience + this.experienceBonus;
		experience = bonusedExperience;

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

		return bonusedExperience;
	}

	// Purpose: Return the player's experience.
	// Arguments: -
	// Return: int
	public int getExperience() {
		return this.experience;
	}

	// Purpose: Alter the player's experience bonus. If the new bonus would go beyond the boundaries of an int, return
	// false.
	// Arguments: int experienceBonus
	// Return: boolean
	public boolean alterExperienceBonus(int experienceBonus) {
		long finalExperienceBonus = this.experienceBonus + experienceBonus;

		if (finalExperienceBonus < Integer.MIN_VALUE || finalExperienceBonus > Integer.MAX_VALUE) {
			return false;
		}

		this.experienceBonus = (int) finalExperienceBonus;
		return true;
	}

	// Purpose: Return the player's experience bonus.
	// Arguments: -
	// Return: int
	public int getExperienceBonus() {
		return this.experienceBonus;
	}

	// Purpose: This changes the player's gold by a certain amount. The return value is whatever gold is left after
	// hitting 0 or Integer.MAX_VALUE. It should be 0.
	// Arguments: int gold
	// Return: int
	public int alterGold(int gold) {
		long finalGold = this.gold + gold;

		if (finalGold > Integer.MAX_VALUE) {
			this.gold = Integer.MAX_VALUE;
			return (int) (finalGold - this.gold);
		}

		if (finalGold < 0) {
			this.gold = 0;
			return (int) finalGold;
		} else {
			this.gold = (int) finalGold;
			return 0;
		}
	}

	// Purpose: Return the player's gold.
	// Arguments: -
	// Return: int
	public int getGold() {
		return this.gold;
	}

	// Purpose: Attempts to give a shop product. The return value indicates the success.
	// Arguments: Product product
	// Return: boolean
	public boolean giveProduct(Product product) {
		if (!product.isAvailable(this)) {
			return false;
		}

		// Save only if it's equipment.
		if (Equipment.class.isInstance(product)) {
			this.products.add(product);
		}
		product.enable(this);
		return true;
	}

	// Purpose: Attempts to remove a product (piece of equipment). The return value indicates the success.
	// Arguments: Product product
	// Return: boolean
	public boolean takeProduct(Product product) {
		if (this.products.contains(product)) {
			product.disable(this);
		}

		return this.products.remove(product); // ArrayList.remove() returns a boolean if the object was found.
	}

	// Purpose: Checks if the player has a specific product.
	// Arguments: Product product
	// Return: boolean
	public boolean hasProduct(Product product) {
		return this.products.contains(product);
	}

	// Purpose: This prints information about the player.
	// Arguments: -
	// Return: -
	public void printDetails() {
		String equipmentList = "";
		String separator = ",\n           ";
		for (int i = 0; i < this.products.size(); i++) {
			equipmentList += products.get(i).getName() + separator;
		}
		equipmentList = (this.products.size() == 0 ? "-" : equipmentList.substring(0, equipmentList.length() - separator.length()));

		System.out.println("     Name: " + this.name);
		System.out.println("    Level: " + this.level);
		System.out.println("      Exp: " + this.experience + "/" + EXP_PER_LEVEL);
		System.out.println("Exp bonus: " + this.experienceBonus);
		System.out.println("   Health: " + this.hitPoints + "/" + this.maxHitPoints);
		System.out.println("   Attack: " + this.attack);
		System.out.println("  Defense: " + this.defense);
		System.out.println("     Gold: " + this.gold);
		System.out.println("Equipment: " + equipmentList + "\n");
	}
}

