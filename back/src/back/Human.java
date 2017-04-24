package back;

import java.io.IOException;
import java.util.Scanner;

public class Human{
	public static Scanner sc;
	int move,movedic;
	Game g;
	
	Human(Game g){
		this.g = g;
	}
	
	public void play() throws IOException{
		BoardDrawerCL.draw(g.getBoard());
		System.out.println("Dice One: " + g.dices.getDiceOne() + "   Remaining Time: " + g.dices.diceOneUses);
		System.out.println("Dice Two: " + g.dices.getDiceTwo() + "   Remaining Time: " + g.dices.diceTwoUses);
		if(g.player == Stone.Color.BLACK){
			System.out.print("WHITE");
		}else{
			System.out.print("BLACK");
		}
		System.out.println(", please enter piece location using blank-space(like a b, a is the number, b is move number): ");
		sc = new Scanner(System.in);
		String input = sc.nextLine();	
		if(input.equals("")){
			System.out.println("Error Movement!");
		}else if(input.equals("no")){
			if(checkValid()){
				System.out.println("This time no move");	
				g.dices.diceOneUses = 0;
				g.dices.diceTwoUses = 0;
			}else{
				System.out.println("There has at least one valid movement... Move that...");	
			}
			
		}else if(input.split(" ").length == 1){
			movedic = Integer.parseInt(input.split(" ")[0]);
			if(g.canPut(movedic)){
				if(movedic == g.dices.getDiceOne() || movedic == g.dices.getDiceTwo()){
					g.put(movedic);
				}
			}else{
				System.out.println("Error Movement!");
			}
		}else if(input.split(" ").length == 2){
			move = Integer.parseInt(input.split(" ")[0]);
			movedic = Integer.parseInt(input.split(" ")[1]);
			if(g.canMove(move-1, movedic)){
				if(movedic == g.dices.getDiceOne() || movedic == g.dices.getDiceTwo()){
					g.move(move-1,movedic);
					
				}
			}else{
				System.out.println("Error Movement!");
			}
		}else{
			System.out.println("Error Movement!");
		}
	}
	
	boolean checkValid(){
		for(int i = 23; i >= 0; i--){
			if(g.canPut(i))	 return false;
			if(g.dices.diceOneUses > 0){
				if(g.canMove(i, g.dices.getDiceOne())) return false;
			}
			if(g.dices.diceTwoUses > 0){
				if(g.canMove(i, g.dices.getDiceTwo())) return false;
			}
		}
		return true;
	}
	
}
