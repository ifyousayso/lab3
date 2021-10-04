public class Spooder extends Monster {
	// Purpose: This is the class' constructor.
	// Arguments: -
	public Spooder() {
		// name, maxHitPoints, attack, defense, experienceValue, goldValue
		super("Spooder", 40, 20, 0, 30, 30);
	}

	// Purpose: Return the monster's reaction to damage.
	// Arguments: -
	// Return: String
	public String painReaction() {
		return "- Sccrrr! *skitter*";
	}
}

