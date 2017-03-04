package back;

import java.io.IOException;

import back.Game;

//import java.util.Scanner;

public class backgammon{
//		private static Scanner sc;

	public int run(String argv,int index) throws IOException{
		Game g = new Game();
		Machine machine = null;
		Human human = null;
//		System.out.println("Please choose human or machine(enter human / machine): ");
//		sc = new Scanner(System.in);
//		String input = sc.nextLine();
		if(argv.equals("machine")){
			machine = new Machine(g);
		}
		else human = new Human(g);
		g.roll();
		while(g.winner() == Stone.Color.NONE){
			g.roll();
			while(g.dices.isRolled()){
				if(argv.equals("machine") ){
					machine.play();
					//new Scanner(System.in).nextLine();
				}
				else human.play();
			}
		}
		int value = 0;
		for(int i = 0;i < 24; i++){
    		value = value + (i+1)*g.getBoard().getStoneCount(i);
    	}
        if(g.winner() == Stone.Color.BLACK){
        	test.writeTxtFile(index + "\t" +"  "+ "BLACK\t" +"    "+ (15-g.getBoard().getHome(Stone.Color.WHITE))+"\t" + "     "+value+"\t" + "\n");
        	return 1;
        }else{
        	test.writeTxtFile(index + "\t" +"  "+ "WHITE\t" +"    "+ (15-g.getBoard().getHome(Stone.Color.BLACK))+"\t" + "     "+value+"\t" +"\n");
       		return 0;
       	}
        }
	
}
