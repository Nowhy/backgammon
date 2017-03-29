package back;

public class ANN {
	
	Game g;
	
	ANN(Game g){
		this.g = g;
		Weight.initLayer();
	}
	
	void play(){
		boolean b1 = true;
 		boolean b2 = true;
 		if(g.dices.isRolledOne()){
 			if(!ANNMove(g.dices.getDiceOne())){
 				b1 = false;
 			}
 		}
 		if(g.dices.isRolledTwo()){
 			if(!ANNMove(g.dices.getDiceTwo())){
 				b2 = false;
 			}else if(g.dices.isRolledOne() && ANNMove(g.dices.getDiceOne())){
 				b1 = true;
 			}
 		}
 		if(!b1 || !b2){
 			g.dices.diceOneUses = 0;
 			g.dices.diceTwoUses = 0;
 		}
 	
	}
	
	boolean ANNMove(int move){
		
	}
	
}
