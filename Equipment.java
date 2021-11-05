public abstract class Equipment extends Product {
	// Purpose: This is the class' constructor.
	// Arguments: String name, String description, int price
	public Equipment(String name, String description, int price) {
		super(name, description, price);
	}

	// Purpose: Apply this equipment's effects on the player.
	// Arguments: Player player
	// Return: -
	public abstract void enable(Player player);

	// Purpose: Remove this equipment's effects from the player.
	// Arguments: Player player
	// Return: -
	public abstract void disable(Player player);

	// Purpose: Checks whether this equipment can be purchased by the player. By default, the player may only have
	// one of each equipment type item.
	// Arguments: Player player
	// Return: boolean
	public boolean isAvailable(Player player) {
		return !player.hasProduct(this);
	}

	// Purpose: Print a message when buying this equipment.
	// Arguments: -
	// Return: -
	public abstract void printGoodBuy();
}

