import java.util.*;
import java.io.*;

/**
 * Stores a list of opponents and generates a random opponent.
 * @author Crystal Chun		ID#012680952
 *
 */
public class OpponentMaker 
{
	/**The list of opponents*/
	private ArrayList <Opponent> opponentList;
	
	/**
	 * Constructs the opponent maker by reading in a list of opponents and
	 * stores each opponent into a master list of opponents.
	 */
	public OpponentMaker()
	{
		opponentList = new ArrayList <Opponent> ();
		try
		{
			Scanner read = new Scanner(new File("OpponentList.txt"));
			while(read.hasNext())
			{
				String name = read.nextLine();
				int hp = Integer.parseInt(read.nextLine());
				String attackSpeech = read.nextLine();
				String lossSpeech = read.nextLine();
				String winSpeech = read.nextLine();
				Opponent temp = new Opponent(name, hp, attackSpeech, lossSpeech, winSpeech);
				
				opponentList.add(temp);
			}
			read.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File does not exist.");
		}
	}
	
	/**
	 * Makes a random opponent of a random type
	 * @return An opponent
	 */
	public Opponent makeRandOpponent()
	{
		int thisOpponent = (int)(Math.random() * 8); //chooses a random opponent from list
		Opponent thisOpp = new Opponent(opponentList.get(thisOpponent).getName(), 
										opponentList.get(thisOpponent).getHP(),
										opponentList.get(thisOpponent).getAttackSpeech(),
										opponentList.get(thisOpponent).getlossSpeech(),
										opponentList.get(thisOpponent).getWinSpeech());
		
		int type = (int)(Math.random() * 4);
		
		//Makes a pokemon of the type the trainer is
		Pokemon oppPoke = PokemonMaker.makeTypePokemon(type);
				
		if(thisOpp.getName().equalsIgnoreCase("Team Rocket")) //Tests if this is team rocket
		{
			//If it is team rocket, constructs another pokemon for them and adds that to their list
			Pokemon oppPoke2 = PokemonMaker.makeTypePokemon(type);
			thisOpp.addPokemon(oppPoke2);
		}
		thisOpp.addPokemon(oppPoke);
		return thisOpp;
	}
}
