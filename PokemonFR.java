import java.util.Scanner;
/**
 * This is the implementation of a pokemon game where the user is able to play the game.
 * @author Crystal Chun ID # 012680952
 *
 */
public class PokemonFR 
{
	public static void main(String[] args) 
	{
		//Creating the player with a starter pokemon
		Player player = intro();
		boolean playing = true;
		
		//Loop that repeats while the user is still playing the game.
		while(playing)
		{
			int mainMenuChoice = mainMenu();
			switch(mainMenuChoice)
			{
				case 1:		//Traveling
							if(player.getCurrentPokemon().getHP() == 0)
							{
								System.out.println("Your pokemon has fainted, and it's unsafe to travel.");
								System.out.println("Switch pokemon, or heal your pokemon with potions.");
							}
							else
							{
								System.out.println("You travel along your way ᕕ( ᐛ )ᕗ");
								
								//Chooses a random encounter and then calls the encounter method
								int encounter = (int) (Math.random() * 9);
								encounters(encounter, player);
							}
							break;
							
				case 2:		//Switch pokemon	
							if(player.getTotalPokemon() <= 1) //Tests if the user even has other pokemon to switch to
							{
								System.out.println("You have only one pokemon.");
							}
							else
							{
								System.out.println("Which pokemon would you like to switch to?");
								player.listPokemon();
								
								int poke = CheckInput.intInput(1, player.getTotalPokemon());
								player.setCurrentPokemon(poke - 1);
							}
							break;
							
				case 3:		//Heal current pokemon by using a potion that restores its hp by 20 points
							System.out.println("You used a potion on " + player.getCurrentPokemon().getName());
							player.usePotion();
							player.getCurrentPokemon().gainHP(20);
							System.out.println(player.getCurrentPokemon().getName() + "'s HP has been restored by 20 points.");
							break;
							
				case 4:		//View stats- Lists all of player's pokemon and their stats
							displayPlayerStats(player);
							player.listPokemon(true);
							break;
							
				case 5:		//Exits the game
							playing = false;
							System.out.println("You leave the world of pokemon behind...");
							break;
			}
			
			if(player.getHP() <= 0)
			{
				System.out.println("You've lost all hp! Game over.");
				playing = false;
			}
		}
	}

	/**
	 * Introduces the world of pokemon to the player and sets up the player and 
	 * their starter pokemon.
	 * @return The newly made player
	 */
	public static Player intro()
	{
		//Creating the player
		System.out.print("What's your name? ");
		Scanner in = new Scanner(System.in);
		String trainerName = in.nextLine();
		System.out.println();
		Player player = new Player(trainerName, 100);
		
		//Choosing the starter pokemon
		System.out.print("Hello " + trainerName + "! Welcome to the world of Pokemon."
				+ "\r\nMy name is Professor Oak, and today you will get to choose"
				+ " your very own Pokemon!"
				+ "\r\nHere are the Pokemon you can choose as your starter:"
				+ "\r\n 1. Charmander"
				+ "\r\n 2. Bulbasaur"
				+ "\r\n 3. Squirtle"
				+ "\r\n 4. Pikachu"
				+ "\r\nWhich Pokemon would you like? ");
		
		int pokeChoice = CheckInput.intInput(1, 4);
		
		System.out.println();
		Pokemon playerPoke = PokemonMaker.makeStartPokemon(pokeChoice);
			
		//Allows the user to name their pokemon
		System.out.println("You have chosen " + playerPoke.getName() + " as your"
				+ " starter pokemon. What would you like to give "  + playerPoke.getName() + " a name?");
		System.out.println(" 1. Yes"
				+ "\r\n 2. No");

		if(CheckInput.intInput(1, 2) == 1)
		{
			System.out.print("What will be " + playerPoke.getName() + "'s new name? ");
			String pokeName = in.nextLine();
			playerPoke.setName(pokeName);
			System.out.println();
		}

		player.addPokemon(playerPoke);
		
		player.setCurrentPokemon(0); //Sets player's pokemon to their starter
		return player;
	}

