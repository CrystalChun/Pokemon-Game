/**
 * Makes an oopponent
 * @author Crystal Chun
 *
 */
public class OpponentMaker 
{
	/**
	 * Makes a random opponent of a random type
	 * @return An opponent
	 */
	public static Opponent makeRandOpponent()
	{
		int type = 0;
		
		int hp = (int)(Math.random() * 41) + 20;
		int thisOpponent = (int)(Math.random() * 6); //chooses a random opponent
		
		String name = " ";
		
		switch(thisOpponent)
		{
			case 0:		System.out.println("(ง︡’-‘︠)ง");
						name = "Youngster BEN";
						type = 2;
						break;
			case 1:		System.out.println("(⌐-■_■)");
						name = "Scientist EUGENE";
						type = 3;
						break;
			case 2:		System.out.println("( ͠° ʖ̯ ͡°)");
						name = "Biker JOE";
						type = 0;
						break;
			case 3:		System.out.println("(▰˘◡˘▰) ");
						name = "Swimmer JESS";
						type = 1;
						break;
			case 4:		System.out.println("ᕙ( * •̀ ᗜ •́ * )ᕗ");
						name = "Buff guy HANS";
						type = 3;
						break;
			case 5: 	name = "Team Rocket";
						type = (int)(Math.random() * 4);
						break;
		}
		
		Opponent opponent = new Opponent(name, hp, type);
		return opponent;
	}
}
