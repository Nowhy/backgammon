package back;

import java.util.Random;
import back.BoardDrawerCL;
import back.Stone;
import back.Stone.Color;

public class Game implements Cloneable  {
	Random generator;
	Board board;
	Stone.Color player;
	Dices dices;
	private Board lastboard;
	private Dices lastdices;
	private Color lastplayer;
	
	
	public Game(){
		generator = new Random();
		board = new Board();
		player = Stone.Color.NONE;
		dices = new Dices();
	}

	
	public void roll(){
		if(player == Stone.Color.NONE){
			board.init();
			dices.rollDifferent();
		}else{
			dices.roll();
		}
		
		switch(player){
		case WHITE:
			player = Stone.Color.BLACK;
			break;
		case BLACK:
			player = Stone.Color.WHITE;
			break;
//		case NONE:
//			if(dices.getDiceOne() > dices.getDiceTwo()){
//				player = Stone.Color.WHITE;
//			}else{
//				player = Stone.Color.BLACK;
//			}
		case NONE:
			player = Stone.Color.BLACK;
			break;
		}
		//BoardDrawerCL.draw(board);
		
	}
	

	public boolean canMove(int from, int count){
		if(!dices.isRolled()) return false;
			
		if(!dices.isOnDice(count)) return false;
		if(!board.canMove(from, count)){
			return false;
		}
		if(board.getStone(from).color() == player){
			return false;
		}
		if(player == Stone.Color.BLACK){
			if(!board.WhiteBearoff() && from + count >= 24){
				return false;
			}
		}
		if(player == Stone.Color.WHITE){
			if(!board.BlackBearoff() && from - count <= -1){
				return false;
			}
		}
		return true;
	}
	
	public void move(int from, int count) {
		if(!canMove(from,count)){
			System.out.println("Error Movement");
		}else{
			lastboard = (Board) board.clone();
			lastdices = (Dices) dices.clone();
			lastplayer = player;
			board.move(from,count);
			dices.takeDice(count);
			//BoardDrawerCL.draw(board);
		}
	}
	
	public void moveback() {
		board = (Board) lastboard.clone();
		dices = (Dices) lastdices.clone();
		player = lastplayer;
		//BoardDrawerCL.draw(board);
	}
	public boolean canPut(int number){
		if(!dices.isRolled()) return false;
		if(!dices.isOnDice(number)) return false;
		if(!board.canPut(player, number)) return false;
		return true;
	}
	
	public void put(int number){
		if(!canPut(number)){
			System.out.println("Error Movement");
		}else{
		board.put(player,number);
		dices.takeDice(number);
		//BoardDrawerCL.draw(board);
		}
	}

	public Board getBoard(){
		return board;
	}
	
	public Stone.Color getPlayer(){
		return player;
	}

	
	public boolean isEnded(){		
		return board.getHome(Stone.Color.WHITE) == 15 || board.getHome(Stone.Color.BLACK) == 15;
	}
	
	public Stone.Color winner(){
		if(!isEnded()) return Stone.Color.NONE;
		if(board.getHome(Stone.Color.WHITE) == 15) return Stone.Color.WHITE;
		else return Stone.Color.BLACK;
	}
}
