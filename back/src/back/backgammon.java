package back;

import java.io.IOException;

import back.Game;

//import java.util.Scanner;

public class backgammon{
//		private static Scanner sc;
	Game game;
	Simple simple;
	SimpleAdvanced advanced;
	Human human;
	RandomPlay ran;
	ANN ann;

	public int run(int index, String argv1,String argv2) throws IOException{
		game = new Game();
//		System.out.println("Please choose human or machine(enter human / machine): ");
//		sc = new Scanner(System.in);
//		String input = sc.nextLine();
		getPlayer(argv1);
		if(argv1 != argv2){
			getPlayer(argv2);
		}
		
		
		while(game.winner() == Stone.Color.NONE){
			game.roll();
			while(game.dices.isRolled() && !game.isEnded()){
				playerPlay(argv1);
			}
			if(argv1 != argv2){
				game.roll();
				while(game.dices.isRolled() && !game.isEnded()){
					playerPlay(argv2);
				}
			}
		}
		int value = 0;
		for(int i = 0;i < 24; i++){
    		value = value + (i+1)*game.getBoard().getStoneCount(i);
    	}
        if(game.winner() == Stone.Color.WHITE){
//        	test.writeTxtFile(index + "\t" +"  "+ "WHITE\t" +"      "+ (15-game.getBoard().getHome(Stone.Color.BLACK))+"\t" + "       "+value+"\t" + "\n","testRandom.txt"); 
        	return 0;
        }else{
//        	test.writeTxtFile(index + "\t" +"  "+ "BLACK\t" +"      "+ (15-game.getBoard().getHome(Stone.Color.WHITE))+"\t" + "       "+value+"\t" +"\n","testRandom.txt");
        	return 1;
       	}
        }
	
	void getPlayer(String str) throws IOException{
		switch(str){
		case "random" :
			ran = new RandomPlay(game);
			break;
		case "simple" :
			simple = new Simple(game);
			break;
		case "advanced" :
			advanced = new SimpleAdvanced(game);
			break;
		case "ANN" :
			ann = new ANN(game);
			break;
		default:
			human = new Human(game);
			break;
			
		}
	}
	
	void playerPlay(String str) throws IOException{
		switch(str){
		case "random" :
			ran.play();
			break;
		case "simple" :
			simple.play();
			break;
		case "advanced" :
			advanced.play();
			break;
		case "ANN" :
			ann.play();
			break;
		default:
			human.play();
			break;
			
		}
	}
	
}
