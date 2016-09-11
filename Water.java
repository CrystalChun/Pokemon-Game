/**
 * The water type interface
 * @author Crystal Chun
 *
 */
public interface Water 
{
	/**Water's type (1)*/
	public static final int type = 1;
	/**Water's special attack menu*/
	public static final String typeMenu = " 1. Water Gun"
			+ "\r\n 2. Bubble Beam"
			+ "\r\n 3. Waterfall";
	/**Water gun attack move, returns the damage done by this move*/
	public int waterGun();
	/**Bubble beam's attack move, returns the damage done by this move*/
	public int bubbleBeam();
	/**Waterfall's attack move, returns the damage done by this move*/
	public int waterfall();
}
