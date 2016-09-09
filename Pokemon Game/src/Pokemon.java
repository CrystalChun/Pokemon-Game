/**
 * An abstract pokemon class with which various
 * pokemons can be made from. This class extends the
 * entity class and stores a name, level, hp, exp, and
 * the next level exp of a pokemon. It also contains a
 * pokemon's basic attacks.
 * @author Crystal Chun		ID# 012680952
 *
 */
public abstract class Pokemon extends Entity
{
	/**The pokemon's level, stored as an integer*/
	private int level;
	/**The pokemon's experience points, stored as an integer*/
	private int exp;
	/**The experience points needed at the pokemon's current level in order to get to the next level*/
	private int nextLevelExp;
	/**Holds whether or not the pokemon is angry*/
	private boolean anger;
	
	/**
	 * Constructs the pokemon by calling entity's constructor and passing in a string
	 * for the name and a calculated random hp value based on the level. Then it stores
	 * the pokemon's level, sets the pokemon's exp to zero, and calculates the next level
	 * exp.
	 * @param name A string variable that's the name of the pokemon.
	 * @param level An integer that's the pokemon's level.
	 */
	public Pokemon(String name, int level)
	{
		/*
		 * Entity's constructor:
		 * Name is set to the String name passed in
		 * HP is set to a random integer between (level * 10) to (level * 15)
		 */
		super(name, 
				(int)(Math.random()*((level * 15) - (level * 10) + 1) + (level * 10)));
		
		exp = 0;
		nextLevelExp = level * 4;
		this.level = level;
	}
	
	/**
	 * An abstract method which all pokemon must have. It returns the pokemon's type (int).
	 * @return The pokemon's type (0 = fire, 1 = water, 2 = grass, 3 = electric).
	 */
	public abstract int getType();
	
	/**
	 * Takes in the move (int) that the trainer wants to use based off of the pokemon's
	 * special fight ability. The move then calls the actual move itself to calculate how
	 * much damage it does.
	 * @param move An integer value that corresponds to the pokemon's special fight ability
	 * @return An integer value which reflects how much damage the ability causes.
	 */
	public abstract int specialFight(int move);
	
	/**
	 * Displays the pokemon's special abilities.
	 */
	public abstract void displaySpecialMenu();
	
	/**
	 * Sets the pokemon's name to the string passed in by calling entity's
	 * method to change the name and passing this string in.
	 * @param name The string value of the new name.
	 */
	public void setName(String name)
	{
		super.changeName(name);
	}
	
	/**
	 * Increases the next level exp when the pokemon levels up.
	 * Next level experience is four times the pokemon's level.
	 */
	public void increaseEXP()
	{
		nextLevelExp = level * 4;
	}
	
	/**
	 * Displays a pokemon's stats.
	 */
	public void displayPokemon()
	{
		String hpBar = "";
		String expBar = "";
		
		//Creates the hp bar
		for(int i = 0; i < super.getMaxHP(); i++)
		{
			if(i < super.getHP())
			{
				hpBar = hpBar + "▓";
			}
			else
			{
				hpBar = hpBar + "░";
			}
		}
		
		//Creates the exp bar
		for(int i = 0; i < nextLevelExp; i++)
		{
			if(i < exp)
			{
				expBar = expBar + "★";
			}
			else
			{
				expBar = expBar + "☆";
			}
		}
		
		//Prints out all the stats
		System.out.printf("%16s %-15s "
				+ "\r\n%20s%-10d"
				+ "\r\n%20s%-40s%10d/%-10d"
				+ "\r\n%20s%-40s%8d/%-10d"
				+ "\r\n", 
				"Name:", super.getName(), 
				"Level: ", level, 
				"HP: ", hpBar, super.getHP(), super.getMaxHP(), 
				"Experience: ", expBar, exp, nextLevelExp);
	}
	
	/**
	 * Overloaded display pokemon method for when displaying a pokemon
	 * in battle. This displays only the pokemon's name, level, and
	 * hp bar.
	 * @param forBattle Boolean variable which shows that it displays the pokemon for battle
	 */
	public void displayPokemon(Pokemon oppPoke)
	{
		//Creates the hp bar
		String hpBar = "";
		for(int i = 0; i < super.getMaxHP(); i++)
		{
			if(i < super.getHP())
			{
				hpBar = hpBar + "▓";
			}
			else
			{
				hpBar = hpBar + "░";
			}
		}
		
		String oppHpBar = "";
		for(int i = 0; i < oppPoke.getMaxHP(); i++)
		{
			if(i < oppPoke.getHP())
			{
				oppHpBar = oppHpBar + "▓";
			}
			else
			{
				oppHpBar = oppHpBar + "░";
			}
		}
		
		System.out.printf("%-40s vs. %-15s\r\n","Your pokemon", oppPoke.getName());
		//Prints out the name, level, and hp in a formatted way.
		System.out.printf("%-6s %-37s |%-7s %-15s"
				+ "\r\n%-7s%-37d |%-8s%-10d"
				+ "\r\n%-7s%-30s%3d/%-3d |%-8s%-30s%10d/%-10d"
				+ "\r\n"
				+ "\r\n", 
				"Name:", super.getName(), "Name:", oppPoke.getName(),
				"Level: ", level, "Level:", oppPoke.getLevel(),
				"HP: ", hpBar, super.getHP(), super.getMaxHP(),
				"HP: ", oppHpBar, oppPoke.getHP(), oppPoke.getMaxHP());
	}
	
