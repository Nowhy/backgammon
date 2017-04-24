package back;

import java.util.Random;

public class Dices implements Cloneable {
	private int diceOne;
	private int diceTwo;
	
	public int diceOneUses;
	public int diceTwoUses;
	private Random generator;
	
	public Dices(){
		generator = new Random();
	}
	
	protected Object clone() 
	  { 
		Dices d = new Dices();
		d.diceOne = this.diceOne;
		d.diceTwo = this.diceTwo;
		d.diceOneUses = this.diceOneUses;
		d.diceTwoUses = this.diceTwoUses;

	  return d; 
	  }
	
	
	public Dices(Dices d) {
		diceOne = d.diceOne;
		diceTwo = d.diceTwo;
		diceOneUses = d.diceOneUses;
		diceTwoUses = d.diceTwoUses;
		generator = new Random();
	}

	public void roll(){
		diceOne = generator.nextInt(6) + 1;
		diceTwo = generator.nextInt(6) + 1;
		if(diceOne == diceTwo){
			diceOneUses = diceTwoUses = 2;
		}else{
			diceOneUses = diceTwoUses = 1;
		}
	}
	
	public boolean isOnDice(int number){
		if(diceOneUses > 0 && diceOne == number) return true;
		if(diceTwoUses > 0 && diceTwo == number) return true;
		return false;
	}
	
	public void rollDifferent(){
		do{
			diceOne = generator.nextInt(6) + 1;
			diceTwo = generator.nextInt(6) + 1;
		}while(diceOne == diceTwo);
		diceOneUses = diceTwoUses = 1;
	}
	
	public void takeDice(int number){
		if(diceOneUses > 0 && diceOne == number){
			diceOneUses--;
		}else if(diceTwoUses > 0 && diceTwo == number){
			diceTwoUses--;
		}else{
			throw new IllegalArgumentException("Trying to take invalid dice " + number);
		}
	}
	
	
	public int takeDiceOne(){
		if(diceOneUses == 0) return 0;
		diceOneUses--;
		return diceOne;
	}
	
	public int takeDiceTwo(){
		if(diceTwoUses == 0) return 0;
		diceTwoUses--;
		return diceTwo;
	}
	
	public boolean isRolled(){
		return diceOneUses > 0 || diceTwoUses > 0;
	}
	
	public boolean isRolledOne(){
		return diceOneUses > 0;
	}
	
	public boolean isRolledTwo(){
		return  diceTwoUses > 0;
	}
	
	public int getDiceOne(){
		return diceOne;
	}
	
	public int getDiceTwo(){
		return diceTwo;
	}

}
