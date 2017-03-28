package back;

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
	
	public void play(){
 		boolean b1 = true;
 		boolean b2 = true;
 		if(g.dices.isRolledOne()){
 			if(!ranMove(g.dices.getDiceOne())){
 				b1 = false;
 			}
 		}
 		if(g.dices.isRolledTwo()){
 			if(!ranMove(g.dices.getDiceTwo())){
 				b2 = false;
 			}else if(g.dices.isRolledOne() && ranMove(g.dices.getDiceOne())){
 				b1 = true;
 			}
 		}
 		if(!b1 || !b2){
 			g.dices.diceOneUses = 0;
 			g.dices.diceTwoUses = 0;
 		}
 	
 	}
	
 	boolean ranMove(int move){
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

 		if((g.getBoard().getBarCount(Stone.Color.BLACK) > 0 && g.player == Stone.Color.WHITE)
 		||(g.getBoard().getBarCount(Stone.Color.WHITE) > 0 && g.player == Stone.Color.BLACK)){
 			if(g.canPut(move)){
 				//System.out.println("Valid Move!   Single Move: " + move );
 				g.put(move);
 				
 				if(g.player == Stone.Color.WHITE && !playStones.contains(24-move)) playStones.add(24 - move);
 				else if(g.player == Stone.Color.BLACK && !playStones.contains(move-1)) playStones.add(move - 1);
 				
 				return true;
 			}else{
 				return false;
 			}
 		}
 			
 		if(playStones.isEmpty()){
 			return false;
 		}else if(!playStones.isEmpty()){
 			return randomMove(playStones,move);
 		}
		return false;
 	}

	
	boolean randomMove(ArrayList<Integer> playStones, int move){
		Random ran = new Random();
		if(playStones.isEmpty()) return false;
		int length = playStones.size();
		int a = ran.nextInt(length);
		if(playStones.get(a) != -1){
			if(g.canMove(playStones.get(a), move)){
				g.move(playStones.get(a),move);
				if(g.player == Stone.Color.WHITE && playStones.get(a) - move >= 0 && !playStones.contains(playStones.get(a) - move)) playStones.add(playStones.get(a) - move);
				else if(g.player == Stone.Color.BLACK && playStones.get(a) + move <= 24 && !playStones.contains(playStones.get(a) + move)) playStones.add(playStones.get(a) + move);
				if(g.getBoard().getStoneCount(playStones.get(a)) == 0) playStones.remove(a);
				
				return true;
			}else{
				playStones.remove(a);
				randomMove(playStones,move);
			}
		}else{
			randomMove(playStones,move);
		}
		return false;
	}

	
}