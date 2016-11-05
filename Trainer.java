import java.io.Serializable;
import java.util.ArrayList;
/**
 * The trainer class that holds the trainer's name, their speeches,
 * and their pokemon.
 * @author Crystal Chun		ID#012680952
 *
 */
public abstract class Trainer extends Entity implements Serializable
{
	/**The list of pokemon the trainer has*/
	private ArrayList <Pokemon> pokemon = new ArrayList <Pokemon> (); 
	/**The index of the trainer's current pokemon*/
	private int currentPokemon;

	
	/**
	 * The trainer constructor
	 * @param name The trainer's name
	 * @param hp The trainer's hp
	 */
	public Trainer(String name, int hp)
	{
		super(name, hp);
	}
	
	/**The attack speeches specific to each trainer type*/
	public abstract void attackSpeech();
	/**The win speeches specific to each trainer type*/
	public abstract void winSpeech();
	/**The loss speeches specific to each trainer type*/
	public abstract void lossSpeech();
	/**The attack style*/
	public abstract int chooseStyle();
	/**The attack move specific to the style chosen*/
	public abstract int chooseMove(int style);

	/**
	 * Displays the trainer's current pokemon with all the stats.
	 */
	public void displayCurrentPokemon()
	{
		pokemon.get(currentPokemon).displayPokemon();
	}
	
	/**
	 * Displays the trainer's pokemon along with a battling pokemon.
	 * This display is for battle and overloads the current
	 * display pokemon.
	 * @param oppPoke The opponent's pokemon.
	 */
	public void displayCurrentPokemon(Pokemon oppPoke)
	{
		pokemon.get(currentPokemon).displayPokemon(oppPoke);
	}
	
	/**
	 * Gets the trainer's current pokemon
	 * @return The trainer's current pokemon
	 */
	public Pokemon getCurrentPokemon()
	{
		return pokemon.get(currentPokemon);
	}
	
	/**
	 * Adds a pokemon to the trainer's list of pokemon.
	 * @param p The pokemon to be added to the list.
	 */
	public void addPokemon(Pokemon p)
	{
		pokemon.add(p);
	}
	
	/**
	 * Lists all the pokemon's names (only)
	 * in the trainer's list
	 */
	public void listPokemon()
	{
		System.out.printf("%34s\r\n","POKEMON");
		System.out.println("_______________________________________________________________________");

		//Displays all the pokemon's names
		for(int i = 0; i < pokemon.size(); i++)
		{
			System.out.print((i + 1) + ". " + pokemon.get(i).getName());
			System.out.println();
		}
		
		System.out.println("_______________________________________________________________________");
		System.out.println();
	}
	
	/**
	 * Lists all the pokemon in the trainer's list with their stats.
	 * @param plusStats The trigger (boolean) that tells the list to also show stats
	 */
	public void listPokemon(boolean plusStats)
	{
		System.out.printf("%34s\r\n","POKEMON");
		System.out.println("_______________________________________________________________________");
		for(int i = 0; i < pokemon.size(); i++)
		{
			System.out.print((i + 1) + ". ");
			pokemon.get(i).displayPokemon();
			System.out.println();
		}
		System.out.println("_______________________________________________________________________");
		System.out.println();
	}
	
	/**
	 * Heals all of the trainer's pokemon by iterating through all of their pokemon
	 * and resetting their health (hp) to the maximum hp
	 */
	public void healAllPokemon()
	{
		for(int i = 0; i < pokemon.size(); i++)
		{
			pokemon.get(i).gainHP(pokemon.get(i).getMaxHP());
		}
	}
	
	/**
	 * Sets the trainer's current pokemon to the index of the pokemon
	 * passed in. The index represents which pokemon it is in their
	 * list of pokemon
	 * @param cur The new current pokemon's index
	 * @return The index of the current pokemon
	 */
	public int setCurrentPokemon(int cur)
	{
		currentPokemon = cur;
		return currentPokemon;
	}

	/**
	 * Gets the next pokemon's index
	 * @return The next pokemon's index.
	 */
	public int getNextCurPokemon()
	{
		if(currentPokemon + 1 >= pokemon.size())
		{
			return currentPokemon;
		}
		return currentPokemon + 1;
	}
	
	/**
	 * Shows a menu for when the user is battling, gives them the option to
	 * either fight or run away.
	 * @return Their choice of whether to fight or run away
	 */
	public int battle()
	{
		int style = chooseStyle();
		int move = chooseMove(style);
		int damage = getCurrentPokemon().fight(style, move);
		return damage;
	}
	
	/**
	 * Gets the total amount of pokemon the trainer has.
	 * @return The total amount of pokemon in the trainer's list.
	 */
	public int getTotalPokemon()
	{
		return pokemon.size();
	}
	
	/**
	 * Gets the trainer's current pokemon's index (position in the list
	 * of pokemon)
	 * @return The current pokemon's index
	 */
	public int getCurPokeNum()
	{
		return currentPokemon;
	}
	
	/**
	 * Gets the pokemon at the specified index.
	 * @param index The index of the pokemon
	 * @return The pokemon at the index
	 */
	public Pokemon getThisPoke(int index)
	{
		return pokemon.get(index);
	}
	
	/**
	 * Gets how many pokemon have fainted (hp <= 0)
	 * @return How many pokemon have fainted
	 */
	public int getFainted()
	{
		int fainted = 0;
		for(Pokemon poke: pokemon)
		{
			if(poke.getHP() <= 0)
			{
				fainted ++;
			}
		}
		return fainted;
	}
}
