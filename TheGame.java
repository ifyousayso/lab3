import java.util.Scanner;

public class TheGame {
	// Create a few constants/statics to be used in the main class (everywhere).
	public static final Scanner SCANNER = new Scanner(System.in);

	public static byte parseDigit(String subject, byte defaultValue) {
		return (subject.matches("^\\d$") ? Byte.parseByte(subject) : defaultValue);
	}

	private static void waitForEnter() {
		System.out.print("[Press enter to continue] ");
		// This is a crappy solution, but fancier ones seem to require a lot more code and knowledge. :(
		SCANNER.nextLine();
		System.out.println();
	}

	private static boolean goAdventuring(Player player, Monster[] monsters) {
		boolean menuLoop = true;

		// If 0 (inclusive) to 10 (exclusive) rounded down rolls a 0 … (10%)
		if (Math.floor(Math.random() * 10) == 0) {
			System.out.println("You see nothing but swaying grass all around you …");
			// Heal 1/8 of max health.
			player.heal(player.getMaxHitPoints() >> 3);
			System.out.println("This rest allows your wounds to heal somewhat.");
			waitForEnter();
		} else {
			// Pick a monster at random from the provided list.
			Monster monster = monsters[(int) Math.floor(Math.random() * monsters.length)];
			System.out.println("Uh oh! A wild " + monster.getName() + " appeared!");
			do {
				System.out.println("You hit the monster, dealing " + monster.hurt(player.generateDamage()) + " damage.");
				if (!monster.isAlive()) {
					int goldGained = monster.generateGold(); // The gold value is random so it's retrieved only once.
					System.out.println("The monster dies, resulting in " + (monster.getExperienceValue() + player.getExperienceBonus()) + " experience and " + goldGained + " gold.");
					player.giveExperience(monster.getExperienceValue());
					// The return value is not used in this game … yet.
					player.alterGold(goldGained);
					System.out.println("You are level " + player.getLevel() + ", have " + player.getExperience() + " exp, " + player.getHitPoints() + " hp and " + player.getGold() + " gold.\n");
					monster.reset();
					if (player.isLevelMaxed()) {
						System.out.println("Congratulations! You have beaten The Game …\n");
						menuLoop = false;
					}
					break;
				}
				System.out.println(monster.painReaction());
				System.out.println("The monster hits you, dealing " + player.hurt(monster.generateDamage()) + " damage.");
				if (!player.isAlive()) {
					System.out.println("You were killed by the monster. :(\n");
					menuLoop = false;
					break;
				}
				System.out.println(player.getName() + ": " + player.getHitPoints() + " hp");
				System.out.println(monster.getName() + ": " + monster.getHitPoints() + " hp");
				waitForEnter();
			} while (true); // No need to check anything here since someone dying breaks the loop.
		}

		return menuLoop;
	}

	// Public method detected!!
	// This one creates the main variables, performs the main loop and quits.
	// Arguments: String[] args
	// Return: -
	public static void main(String[] args) {
		Player player;
		byte mainMenuChoice;
		boolean mainMenuLoop = true;
		// A Monster array is created in main() and sent to goAdventuring(). In this solution, individual monsters are not
		// created or destroyed but simply picked for a fight and then reset.
		Monster[] monsters = {new GiantWurm(), new Scarab(), new Spooder(), new Pikachu()};

		System.out.println("************************");
		System.out.println("* Welcome to The Game! *");
		System.out.println("************************\n");

		System.out.print("Enter your name, please: ");
		// The player's name can be empty.
		player = new Player(SCANNER.nextLine());
		System.out.println();

		do {
			System.out.println("1. Go adventuring");
			System.out.println("2. Show details about your character");
			System.out.println("3. Visit the shop");
			System.out.println("0. Exit to DOS");
			System.out.print("> ");
			mainMenuChoice = parseDigit(SCANNER.nextLine(), (byte) -1);
			System.out.println();

			switch (mainMenuChoice) {
				case 1:
					mainMenuLoop = goAdventuring(player, monsters);
					break;
				case 2:
					player.printDetails();
					break;
				case 3:
					Shop.get().visit(player);
					break;
				case 0:
					mainMenuLoop = false;
					break;
				default:
					System.out.println("Please make a valid selection.\n");
			}
		} while (mainMenuLoop);

		System.out.println("Bye!");
	}
}

