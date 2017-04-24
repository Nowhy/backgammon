 package back;
 
 import java.io.IOException;
import java.util.ArrayList;
 import java.util.Scanner;
 
 public class SimpleAdvanced {
 	public static Scanner sc;
 	int move,movedic;
 	Game g;
 	
 	SimpleAdvanced(Game g){
 		this.g = g;
 	}
 	
	
	public void play() throws IOException{
//		BoardDrawerCL.draw(g.getBoard());
//		System.out.println("Dice One: " + g.dices.getDiceOne() + "   Remaining Time: " + g.dices.diceOneUses);
//		System.out.println("Dice Two: " + g.dices.getDiceTwo() + "   Remaining Time: " + g.dices.diceTwoUses);
//		if(g.player == Stone.Color.BLACK){
//			System.out.println("WHITE");
//		}else{
//			System.out.println("BLACK");
//		}
//		
		
		if(g.dices.diceOneUses > 0 && g.dices.diceTwoUses == 0){
			if(isPut() && !canPut(g.dices.getDiceTwo())){
 	 			g.dices.diceOneUses = 0;
 			}else if(!isPut() && canMove(g.dices.getDiceOne()) == 0){
 				g.dices.diceOneUses = 0;
 			}else if(canPut(g.dices.getDiceOne())){
 				g.put(g.dices.getDiceOne());
			}else if(canMove(g.dices.getDiceOne()) > 0){
				advMove(g.dices.getDiceOne(),canMove(g.dices.getDiceOne()));
	 		}
		}else if(g.dices.diceTwoUses > 0 && g.dices.diceOneUses == 0 ){
			if(isPut() && !canPut(g.dices.getDiceTwo())){
 	 			g.dices.diceTwoUses = 0;
 			}else if(!isPut() && canMove(g.dices.getDiceTwo()) == 0){
 	 			g.dices.diceTwoUses = 0;
 			}else if(canPut(g.dices.getDiceTwo())){
 				g.put(g.dices.getDiceTwo());
			}else if(canMove(g.dices.getDiceTwo()) > 0){
				advMove(g.dices.getDiceTwo(),canMove(g.dices.getDiceTwo()));
	 		}
		}else{
 		if(g.dices.getDiceOne() == g.dices.getDiceTwo()){
 			if(isPut() && !canPut(g.dices.getDiceTwo())){
 	 			g.dices.diceOneUses = 0;
 	 			g.dices.diceTwoUses = 0;
 			}else if(!isPut() && canMove(g.dices.getDiceOne()) == 0){
 				g.dices.diceOneUses = 0;
 	 			g.dices.diceTwoUses = 0;
 			}else if(canPut(g.dices.getDiceOne())){
 				g.put(g.dices.getDiceOne());
 			}else if(canMove(g.dices.getDiceOne()) > 0){
 				advMove(g.dices.getDiceOne(),canMove(g.dices.getDiceOne()));
 			}
 		}else{
 			if(!canPut(g.dices.getDiceOne()) && canMove(g.dices.getDiceOne()) == 0 && !canPut(g.dices.getDiceOne()) && canMove(g.dices.getDiceOne()) == 0){
				g.dices.diceOneUses = 0;
				g.dices.diceTwoUses = 0;
			}else if(canPut(g.dices.getDiceOne())){
 				g.put(g.dices.getDiceOne());
 			}else if(canPut(g.dices.getDiceTwo())){
 				g.put(g.dices.getDiceTwo());
 			}else if(canMove(g.dices.getDiceOne()) >= canMove(g.dices.getDiceTwo())){
 				advMove(g.dices.getDiceOne(),canMove(g.dices.getDiceOne()));
 				advMove(g.dices.getDiceTwo(),canMove(g.dices.getDiceTwo()));
 			}else{
 				advMove(g.dices.getDiceOne(),canMove(g.dices.getDiceOne()));
 				advMove(g.dices.getDiceTwo(),canMove(g.dices.getDiceTwo()));
 			}
 		}
		}
 	
 	}
 	
 	
 	
 	boolean canPut(int move){
 		ArrayList<Integer> playStones  = new ArrayList<Integer>();
 		if(g.player ==  Stone.Color.WHITE){
 			for(int i = 23; i >= 0; i--){
 				if(g.getBoard().getStone(i).getColor() ==  Stone.Color.BLACK){
 					playStones.add(i);
 				}
 			}
 		}else{
 			for(int i = 0; i < 24; i++){
 				if(g.getBoard().getStone(i).getColor() == Stone.Color.WHITE){
 					playStones.add(i);
 				}
 			}
 		}

 		if(isPut() && g.canPut(move)){
 				//System.out.println("Valid Move!   Single Move: " + move );
 				return true;
 		}else{
 				return false;
 			
 		}
 	}
 	
 	boolean isPut(){
 		if((g.getBoard().getBarCount(Stone.Color.BLACK) > 0 && g.player == Stone.Color.WHITE)
 		 		||(g.getBoard().getBarCount(Stone.Color.WHITE) > 0 && g.player == Stone.Color.BLACK))
 		return true;
 		
 		else return false;
 	}

 	int canMove(int move){
 		ArrayList<Integer> playStones  = new ArrayList<Integer>();
 		if(g.player ==  Stone.Color.WHITE){
 			for(int i = 23; i >= 0; i--){
 				if(g.getBoard().getStone(i).getColor() ==  Stone.Color.BLACK){
 					playStones.add(i);
 				}
 			}
 		}else{
 			for(int i = 0; i < 24; i++){
 				if(g.getBoard().getStone(i).getColor() == Stone.Color.WHITE){
 					playStones.add(i);
 				}
 			}
 		}
 		
 		int i = 0;
 		int high = 0;
 		while( i < playStones.size()){
 			int selectedOne = playStones.get(i);
 			if(g.player == Stone.Color.WHITE && g.canMove(selectedOne, move)){
 				if(selectedOne - move < 0){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 					high = 4;
 				}else if(g.getBoard().getStone(selectedOne - move).equals(Stone.Color.WHITE)){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 					if(high <= 3) high = 3;
 				}else if(g.getBoard().getStone(selectedOne - move).equals( Stone.Color.BLACK )){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 					if(high <= 2) high = 2;
 				}else{
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 					if(high <= 1) high = 1;
 				}
 			}else if(g.player == Stone.Color.BLACK && g.canMove(selectedOne, move)){
 				if(selectedOne + move >= 24){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			high = 4;
 				}else if(g.getBoard().getStone(selectedOne + move).equals(Stone.Color.BLACK)){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 					if(high <= 3) high = 3;
 				}else if(g.getBoard().getStone(selectedOne + move).equals( Stone.Color.WHITE )){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 					if(high <= 2) high = 2;
 				}else{
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 					if(high <= 1) high = 1;
 				}
 			}
 			i++;
 		}
 		
  	return high;
 	}
  	
 	
 	void advMove(int move, int high){
 		ArrayList<Integer> playStones  = new ArrayList<Integer>();
 		if(g.player ==  Stone.Color.WHITE){
 			for(int i = 23; i >= 0; i--){
 				if(g.getBoard().getStone(i).getColor() ==  Stone.Color.BLACK){
 					playStones.add(i);
 				}
 			}
 		}else{
 			for(int i = 0; i < 24; i++){
 				if(g.getBoard().getStone(i).getColor() == Stone.Color.WHITE){
 					playStones.add(i);
 				}
 			}
 		}
 		int i = 0;
 		while( i < playStones.size()){
 			int selectedOne = playStones.get(i);
 			if(g.player == Stone.Color.WHITE && g.canMove(selectedOne, move)){
 				
 				if(high == 4 && selectedOne - move < 0){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			break;
 				}else if(high == 3 && g.getBoard().getStone(selectedOne - move).equals(Stone.Color.BLACK)){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			break;
 				}else if(high == 2 && g.getBoard().getStone(selectedOne - move).equals( Stone.Color.WHITE )){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			break;
 				}else if(high == 1){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			break;
 				}
 			}else if(g.player == Stone.Color.BLACK && g.canMove(selectedOne, move)){
 				if(high == 4 && selectedOne + move >= 24){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			break;
 				}else if(high == 3 && g.getBoard().getStone(selectedOne + move).equals(Stone.Color.WHITE)){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			break;
 				}else if(high == 2 && g.getBoard().getStone(selectedOne + move).equals( Stone.Color.BLACK )){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			break;
 				}else if(high == 1){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			break;
 				}
 			}
 			i++;
 		}
 	}
  }


