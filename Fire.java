/**
 * The fire interface of all pokemon that are of
 * the type fire.
 * @author Crystal Chun
 *
 */
public interface Fire 
{
	/** The integer of the fire type */
	public static final int type = 0;
	
	/** The fire type's special attack menu */
	public static final String typeMenu = " 1. Ember"
			+ "\r\n 2. Fire Blast"
			+ "\r\n 3. Fire Punch";
	
	/**The ember attack move that returns the attack damage*/
	public int ember();
	/**The fire blast move that returns the attack damage of this move*/
	public int fireBlast();
	/**The fire punch move that returns the attack damage of this move*/
	public int firePunch();
}
