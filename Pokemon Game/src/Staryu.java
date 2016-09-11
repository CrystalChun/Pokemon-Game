/**
 * Staryu pokemon
 * @author Crystal Chun		ID#012680952
 *
 */
public class Staryu extends Pokemon implements Water
{
	/**
	 * Constructs the staryu pokemon
	 */
	public Staryu() 
	{
		super("Staryu", ((int)(Math.random() * 2)) + 1);
	}

	/**
	 * Gets staryu's type (water = 1)
	 */
	@Override
	public int getType() 
	{

		return Water.type;
	}

	/**
	 * Calls staryu's special attacks
	 * @param move The specific special attack move
	 * @return The damage for that special move
	 */
	@Override
	public int specialFight(int move) 
	{
		int attackDamage = 0;
		switch(move)
		{
			case 1:		attackDamage = waterGun();
						break;
			case 2:		attackDamage = bubbleBeam();
						break;
			case 3:		attackDamage = waterfall();
						break;
		}
		return attackDamage;
	}

	/**
	 * Displays staryu's special attacks menu
	 */
	@Override
	public void displaySpecialMenu() 
	{
		System.out.println(Water.typeMenu);
	}

	/**
	 * Gets the water gun attack move's damage
	 * @return water gun attack damage
	 */
	@Override
	public int waterGun() 
	{
		int upperDamage = getLevel() * 3;
		int lowerDamage = getLevel() * 2;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}

	/**
	 * Gets bubble beam's attack damage
	 * @return bubble beam's attack damage
	 */
	@Override
	public int bubbleBeam() 
	{
		int upperDamage = getLevel() * 4;
		int lowerDamage = getLevel() * 3;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}

	/**
	 * Gets waterfall's attack damage
	 * @return waterfall's attack damage
	 */
	@Override
	public int waterfall() 
	{
		int upperDamage = getLevel() * 5;
		int lowerDamage = getLevel() * 3;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}
}
