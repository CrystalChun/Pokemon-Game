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
	public static boolean opponentBattle(Player player, Opponent opponent)
	{
		boolean fighting = true;
		trainerBattle(player, opponent); //player attacks

		player.displayCurrentPokemon(opponent.getCurrentPokemon());

		//Tests if the opponent's pokemon fainted
		if(opponent.getCurrentPokemon().getHP() == 0)
		{
			//The player's pokemon gained experience
			int gain_exp = opponent.getCurrentPokemon().getLevel() * 3;
			player.getCurrentPokemon().gainExp(gain_exp);

			//Tests if the opponent is team rocket
			if(opponent.getName().equalsIgnoreCase("Team Rocket"))
			{
				//If it is, tests to see if they have any more pokemon, if they do
				if(opponent.getFainted() < 2)
				{
					System.out.println(opponent.getCurrentPokemon().getName() + " has fainted!");
					//Recalls fainted pokemon
					System.out.println("JESSE: \"This darn kid!\" *Recalls " 
							+ opponent.getCurrentPokemon().getName() + "*");
					System.out.println("JAMES: \"Well we shall see if " 
							+ player.getName() + " can face our next pokemon!!\"");

					//Sends out next pokemon
					opponent.setCurrentPokemon(opponent.getNextCurPokemon());
					System.out.println("Team Rocket sends out "	 
							+ opponent.getCurrentPokemon().getName() + "!");
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
		else
		{
			//Calculates remaining hp = (Max hp - current hp)/Max hp
			double remainingHP = (opponent.getCurrentPokemon().getHP())/
					opponent.getCurrentPokemon().getMaxHP();

			//If opponent's pokemon is below a certain hp, then opponent becomes angry
			if(remainingHP < 0.4 && !opponent.getAnger())	
			{
				System.out.println(opponent.getName() + " is getting furious! (●o≧д≦)o");
				opponent.setAnger(true);
			}
		}

		if(fighting && !opponent.getName().equalsIgnoreCase("Team Rocket"))
		{
			trainerBattle(opponent, player); //opponent attacks

			//Tests to see if the player's pokemon fainted
			if(player.getCurrentPokemon().getHP() == 0)
			{
				player.displayCurrentPokemon(opponent.getCurrentPokemon());

				//numPokesFaintedPlayer ++;
				System.out.println(player.getCurrentPokemon().getName() + " has fainted!");

				//If the player has at least one pokemon that hasn't fainted, allows them to switch
				if((player.getTotalPokemon() - player.getFainted()) > 0)
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
							if(player.getThisPoke(nextPoke).getHP() == 0)
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

					//player decided not to switch pokemon, and gave up the battle
					else
					{
						System.out.println(opponent.getName() + " has defeated you! You lost 20 hp for losing!");
						opponent.winSpeech();
						player.lossSpeech();
						player.loseHP(20);
						fighting = false;
					}
				}

				//player has no more pokemon to use and has lost the battle
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
		}
		return fighting;
	}

	
	
	/**
	 * Simulates a pokemon battle with a wild pokemon.
	 * @param player The player
	 * @param poke The wild pokemon
	 */
	public static boolean wildPokeBattle(Player player, Pokemon poke)
	{
		boolean fighting = true;
		//Gets the player's attack move's damage 
		int playerAttack = player.battle();

		//Adjusts the attack damage based on the damage table if it's a special attack
		playerAttack = (int)(playerAttack * fightTable
				[player.getCurrentPokemon().getType()]
						[poke.getType()]);

		poke.loseHP(playerAttack);

		//Tests if the wild pokemon has fainted
		if(poke.getHP() == 0)
		{
			System.out.println(poke.getName() + " has fainted!");

			//Player's pokemon gains experience and the loop exits
			int gain_exp = poke.getLevel() * 3;
			player.getCurrentPokemon().gainExp(gain_exp);
			fighting = false;
		}

		if(fighting)
		{
			//Chooses a random attack move for the wild pokemon, gets the damage then multiply by adjuster
			int wildPokeStyle = (int)(Math.random() * 2) + 1;
			int pokeAttack = poke.fight(wildPokeStyle, ((int)(Math.random() * 3) + 1));

			//Adjusts the attack damage based on the damage table
			pokeAttack = (int) (pokeAttack * fightTable
					[poke.getType()]
							[player.getCurrentPokemon().getType()] );

			if(poke.getAnger()) //If pokemon is angry they do 2 times damage
			{
				pokeAttack = pokeAttack * 2;
			}

			//Subtracts the attacks from the pokemons' hp
			player.getCurrentPokemon().loseHP(pokeAttack);

			//Tests if the player's pokemon fainted
			if(player.getCurrentPokemon().getHP() == 0)
			{
				System.out.println(player.getCurrentPokemon().getName() + " has fainted!");

				//Tests if the player has at least one pokemon to switch to (pokemon not fainted)
				if((player.getTotalPokemon() - player.getFainted()) > 0)
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
							else if(player.getThisPoke(nextPoke).getHP() == 0)
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
						System.out.println("You stumble but not before " + poke.getName() + " does 15 damage to  you");
						player.loseHP(15);
						fighting = false;
					}
				}

				//Player has no more pokemon to use and has lost the battle
				else
				{
					System.out.println("All of your pokemon has fainted.");
					System.out.println(poke.getName() + " does 15 damage to you then runs away!");
					player.loseHP(15);
					fighting = false;
				}
			}
		}
		return fighting;
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
		
		System.out.println(attacker.getCurrentPokemon().getName() + " does " 
						+ attack + " damage to " + defender.getCurrentPokemon().getName() + ".");
		defender.getCurrentPokemon().loseHP(attack);
		System.out.println();
	}
}
