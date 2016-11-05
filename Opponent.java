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
	/**The attack speech*/
	private String atkSpeech;
	/**The loss speech*/
	private String lossSpeech;
	/**The win speech*/
	private String winSpeech;

	/**
	 * Constructs an opponent with a specified type,
	 * a name, a set amount of hp, and adds a pokemon
	 * of that type to their list.
	 * @param name The opponent's name
	 * @param hp The opponent's hp
	 * @param type The type of pokemon the opponent holds
	 */
	public Opponent(String name, int hp, 
			String attackSpeech, String lossSpeech, String winSpeech) 
	{
		super(name, hp);
		this.atkSpeech = attackSpeech;
		this.lossSpeech = lossSpeech;
		this.winSpeech = winSpeech;

		//Gives them a random amount of money that the user can win
		money = (int) (Math.random() * 201)  + 50;
		
		
	}

	/**
	 * Chooses the opponent's style and move 
	 * then returns the damage done.
	 * @return The damage the opponent does
	 */
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
	 * The attack speech the opponent says in battle
	 */
	@Override
	public void attackSpeech() 
	{
		System.out.println(atkSpeech);
	}

	/**
	 * Gets the opponent's attack speech
	 * @return The attack speech
	 */
	public String getAttackSpeech()
	{
		return atkSpeech;
	}
	
	/**
	 * Gets the opponent's loss speech
	 * @return The loss speech
	 */
	public String getlossSpeech()
	{
		return lossSpeech;
	}
	
	/**
	 * Gets the opponent's win speech
	 * @return The win speech
	 */
	public String getWinSpeech()
	{
		return winSpeech;
	}
	
	/**
	 * The speech the opponent gives when they win.
	 */
	@Override
	public void winSpeech() 
	{
		System.out.println(winSpeech);
	}

	/**
	 * The speech the opponent gives when they lose.
	 */
	@Override
	public void lossSpeech() 
	{
		System.out.println(lossSpeech);	
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
