public class AmuletOfPower extends Equipment {
	private static AmuletOfPower amuletOfPower;

	private AmuletOfPower() {
		super("Amulet of Power", "+4 attack", 100);
	}

	// If there's no instance of AmuletOfPower, create one and return a reference to it.
	// This ensures that there is only one instance of AmuletOfPower and that it can be accessed from anywhere.
	public static AmuletOfPower get() {
		if (AmuletOfPower.amuletOfPower == null) {
			AmuletOfPower.amuletOfPower = new AmuletOfPower();
		}

		return AmuletOfPower.amuletOfPower;
	}

	// Purpose: Apply this equipment's effects on the player.
	// Arguments: Player player
	// Return: -
	public void enable(Player player) {
		player.alterAttack(4);
	}

	// Purpose: Remove this equipment's effects from the player.
	// Arguments: Player player
	// Return: -
	public void disable(Player player) {
		player.alterAttack(-4);
	}

	// Purpose: Print a message when buying this equipment.
	// Arguments: -
	// Return: -
	public void printGoodBuy() {
		System.out.println("You equip your brand new " + this.name + " and feel stronger than ever before!");
	}
}

