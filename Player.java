import java.awt.Point;
import java.io.Serializable;

/**
 * Player class that creates a player that holds the player's
 * potions, pokeballs, and money.
 * @author Crystal Chun		ID#012680952
 *
 */
public class Player extends Trainer implements Serializable
{
	/**The potions that the player has*/
	private int potions;
	/**The pokeballs that the player has*/
	private int pokeballs;
	/**The amount of money that the player has*/
	private int money;
	/**Where the user starts out on the map*/
	private Point location;
	/**The map level the user is on*/
	private int level;

	/**
	 * Constructs the player with a set amount potions, pokeballs, and money.
	 * @param name The name of the player
	 * @param hp The hp of the player
	 */
	public Player(String name, int hp, Point start) 
	{
		super(name, hp);
		potions = 5;
		pokeballs = 5;
		setMoney(200);
		location = start;
		level = 1;
	}
	
	/**
	 * Uses up one potion
	 * @return Whether the user was able to use a potion or not
	 */
	public boolean usePotion()
	{
		//Tests if the player even has any potions to use up
		if(potions <= 0)
		{
			System.out.println("You're out of potions.");
			return false;
		}
		else
		{
			potions --;
			return true;
		}
		
	}
	
	/**
	 * Simulates buying a potion by adding one to potions and
	 * spending money for one potion.
	 */
	public void buyPotion()
	{
		spendMoney(20);
		potions ++;
	}
	
	/**
	 * Simulates buying multiple potions by adding to the player's potions
	 * the number of potions bought, and also subtracting the money for the cost
	 * of the potions.
	 * @param numPotions The amount of potions that are being bought
	 */
	public void buyPotion(int numPotions)
	{
		//Subtracts money from the amount of potions being bought times 20
		spendMoney((20 * numPotions));
		potions = potions + numPotions;
	}
	
	/**
	 * Gets the number of potions the player has
	 * @return The number of potions the player has
	 */
	public int getNumPotionsLeft()
	{
		return potions;
	}
	
	/**
	 * Uses up a pokeball by subtracting one from the player's total pokeball
	 * @return True or false depending on if the user was able to use a pokeball
	 */
	public boolean usePokeball()
	{
		if(pokeballs <= 0)
		{
			return false;
		}
		else
		{
			pokeballs --;
			return true;
		}
	}
	
	/**
	 * Gets the number of pokeballs the player has
	 * @return The number of pokeballs the player has 
	 */
	public int getNumPokeballsLeft()
	{
		return pokeballs;
	}
	
	/**
	 * Simulates buying just one pokeball by
	 * subtracting the cost of one pokeball from the
	 * player's money. Adds a pokeball to the user's pokeballs
	 */
	public void buyPokeball()
	{
		spendMoney(20);
		pokeballs ++;
	}
	
	/**
	 * Simulates buying multiple pokeballs by adding the pokeballs
	 * bought to the player's total pokeballs, and then subtracting the
	 * cost of all the pokeballs from the user's money.
	 * @param numPokeballs The number of pokeballs bought
	 */
	public void buyPokeball(int numPokeballs)
	{
		
		spendMoney((numPokeballs * 20));
		pokeballs = pokeballs + numPokeballs;
	}
	
	/**
	 * Simulates spending money by subtracting the price of the item
	 * from the total money the player has.
	 * @param price
	 */
	public void spendMoney(int price)
	{
		setMoney(getMoney() - price);
	}
	
	/**
	 * Simulates gaining money by adding the money gained
	 * by the player to the total money.
	 * @param money The money gained by the player
	 */
	public void gainMoney(int money)
	{
		this.setMoney(this.getMoney() + money);
	}
	
	/**
	 * The attack speech that's randomly chosen.
	 */
	@Override
	public void attackSpeech() 
	{
		int speach = (int) (Math.random() * 4) + 1;
		switch(speach)
		{
			case 1:		System.out.println(super.getName() + ": \"" + getCurrentPokemon().getName() + " fight on!\"");
						break;
			case 2:		System.out.println(super.getName() + ": \"Go " + getCurrentPokemon().getName() + "!\"");
						break;
			case 3:		System.out.println(super.getName() + ": \"Yes! " + getCurrentPokemon().getName() +" you got this!\"");
						break;
			case 4:		System.out.println(super.getName() + ": \"Keep on fighting!\"");
						break;
		}
	}

	/**
	 * The win speech that's randomly chosen.
	 */
	@Override
	public void winSpeech() 
	{
		int speach = (int) (Math.random() * 4) + 1;
		switch(speach)
		{
			case 1:		System.out.println(super.getName() + ": \"Training is coming in clutch!\"");
						break;
			case 2:		System.out.println(super.getName() + ": \"Way to go " + getCurrentPokemon().getName() + "!\"");
						break;
			case 3:		System.out.println(super.getName() + ": \"Good job " + getCurrentPokemon().getName() + "!\"");
						break;
			case 4:		System.out.println(super.getName() + ": \"Nailed it!\"");
						break;
		}	
	}

	/**
	 * The speech that the player says after they lose. 
	 */
	@Override
	public void lossSpeech() 
	{
		int speach = (int) (Math.random() * 5) + 1;
		switch(speach)
		{
			case 1:		System.out.println(super.getName() + ": \"It's ok " + getCurrentPokemon().getName() + ", you did good.\"");
						break;
			case 2:		System.out.println(super.getName() + ": \"Looks like I have more training to do!\"");
						break;
			case 3:		System.out.println(super.getName() + ": \"Come back " + getCurrentPokemon().getName() + "!\"");
						break;
			case 4:		System.out.println(super.getName() + ": \"Can you teach me your ways?\"");
						break;
			case 5:		System.out.println(super.getName() + ": T_T");
						break;
		}	
		
	}