	/**
	 * Displays the game's main menu and allows the user to choose what
	 * they want to do in the game.
	 * @return What the user wants to do in the game
	 */
	public static int mainMenu()
	{
		System.out.println("What would you like to do?"
				+ "\r\n 1. Travel"
				+ "\r\n 2. Switch pokemon"
				+ "\r\n 3. Heal current pokemon"
				+ "\r\n 4. View stats"
				+ "\r\n 5. Quit game");
		return CheckInput.intInput(1, 5);
	}
	
	/**
	 * Holds all of the possible situations/encounters the player can run into while traveling
	 * @param situation The specific situation the user runs into (randomly generated)
	 * @param player The player 
	 */
	public static void encounters(int situation, Player player)
	{
		switch(situation)
		{
			case 0: 	//Wild Pokemon
						System.out.println("^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/");
						System.out.println();
						System.out.println("              (⊙ヮ⊙)");
						System.out.println();
						
						//Creates wild pokemon
						Pokemon wildPoke = PokemonMaker.makeWildPokemon();
						System.out.println("A WILD " + wildPoke.getName() + " APPEARS!");
						
						//Calls wild battle method
						PokemonBattles.wildPokeBattle(player, wildPoke);
						break;
			case 1: 	//Stumble upon town
						System.out.print("^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/");
						System.out.println("^^ - - -ᕕ( ᐛ )ᕗ /\\- - - ᕕ( ᐛ )ᕗ /\\ - - -");
						System.out.println();
						System.out.println("You stumbled upon a town!\r\n");
						displayTown();
						
						int inTown = townMenu();
						while(inTown != 3)
						{
							if(inTown == 1)	//Pokemon Center
							{
								System.out.println("NURSE JOY: ꒰⑅ᵕ◡ᵕ꒱ \"Hello there! My name is NURSE JOY, and this is the "
										+ "Pokemon Center. I can heal all of your pokemon back to full health. \r\nWould "
										+ "you like me to heal your pokemon?\""
										+ "\r\n 1. Yes"
										+ "\r\n 2. No");
								
								int healing = CheckInput.intInput(1, 2);
								if(healing == 1)
								{
									System.out.println("~~~~~~~~~~~Restoring your pokemon to full health~~~~~~~~~~~");
									player.healAllPokemon();
									System.out.println("                *ping*                  ");
									System.out.println("All of your pokemon have been healed.");
								}
								System.out.println("NURSE JOY: (＾∇＾)ノ~ \"Thank you for visiting the Pokemon Center! "
										+ "Feel free to stop by anytime!\"");
								
							}
							else	//Shopping in Poke Mart
							{
								shopping(player);
							}
							inTown = townMenu();
						}
						
						System.out.println();
						System.out.println("The towns people look upon your departure "
								+ "as you run through their gates and off into the sunset.");
						System.out.println("( ͡° ͜ʖ ( ͡° ͜ʖ ( ͡° ͜ʖ ( ͡° ͜ʖ ͡°) ͜ʖ ͡°)ʖ ͡°)ʖ ͡°)                   ~|| ┏( ゜)ਊ゜)┛||");
						System.out.println();
						break;
			case 2: 	//Opponent wants to battle you
						Opponent opponent = OpponentMaker.makeRandOpponent();
						PokemonBattles.opponentBattle(player, opponent);
						break;
			case 3:		//Mom runs up to you and gives you pair of running shoes which does nothing but you put them on anyways
						System.out.println("(ﾉ^ヮ^)ﾉ*:・ﾟ✧ MOM: \"Oh honey, hold on!\" ~Runs up to you~"
								+ "\r\n \"I have a pair of shoes for you to help you on your journey!\""
								+ "\r\n ~received running shoes!~");
						System.out.println("You put on the running shoes, but nothing happens!");
						break;
			case 4: 	//Run into brock
						System.out.println("Brock approaches you from the bushes, and heals your soul."
								+ "\r\nYou gained 10 hp.");
						player.gainHP(10);
						break;
			case 5:		//Nothing happens
						System.out.println("^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/"
								+ "\r\n--Nothing happens--");
						break;
			case 6:		System.out.println("MISTY: \"Hey twerp! Where's my bike?!?!\""
						+ "\r\n *She hits you on the head and you lose 15 hp*");
						player.loseHP(15);
						break;
			case 7:		//Wild Angry Pokemon
						System.out.println("^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/");
						System.out.println();
						System.out.println("              (⊙ヮ⊙)");
						System.out.println();
						
						//Creates wild pokemon
						Pokemon wildPokeAnger = PokemonMaker.makeWildPokemon();
						wildPokeAnger.setAnger(true);
						System.out.println("A WILD " + wildPokeAnger.getName() + " APPEARS!"
								+ "\r\nIt huffs and puffs at you as you come near.");
						
						//Calls wild battle method
						PokemonBattles.wildPokeBattle(player, wildPokeAnger);
						break;
			case 8:		//Angry opponent wants to battle you
						Opponent angerOpponent = OpponentMaker.makeRandOpponent();
						angerOpponent.setAnger(true);
						PokemonBattles.opponentBattle(player, angerOpponent);
						break;
			
		}
	}
	
