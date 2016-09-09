/**
 * Holds all the possible pokemon battles and simulates them.
 * @author Crystal Chun		ID#012680952
 *
 */
public class PokemonBattles 
{
	/** 
	 * The damage adjusting table that holds the pokemon types (all four of them) and adjusts any attack
	 * based on how the attacking pokemon's type compares to the other pokemon's type.
	 * 0 = Fire
	 * 1 = Water
	 * 2 = Grass
	 * 3 = Electric
	 */
	private final static double [][] fightTable = {{0.5, 0.5, 2, 1}, {2, 0.5, 0.5, 1}, {0.5, 2, 0.5, 1}, {1, 2, 0.5, 0.5}};
	
	/**
	 * Simulates the battle with an opponent
	 * @param player The player
	 * @param opponent The opponent
	 */
	public static void opponentBattle(Player player, Opponent opponent)
	{
		opponent.introSpeech(); //The opponent
		player.introSpeech();	//The player
		
		System.out.println(opponent.getName() + " sends out " + opponent.getCurrentPokemon().getName() + "!");
		System.out.println("You send out " + player.getCurrentPokemon().getName() + "!");
		

		int numPokesFaintedPlayer = 0;
		int numPokesFaintedOpp = 0;
		boolean fighting = true;
		
		//Loop that keeps repeating while the user is fighting
		while(fighting)
		{
			player.displayCurrentPokemon(opponent.getCurrentPokemon());
			System.out.println("What would you like to do?"
					+ "\r\n 1. Fight"
					+ "\r\n 2. Run away");
			int battling = CheckInput.intInput(1, 2);
			
			switch(battling)	//Switches what the player wants to do
			{
				case 1:		//The player wants to fight
							//Getting the player's attack damage

							trainerBattle(player, opponent);
							trainerBattle(opponent, player);
							//Tests to see if the player's pokemon fainted
							if(player.getCurrentPokemon().getHP() == 0)
							{
								numPokesFaintedPlayer ++;
								System.out.println(player.getCurrentPokemon().getName() + " has fainted!");
								
								//If the player has at least one pokemon that hasn't fainted, allows them to switch
								if((player.getTotalPokemon() - numPokesFaintedPlayer) > 0)
								{
									System.out.println("What would you like to do?"
											+ "\r\n 1. Use a different pokemon"
											+ "\r\n 2. Give up");
									
									//player is switching pokemon
									if(CheckInput.intInput(1, 2) == 1)
									{
										boolean notChosen = true;
										
										//Loop that allows the player to switch pokemon until they choose a valid pokemon to use
										while(notChosen)
										{
											System.out.println("Which pokemon will you use next?");
											player.listPokemon();
											int nextPoke = (CheckInput.intInput(1, player.getTotalPokemon()) - 1);
											
											//Tests if the pokemon they want to switch to has fainted
											if(nextPoke == player.getCurPokeNum())
											{
												System.out.println("This pokemon has fainted! Choose a different one.");
											}
											else
											{
												System.out.println("Come back " + player.getCurrentPokemon().getName() + "!");
												player.setCurrentPokemon(nextPoke);
												
												System.out.println("Do it " + player.getCurrentPokemon().getName() + "!");
												notChosen = false;
											}
										}
									}
									
									//player (player) decided not to switch pokemon, and gave up the battle
									else
									{
										System.out.println(opponent.getName() + " has defeated you!");
										opponent.winSpeech();
										player.lossSpeech();
										fighting = false;
									}
								}
								
								//player (player) has no more pokemon to use and has lost the battle
								else
								{
									System.out.println("All of your pokemon has fainted.");
									System.out.println(opponent.getName() + " has defeated you! You lost 20 hp for losing!");
									opponent.winSpeech();
									player.lossSpeech();
									player.loseHP(20);
									fighting = false;
								}
							}
							
							//The opponent's pokemon fainted
							else if(opponent.getCurrentPokemon().getHP() == 0)
							{
								//The player's pokemon gained experience
								int gain_exp = opponent.getCurrentPokemon().getLevel() * 3;
								player.getCurrentPokemon().gainExp(gain_exp);
								
								//Tests if the opponent is team rocket
								if(opponent.getName().equalsIgnoreCase("Team Rocket"))
								{
									//If it is, tests to see if they have any more pokemon, if they do
									if(numPokesFaintedOpp == 0)
									{
										//Recalls fainted pokemon
										System.out.println("JESSE: \"This darn kid!\" *Recalls " 
															+ opponent.getCurrentPokemon().getName() + "*");
										System.out.println("JAMES: \"Well we shall see if " 
															+ player.getName() + " can face our next pokemon!!\"");
										
										//Sends out next pokemon
										opponent.setCurrentPokemon(opponent.getNextCurPokemon());
										System.out.println("Team Rocket sends out "	 
													+ opponent.getCurrentPokemon().getName() + "!");
										numPokesFaintedOpp = 1;
										System.out.println("MEOWTH: \"Now we're really angry!!!\"");
										opponent.setAnger(true);
									}
									//Defeated both of team rocket's pokemon and makes them disappear
									else
									{
										System.out.println(opponent.getCurrentPokemon().getName() + " has fainted!");
										System.out.println("JESSE, JAMES, AAAAND MEOWTH: "
												+ "\"WE'RE BLASTING OF AGAAAAAAAIN . . . .\""
												+ "\r\n            *★* ~ping~ ");
										
										
										System.out.println("You've defeated " + opponent.getName() + "!");
										player.winSpeech();
										
										//Player gaining money
										player.gainMoney(opponent.getMoney());
										System.out.println("Gained $" + opponent.getMoney() + " from the battle!");
										fighting = false;
									}
								}
								//Opponent not team rocket and player defeated their pokemon
								else
								{
									//Speeches
									System.out.println("You've defeated " + opponent.getName() + "!");
									opponent.lossSpeech();
									player.winSpeech();
									
									//Player gaining money
									player.gainMoney(opponent.getMoney());
									System.out.println("Gained $" + opponent.getMoney() + " from the battle!");
									fighting = false;
								}
							}
							//The battle goes on
							else
							{
								//Calculates remaining hp = (Max hp - current hp)/Max hp
								double remainingHP = (opponent.getCurrentPokemon().getMaxHP() -
										opponent.getCurrentPokemon().getHP())/
										opponent.getCurrentPokemon().getMaxHP();
								
								//If opponent's pokemon is below a certain hp, then opponent becomes angry
								if(remainingHP < 0.4)	
								{
									System.out.println(opponent.getName() + " is getting furious! (●o≧д≦)o");
									opponent.setAnger(true);
								}
							}
							break;
							
				case 2:		//Player wants to run away
							if(opponent.getAnger())	//The opponent is angry and won't let you leave
							{
								System.out.println(opponent.getName() + " is angry, and stops you from leaving.");
							}
							else	//You're able to run away, but you lose 15 hp
							{
								fighting = false;
								System.out.println(opponent.getName() + " looks at you in confusion as you sprint away.");
								System.out.println("     ε=ε=ε=ε=ε=ε=٩(  ͡๏̯͡๏)۶");
								System.out.println("You lost 15 hp for leaving battle.");
								player.loseHP(15);
							}
							break;
			}
		}
	}
	