	/**
	 * Gets the pokemon's current level.
	 * @return The pokemon's current level (integer value)
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Gets the pokemon's current experience points.
	 * @return The experience points (int)
	 */
	public int getExp()
	{
		return exp;
	}
	
	/**
	 * Gets how many experience points the pokemon
	 * needs to get to the next level.
	 * @return The total experience points needed to level up.
	 */
	public int getNextLevelExp()
	{
		return nextLevelExp;
	}
	
	/**
	 * Increases the pokemon's experience points, and adjusts
	 * the total experience points, the next level experience points,
	 * and the level if the pokemon's experience points reaches the amount
	 * of experience points needed for the pokemon to level up.
	 * @param gain_exp The amount of experience points the pokemon gained.
	 * @return The current experience points the pokemon has.
	 */
	public int gainExp(int gain_exp)
	{
		//Adds the experience points into the pokemon's experience points.
		System.out.println(super.getName() + " gained " + gain_exp + " experience!");
		exp = exp + gain_exp;
		
		/*Tests if the pokemon's experience points is greater than or equal 
		 * to the amount of exp needed to level up. If it is, shows the
		 * pokemon leveling up, and increases pokemon's level by 1, resets exp
		 * to the remaining points, increases the pokemon's max hp, increases next level exp,
		 * and displays the pokemon's stats.
		 */
		if(exp >= nextLevelExp)
		{
			System.out.println(super.getName() + " has leveled up!");
			
			//Resets exp
			exp = (exp - nextLevelExp);
			
			//Increase level, max hp, and next level exp
			level ++;
			super.incMaxHp();
			increaseEXP();
			
			displayPokemon();
		}
		return exp;
	}

	/**
	 * Displays the basic attack moves for a pokemon
	 */
	public void displayBasicMenu()
	{
		System.out.println("Basic Attack Menu:"
				+ "\r\n  1. Slam"
				+ "\r\n  2. Tackle"
				+ "\r\n  3. Mega Punch");
	}
	
	/**
	 * Takes in the basic attack move the user wants to use, and
	 * calls that move's method to get the amount of damage it will
	 * deal. Then returns the damage dealt.
	 * @param move The basic attack move.
	 * @return The damage dealt
	 */
	public int basicFight(int move)
	{
		int attack = 0;
		switch(move)
		{
			case 1:		attack = slam();
						break;
			case 2:		attack = tackle();
						break;	
			case 3:		attack = megaPunch();
						break;
		}
		return attack;
	}

	/**
	 * A method that takes in which style (basic or special)
	 * attack the user wants the pokemon to use, and then
	 * takes in the move number that the user wants to use
	 * based on which style they chose.
	 * @param style Either 1 (basic) or 2 (special) 
	 * @param move The move from either of those two style's movesets
	 * @return The damage dealt based on the move
	 */
	public int fight(int style, int move)
	{
		int damage = 0;
		if(style == 1) //Basic
		{
			damage = basicFight(move);
		}
		else //Special
		{
			damage = specialFight(move);
		}
		return damage;
	}
	
	/**
	 * Basic move number 1: slam. Sets the damage dealt within a range based
	 * on the pokemon's level and randomly chooses a number in that range
	 * to represent how much damage is dealt.
	 * @return The damage dealt.
	 */
	public int slam()
	{
		System.out.println(super.getName() + " charges up and uses slam!");
		
		int upperRange = (level * 3) + 1;
		int lowerRange = level * 2;
		
		//Getting the damage within the range.
		int attack = ((int)(Math.random() * upperRange) + lowerRange);
		return attack;
	}
	
	/**
	 * Basic move number 2: tackle. The damage dealt is a random number in a range 
	 * that's based on the pokemon's level.
	 * @return The damage dealt from this move.
	 */
	public int tackle()
	{
		System.out.println(super.getName() + " uses tackle!");
		
		int upperRange = (level * 4) + 1;
		int lowerRange = level * 2;
		
		//The damage dealt based on the range.
		int attack = ((int)(Math.random() * upperRange) + lowerRange);
		return attack;
	}
	
	/**
	 * Basic move number 3: mega punch. The damage dealt is a random
	 * number in range that's based on the pokemon's level.
	 * @return The damage dealt from this move.
	 */
	public int megaPunch()
	{
		System.out.println(super.getName() + " balls up its fist, gets angry, and uses MEGA PUNCH!!!! ლಠ益ಠ)ლ ALA KA POW ");
		
		int upperRange = (level * 4) + 1;
		int lowerRange = level * 3;
		
		int attack = ((int)(Math.random() * upperRange) + lowerRange);
		return attack;
	}
	
	/**
	 * Gets the pokemon's anger
	 * @return Whether or not the pokemon is angry
	 */
	public boolean getAnger()
	{
		return anger;
	}
	
	/**
	 * Sets the pokemon anger to true/false
	 * @param anger Pokemon's anger true or false
	 */
	public void setAnger(boolean anger)
	{
		this.anger = anger;
	}
}
