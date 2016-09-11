/**
 * This is a pikachu, which extends from the pokemon class
 * and implements the electric type.
 * @author Crystal Chun		ID#012680952
 *
 */
public class Pikachu extends Pokemon implements Electric
{
	/**
	 * Constructs the pikachu by setting the name to pikachu and the 
	 * level to either 1 or 2
	 */
	public Pikachu()
	{
		super("Pikachu", ((int)(Math.random() * 2)) + 1);
	}
	public Pikachu(int level)
	{
		super("Pikachu", level);
	}

	/**
	 * This menu comes from the pokemon class and it displays the pokemon's 
	 * special abilities
	 */
	@Override
	public void displaySpecialMenu()
	{
		System.out.println(Electric.typeMenu);

	}

	/**
	 * Takes in the special move that the pokemon
	 * is going to use and calls the specific attack method.
	 * Returns the damage it does.
	 * @param move the special move
	 * @return The damage the special attack does
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
	 * Thunder shock attack that deals damage in a range from 
	 * the pokemon's level times two to the pokemon's level
	 * times three.
	 * @return The attack damage dealt
	 */
	@Override
	public int thunderShock()
	{
		System.out.println(super.getName() + " uses Thunder Shock!"
				+ "\r\n ヽ( ･益･)ﾉﾟ･*:.｡. .｡.:*･゜ﾟ･*oooh shocker ~zing~");		
		int highestDamage = getLevel() * 3;
		int lowestDamage = getLevel() * 2;
		
		int damage = (int)(Math.random() * (highestDamage - lowestDamage + 1)) + lowestDamage;
		return damage;
	}
	
	/**
	 * Thunder bolt attack that deals damage in a range from the pokemon's level
	 * times three to the pokemon's level times five.
	 * @return The attack damage
	 */
	@Override
	public int thunderBolt()
	{
		System.out.println(super.getName() + "Thunder Bolt! ϟ ZAPPERINO ϟ");
		int highestDamage = getLevel() * 5;
		int lowestDamage = getLevel() * 3;
		
		int damage = (int)(Math.random()*(highestDamage - lowestDamage + 1)) + lowestDamage;
		return damage;
	}
	
	/**
	 * Thunder punch attack that deals damage in a range from the pokemon's level times three
	 * to the pokemon's level times four.
	 * @return The attack damage
	 */
	@Override
	public int thunderPunch()
	{
		System.out.println(super.getName() + " gathers electricity in its fist and uses Thunder PUNCH!!"
				+ "\r\n ᕦ( ✿ ⊙ ͜ʖ ⊙ ✿ )⊃☆ﾟ.*･｡ﾟ ZA POWWWW");
		int highestDamage = getLevel() * 4;
		int lowestDamage = getLevel() * 3;
		
		int damage = (int)(Math.random() * (highestDamage - lowestDamage + 1)) + lowestDamage;
		return damage;	
	}
	
	/**
	 * Gets the pikachu's type, which is electric
	 * and an integer value of 3.
	 * @return The electric type value = 3
	 */
	@Override
	public int getType() 
	{
		return Electric.type;
	}
	
}