	/**
	 * Displays the menu for when the user travels to a town.
	 * Gets the user's input (which is what they want to do 
	 * in the town)
	 * @return What the user wants to do in town (integer value)
	 */
	public static int townMenu()
	{
		System.out.println("What would you like to do here?"
				+ "\r\n 1. Go to Pokemon Center"
				+ "\r\n 2. Go to Pokemon Mart"
				+ "\r\n 3. Run away");
		int choice = CheckInput.intInput(1, 3);
		return choice;
	}
	
	/**
	 * Displays which town the user is in
	 */
	public static void displayTown()
	{
		System.out.println("You look to your left and spot a sign. ");
		System.out.println("+-------------------------------+");
		
		//Displays a random town name
		int whichTown = (int)(Math.random() * 8);
		switch(whichTown)
		{
		case 0:		System.out.println("|          Pallet Town          |");
					break;
		case 1:		System.out.println("|          Viridian City        |");
					break;
					
		case 2:		System.out.println("|          Pewter City          |");
					break;
		case 3:		System.out.println("|          Cerulean City        |");
					break; 
		case 4:		System.out.println("|        Saffron City           |");
					break;
		case 5:		System.out.println("|        Vermillion City        |");
					break;
		case 6:		System.out.println("|         Celadon City          |");
					break;
		case 7:		System.out.println("|          Fuschia City         |");
					break;
		}
		System.out.println("+-------------------------------+");
		System.out.println();
	}
	
	/**
	 * Displays the shop's menu where the user can purchase
	 * potions or pokeballs.
	 * @return What the user wants to purchase in the shop (an integer value)
	 */
	public static int shopMenu()
	{
		System.out.printf("\r\n%20s\r\n%30s\r\n%32s\r\n%38s","Shop Menu:"," 1. Potions",
				"2. Pokeballs",
				"3. Done shopping\r\n");
		int choice = CheckInput.intInput(1, 3);
		return choice;
	}
	
