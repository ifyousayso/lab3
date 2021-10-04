public class LesserHeal extends Spell {
	private static LesserHeal lesserHeal;

	private LesserHeal() {
		super("Lesser Heal", "+20 hp", 20);
	}

	// If there's no instance of LesserHeal, create one and return a reference to it.
	// This ensures that there is only one instance of LesserHeal and that it can be accessed from anywhere.
	public static LesserHeal get() {
		if (LesserHeal.lesserHeal == null) {
			LesserHeal.lesserHeal = new LesserHeal();
		}

		return LesserHeal.lesserHeal;
	}

	// Purpose: Apply this spell's effects on the player.
	// Arguments: Player player
	// Return: -
	public void enable(Player player) {
		player.heal(20);
	}

	// Purpose: Checks whether this spell can be purchased by the player.
	// Arguments: Player player
	// Return: boolean
	public boolean isAvailable(Player player) {
		return player.getHitPoints() != player.getMaxHitPoints();
	}

	// Purpose: Print a message when buying this spell.
	// Arguments: -
	// Return: -
	public void printGoodBuy() {
		System.out.println("You stand still while the healer points at your left nipple,");
		System.out.println("blasting it with the " + this.name + " spell.");
	}
}

