 package back;
 
 import java.util.ArrayList;
 import java.util.Scanner;
 
 public class SimpleAdvanced {
 	public static Scanner sc;
 	int move,movedic;
 	Game g;
 	
 	SimpleAdvanced(Game g){
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
 			}else if(g.dices.isRolledOne() && machineMove(g.dices.getDiceOne())){
 				b1 = true;
 			}
 		}
 		if(!b1 || !b2){
 			g.dices.diceOneUses = 0;
 			g.dices.diceTwoUses = 0;
 		}
 	
 	}
 	
 	
 	
 	boolean machineMove(int move){
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
 			return BestMove(playStones,move);
 			
 		}
		return true;
 	}

 	boolean BestMove(ArrayList<Integer> playStones, int move){
 		int i = 0;
 		while( i < playStones.size()){
 			int selectedOne = playStones.get(i);
 			if(g.player == Stone.Color.WHITE && g.canMove(selectedOne, move)){
 				if(selectedOne - move < 0){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			return true;
 				}else if(g.getBoard().getStone(selectedOne - move).equals(Stone.Color.BLACK)){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			return true;
 				}else if(g.getBoard().getStone(selectedOne - move).equals( Stone.Color.WHITE )){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			return true;
 				}else if(g.getBoard().getStoneCount(selectedOne) == 1){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			return true;
 				}
 				
 			}else if(g.player == Stone.Color.BLACK && g.canMove(selectedOne, move)){
 				if(selectedOne + move >= 24){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			return true;
 				}else if(g.getBoard().getStone(selectedOne + move).equals(Stone.Color.WHITE)){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			return true;
 				}else if(g.getBoard().getStone(selectedOne + move).equals( Stone.Color.BLACK )){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			return true;
 				}else if(g.getBoard().getStoneCount(selectedOne) == 1){
 					//System.out.println("Valid Move!   Common Move: " + (selectedOne+1) + "  " + move );
 		 			g.move(selectedOne,move);
 		 			return true;
 				}
 			}
 			i++;
 		}
 		
 		i = 0;
 		while( i < playStones.size()){
 			int selectedOne = playStones.get(i);
 			if(g.canMove(selectedOne, move)){
 				g.move(selectedOne,move);
 				return true;
 			}
 			i++;
 		}
  	return false;
 	}
  	
  }


