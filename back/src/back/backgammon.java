package back;

import java.io.IOException;

import back.Game;

//import java.util.Scanner;

public class backgammon{
//		private static Scanner sc;

	public int run(String argv,int index) throws IOException{
		Game g = new Game();
		Simple machine = null;
		SimpleAdvanced machineA = null;
		Human human = null;
		RandomPlay r = null;
//		System.out.println("Please choose human or machine(enter human / machine): ");
//		sc = new Scanner(System.in);
//		String input = sc.nextLine();
		switch(argv){
		case "machine" :
			machine = new Simple(g);
			break;
		case "random" :
			r = new RandomPlay(g);
			break;
		case "random&machine" :
			machine = new Simple(g);
			r = new RandomPlay(g);
			break;
		case "random&advanced" :
			machineA = new SimpleAdvanced(g);
			r = new RandomPlay(g);
			break;
		default:
			human = new Human(g);
			break;
			
		}
		g.roll();
		while(g.winner() == Stone.Color.NONE){
			g.roll();
			while(g.dices.isRolled()){
				switch(argv){
				case "machine" :
					machine.play();
					break;
				case "random" :
					r.play();
					break;
				case "random&machine" :
					machine.play();
					break;
				case "random&advanced" :
					machineA.play();
					break;
				default:
					human.play();
					break;
					
				}
			}
			g.roll();
			while(g.dices.isRolled()){
				switch(argv){
				case "machine" :
					machine.play();
					break;
				case "random" :
					r.play();
					break;
				case "random&machine" :
					r.play();
					break;
				case "random&advanced" :
					r.play();
					break;
				default:
					human.play();
					break;
					
				}
			}
		}
		int value = 0;
		for(int i = 0;i < 24; i++){
    		value = value + (i+1)*g.getBoard().getStoneCount(i);
    	}
        if(g.winner() == Stone.Color.WHITE){
        	//test.writeTxtFile(index + "\t" +"  "+ "BLACK\t" +"    "+ (15-g.getBoard().getHome(Stone.Color.WHITE))+"\t" + "     "+value+"\t" + "\n");
        	return 1;
        }else{
        	//test.writeTxtFile(index + "\t" +"  "+ "WHITE\t" +"    "+ (15-g.getBoard().getHome(Stone.Color.BLACK))+"\t" + "     "+value+"\t" +"\n");
       		return 0;
       	}
        }
	
}
