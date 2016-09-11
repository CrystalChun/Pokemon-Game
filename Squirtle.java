/**
 * Squirtle pokemon class
 * @author Crystal Chun		ID#012680952
 *
 */
public class Squirtle extends Pokemon implements Water
{
	/**
	 * Constructs the squirtle
	 */
	public Squirtle() 
	{
		super("Squirtle", ((int)(Math.random() * 2)) + 1);
	}
	
	public Squirtle(int level)
	{
		super("Squirtle", level);
	}

	/**
	 * Gets the squirtle's type, which is water (1)
	 * @return Squirtle's type (water = 1)
	 */
	@Override
	public int getType() 
	{

		return Water.type;
	}

	/**
	 * Gets the special move's damage
	 * @param move The specific special move
	 * @return The special move's damage
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
	 * Displays the special menu for squirtle
	 */
	@Override
	public void displaySpecialMenu() 
	{
		System.out.println(Water.typeMenu);
	}

	/**
	 * Gets the damage for the water gun move
	 * @return the damage for water gun
	 */
	@Override
	public int waterGun() 
	{
		System.out.println(super.getName() + " loads up on water and uses Water Gun! (･ω･)┌三三三");
		int upperDamage = getLevel() * 3;
		int lowerDamage = getLevel() * 2;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}

	/**
	 * Gets the damage for bubble beam
	 * @return the damage for bubble beam
	 */
	@Override
	public int bubbleBeam() 
	{
		System.out.println(super.getName() + " unleashes a Bubble Beam on its opponent!"
				+ "\r\n 0( =^･_･^)=〇♪.｡*･゜ﾟ･*｡.");
		int upperDamage = getLevel() * 4;
		int lowerDamage = getLevel() * 3;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}

	/**
	 * Gets the damage for waterfall
	 * @return the damage for waterfall
	 */
	@Override
	public int waterfall() 
	{
		System.out.println(super.getName() + " gathers all moisture from the surrounding air "
				+ "and creates a massive waterfall above its opponent!");
		int upperDamage = getLevel() * 5;
		int lowerDamage = getLevel() * 3;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}

}
