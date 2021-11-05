public class GiantWurm extends Monster {
	// Purpose: This is the class' constructor.
	// Arguments: -
	public GiantWurm() {
		// name, maxHitPoints, attack, defense, experienceValue, goldValue
		super("Giant Wurm", 36, 12, 4, 36, 24);
	}

	// Purpose: Return the monster's reaction to damage.
	// Arguments: -
	// Return: String
	public String painReaction() {
		return "- UUuoooaah! *slurp*";
	}
}

