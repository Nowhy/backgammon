package back;

import back.Stone.Color;
import back.BoardDrawerCL;

public class Board {
	private int homeWhite;
	private int homeBlack;
	private int barWhite;
	private int barBlack;
	private int[] stoneCounts;
	private Stone.Color[] stoneColors;
	
	public Board(){
		init();
		BoardDrawerCL.draw(this);
	}
	
	public void init(){
		homeWhite = 0;
		homeBlack = 0;
		barWhite = 0;
		barBlack = 0;
		stoneCounts = new int[24];
		stoneColors = new Stone.Color[24];
		for(int i=0; i<24; i++){
			stoneColors[i] = Stone.Color.NONE;
		}
		stoneCounts[0] = 2; stoneColors[0] = Stone.Color.WHITE;
		stoneCounts[11] = 5; stoneColors[11] = Stone.Color.WHITE;
		stoneCounts[16] = 5; stoneColors[16] = Stone.Color.WHITE;
		stoneCounts[18] = 3; stoneColors[18] = Stone.Color.WHITE;
		stoneCounts[23] = 2; stoneColors[23] = Stone.Color.BLACK;
		stoneCounts[12] = 5; stoneColors[12] = Stone.Color.BLACK;
		stoneCounts[7] = 5; stoneColors[7] = Stone.Color.BLACK;
		stoneCounts[5] = 3; stoneColors[5] = Stone.Color.BLACK;
		
	}
	
	public boolean BlackBearoff(){
		int sum = 0;
		for(int i = 23; i >= 7; i--){
			if(stoneColors[i] ==  Stone.Color.BLACK) sum = getStoneCount(i) + sum;
		}
		System.out.println("hellogays" + sum);
		return sum == 0;
	}
	
	public boolean WhiteBearoff(){
		int sum = 0;
		for(int i = 0; i <= 18; i++){
			if(stoneColors[i] ==  Stone.Color.WHITE) sum = getStoneCount(i) + sum;
		}
		System.out.println("helloles" + sum);
		return sum == 0;
	}

	public int getStoneCount(int i) {
		if(i < 0 || i > 24) return 0;
		
		return stoneCounts[i];
	}

	public Stone getStone(int i) {
		if(i < 0 || i > 24) return Stone.NONE;
		
		switch(stoneColors[i]){
		case WHITE: return Stone.WHITE;
		case BLACK: return Stone.BLACK;
		default: return Stone.NONE;
		}
	}

	public int getBarCount(Color color) {
		switch(color){
		case WHITE: return barWhite;
		case BLACK: return barBlack;
		default: return 0;
		}
	}

	public boolean canMove(int from, int count){
		if(from < 0 || from > 24) return false;
		
		if(stoneCounts[from] == 0) return false;
		
		Stone.Color who = stoneColors[from];
		int target;
		if(who == Stone.Color.WHITE){
			if(barWhite > 0) return false;
			target = from + count;
		}else{
			if(barBlack > 0) return false;
			target = from - count;
		}
		if(target >= 24){
			return true;
		}else if(target <= 0){
			return true;
		}else{
			Stone.Color tarwho = stoneColors[target];
			if(tarwho == who || tarwho == Stone.Color.NONE){
				return true;
			}else{
				return stoneCounts[target] == 1;
			}
		}
	}
	
public boolean canPut(Stone.Color color, int number){
		switch(color){
		case BLACK:
			if(barWhite == 0) return false;
			if(stoneColors[number-1] == Stone.Color.BLACK) return false;
			break;
		case WHITE:
			if(barBlack == 0) return false;
			if(stoneColors[24-number] == Stone.Color.WHITE) return false;
			break;
		default:	return false;
		}
		return true;
	}
	

	public void move(int from, int count) throws WrongMoveException{
		if(!canMove(from,count)) throw new WrongMoveException();
		if(stoneColors[from] == Stone.Color.WHITE){
			int target = from + count;
			if(target >= 24){
				homeWhite++;
			}else if(stoneColors[target] == Stone.Color.BLACK){
				removeStone(target);
				barBlack++;
				addStone(target,Stone.Color.WHITE);
			}else{
				addStone(target,Stone.Color.WHITE);
			}
		}else{
			int target = from - count;
			if(target <= -1){
				homeBlack++;
			}else if(stoneColors[target] == Stone.Color.WHITE){
				removeStone(target);
				barWhite++;
				addStone(target,Stone.Color.BLACK);
			}else{
				addStone(target,Stone.Color.BLACK);
			}
		}
		removeStone(from);
	}
	
	public void put(Stone.Color color, int number) throws WrongMoveException{
		if(!canPut(color,number)) throw new WrongMoveException();
		switch(color){
		case WHITE:
			barBlack--;
			addStone(24-number,Stone.Color.BLACK);
			break;
		case BLACK:
			barWhite--;
			addStone(number-1,Stone.Color.WHITE);
			break;
		}		
	}

	private void removeStone(int from) {
		if(stoneCounts[from] <= 0)
			throw new IllegalArgumentException("Removing stone from zero at " + from);
		stoneCounts[from]--;
		if(stoneCounts[from] == 0){
			stoneColors[from] = Stone.Color.NONE;
		}
	}

	private void addStone(int to, Stone.Color color) {
		if(stoneColors[to] != Stone.Color.NONE && stoneColors[to] != color)
			throw new IllegalArgumentException("Adding wrong color of stone to " + to);
		stoneCounts[to]++;
		if(stoneColors[to] == Stone.Color.NONE){ 
			stoneColors[to] = color;
		}
	}

	public int getHome(Color color) {
		switch(color){
		case WHITE: return homeWhite;
		case BLACK: return homeBlack;
		default: return 0;
		}
	}

}