	/**
	 * Simulates a pokemon battle with a wild pokemon.
	 * @param player The player
	 * @param poke The wild pokemon
	 */
	public static void wildPokeBattle(Player player, Pokemon poke)
	{
		boolean fighting = true;
		int numberPokesFaint = 0; //Keeps track of tne number of pokemon that have fainted.
		String initialName = poke.getName();
		
		//Changes the pokemon's name to indicate it is wild
		poke.setName("Wild " + poke.getName());
		
		//Loop that simulates the fight until at least one pokemon faints or the user decides to run away
		while(fighting)
		{
			//Displays both pokemons for the fight
			player.displayCurrentPokemon(poke);
			
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
				//Gets the player's attack move's damage 
				int playerAttack = player.battle();

				//Adjusts the attack damage based on the damage table if it's a special attack
				playerAttack = (int)(playerAttack * fightTable
							[player.getCurrentPokemon().getType()]
							[poke.getType()]);

				//Chooses a random attack move for the wild pokemon, gets the damage then multiply by adjuster
				int wildPokeStyle = (int)(Math.random() * 2) + 1;
				int pokeAttack = poke.fight(wildPokeStyle, ((int)(Math.random() * 3) + 1));
				if(wildPokeStyle == 2)
				{
					//Adjusts the attack damage based on the damage table
					pokeAttack = (int) (pokeAttack * fightTable
							[poke.getType()]
							[player.getCurrentPokemon().getType()] );
				}
				
				if(poke.getAnger())
				{
					pokeAttack = pokeAttack * 2;
				}
				
				//Subtracts the attacks from the pokemons' hp
				player.getCurrentPokemon().loseHP(pokeAttack);
				poke.loseHP(playerAttack);
				
				//Tests if the player's pokemon fainted
				if(player.getCurrentPokemon().getHP() == 0)
				{
					numberPokesFaint ++;
					System.out.println(player.getCurrentPokemon().getName() + " has fainted!");
					
					//Tests if the player has at least one pokemon to switch to (pokemon not fainted)
					if((player.getTotalPokemon() - numberPokesFaint) > 0)
					{
						System.out.println("What would you like to do?"
								+ "\r\n 1. Use a different pokemon"
								+ "\r\n 2. Give up");
						
						//Player is switching pokemon
						if(CheckInput.intInput(1, 2) == 1)
						{
							boolean notChosen = true;
							
							//Loop while the player is choosing pokemon
							while(notChosen)
							{
								System.out.println("Which pokemon will you use next? (Press 0 to cancel)");
								player.listPokemon();
								int nextPoke = ((CheckInput.intInput(0, player.getTotalPokemon())) - 1);
								
								//The player doesn't want to switch pokemon and the fighting loop exits
								if(nextPoke == -1)
								{
									notChosen = false;
									fighting = false;
								}
								else if(nextPoke == player.getCurPokeNum())
								{
									System.out.println("This pokemon has fainted! Choose a different one.");
								}
								//Switching pokemon
								else
								{
									System.out.println("Come back " + player.getCurrentPokemon().getName() + "!");
									
									player.setCurrentPokemon(nextPoke);
									System.out.println("Do it " + player.getCurrentPokemon().getName() + "!");
									notChosen = false;
								}
							}
						}						
						//Player decided not to switch pokemon, and gave up the battle
						else
						{
							System.out.println("You stumble away as " + poke.getName() + " attempts to chase you.");
							fighting = false;
						}
					}
					
					//Player has no more pokemon to use and has lost the battle
					else
					{
						System.out.println("All of your pokemon has fainted.");
						System.out.println(poke.getName() + " does 10 damage to you then runs away!");
						player.loseHP(10);
						fighting = false;
					}
				}
				//Tests if the wild pokemon has fainted
				else if(poke.getHP() == 0)
				{
					System.out.println(poke.getName() + " has fainted!");
					
					//Player's pokemon gains experience and the loop exits
					int gain_exp = poke.getLevel() * 3;
					player.getCurrentPokemon().gainExp(gain_exp);
					fighting = false;
				}
			}
			//Catching the pokemon
			else if(choice == 2)
			{
				player.usePokeball();
				double chance = (poke.getMaxHP() - poke.getHP())/poke.getMaxHP();
				int capture = (int)(Math.random() * 11);
				int caught = (int)(Math.random() * 11);
				
				if(chance < 0.50)
				{
					capture = (int)(Math.random() * 5);
					caught = (int)(Math.random() * 5);
					
					if(chance < 0.25)
					{
						capture = (int)(Math.random() * 2);
						caught = (int)(Math.random() * 2);
					}
				}
				
				if(capture == caught)
				{
					System.out.println("You caught " + poke.getName() + "!");
					System.out.println("Would you like to name " + poke.getName() + "?"
							+ "\r\n 1. Yes"
							+ "\r\n 2. No");
					if(CheckInput.intInput(1, 2) == 1)
					{
						System.out.println("What name would you like to give " + poke.getName() + "?");
						String name = CheckInput.getString();
						poke.setName(name);
					}
					else
					{
						poke.setName(initialName);
					}
					player.addPokemon(poke);
					fighting = false;
				}
				else
				{
					System.out.println(poke.getName() + " has broken free and is angry!");
					poke.setAnger(true);
				}
			}
			else if(choice == 3)
			{
				System.out.println("You used a potion on " + player.getCurrentPokemon().getName() 
						+ "! " + player.getCurrentPokemon() + " gained 20 hp!");
				player.usePotion();
				player.getCurrentPokemon().gainHP(20);
			}
			else
			{
				if(poke.getAnger())
				{
					System.out.println(poke.getName() + "'s anger prevents you from leaving.(╬ಠ益ಠ)");
				}
				else
				{
					System.out.println(poke.getName() + " stares at you in confusion as you frolic away through the trees.");
					System.out.println("          ε=ε=ε=ε=ε=ε=┌(๑ʘ∀ʘ)┘");
					fighting = false;
					System.out.println("Got away safely!");
				}
			}
		}	
	}
	
	/**
	 * Simulates a trainer battle where one person attacks and the other person
	 * is the defender. It calculates the attack damage from the attacker's pokemon
	 * then the defender's pokemon takes damage.
	 * @param attacker
	 * @param defender
	 */
	private static void trainerBattle(Trainer attacker, Trainer defender)
	{
		int attack = attacker.battle();
		
		//Adjusts the damage based on the pokemon types
		attack = (int)(attack * fightTable
							[attacker.getCurrentPokemon().getType()]
							[defender.getCurrentPokemon().getType()]);
		
		defender.getCurrentPokemon().loseHP(attack);
		System.out.println();
	}
}
