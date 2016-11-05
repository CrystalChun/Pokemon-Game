import java.awt.Point;
import java.util.Scanner;
import java.io.*;
/**
 * This is the implementation of a pokemon game where the user is able to play the game.
 * @author Crystal Chun ID # 012680952
 */
public class PokemonFR 
{
	public static void main(String[] args) 
	{
		//Checks for saved file
		Player player = null;
		File saved = new File("pokemon.dat");
		boolean loaded = false;
		if(saved.exists())
		{
			//If there is, asks if user wants to restart or continue from saved
			System.out.println("Would you like to continue where you left off?"
					+ "\r\n1. Yes"
					+ "\r\n2. No start new game");
			
			int choice = CheckInput.intInput(1,2);
			if(choice == 1)	//Continued from save
			{
				loaded = true;
				try
				{
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(saved));
					player = (Player) in.readObject();
					in.close();
				}
				catch(IOException e)
				{
					System.out.println("Error processing file.");
				}
				catch(ClassNotFoundException e)
				{
					System.out.println("Could not find class.");
				}
			}
		}
		//Creating the player with a starter pokemon if game not loaded
		if(!loaded)
		{
			player = intro();
		}
		boolean playing = true;
		boolean lost = false;
		
		Map map = new Map();
		map.generateArea(player.getLevel());
		
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
								boolean moving = false;
								char encounter = ' ';
								while(!moving)
								{
									map.displayMap(player.getLocation());
									System.out.println("Which way would you like to travel?"
											+ "\r\n 1. North"
											+ "\r\n 2. South"
											+ "\r\n 3. East"
											+ "\r\n 4. West");
									int way = CheckInput.intInput(1, 4);
									//Point temp = null;
									switch(way)
									{
										case 1:		//North
													if(player.goNorth(map) != 'G')
													{
														moving = true;
														encounter = map.getCharAtLoc(player.getLocation());
														//player.setLocation(temp);
													}
													break;
										case 2:		//South
													if(player.goSouth(map) != 'G')
													{
														moving = true;
														encounter = map.getCharAtLoc(player.getLocation());
														//player.setLocation(temp);
													}
													break;
										case 3:		//East
													if(player.goEast(map) != 'G')
													{
														moving = true;
														encounter = map.getCharAtLoc(player.getLocation());
														//player.setLocation(temp);
													}
													break;
										case 4:		//West
													if(player.goWest(map) != 'G')
													{
														moving = true;
														encounter = map.getCharAtLoc(player.getLocation());
														//player.setLocation(temp);
													}
													break;
									}
								}
								Point playerCurrentLoc = player.getLocation();
								System.out.println("You travel along your way ᕕ( ᐛ )ᕗ");
								
