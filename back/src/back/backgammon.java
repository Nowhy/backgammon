package back;

import back.Game;
import java.util.Scanner;

public class backgammon {
	private static Scanner sc;

	public static void main(String argv[]){
		Game g = new Game();
		int move,movedic;
		g.roll();
		while(g.winner() == Stone.Color.NONE){
			g.roll();
			while(g.dices.isRolled()){
				System.out.println("Please enter piece location using blankspace(like a b, a is the numer, b is move number): ");
				sc = new Scanner(System.in);
				String input = sc.nextLine();	
				if(input.split(" ").length == 1){
					movedic = Integer.parseInt(input);
					if(g.canPut(movedic)){
						if(movedic == g.dices.getDiceOne() || movedic == g.dices.getDiceTwo()){
							try {	
								g.put(movedic);
							} catch (WrongMoveException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}else if(input.split(" ").length == 2){
					move = Integer.parseInt(input.split(" ")[0]);
					movedic = Integer.parseInt(input.split(" ")[1]);
					if(g.canMove(move-1, movedic)){
						if(movedic == g.dices.getDiceOne() || movedic == g.dices.getDiceTwo()){
							try {	
								g.move(move-1,movedic);
							} catch (WrongMoveException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				else{
					System.out.println("Error move!");
				}
			}
			
			}
		}
		System.out.println("Congratulations!" + g.winner() + " is the winner!");

	}
}
