/**
 * The electric interface that holds the special abilities
 * for this type and the type number (3)
 * @author Crystal Chun		ID#012680952
 *
 */
public interface Electric 
{
	/**The electric type*/
	public static final int type = 3;
	
	/**The electric type's special ability menu*/
	public static final String typeMenu = " 1. Thunder Shock"
			+ "\r\n 2. Thunderbolt"
			+ "\r\n 3. Thunder Punch";
	
	/**The thunder shock move that returns the damage*/
	public int thunderShock();
	/**The thunder bolt move that returns the damage*/
	public int thunderBolt();
	/**The thunder punch move that returns the damage*/
	public int thunderPunch();
}
