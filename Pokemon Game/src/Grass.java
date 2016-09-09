/**
 * Grass interface 
 * @author Crystal Chun		ID#012680952
 *
 */
public interface Grass 
{
	/**The grass type (2)*/
	public static final int type = 2;
	
	/**Grass type's special abilities menu*/
	public static final String typeMenu = " 1. Vine Whip"
			+ "\r\n 2. Razor Leaf"
			+ "\r\n 3. Solar Beam";
	
	/**Vine whip attack, returns the damage*/
	public int vineWhip();
	/**Razor leaf attack, returns the damage*/
	public int razorLeaf();
	/**Solar beam attack, return the damage*/
	public int solarBeam();
	
}
