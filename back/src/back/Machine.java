 package back;
 
 import java.util.ArrayList;
 import java.util.Scanner;
 
 public class Machine {
 	public static Scanner sc;
 	int move,movedic;
 	Game g;
 	
 	Machine(Game g){
 		this.g = g;
 	}
 	
 	public void play(){
 		boolean b1 = true;
 		boolean b2 = true;
 		if(g.dices.isRolledOne()){
 			if(!machineMove(g.dices.getDiceOne())){
 				b1 = false;
 			}
 		}
 		if(g.dices.isRolledTwo()){
 			if(!machineMove(g.dices.getDiceTwo())){
 				b2 = false;
 			}
 		}
 		if(!b1 || !b2){
 			g.dices.diceOneUses = 0;
 			g.dices.diceTwoUses = 0;
 		}
 			//System.out.println(g.dices.isRolled() + "   " + g.player);
 		// }

 	
 	}
 	
 	
 	
 	boolean machineMove(int move){
 		ArrayList<Integer> playStones  = new ArrayList<Integer>();
 		if(g.player ==  Stone.Color.WHITE){
 			for(int i = 0; i < 24; i++){
 				if(g.getBoard().getStone(i).getColor() ==  Stone.Color.BLACK){
 					playStones.add(i);
 				}
 			}
 		}else{
 			for(int i = 23; i >= 0; i--){
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
 				
 				if(g.player == Stone.Color.BLACK && !playStones.contains(24-move)) playStones.add(24 - move);
 				else if(g.player == Stone.Color.WHITE && !playStones.contains(move-1)) playStones.add(move - 1);
 				
 				return true;
// 			}else if(g.canPut(move2)){
// 				System.out.println("Valid Move!   Single Move: " + move2 );
// 				g.put(move2);
// 				
// 				if(g.player == Stone.Color.BLACK && !playStones.contains(24-move2)) playStones.add(24 - move2);
// 				else if(g.player == Stone.Color.WHITE && !playStones.contains(move2-1)) playStones.add(move2 - 1);
// 				
// 				return true;
 			}else{
 				return false;
 			}
 		}
 			
 		if(playStones.isEmpty()){
 			return false;
 		}else if(!playStones.isEmpty()){
 			return BestMove(playStones,move);
 			
 		}
		return true;
 	}

 	boolean BestMove(ArrayList<Integer> playStones, int move){
 		for(int i = 0; i < playStones.size(); i++){
 			int selectedOne = playStones.get(i);
 			if(g.player == Stone.Color.BLACK && g.canMove(selectedOne, move)){
 				if(selectedOne - move < 0){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 		
 		 			if(g.player == Stone.Color.WHITE && !playStones.contains(selectedOne + move)) playStones.add(selectedOne + move);
 		 			if(g.getBoard().getStoneCount(selectedOne) == 0){
 		 				playStones.remove(i);
 		 				i --;
 		 			}
 		 			return true;
 				}else if(g.getBoard().getStone(selectedOne - move).equals(Stone.Color.BLACK)){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 		
 		 			if(g.player == Stone.Color.WHITE && !playStones.contains(selectedOne + move)) playStones.add(selectedOne + move);
 		 			if(g.getBoard().getStoneCount(selectedOne) == 0){
 		 				playStones.remove(i);
 		 				i --;
 		 			}
 		 			return true;
 				}else if(g.getBoard().getStone(selectedOne - move).equals( Stone.Color.WHITE )){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			if(g.getBoard().getStoneCount(selectedOne) == 0){
 		 				playStones.remove(i);
 		 				i --;
 		 			}
 		 			return true;
 				}else{
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			if(g.getBoard().getStoneCount(selectedOne) == 0){
 		 				playStones.remove(i);
 		 				i --;
 		 			}
 		 			return true;
 				}
 			}else if(g.player == Stone.Color.WHITE && g.canMove(selectedOne, move)){
 				if(selectedOne + move >= 24){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 		
 		 			if(g.player == Stone.Color.WHITE && !playStones.contains(selectedOne + move)) playStones.add(selectedOne + move);
 		 			if(g.getBoard().getStoneCount(selectedOne) == 0){
 		 				playStones.remove(i);
 		 				i --;
 		 			}
 		 			return true;
 				}else if(g.getBoard().getStone(selectedOne + move).equals(Stone.Color.WHITE)){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 		
 		 			if(g.player == Stone.Color.WHITE && !playStones.contains(selectedOne + move)) playStones.add(selectedOne + move);
 		 			if(g.getBoard().getStoneCount(selectedOne) == 0){
 		 				playStones.remove(i);
 		 				i --;
 		 			}
 		 			return true;
 				}else if(g.getBoard().getStone(selectedOne + move).equals( Stone.Color.BLACK )){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			if(g.getBoard().getStoneCount(selectedOne) == 0){
 		 				playStones.remove(i);
 		 				i --;
 		 			}
 		 			return true;
 				}else{
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			if(g.getBoard().getStoneCount(selectedOne) == 0){
 		 				playStones.remove(i);
 		 				i --;
 		 			}
 		 			return true;
 				}
 			}
 		}
 		
 		
 		
// 		Random ran = new Random();
// 		int a = ran.nextInt(playStones.size());
//// 		 if(g.canMove(playStones.get(a), move1) &&  )
// 		if(g.canMove(playStones.get(a), move1)){
// 			System.out.println("Valid Move!   Common Move: " + (playStones.get(a)+1) + "  " + move1 );
// 			g.move(playStones.get(a),move1);
// 			
// 			if(g.player == Stone.Color.BLACK && !playStones.contains(playStones.get(a) - move1)) playStones.add(playStones.get(a) - move1);
// 			else if(g.player == Stone.Color.WHITE && !playStones.contains(playStones.get(a) + move1)) playStones.add(playStones.get(a) + move1);
// 			if(g.getBoard().getStoneCount(playStones.get(a)) == 0) playStones.remove(a);
// 			
// 			return true;
// 		}else if(g.canMove(playStones.get(a),move2)){
// 			System.out.println("Valid Move!   Common Move: " + (playStones.get(a)+1) + "  " + move2 );
// 			g.move(playStones.get(a),move2);
// 			
// 			if(g.player == Stone.Color.BLACK && !playStones.contains(playStones.get(a) - move2)) playStones.add(playStones.get(a) - move2);
// 			else if(g.player == Stone.Color.WHITE && !playStones.contains(playStones.get(a) + move2)) playStones.add(playStones.get(a) + move2);
// 			if(g.getBoard().getStoneCount(playStones.get(a)) == 0) playStones.remove(a);
// 			
// 			return true;
// 		}else{
// 			playStones.remove(a);
// 			machineMove(playStones);
// 		}
// 		return false;
  	return false;
 	}
  	
  }


