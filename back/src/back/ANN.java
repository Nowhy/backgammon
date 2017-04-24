package back;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ANN {
	
	 Game g;
	 double score;
	 int max;
	 ArrayList<Double> bo;
	
	 double scoreAll;
	ANN(Game g) throws IOException{
		this.g = g;
	}
	
	
public void play() throws IOException{

//		test.writeTxtFile(g.player.toString(),"trainANN.txt");
//		test.writeTxtFile(g.dices.diceOneUses + "   "+ g.dices.getDiceOne() + "   " + g.dices.diceTwoUses + g.dices.getDiceTwo(),"trainANN.txt");
//		BoardDrawerCL.draw(g.getBoard());
		double first = 0;
		double second = 0;
		if(g.dices.diceOneUses > 0 && g.dices.diceTwoUses == 0){
			if(canANNMove(g.dices.getDiceOne()) == 0){
				g.dices.diceOneUses = 0;
			}else{
				ANNMove(g.dices.getDiceOne());
	 		}
		}else if(g.dices.diceTwoUses > 0 && g.dices.diceOneUses == 0 ){
			if(canANNMove(g.dices.getDiceTwo()) == 0){
				g.dices.diceTwoUses = 0;
			}else{
				ANNMove(g.dices.getDiceTwo());
	 		}
		}else{
 		if(g.dices.getDiceOne() == g.dices.getDiceTwo()){
 			if(canANNMove(g.dices.getDiceOne()) == 0){
 	 			g.dices.diceOneUses = 0;
 	 			g.dices.diceTwoUses = 0;
 			}else{
 				ANNMove(g.dices.getDiceOne());
 			}
 		}else{
 			if(canANNMove(g.dices.getDiceOne()) == 0 && canANNMove(g.dices.getDiceTwo()) == 0){
 	 			g.dices.diceOneUses = 0;
 	 			g.dices.diceTwoUses = 0;
 			}else{
 			first = canANNMove(g.dices.getDiceOne());
 			second = canANNMove(g.dices.getDiceTwo());
 		    if(first == 100 || first > second){
 				ANNMove(g.dices.getDiceOne());
 			}else if(second == 100 || second > first){
 				ANNMove(g.dices.getDiceTwo());
 			}else{
 				ANNMove(g.dices.getDiceOne());
 			}
 			}
 		}
		}
 	
 	}
	
     double canANNMove(int move){
		max = -1;
		score = 0;
		if( g.player == Stone.Color.WHITE){
			if(g.getBoard().getBarCount(Stone.Color.BLACK) > 0){
				if(g.canPut(move)){
					return 100;
				}
			}else{
				 for(int i = 23; i >= 0;i--){  
					 if(g.canMove(i, move)){
						 if(score == 0 || score < ValMove(i,move)){
							 max = i;
							 score = ValMove(i,move);
						 }
					 }
				 }
				 if(max != -1){
					 return score;
				 }else return 0;
			}
		}else if( g.player == Stone.Color.BLACK){
			if(g.getBoard().getBarCount(Stone.Color.WHITE) > 0){
				if(g.canPut(move)){
					return 100;
				}
			}else{
				 for(int i = 0; i < 24;i++){  
					 if(g.canMove(i, move)){
						 if(score == 0 || score < ValMove(i,move)){
							 max = i;
							 score = ValMove(i,move);
						 }
					 }
				 }
				 if(max != -1){
					 return score;
				 }else return 0;
			}
			
		}
		
			return 0;
 		
	}
     
     
     
     void ANNMove(int move){
 		max = -1;
 		score = 0;
 		if( g.player == Stone.Color.WHITE){
 			if(g.getBoard().getBarCount(Stone.Color.BLACK) > 0){
 				if(g.canPut(move)){
 					g.put(move);
 				}
 			}else{
 				 for(int i = 23; i >= 0;i--){  
 					 if(g.canMove(i, move)){
 						 if(score == 0 || score < ValMove(i,move)){
 							 max = i;
 							 score = ValMove(i,move);
 						 }
 					 }
 				 }
 				 if(max != -1){
 					 g.move(max, move);
 				 }
 			}
 		}else if( g.player == Stone.Color.BLACK){
 			if(g.getBoard().getBarCount(Stone.Color.WHITE) > 0){
 				if(g.canPut(move)){
 					g.put(move);
 				}
 			}else{
 				 for(int i = 0; i < 24;i++){  
 					 if(g.canMove(i, move)){
 						 if(score == 0 || score < ValMove(i,move)){
 							 max = i;
 							 score = ValMove(i,move);
 						 }
 					 }
 				 }
 				 if(max != -1){
 					 g.move(max, move);
 				 }
 			}
 			
 		}
  		
 	}

		
	public  double ValMove(int num,int move){
		double val = 0;
		if(g.player == Stone.Color.WHITE){
			g.move(num, move);
			bo = new ArrayList<Double>();
			bo.add((double) 1);
			for(int i = 23; i >= 0;i--){  
				if(g.getBoard().getStone(i).getColor() == Stone.Color.BLACK){
					if(g.getBoard().getHome(Stone.Color.BLACK) == 15){
						bo.add((double) 1);
					}else{
						bo.add((double) g.getBoard().getStoneCount(i)/(15-g.getBoard().getHome(Stone.Color.BLACK)));
					}
				}else if(g.getBoard().getStone(i).getColor() == Stone.Color.WHITE){
					if(g.getBoard().getHome(Stone.Color.WHITE) == 15){
						bo.add((double) -1);
					}else{
						bo.add((double) -g.getBoard().getStoneCount(i)/(15-g.getBoard().getHome(Stone.Color.WHITE)));
					}
				}else {
					bo.add((double) 0);
				}
			} 
			val = CalArray(bo);	
			g.moveback();
		}else{
			g.move(num, move);
			bo = new ArrayList<Double>();
			bo.add((double) 1);
			for(int i = 0; i < 24; i++){  
				if(g.getBoard().getStone(i).getColor() == Stone.Color.WHITE){
					if(g.getBoard().getHome(Stone.Color.WHITE) == 15){
						bo.add((double) 1);
					}else{
						bo.add((double) g.getBoard().getStoneCount(i)/(15-g.getBoard().getHome(Stone.Color.WHITE)));
					}
				}else if(g.getBoard().getStone(i).getColor() == Stone.Color.BLACK){
					if(g.getBoard().getHome(Stone.Color.BLACK) == 15){
						bo.add((double) -1);
					}else{
						bo.add((double) -g.getBoard().getStoneCount(i)/(15-g.getBoard().getHome(Stone.Color.BLACK)));
					}
				}else {
					bo.add((double) 0);
				}
			}
				val = CalArray(bo);	
				g.moveback();
		}
		return val;
	}
	
	
	public  double CalArray(ArrayList<Double> bo){
		ArrayList<Double> newbo = null;
		if(g.player == Stone.Color.WHITE){
			for(int i = 0; i < Weight.layers.size(); i ++){
				newbo = new ArrayList<Double>();
				newbo = Createnewbo(bo,Weight.layers.get(i));	
			}
		}else if(g.player == Stone.Color.BLACK){
			for(int i = 0; i < Weight.betterlayers.size(); i ++){
				newbo = new ArrayList<Double>();
				newbo = Createnewbo(bo,Weight.betterlayers.get(i));	
			}	
		}
		return CalFinalLayer(newbo);
	}
	
	
	
	public ArrayList<Double> Createnewbo(ArrayList<Double> old, Map<Integer,ArrayList<Double>> weight){
		ArrayList<Double> newbo = new ArrayList<Double>();
		newbo.add((double) 1);
		double num ;
		for(int i = 0 ; i < weight.size(); i ++){
			num = 0;
			for(int j = 0; j < weight.get(i).size(); j ++){
				if(j == 0){
					Random r = new Random();
					num = num + old.get(0)*(Weight.formatDouble((r.nextDouble()*2 -1)/100) + 0.5);
				}else{
					 num = num + old.get(j)*weight.get(i).get(j-1);
				}
				
			}
			newbo.add(num);
		}
		return newbo;
	}
	
	public double CalFinalLayer(ArrayList<Double> newbo){
		double sum = 0;
		for(int i = 0; i < newbo.size(); i ++){
			sum = sum + newbo.get(i);
		}	
		return sum;
	}
	
}
