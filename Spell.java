public abstract class Spell extends Product {
	// Purpose: This is the class' constructor.
	// Arguments: String name, String description, int price
	public Spell(String name, String description, int price) {
		super(name, description, price);
	}

	// Purpose: Apply this spell's effects on the player.
	// Arguments: Player player
	// Return: -
	public abstract void enable(Player player);

	// Purpose: This doesn't do anything for spells since they cannot be removed.
	// Arguments: Player player
	// Return: -
	public final void disable(Player player) {}

	// Purpose: Checks whether this spell can be purchased by the player. This is just true by default.
	// Arguments: Player player
	// Return: boolean
	public boolean isAvailable(Player player) {
		return true;
	}

	// Purpose: Print a message when buying this spell.
	// Arguments: -
	// Return: -
	public abstract void printGoodBuy();
}

