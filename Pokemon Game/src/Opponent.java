/**
 * This is an opponent class which holds pokemon as well as
 * what pokemon type this trainer will have.
 * @author Crystal Chun		ID#012680952
 *
 */
public class Opponent extends Trainer
{
	/**The pokemon type the trainer will have*/
	private int type;
	/**The amount of money the opponent has that will be given to the player if they beat them*/
	private int money;
	/**Sets whether the opponent is angered*/
	private boolean anger;

	/**
	 * Constructs an opponent with a specified type,
	 * a name, a set amount of hp, and adds a pokemon
	 * of that type to their list.
	 * @param name The opponent's name
	 * @param hp The opponent's hp
	 * @param type The type of pokemon the opponent holds
	 */
	public Opponent(String name, int hp, int type) 
	{
		super(name, hp);
		this.type = type;
		money = (int) (Math.random() * 201)  + 50;
		
		//Makes a pokemon of the type the trainer is
		Pokemon oppPoke = PokemonMaker.makeTypePokemon(type);
		
		if(name.equalsIgnoreCase("Team Rocket")) //Tests if this is team rocket
		{
			//If it is team rocket, constructs another pokemon for them and adds that to their list
			Pokemon oppPoke2 = PokemonMaker.makeTypePokemon(type);
			super.addPokemon(oppPoke2);
		}
		super.addPokemon(oppPoke);
	}

	@Override
	public int battle()
	{
		int style = chooseStyle();
		int damage = chooseMove(style);
		return damage;
	}
	/**
	 * Gets what type of pokemon the opponent holds
	 * @return The type of pokemon that this opponent holds
	 * 0 = fire
	 * 1 = water
	 * 2 = grass
	 * 3 = electric
	 */
	public int getOppType()
	{
		return type;
	}
	
	/**
	 * The intro speech the opponent gives at the beginning of the battle.
	 */
	@Override
	public void introSpeech()
	{
		int speech = (int) (Math.random() * 6) + 1;
		switch(speech)
		{
			case 1:		System.out.println(super.getName() + ": \"Get ready to battle!\"");
						break;
			case 2:		System.out.println(super.getName() + ": \"My pokemon can beat you any day!\"");
						break;
			case 3:		System.out.println(super.getName() + ": \"Finally a worthy opponent.\"");
						break;
			case 4:		System.out.println(super.getName() + ": \"Distract me for awhile.\"");
						break;
			case 5:		System.out.println(super.getName() + " would like to battle!");
						break;
			case 6:		System.out.println(super.getName() + ": \"I see you're a trainer, I challenge you!\"");
						break;
		}	
	}
	
	/**
	 * The attack speech the opponent says in battle
	 */
	@Override
	public void attackSpeech() 
	{
		int speech = (int) (Math.random() * 4) + 1;
		switch(speech)
		{
			case 1:		System.out.println(super.getName() + ": \"Is that all you've got? *smirk*\"");
						break;
			case 2:		System.out.println(super.getName() + ": \"I'm just getting started.\"");
						break;
			case 3:		System.out.println(super.getName() + ": \"Oh so you want to play that game, huh?!\"");
						break;
			case 4:		System.out.println(super.getName() + ": \"\"");
						break;
		}	
	}

	/**
	 * The speech the oppoennt gives when they win.
	 */
	@Override
	public void winSpeech() 
	{
		int speech = (int) (Math.random() * 4) + 1;
		switch(speech)
		{
			case 1:		System.out.println(super.getName() + ": \"Hah!\"");
						break;
			case 2:		System.out.println(super.getName() + ": \"Looks like you have a lot more training to do.\"");
						break;
			case 3:		System.out.println(super.getName() + ": \"Better luck next time!\"");
						break;
			case 4:		System.out.println(super.getName() + ": \"EZ\"");
						break;
		}	
		
	}

	/**
	 * The speech the opponent gives when they lose.
	 */
	@Override
	public void lossSpeech() 
	{
		int speech = (int) (Math.random() * 9) + 1;
		switch(speech)
		{
			case 1:		System.out.println(super.getName() + ": \"Wow . . .\"");
						break;
			case 2:		System.out.println(super.getName() + ": \"Looks like I have more training to do!\"");
						break;
			case 3:		System.out.println(super.getName() + ": \"A worthy opponent, indeed!\"");
						break;
			case 4:		System.out.println(super.getName() + ": \"Can you teach me your ways?\"");
						break;
			case 5:		System.out.println(super.getName() + ": \"You are truly a pokemon master.\"");
						break;
			case 6:		System.out.println(super.getName() + ": \"Defeated, again . . .\"");
						break;
			case 7:		System.out.println(super.getName() + ": \"I hope to be as good as you one day.\"");
						break;
			case 8:		System.out.println(super.getName() + " ¯\\_(ツ)_/¯ \"I ain't even mad\"");
						break;
			case 9:		System.out.println(super.getName() + ": \"Hey no fair! I want a rematch!\"");
						break;
		}	
		
	}

	/**
	 * Chooses a style of attack (basic or special) when
	 * battling. It's random (1 or 2)
	 * @return Which style the opponent chooses
	 */
	@Override
	public int chooseStyle() 
	{
		int style = (int)(Math.random() * 2) + 1;
		return style;
	}

	/**
	 * Chooses a move at random based on which style of attack is chosen
	 * @param style The style of attack (1 = basic, 2 = special)
	 * @return The attack damage from the move chosen.
	 */
	@Override
	public int chooseMove(int style) 
	{
		int damage = 0;
		switch(style)
		{
			case 1:		damage = super.getCurrentPokemon().basicFight((int) (Math.random() * 3) + 1);
						break;
			case 2:		damage = super.getCurrentPokemon().specialFight((int) (Math.random() * 3) + 1);
						break;
		}
		return damage;
	}
	
	/**
	 * Gets the amount of money the opponent has
	 * @return The amount of money the opponent has
	 */
	public int getMoney()
	{
		return money;
	}
	
	/**
	 * Sets the opponent's anger to true or false
	 * @param anger Whether the opponent is angry or not
	 */
	public void setAnger(boolean anger)
	{
		this.anger = anger;
	}
	
	/**
	 * Gets whether the opponent is angry or not
	 * @return The opponent's anger
	 */
	public boolean getAnger()
	{
		return anger;
	}
}
