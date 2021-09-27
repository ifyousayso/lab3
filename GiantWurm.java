public class GiantWurm extends Monster {
	// Purpose: This is the class' constructor.
	// Arguments: -
	public GiantWurm() {
		// name, maxHitPoints, attack, defense, experienceValue, goldValue
		super("Giant Wurm", 60, 14, 0, 36, 20);
	}

	// Purpose: Return the monster's reaction to damage.
	// Arguments: -
	// Return: String
	public String painReaction() {
		return "- UUuoooaah! *slurp*";
	}
}

