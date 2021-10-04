public class StoneskinRing extends Equipment {
	private static StoneskinRing stoneskinRing;

	private StoneskinRing() {
		super("Stoneskin Ring", "+2 defense", 100);
	}

	// If there's no instance of StoneskinRing, create one and return a reference to it.
	// This ensures that there is only one instance of StoneskinRing and that it can be accessed from anywhere.
	public static StoneskinRing get() {
		if (StoneskinRing.stoneskinRing == null) {
			StoneskinRing.stoneskinRing = new StoneskinRing();
		}

		return StoneskinRing.stoneskinRing;
	}

	// Purpose: Apply this equipment's effects on the player.
	// Arguments: Player player
	// Return: -
	public void enable(Player player) {
		player.alterDefense(2);
	}

	// Purpose: Remove this equipment's effects from the player.
	// Arguments: Player player
	// Return: -
	public void disable(Player player) {
		player.alterDefense(-2);
	}

	// Purpose: Print a message when buying this equipment.
	// Arguments: -
	// Return: -
	public void printGoodBuy() {
		System.out.println("You equip your brand new " + this.name + " and feel tougher than ever before!");
	}
}

