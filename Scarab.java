public class Scarab extends Monster {
	// Purpose: This is the class' constructor.
	// Arguments: -
	public Scarab() {
		// name, maxHitPoints, attack, defense, experienceValue, goldValue
		super("Scarab", 80, 8, 0, 24, 32);
	}

	// Purpose: Return the monster's reaction to damage.
	// Arguments: -
	// Return: String
	public String painReaction() {
		return "- Clikkrt! *bzzzz*";
	}
}