	/**
	 * Simulates the user going to the pokemart and shopping for
	 * either potions or pokeballs.
	 * @param player Passes the player into the shop
	 */
	public static void shopping(Player player)
	{
		System.out.println("ᕕ( ᐛ )ᕗ~You skip on over to the Poke Mart "
				+ "and enter it through the sliding doors~|| ᕕ( ᐛ )ᕗ||");
		System.out.printf("%10s","CASHIER Jim: \"Welcome to our Poke Mart~! What would you like to buy?\"\r\n");
		
		System.out.print("Current Money: $" + player.getMoney());
		boolean shopping = true;
		int choice = 0;	//Gets what the user wants to do in the shop
		
		//Loops while the user is still shopping
		while(shopping)
		{
			choice = shopMenu();
			if(choice == 1) //Buying potions
			{
				boolean buyingPotions = true;
				
				//Loop that simulates the user buying potions
				while(buyingPotions)
				{
					System.out.println("Potions are $20 each. How many potions would you like to buy?");
					int numPotions = CheckInput.intInput();
					
					//Tests if the user has enough money to purchase this amount of potions
					if((numPotions * 20) > player.getMoney())
					{
						System.out.println("CASHIER Jim: \"Sorry, you don't have enough money to buy " 
								+ numPotions + " potions.\"");
						System.out.println(" 1. Edit number of potions"
								+ "\r\n 2. Go back to main shop menu"
								+ "\r\n 3. Leave shop");
						int notEnoughChoice = CheckInput.intInput(1, 3);
						if(notEnoughChoice == 2)	//Goes back to main shop menu
						{
							buyingPotions = false;
						}
						else if(notEnoughChoice == 3)	//Leaves shop
						{
							buyingPotions = false;
							shopping = false;
						}
					}
					
					//The user has enough money to purchase the quantity of potions
					else 
					{
						//Asks the user to confirm
						System.out.println("CASHIER Jim: \"You want to purchase " + numPotions 
								+ " potions," + " which will be $" + (numPotions * 20) + " is this correct?\""
								+ "\r\n 1. Yes"
								+ "\r\n 2. No, change amount of potions"
								+ "\r\n 3. No, cancel purchase");
						
						int confirm = CheckInput.intInput(1, 3); //If this is 2 then it loops around again and takes in a new quantity
						if(confirm == 1)
						{
							System.out.println("Received: " + numPotions + " potions!");
							player.buyPotion(numPotions);
							buyingPotions = false;
						}
						
						//User exits the shop
						else if(confirm == 3)
						{
							buyingPotions = false;
						}
					}
				}
			}
			else if(choice == 2)	//Buying pokeballs
			{
				boolean buyingPokeballs = true;
				
				//Loop that simulates the user buying pokeballs
				while(buyingPokeballs)
				{
					System.out.println("Pokeballs are $20 each. How many pokeballs would you like to buy?");
					int numPokeballs  = CheckInput.intInput();
					
					//Tests if the user has enough money to buy the pokeballs
					if((numPokeballs  * 20 > player.getMoney()))
					{
						System.out.println("CASHIER Jim: \"Sorry, you don't have enough money to buy " 
								+ numPokeballs + " pokeballs.\"");
						
						System.out.println(" 1. Edit number of pokeballs"
								+ "\r\n 2. Go back to main shop menu"
								+ "\r\n 3. Leave shop");
						int notEnoughChoice = CheckInput.intInput(1, 3);
						
						if(notEnoughChoice == 2)	//Goes back to main shop menu
						{
							buyingPokeballs = false;
						}
						else if(notEnoughChoice == 3) //The user is leaving the shop
						{
							buyingPokeballs = false;
							shopping = false;
						}
					}
					//The user has enough money to purchase pokeballs and asks them to confirm
					else
					{
						System.out.println("CASHIER Jim: \"You want to purchase " + numPokeballs 
								+ " pokeballs," + " which will be $" + (numPokeballs * 20) + " is this correct?\""
								+ "\r\n 1. Yes"
								+ "\r\n 2. No, change amount of pokeballs"
								+ "\r\n 3. No, cancel purchase");
						
						int confirm = CheckInput.intInput(1, 3);
						if(confirm == 1)	//Confirmed buying pokeballs
						{
							System.out.println("Received: " + numPokeballs + " pokeballs!");
							player.buyPokeball(numPokeballs);	
							buyingPokeballs = false;
						}
						else if(confirm == 3)	//Goes back to main shop menu
						{
							buyingPokeballs = false;
						}
					}
				}
			}
			else
			{
				shopping = false;
			}
		}
		System.out.println("CASHIER Jim: (￣▽￣)ノ\"Thanks for stopping by!\"");
		System.out.println("|| ~ ᕕ( ᐛ )ᕗ||");
	}

	/**
	 * Displays the player's stats, which includes the name, the total pokemon
	 * the player has, the amount of money the player has, the player's total
	 * potions, and the player's total pokeballs.
	 * @param player The player
	 */
	public static void displayPlayerStats(Player player)
	{
		System.out.print("Trainer Stats:");
		
		System.out.printf("%16s %-15s \r\n"
				+ "%30s %-15d \r\n"
				+ "%30s %-15d \r\n"
				+ "%30s $%-15d \r\n"
				+ "%30s %-15d \r\n"
				+ "%30s %-15d \r\n"
				+ "_______________________________________________________________________"
				+ "\r\n",
				"Name:", player.getName(),
				"HP:", player.getHP(),
				"Total pokemon:", player.getTotalPokemon(),
				"Money:", player.getMoney(),
				"Potions:", player.getNumPotionsLeft(),
				"Pokeballs:", player.getNumPokeballsLeft());
	}
}
