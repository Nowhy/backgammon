package back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Machine {
	public static Scanner sc;
	int move,movedic;
	Game g;
	
	Machine(Game g){
		this.g = g;
	}
	
	public void play() throws WrongMoveException{
		ArrayList<Integer> playStones  = new ArrayList<Integer>();;
		if(g.player ==  Stone.Color.WHITE){
			for(int i = 0; i < 24; i++){
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
		
		//
		while(machineMove(playStones));
		
	}
	
	boolean machineMove(ArrayList<Integer> playStones) throws WrongMoveException{
		Random ran = new Random();
		int move1 = g.dices.getDiceOne();
		int move2 = g.dices.getDiceTwo();
		int length = playStones.size();
		int a = ran.nextInt(length);
		System.out.println(a + "  dshjfsdh");
		if(a == length){
			if(g.canPut(move1)){
				g.put(move1);
				System.out.println("Valid Move!   Single Move: " + move1 );
				if(g.player == Stone.Color.BLACK && !playStones.contains(24-move1)) playStones.add(24 - move1);
				else if(g.player == Stone.Color.WHITE && !playStones.contains(move1-1)) playStones.add(move1 - 1);
				return true;
			}else if(g.canPut(move2)){
				g.put(move2);
				System.out.println("Valid Move!   Single Move: " + move2 );
				if(g.player == Stone.Color.BLACK && !playStones.contains(24-move2)) playStones.add(24 - move2);
				else if(g.player == Stone.Color.WHITE && !playStones.contains(move2-1)) playStones.add(move2 - 1);
				return true;
			}else{
				machineMove(playStones);
			}
		}else if(playStones.get(a) != -1){
			if(g.canMove(playStones.get(a), move1)){
				System.out.println("Valid Move!   Common Move: " + (playStones.get(a)+1) + "  " + move1 );
				g.move(playStones.get(a),move1);
				
				if(g.getBoard().getStoneCount(playStones.get(a)) == 0) playStones.remove(a);
				if(g.player == Stone.Color.BLACK && !playStones.contains(playStones.get(a) - move1)) playStones.add(playStones.get(a) - move1);
				else if(g.player == Stone.Color.WHITE && !playStones.contains(playStones.get(a) + move1)) playStones.add(playStones.get(a) + move1);
				
				return true;
			}else if(g.canMove(playStones.get(a),move2)){
				System.out.println("Valid Move!   Common Move: " + (playStones.get(a)+1) + "  " + move2 );
				g.move(playStones.get(a),move2);
				
				if(g.getBoard().getStoneCount(playStones.get(a)) == 0) playStones.remove(a);
				if(g.player == Stone.Color.BLACK && !playStones.contains(playStones.get(a) - move2)) playStones.add(playStones.get(a) - move2);
				else if(g.player == Stone.Color.WHITE && !playStones.contains(playStones.get(a) + move2)) playStones.add(playStones.get(a) + move2);
				
				return true;
			}else{
				playStones.set(a, -1);
				machineMove(playStones);
			}
		}else if(allFail(playStones)){
			return false;
		}else{
			machineMove(playStones);
		}
		return true;
	}
	
	boolean allFail(ArrayList<Integer> playStones){
		boolean bool = true;
		for(int i = 0; i < playStones.size() - 1; i++){
			if(playStones.get(i) != -1) bool = false;
		}
		return bool;
	}
	
}
