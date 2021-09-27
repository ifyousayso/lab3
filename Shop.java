public class Shop {
	private static Shop shop;

	// Prevent uncontrolled creation of Shop instances.
	private Shop() {}

	// If there's no instance of Shop, create one and return a reference to it.
	// This ensures that there is only one instance of Shop and that it can be accessed from anywhere.
	public static Shop getShop() {
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
		char shopMenuChoice;
		boolean shopMenuLoop = true;

		System.out.println("Welcome! What do you want to buy?");

		// The items, with properties and strings, could be placed in classes of their own, but I'm too lazy …
		do {
			System.out.println("You have " + player.getGold() + " gold.\n");
			System.out.println("1. Amulet of Power (+4 attack): " + (player.hasEquipment(Equipment.AMULET_OF_POWER) ? "out of stock" : "100 gold"));
			System.out.println("2. Stoneskin Ring (+2 defense): " + (player.hasEquipment(Equipment.STONESKIN_RING) ? "out of stock" : "100 gold"));
			System.out.println("3. Lesser Heal (+20 hp): " + (player.getHitPoints() == player.getMaxHitPoints() ? "out of stock" : "20 gold"));
			System.out.println("0. Leave the shop");
			System.out.print("> ");
			shopMenuChoice = TheGame.parseChar(TheGame.SCANNER.nextLine(), ' ');
			System.out.println();

			switch (shopMenuChoice) {
				case '1':
					if (player.hasEquipment(Equipment.AMULET_OF_POWER)) {
						System.out.println("This item is currently out of stock.");
					} else if (player.getGold() >= 100) {
						player.alterGold(-100);
						player.giveEquipment(Equipment.AMULET_OF_POWER);
						System.out.println("You equip your brand new Amulet of Power and feel stronger than ever before!");
					} else {
						System.out.println("Sorry, you don't have enough gold for this exquisite piece of jewelry.");
					}
					break;
				case '2':
					if (player.hasEquipment(Equipment.STONESKIN_RING)) {
						System.out.println("This item is currently out of stock.");
					} else if (player.getGold() >= 100) {
						player.alterGold(-100);
						player.giveEquipment(Equipment.STONESKIN_RING);
						System.out.println("You equip your brand new Stoneskin Ring and feel tougher than ever before!");
					} else {
						System.out.println("Sorry, you don't have enough gold for this exquisite piece of jewelry.");
					}
					break;
				case '3':
					if (player.getHitPoints() == player.getMaxHitPoints()) {
						System.out.println("The healer is on vacation and cannot help you today.");
					} else if (player.getGold() >= 20) {
						player.alterGold(-20);
						player.heal(20);
						System.out.println("You stand still while the healer points at your left nipple,");
						System.out.println("blasting it with the Lesser Heal spell - current hp: " + player.getHitPoints() + ".");
					} else {
						System.out.println("Sorry, you don't have enough gold for this service.");
					}
					break;
				case '0':
					shopMenuLoop = false;
					break;
				default:
					System.out.println("Please make a valid selection.\n");
			}
		} while (shopMenuLoop);
	}
}

