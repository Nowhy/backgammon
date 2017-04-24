package back;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RandomPlay {
	public static Scanner sc;
	int move,movedic;
	Game g;
	
	RandomPlay(Game g){
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
			if(!canPut(g.dices.getDiceOne()) && !canMove(g.dices.getDiceOne())){
				g.dices.diceOneUses = 0;
			}else if(canPut(g.dices.getDiceOne())){
				ranPut(g.dices.getDiceOne());
			}else if(canMove(g.dices.getDiceOne())){
	 			ranMove(g.dices.getDiceOne());
	 		}
		}
		if(g.dices.diceTwoUses > 0 && g.dices.diceOneUses == 0){
			if(!canPut(g.dices.getDiceTwo()) && !canMove(g.dices.getDiceTwo())){
				g.dices.diceTwoUses = 0;
			}else if(canPut(g.dices.getDiceTwo())){
				ranPut(g.dices.getDiceTwo());
			}else if(canMove(g.dices.getDiceTwo())){
	 			ranMove(g.dices.getDiceTwo());
	 		}
		}
		
		if(g.dices.diceTwoUses > 0 && g.dices.diceOneUses > 0){
			//System.out.println("dices :" + g.dices.getDiceOne() + "     " + g.dices.getDiceTwo());
			if(!canPut(g.dices.getDiceOne()) && !canMove(g.dices.getDiceOne()) && !canPut(g.dices.getDiceOne()) && !canMove(g.dices.getDiceOne())){
				g.dices.diceOneUses = 0;
				g.dices.diceTwoUses = 0;
			}else if(canPut(g.dices.getDiceOne())){
				ranPut(g.dices.getDiceOne());
			}else if(canPut(g.dices.getDiceTwo())){
				ranPut(g.dices.getDiceTwo());
			}else if(canMove(g.dices.getDiceOne())){
				ranMove(g.dices.getDiceOne());
			}else if(canMove(g.dices.getDiceTwo())){
				ranMove(g.dices.getDiceTwo());
			}
		}
 	
 	}
	
	

	boolean canPut(int move){
 		if((g.getBoard().getBarCount(Stone.Color.BLACK) > 0 && g.player == Stone.Color.WHITE)
 		||(g.getBoard().getBarCount(Stone.Color.WHITE) > 0 && g.player == Stone.Color.BLACK)){
 			if(g.canPut(move)){
 				return true;
 			}
 		}
 		return false;
 	}
	
	void ranPut(int move){
 		if((g.getBoard().getBarCount(Stone.Color.BLACK) > 0 && g.player == Stone.Color.WHITE)
 		||(g.getBoard().getBarCount(Stone.Color.WHITE) > 0 && g.player == Stone.Color.BLACK)){
 			if(g.canPut(move)){
 				g.put(move);
 			}
 		}
	}


	
	boolean canMove(int move){
		ArrayList<Integer> playStones  = new ArrayList<Integer>();
 		if(g.player ==  Stone.Color.WHITE){
 			for(int i = 23; i >= 0; i--){
 				if(g.getBoard().getStone(i).getColor() ==  Stone.Color.BLACK && g.canMove(i, move)){
 					playStones.add(i);
 				}
 			}
 		}else{
 			for(int i = 0; i < 24; i++){
 				if(g.getBoard().getStone(i).getColor() == Stone.Color.WHITE && g.canMove(i, move)){
 					playStones.add(i);
 				}
 			}
 		}
		
 		
 		if(playStones.size() == 0) return false;
		
		Random ran = new Random();
		int length = playStones.size();
		int a = ran.nextInt(length);
		if(playStones.get(a) != -1){
			if(g.canMove(playStones.get(a), move)){
				return true;
			}else{
				playStones.remove(a);
				canMove(move);
			}
		}else{
			canMove(move);
		}
		return false;
	}



	
	void ranMove(int move){
		ArrayList<Integer> playStones  = new ArrayList<Integer>();
 		if(g.player ==  Stone.Color.WHITE){
 			for(int i = 23; i >= 0; i--){
 				if(g.getBoard().getStone(i).getColor() ==  Stone.Color.BLACK && g.canMove(i, move)){
 					playStones.add(i);
 				}
 			}
 		}else{
 			for(int i = 0; i < 24; i++){
 				if(g.getBoard().getStone(i).getColor() == Stone.Color.WHITE && g.canMove(i, move)){
 					playStones.add(i);
 				}
 			}
 		}
		Random ran = new Random();
		int length = playStones.size();
		int a = ran.nextInt(length);
		if(playStones.get(a) != -1){
			if(g.canMove(playStones.get(a), move)){
				g.move(playStones.get(a),move);
			}else{
				playStones.remove(a);
				ranMove(move);
			}
		}else{
			ranMove(move);
		}
	}

	
}