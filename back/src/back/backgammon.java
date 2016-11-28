package back;

import back.Game;
import back.Stone.Color;

import java.util.Scanner;

public class backgammon {
	private static final Color BLACK = null;
	private static Scanner sc;

	public static void main(String argv[]) throws WrongMoveException{
		Game g = new Game();
		Machine machine = null;
		Human human = null;
		System.out.println("Please choose human or machine(enter human / machine): ");
		sc = new Scanner(System.in);
		String input = sc.nextLine();
		if(input.equals("machine")){
			machine = new Machine(g);
		}
		else human = new Human(g);
		g.roll();
		while(g.winner() == Stone.Color.NONE){
			g.roll();
			while(g.dices.isRolled()){
				if(input.equals("machine") ){
					machine.play();
					new Scanner(System.in).nextLine();
				}
				else human.play();
			}
		}
		System.out.println("Congratulations!" + g.winner() + " is the winner!");

	}
	
}
