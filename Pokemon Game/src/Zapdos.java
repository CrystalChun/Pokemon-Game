/**
 * Holds attacks and pokemon type for the zapdos pokemon
 * @author Crystal Chun		ID#012680952
 *
 */
public class Zapdos extends Pokemon implements Electric
{
	/**
	 * Constructs a zapdos
	 */
	public Zapdos() 
	{
		super("Zapdos", ((int)(Math.random() * 2)) + 1);

	}
	
	/**
	 * Displays zapdos' special attack menu
	 */
	@Override
	public void displaySpecialMenu()
	{
		System.out.println(Electric.typeMenu);
	}
	
	/**
	 * Gets the attack damage for the specific special attack
	 * zapdos will use
	 * @param move The special attack move zapdos will use
	 * @return The damage from the special attack
	 */
	@Override
	public int specialFight(int move)
	{
		int attack = 0;
		switch(move)
		{
			case 1:		attack = thunderShock();
						break;
			case 2:		attack = thunderBolt();
						break;	
			case 3:		attack = thunderPunch();
						break;
		}
		return attack;
	}
	
	/**
	 * Gets the damage from thunder shock attack
	 * @return thunder shock's attack damage
	 */
	@Override
	public int thunderShock()
	{
		System.out.println(super.getName() + " used thunder shock!");
		int highestDamage = getLevel() * 3;
		int lowestDamage = getLevel() * 2;
		
		int damage = (int)(Math.random() * (highestDamage - lowestDamage + 1)) + lowestDamage;
		return damage;
	}
	
	/**
	 * Gets thunder bolt's attack damage
	 * @return Thunder bolt's attack damage 
	 */
	@Override
	public int thunderBolt()
	{
		System.out.println(super.getName() + " used thunder bolt!");
		int highestDamage = getLevel() * 5;
		int lowestDamage = getLevel() * 3;
		
		int damage = (int)(Math.random()*(highestDamage - lowestDamage + 1)) + lowestDamage;
		return damage;
	}
	
	/**
	 * Gets thunder punch's attack damage
	 * @return thunder punch's attack damage
	 */
	@Override
	public int thunderPunch()
	{
		System.out.println(super.getName() + " used thunder punch!");
		int highestDamage = getLevel() * 4;
		int lowestDamage = getLevel() * 3;
		
		int damage = (int)(Math.random() * (highestDamage - lowestDamage + 1)) + lowestDamage;
		return damage;	
	}
	
	/**
	 * Gets zapdos' type (electric = 3)
	 * @return zapdos' type (3 = electric)
	 */
	@Override
	public int getType() 
	{
		return Electric.type;
	}

}