								//If the player reaches a new area/map
								boolean newArea = encounters(encounter, player, map);
								if(newArea)
								{
									System.out.println("Would you like to save your game progress?"
											+ "\r\n 1. Yes"
											+ "\r\n 2. No");
									
									//User wants to save game progress
									int input = CheckInput.intInput(1, 2);
									if(input == 1)
									{
										try
										{
											ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saved));
											out.writeObject(player);
											System.out.println("Saving game . . .");
											out.close();
										}
										catch(IOException e)
										{
											System.out.println("Error processing file.");
										}
									}
									
									//Generates the next map and increases player level
									player.incLevel();
									map.generateArea(player.getLevel());
								}
								
								//Means that the player ran in a random direction away
								if(!(player.getLocation().equals(playerCurrentLoc)))
								{
									encounters(encounter, player, map);
								}
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
							break;
			}
			
			//Means the player lost all their hp and has lost the game
			if(player.getHP() <= 0)
			{
				System.out.println("You've lost all hp! Game over.");
				lost = true;
				playing = false;
			}
		}
		
		//The player didn't lose their hp, writes game to file (saves)
		if(!lost)
		{
			try
			{
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saved));
				out.writeObject(player);
				System.out.println("Saving game . . .");
				System.out.println("You leave the world of pokemon behind...");
				out.close();
			}
			catch(IOException e)
			{
				System.out.println("Error processing file.");
			}
		}
		
		if(player.getCurrentPokemon().getAnger())
		{
			System.out.println("Your pokemon is angry at you for running into too many nothings.\r\n"
					+ player.getCurrentPokemon().getName() + " hurts you for it. You lose 2 hp!");
			player.loseHP(2);
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

		Map m = new Map();
		m.generateArea(1);
		Point temp = new Point();
		temp.setLocation(m.findStartLocation().getX(), m.findStartLocation().getY());
		Player player = new Player(trainerName, 100, temp);

		
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
	public static boolean encounters(char situation, Player player, Map m)
	{
		boolean newPlace = false;
		switch(situation)
		{
			case 'w': 	//Wild Pokemon
						System.out.println("^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/");
						System.out.println();
						System.out.println("              (⊙ヮ⊙)");
						System.out.println();
						
						//Creates wild pokemon
						Pokemon wildPoke = PokemonMaker.makeWildPokemon();
						String initialName = wildPoke.getName();
						wildPoke.setName("Wild " + wildPoke.getName());
						System.out.println("A WILD " + wildPoke.getName() + " APPEARS!");
						
						boolean fighting = true;
						int playerHp = player.getHP();
						
						while(fighting)
						{
							//Displays both pokemons for the fight
							player.displayCurrentPokemon(wildPoke);
							
							//Menu which asks the user what they would like to do
							System.out.println("What would you like to do?"
									+ "\r\n  1. Fight"
									+ "\r\n  2. Use Pokeball"
									+ "\r\n  3. Use Potion"
									+ "\r\n  4. Run");
							int choice = CheckInput.intInput(1, 4);
							//Indicates that the user wants to fight
							if(choice == 1)
							{
								fighting = PokemonBattles.wildPokeBattle(player, wildPoke);
							}
							else if(choice == 2)
							{
								if(player.usePokeball())
								{
									//Calculates the chance the user will capture pokemon lower hp = higher chance
									double chance = (wildPoke.getMaxHP() - wildPoke.getHP())/wildPoke.getMaxHP();
									//Generates two random numbers from 0 to 10 and if they match, then they caught pokemon
									int capture = (int)(Math.random() * 11); 
									int caught = (int)(Math.random() * 11);
									
									//The lower the hp of the pokemon, the higher chance user has of catching it
									//--changes random number range
									if(chance > 0.50)
									{
										capture = (int)(Math.random() * 5);
										caught = (int)(Math.random() * 5);
										if(chance > 0.25) //Hp of pokemon is less than 1/4 of what it was originally
										{
											capture = (int)(Math.random() * 2);
											caught = (int)(Math.random() * 2);
										}
									}
									//The user was able to catch the pokemon
									if(capture == caught)
									{
										System.out.println("You caught " + wildPoke.getName() + "!");
										System.out.println("Would you like to name " + wildPoke.getName() + "?"
												+ "\r\n 1. Yes"
												+ "\r\n 2. No");
										
										//Sets name of pokemon if user decides to change it
										if(CheckInput.intInput(1, 2) == 1)	
										{
											System.out.println("What name would you like to give " + wildPoke.getName() + "?");
											String name = CheckInput.getString();
											wildPoke.setName(name);
										}
										else
										{
											wildPoke.setName(initialName);
										}
										player.addPokemon(wildPoke);
										fighting = false;
									}
									else
									{
										System.out.println(wildPoke.getName() + " has broken free and is angry!");
										wildPoke.setAnger(true);
									}
								}
								else
								{
									System.out.println("You are out of pokeballs.");
								}	
							}
							else if(choice == 3)
							{
								if(player.usePotion())
								{
									System.out.println("You used a potion on " + player.getCurrentPokemon().getName() 
											+ "! " + player.getCurrentPokemon() + " gained 20 hp!");
									player.getCurrentPokemon().gainHP(20);
								}
							}
							else
							{
								System.out.println("You try to frolic away through the trees, but not before "
										+ wildPoke.getName() + " deals 10 damage to you!");
								System.out.println("     ε=ε=ε=ε=ε=ε=٩(  ͡๏̯͡๏)۶");
								player.loseHP(10);
								
								//Sends player off in random direction
								boolean switching = true;
								while(switching)
								{
									int direction = (int) (Math.random() * 4);
									switch(direction)
									{
										case 0:		if(player.goEast(m) != 'G')
													{
														player.goEast(m);
														switching = false;
													}
													break;
										case 1:		if(player.goWest(m) != 'G')
													{
														player.goWest(m);
														switching = false;
													}
													break;
										case 2:		if(player.goNorth(m) != 'G')
													{
														player.goNorth(m);
														switching = false;
													}
													break;
										case 3:		if(player.goSouth(m) != 'G')
													{
														player.goSouth(m);
														switching = false;
													}
													break;
									}
								}
								fighting = false;
							}
						}
						
						//Tests if player lost hp, if not, then defeated poke and removes them from map
						if(player.getHP() == playerHp)
						{
							m.removeOppAtLoc(player.getLocation());
						}
						player.getCurrentPokemon().resetNothing();
						break;

			case 'c': 	//Stumble upon town
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
						
						player.getCurrentPokemon().resetNothing();
						break;
			case 'o': 	//Opponent wants to battle you
						OpponentMaker makeNewOpp = new OpponentMaker();
						Opponent opponent = makeNewOpp.makeRandOpponent();

						opponent.getAttackSpeech(); //The opponent's call to the player

						int playerHP = player.getHP();
						System.out.println(opponent.getName() + " sends out " + opponent.getCurrentPokemon().getName() + "!");
						System.out.println("You send out " + player.getCurrentPokemon().getName() + "!");
	
						boolean fight = true;
						while(fight)
						{
							player.displayCurrentPokemon(opponent.getCurrentPokemon());
							System.out.println("What would you like to do?"
									+ "\r\n 1. Fight"
									+ "\r\n 2. Run away");
							
							int battling = CheckInput.intInput(1, 2);
							switch(battling)
							{
								case 1:		//The player wants to fight
											fight = PokemonBattles.opponentBattle(player, opponent);
											break;
											
								case 2:		//Player wants to run away
											if(opponent.getAnger())	//The opponent is angry and deals more damage as you leave
											{
												fight = false;
												System.out.println(opponent.getName() + " is angry, and does 30 damage to you as you sprint away.");
												System.out.println("     ε=ε=ε=ε=ε=ε=٩(  ͡๏̯͡๏)۶");
												player.loseHP(30);
											}
											else	//You're able to run away, but you lose 15 hp
											{
												fight = false;
												System.out.println(opponent.getName() + " looks at you in confusion as you sprint away.");
												System.out.println("     ε=ε=ε=ε=ε=ε=٩(  ͡๏̯͡๏)۶");
												System.out.println("You lost 15 hp for leaving battle.");
												player.loseHP(15);
											}
											
											//Sends player off in random direction
											boolean switching = true;
											while(switching)
											{
												int direction = (int) (Math.random() * 4);
												switch(direction)
												{
													case 0:		if(player.goEast(m) != 'G')
																{
																	player.goEast(m);
																	switching = false;
																}
																break;
													case 1:		if(player.goWest(m) != 'G')
																{
																	player.goWest(m);
																	switching = false;
																}
																break;
													case 2:		if(player.goNorth(m) != 'G')
																{
																	player.goNorth(m);
																	switching = false;
																}
																break;
													case 3:		if(player.goSouth(m) != 'G')
																{
																	player.goSouth(m);
																	switching = false;
																}
																break;
												}
											}
							}
						}
						
						//Tests if the player lost hp, if they didn't then they defeated opponent
						if(player.getHP() == playerHP)
						{
							m.removeOppAtLoc(player.getLocation());
						}
						
						player.getCurrentPokemon().resetNothing();
						break;
			case 'n':	//Nothing happens
						System.out.println("^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/"
								+ "\r\n--Nothing happens--");
						
						player.getCurrentPokemon().incNothing();
						break;
			case 'f':	//Reached finish and move onto the next map
						System.out.println("You've made it into a new area!");
						newPlace = true;
						
						player.getCurrentPokemon().resetNothing();
						break;
			case 's':	//Back at start
						System.out.println("You've wandered right back from where you came from.");
						
						player.getCurrentPokemon().resetNothing();
						break;
	
/*			case 7:		//Wild Angry Pokemon
						System.out.println("^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/ ᕕ( ᐛ )ᕗ ^\\^\\/");
						System.out.println();
						System.out.println("              (⊙ヮ⊙)");
						System.out.println();
						
						//Creates wild pokemon
						Pokemon wildPokeAnger = PokemonMaker.makeWildPokemon();
						wildPokeAnger.setAnger(true);
						System.out.println("A WILD ANGRY " + wildPokeAnger.getName() + " APPEARS!"
								+ "\r\nIt huffs and puffs at you as you come near and deals damage to you!");
						player.loseHp(wildPokeAnger.battle());
						break;
			case 8:		//Angry opponent wants to battle you
						Opponent angerOpponent = OpponentMaker.makeRandOpponent();
						angerOpponent.setAnger(true);
						System.out.println(angerOpponent.getName() + " is having a bad day today...");
						PokemonBattles.opponentBattle(player, angerOpponent);
						break;*/
		}
		return newPlace;
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
