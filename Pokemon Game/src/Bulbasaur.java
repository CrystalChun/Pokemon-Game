/**
 * Holds the methods for a bulbasaur
 * @author Crystal Chun		ID#012680952
 *
 */
public class Bulbasaur extends Pokemon implements Grass
{
	/**
	 * Constructs a bulbasaur
	 */
	public Bulbasaur()
	{
		super("Bulbasaur", ((int)(Math.random() * 2)) + 1);
	}
	public Bulbasaur(int level)
	{
		super("Bulbasaur", level);
	}

	/**
	 * Gets bulbasaur's type (grass = 3)
	 */
	@Override
	public int getType() 
	{
		return Grass.type;
	}

	/**
	 * Gets bulbasaur's special attack moves for battle
	 * @param move Bulbasaur's special attack move
	 * @return the damage for bulbasaur's special attack
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
	 * Displays the menu for bulbasaur's special attack moves
	 */
	@Override
	public void displaySpecialMenu() 
	{
		System.out.println(Grass.typeMenu);
	}

	/**
	 * Simulates the vine whip attack and calculates the attack damage from it.
	 * @return Vine whip's attack damage
	 */
	@Override
	public int vineWhip() 
	{
		System.out.println(super.getName() + " used vine whip!");
		int upperDamage = getLevel() * 3;
		int lowerDamage = getLevel() * 2;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}

	/**
	 * Gets razor leaf's attack damage
	 * @return Razor leaf's attack damage
	 */
	@Override
	public int razorLeaf() 
	{
		System.out.println(super.getName() + " used razor leaf!");
		int upperDamage = getLevel() * 4;
		int lowerDamage = getLevel() * 3;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}

	/**
	 * Gets solar beam's attack damage
	 * @return Solar beam's attack damage
	 */
	@Override
	public int solarBeam() 
	{
		System.out.println(super.getName() + " used solar beam!");
		int upperDamage = getLevel() * 5;
		int lowerDamage = getLevel() * 3;
		
		int damage = (int) (Math.random() * (upperDamage - lowerDamage + 1)) + lowerDamage;
		return damage;
	}


}
