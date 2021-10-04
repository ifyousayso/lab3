public class RepeatingCharmOfRoutine extends Equipment {
	private static RepeatingCharmOfRoutine repeatingCharmOfRoutine;

	private RepeatingCharmOfRoutine() {
		super("Repeating Charm of Routine", "+8 exp bonus", 100);
	}

	// If there's no instance of StoneskinRing, create one and return a reference to it.
	// This ensures that there is only one instance of StoneskinRing and that it can be accessed from anywhere.
	public static RepeatingCharmOfRoutine get() {
		if (RepeatingCharmOfRoutine.repeatingCharmOfRoutine == null) {
			RepeatingCharmOfRoutine.repeatingCharmOfRoutine = new RepeatingCharmOfRoutine();
		}

		return RepeatingCharmOfRoutine.repeatingCharmOfRoutine;
	}

	// Purpose: Apply this equipment's effects on the player.
	// Arguments: Player player
	// Return: -
	public void enable(Player player) {
		player.alterExperienceBonus(8);
	}

	// Purpose: Remove this equipment's effects from the player.
	// Arguments: Player player
	// Return: -
	public void disable(Player player) {
		player.alterExperienceBonus(-8);
	}

	// Purpose: Print a message when buying this equipment.
	// Arguments: -
	// Return: -
	public void printGoodBuy() {
		System.out.println("The " + this.name + " certainly makes monotonous tasks \nless tedious …");
	}
}

