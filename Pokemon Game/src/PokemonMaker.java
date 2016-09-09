/**
 * Makes pokemons based on what type of pokemon.
 * @author Crystal Chun		ID#012680952
 *
 */
public class PokemonMaker 
{
	/**
	 * Makes a wild pokemon at random
	 * @return The wild pokemon
	 */
	public static Pokemon makeWildPokemon()
	{
		//Chooses a random type of pokemon then calls makeTypePokemon function
		int type = (int)Math.random() * 4;
		
		Pokemon newPoke = makeTypePokemon(type);
		return newPoke;
	}
	
	/**
	 * Makes a specific type of pokemon at random
	 * @param type The type of pokemon
	 * @return The pokemon of that type
	 */
	public static Pokemon makeTypePokemon(int type)
	{
		//Initializes a pokemon
		Pokemon thisPoke = null;
		int pokemon = (int)(Math.random() * 2) + 1; //Chooses a random pokemon based on type
		
		switch(type)
		{
			//Fire type
			case 0:		if(pokemon == 1)
						{
							thisPoke = new Charmander();
						}
						else
						{
							thisPoke = new Ponyta();
						}
						break;
			//Water type
			case 1:		if(pokemon == 1)
						{
							thisPoke = new Squirtle();
						}
						else
						{
							thisPoke = new Staryu();
						}
						break;
			//Grass type
			case 2:		if(pokemon == 1)
						{
							thisPoke = new Oddish();
						}
						else
						{
							thisPoke = new Bulbasaur();
						}
						break;
			//Electric type
			case 3:		if(pokemon == 1)
						{
							thisPoke = new Pikachu();
						}
						else
						{
							thisPoke = new Zapdos();
						}
						break;
		}
		return thisPoke;
	}
	
	/**
	 * Makes the starter pokemon based on what the player
	 * chooses
	 * @param start The starter pokemon the user chooses
	 * @return The starter pokemon the user chooses
	 */
	public static Pokemon makeStartPokemon(int start)
	{
		Pokemon playersPoke = null;
		switch(start)
		{
			case 1:		playersPoke = new Charmander(2);
						break;
			case 2:		playersPoke = new Bulbasaur(2);
						break;
			case 3:		playersPoke = new Squirtle(2);
						break;
			case 4:		playersPoke = new Pikachu(2);
						break;
		}
		
		return playersPoke;
	}
}
