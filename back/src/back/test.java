package back;

import back.backgammon;

public class test {

		 public static int finalWinner;
		 public static void testMachine(int index, backgammon bg) {
			 System.out.print("test " + index +  ": " );
			 
			if(bg.run("machine") == 1){
				 System.out.println("BLACK");
				 setFinalWinner(getFinalWinner()+1);
		        }else{
		         System.out.println("WHITE");
		        }
		 }
		 
		public static void setFinalWinner(int num){
			finalWinner = num;
		}
		
		public static int getFinalWinner(){
			return finalWinner;
		}
		 
		public static void main(String[] args){
			  backgammon bg = new backgammon();
			  int index = 0;
			  setFinalWinner(0);
			  final int count = 100;
			  do{
			   index++;
			   testMachine(index, bg);
			  }while(index<count);
			  
			  System.out.println("final % is " + (float)getFinalWinner());//(float)count);
		 }
}