	/**
	 * Chooses the style of attack if the player is fighting.
	 * There's basic and there's special.
	 * @return The style chosen, basic = 1, special = 2
	 */
	@Override
	public int chooseStyle()
	{
		System.out.println("Choose an attack type:"
				+ "\r\n 1. Basic"
				+ "\r\n 2. Special");
		int choice = CheckInput.intInput(1, 2);
		return choice;
	}

	/**
	 * Chooses the move that the player wants to use by showing them a 
	 * menu of the moves available based on the style they chose.
	 * @param style The style of attack the player wants to use, 1 = basic
	 * 2 = special
	 * @return The attack damage that the move dealt.
	 */
	@Override
	public int chooseMove(int style) 
	{
		int move = 0;
		switch(style)
		{
			case 1:		super.getCurrentPokemon().displayBasicMenu();
						move = CheckInput.intInput(1, 3);
						break;
			case 2:		super.getCurrentPokemon().displaySpecialMenu();
						move = CheckInput.intInput(1, 3);
						break;
		}
		return move;
	}

	/**
	 * Gets the amount of money the player currently has.
	 * @return The amount of money the player currently has
	 */
	public int getMoney() 
	{
		return money;
	}

	/**
	 * Gets the pokemon at the specified index
	 * @param index The index of the pokemon wanted
	 * @return The pokemon at the index
	 */
	public Pokemon getThisPoke(int index)
	{
		return super.getThisPoke(index);
	}
	
	/**
	 * Sets the amount of money for the player.
	 * @param money The amount of money the player will have
	 */
	public void setMoney(int money) 
	{
		this.money = money;
	}

	/**
	 * Gets where the user is in the map.
	 * @return The point where the user is in the map.
	 */
	public Point getLocation()
	{
		return location;
	}
	
	/**
	 * Gets which map level the user is on.
	 * @return The map area/level the user is on.
	 */
	public int getLevel()
	{
		return level;
	}

	/**
	 * Increases the user's map level.
	 */
	public void incLevel()
	{
		level = level + 1;
	}

	/**
	 * Sets the user's location
	 * @param p The point where the user's location will be
	 * @return Whether setting the location worked
	 */
	public boolean setLocation(Point p)
	{
		if(p.getX() < 5 && p.getX() >= 0 && p.getY() < 5 && p.getY() >= 0)
		{
			this.location.setLocation(p);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Simulates the user going North in the map.
	 * If moving North is valid, then sets user's
	 * location to North, reveals that location,
	 * and returns the character at that location.
	 * @param m The map the user is on
	 * @return The character North or a 
	 * 'G' if the location isn't valid.
	 */
	public char goNorth(Map m)
	{
		int newX = (int) location.getX() - 1;
		
		Point temp = new Point();
		temp.setLocation(newX, location.getY());
		
		//If the location is valid, sets user's location, reveals it, and returns character
		if(m.getCharAtLoc(temp) != 'G')
		{
			this.location.setLocation(newX, location.getY());
			m.reveal(location);
			return m.getCharAtLoc(location);
		}
		return 'G';
	}
	
	/**
	 * Tests if moving South is valid, if it is then sets
	 * the user's location to South, reveals that location,
	 * and returns the character at that location. Otherwise
	 * returns 'G' which means it's not valid.
	 * @param m The map the user is on.
	 * @return The character South or a 'G' if invalid.
	 */
	public char goSouth(Map m)
	{
		int newX = (int)location.getX() + 1;
		
		Point temp = new Point();
		temp.setLocation(newX, location.getY());
		
		//If the location is valid, sets user's location, reveals it, and returns character
		if(m.getCharAtLoc(temp) != 'G')
		{
			location.setLocation(newX, location.getY());
			m.reveal(location);
			return m.getCharAtLoc(location);
		}
		return 'G';
	}
	
	/**
	 * Tests if moving East is valid, if it is then sets
	 * the user's location to East, reveals that location, 
	 * and returns the character at that location. Otherwise
	 * returns 'G', which means invalid.
	 * @param m The map the user is on.
	 * @return The character East or a 'G' if invalid.
	 */
	public char goEast(Map m)
	{
		int newY = (int)location.getY() + 1;
		
		Point temp = new Point();
		temp.setLocation(location.getX(), newY);
		
		//If the location is valid, sets user's location, reveals it, and returns character
		if(m.getCharAtLoc(temp) != 'G')
		{
			location.setLocation(location.getX(), newY);
			m.reveal(location);
			return m.getCharAtLoc(location);
		}
		return 'G';
	}
	
	/**
	 * Tests if moving West is valid, if it is then sets
	 * the user's location to West, reveals that location,
	 * and returns the character at that location. Otherwise
	 * returns 'G', which means invalid.
	 * @param m The map the user is on.
	 * @return The character West or a 'G' if invalid.
	 */
	public char goWest(Map m)
	{
		int newY = (int)location.getY() - 1;
		
		Point temp = new Point();
		temp.setLocation(location.getX(), newY);
		
		//If the location is valid, sets user's location, reveals it, and returns character
		if(m.getCharAtLoc(temp) != 'G')
		{
			location.setLocation(location.getX(), newY);
			m.reveal(location);
			return m.getCharAtLoc(location);
		}
		return 'G';
	}
}
