// This shop supports 0 to 9 products.
public class Shop {
	private static Shop shop;
	private Product[] products;

	private Shop() {
		products = new Product[]{AmuletOfPower.get(), StoneskinRing.get(), RepeatingCharmOfRoutine.get(), LesserHeal.get()};
	}

	// If there's no instance of Shop, create one and return a reference to it.
	// This ensures that there is only one instance of Shop and that it can be accessed from anywhere.
	public static Shop get() {
		if (Shop.shop == null) {
			Shop.shop = new Shop();
		}

		return Shop.shop;
	}

	// Purpose: Run the entire shop system. This includes the menu loop, checking what the player has or can use,
	// charging gold and supplying the items/service.
	// Arguments: Player player
	// Return: -
	public void visit(Player player) {
		byte shopMenuChoice;
		boolean shopMenuLoop = true;

		System.out.println("Welcome! What do you want to buy?");

		do {
			System.out.println("You have " + player.getGold() + " gold.\n");
			for (int i = 0; i < products.length; i++) {
				System.out.println((i + 1) + ". " + products[i].getName() + " (" + products[i].getDescription() + "): " + (products[i].isAvailable(player) ? products[i].getPrice() + " gold" : "out of stock"));
			}
			System.out.println("0. Leave the shop");
			System.out.print("> ");
			shopMenuChoice = TheGame.parseDigit(TheGame.SCANNER.nextLine(), (byte) -1);
			System.out.println();

			if (shopMenuChoice < 0 || shopMenuChoice > products.length) {
				System.out.println("Please make a valid selection.\n");
			} else if (shopMenuChoice == 0) {
				shopMenuLoop = false;
			} else {
				if (!products[shopMenuChoice - 1].isAvailable(player)) {
					System.out.println("This item is currently out of stock.");
				} else if (player.getGold() >= products[shopMenuChoice - 1].getPrice()) {
					player.alterGold(-products[shopMenuChoice - 1].getPrice());
					player.giveProduct(products[shopMenuChoice - 1]);
					products[shopMenuChoice - 1].printGoodBuy();
				} else {
					System.out.println("Sorry, you cannot afford " + products[shopMenuChoice - 1].getName() + " at the moment.");
				}
			}
		} while (shopMenuLoop);
	}
}

