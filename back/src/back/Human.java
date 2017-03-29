package back;

import java.util.Scanner;

public class Human{
	public static Scanner sc;
	int move,movedic;
	Game g;
	
	Human(Game g){
		this.g = g;
	}
	
	public void play(){
		System.out.println("Please enter piece location using blankspace(like a b, a is the numer, b is move number): ");
		sc = new Scanner(System.in);
		String input = sc.nextLine();	
		if(input.equals("no")){
			System.out.println("This time no move");					
		}else if(input.split(" ").length == 1){
			movedic = Integer.parseInt(input);
			if(g.canPut(movedic)){
				if(movedic == g.dices.getDiceOne() || movedic == g.dices.getDiceTwo()){
					g.put(movedic);
				}
			}
		}else if(input.split(" ").length == 2){
			move = Integer.parseInt(input.split(" ")[0]);
			movedic = Integer.parseInt(input.split(" ")[1]);
			if(g.canMove(move-1, movedic)){
				if(movedic == g.dices.getDiceOne() || movedic == g.dices.getDiceTwo()){
					g.move(move-1,movedic);
					
				}
			}
		else{
			System.out.println("Error Movement!");
		}
	}
	
	}
	
}
