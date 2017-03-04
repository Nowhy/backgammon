package back;

import back.Game;

//import java.util.Scanner;

public class backgammon{
//		private static Scanner sc;

	public int run(String argv){
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
        if(g.winner() == Stone.Color.BLACK){
        	return 1;
        }else{
       		return 0;
       	}
        }
	
}
