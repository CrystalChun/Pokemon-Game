/**
 * The pokemon oddish
 * @author Crystal Chun		ID#012680952
 *
 */
public class Oddish extends Pokemon implements Grass
{

	/**
	 * Constructs oddish by setting the name to oddish and
	 * the level to either 1 or 2
	 */
	public Oddish() 
	{
		super("Oddish", ((int)(Math.random() * 2)) + 1);
	}

	/**
	 * Gets oddish's type (grass = 2)
	 */
	@Override
	public int getType() 
	{
		return Grass.type;
	}

	/**
	 * Gets oddish's special fight damage
	 * @param move the move from the special fight menu
	 * @return the damage from the move
	 */
	@Override
	public int specialFight(int move) 
	{
		int attackDamage = 0;
		switch(move)
		{
			case 1:		attackDamage = vineWhip();
						break;
			case 2:		attackDamage = razorLeaf();
						break;
			case 3:		attackDamage = solarBeam();
						break;
		}
		return attackDamage;
	}

	/**
	 * Displays the special attack menu for oddish
	 */
	@Override
	public void displaySpecialMenu() 
	{
		System.out.println(Grass.typeMenu);
	}

	/**
	 * Gets the damage dealt when vine whip is called
	 * @return The damage dealt from vine whip
	 */
	@Override
	public int vineWhip() 
	{
		int upperDamage = getLevel() * 3;
		int lowerDamage = getLevel() * 2;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}

	/**
	 * Gets the damage from razor leaf
	 * @return The damage from razor leaf
	 */
	@Override
	public int razorLeaf() 
	{
		int upperDamage = getLevel() * 4;
		int lowerDamage = getLevel() * 3;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}

	/**
	 * Gets the damage from solar beam
	 * @return the damage from solar beam
	 */
	@Override
	public int solarBeam() 
	{
		int upperDamage = getLevel() * 5;
		int lowerDamage = getLevel() * 3;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}

}
