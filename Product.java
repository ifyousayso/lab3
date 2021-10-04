public abstract class Product {
	protected String name;
	protected String description;
	protected int price;

	// Purpose: This is the class' constructor.
	// Arguments: String name, String description, int price
	public Product(String name, String description, int price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	// Purpose: Retrieve the product's description for the shop.
	// Arguments: -
	// Return: String
	public final String getDescription() {
		return this.description;
	}

	// Purpose: Return the product's name.
	// Arguments: -
	// Return: String
	public final String getName() {
		return this.name;
	}

	// Purpose: Return the product's price.
	// Arguments: -
	// Return: int
	public final int getPrice() {
		return this.price;
	}

	// Purpose: Apply this product's effects on the player.
	// Arguments: Player player
	// Return: -
	public abstract void enable(Player player);

	// Purpose: Remove this product's effects from the player.
	// Arguments: Player player
	// Return: -
	public abstract void disable(Player player);

	// Purpose: Checks whether this product can be purchased by the player.
	// Arguments: Player player
	// Return: boolean
	public abstract boolean isAvailable(Player player);

	// Purpose: Print a message when buying this product.
	// Arguments: -
	// Return: -
	public abstract void printGoodBuy();
}

