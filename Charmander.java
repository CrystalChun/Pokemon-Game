/**
 * The pokemon charmander that has the charmander's special abilities
 * @author Crystal Chun 		ID#012680952
 *
 */
public class Charmander extends Pokemon implements Fire
{
	/**
	 * Constructs a charmander
	 */
	public Charmander()
	{
		//Calls parent class and constructs a charmander pokemon that's level 1 or 2
		super("Charmander", ((int)(Math.random() * 2)) + 1);	
	}
	public Charmander(int level)
	{
		super("Charmander", level);
	}

	/**
	 * Displays charmander's special attack moves 
	 */
	@Override
	public void displaySpecialMenu()
	{
		System.out.println(Fire.typeMenu);
	}
	
	/**
	 * Gets the special fight's move's damage
	 * @param move the special attack move
	 * @return The damage from the special move
	 */
	@Override
	public int specialFight(int move)
	{
		int attackDamage = 0;
		switch(move)
		{
			case 1: 	attackDamage = ember();
						break;
						
			case 2: 	attackDamage = fireBlast();
						break;
						
			case 3: 	attackDamage = firePunch();
						break;
		}
		return attackDamage;
	}

	/**
	 * Gets the attack damage from using ember
	 * @return the attack damage from ember
	 */
	@Override
	public int ember()
	{
		System.out.println(super.getName() + " uses Ember! (੭•̀ω•́)੭̸*炎炎炎炎");
		int upperDamage = getLevel() * 3;
		int lowestDamage = getLevel() * 2;
		
		int damage = (int)(Math.random() * (upperDamage - lowestDamage + 1)) + lowestDamage;
		return damage;
	}
	
	/**
	 * Gets the attack damage from using fire blast
	 * @return The attack damage from fire blast
	 */
	@Override
	public int fireBlast()
	{
		System.out.println(super.getName() + " inhales deeply and lets out a Fire Blast! "
				+ "\r\n (┛｀Д´)┛・‥…炎炎炎炎炎炎炎 ROAR");
		int upperDamage = getLevel() * 5;
		int lowestDamage = getLevel() * 3;
		
		int damage = (int)(Math.random() * (upperDamage - lowestDamage + 1)) + lowestDamage;
		return damage;
	}
	
	/**
	 * Gets the attack damage from using fire punch
	 * @return The attack damage from fire punch
	 */
	@Override
	public int firePunch()
	{
		System.out.println(super.getName() + " balls up its fists and rushes forward using Fire Punch!!"
				+ "\r\n ‾͟͟͞(((ꎤˋ⁻̫ˊ)—̳͟͞͞o炎炎炎 POWWWWW");
		int upperDamage = getLevel() * 5;
		int lowestDamage = getLevel() * 3;
		
		int damage = (int)(Math.random()*(upperDamage - lowestDamage + 1)) + lowestDamage;
		return damage;
	}
	
	/**
	 * Gets charmander's type (fire = 0)
	 * @return Charmander's type (0 = fire)
	 */
	@Override
	public int getType() 
	{
		return Fire.type;
	}
}
