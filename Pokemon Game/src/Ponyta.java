/**
 * The ponyta pokemon
 * @author Crystal Chun		ID#012680952
 *
 */
public class Ponyta extends Pokemon implements Fire
{
	/**
	 * Constructs a ponyta
	 */
	public Ponyta() 
	{
		super("Ponyta", ((int)(Math.random() * 2)) + 1);
	}

	/**
	 * Gets ponyta's type (fire = 0)
	 */
	@Override
	public int getType() 
	{
		return Fire.type;
	}

	/**
	 * Gets the damage dealt for a pokemon's special ability fight
	 * @param move the special attack move
	 * @return The attack move's damage
	 */
	@Override
	public int specialFight(int move) 
	{
		int attackDamage = 0;
		switch(move)
		{
			case 1:		attackDamage = ember();
						break;
			case 2:		attackDamage = fireBlast();
						break;
			case 3:		attackDamage = firePunch();
						break;
		}
		return attackDamage;
	}

	/**
	 * Displays the special menu for this ponyta's special attack
	 */
	@Override
	public void displaySpecialMenu() 
	{
		System.out.println(Fire.typeMenu);
		
	}

	/**
	 * The attack ember's damage
	 * @return Ember's attack damage
	 */
	@Override
	public int ember()
	{
		int upperDamage = getLevel() * 3;
		int lowestDamage = getLevel() * 2;
		int damage = (int)(Math.random() * (upperDamage - lowestDamage + 1)) + lowestDamage;
		return damage;
	}
	
	/**
	 * Fire blast attack's damage
	 * @return Fire blast's attack damage
	 */
	@Override
	public int fireBlast()
	{
		int upperDamage = getLevel() * 5;
		int lowestDamage = getLevel() * 3;
		int damage = (int)(Math.random() * (upperDamage - lowestDamage + 1)) + lowestDamage;
		return damage;
	}
	
	/**
	 * Fire punch attack damage
	 * @return Fire punch's attack damage
	 */
	@Override
	public int firePunch()
	{
		int upperDamage = getLevel() * 5;
		int lowestDamage = getLevel() * 3;
		int damage = (int)(Math.random() * (upperDamage - lowestDamage + 1)) + lowestDamage;
		return damage;
	}

}
