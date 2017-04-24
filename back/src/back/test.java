package back;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import back.backgammon;

public class test {

		 public static int AWinner=0,BWinner=0;
		 public static int best = 0;
		 public static Map<Integer,Map<Integer,ArrayList<Double>>> temp;
		 
		 public static void main(String[] args) throws IOException{
				  creatTxtFile("testRandom");
				  creatTxtFile("testSimple");
				  writeTxtFile("testID    " + "winner   " +  " count    " + "value", "testRandom.txt");
				  writeTxtFile("testID    " + "winner   " +  " count    " + "value", "testSimple.txt");
				  final int count = 100;
				  int round;
				  int wholeround = 0;
				  Weight.initLayer();
				  
				  do{
					  round = 0;
				  do{
					  int index = 0;
					  setAWinner(0);
					  setBWinner(0);
					  do{
						  index++;
						  if(index == 1){
							  testBGame(index,"ANN","ANN");
						  }if(index%2 == 1){
							  temp = Weight.betterlayers;
							  Weight.setBetterWeight(Weight.getWeight());
							  Weight.setWeight(temp);
							  testBGame(index,"ANN","ANN");
						  }else {
							  temp = Weight.betterlayers;
							  Weight.setBetterWeight(Weight.getWeight());
							  Weight.setWeight(temp);
							  testAGame(index,"ANN","ANN");
						  }
					  }while(index<count);
					  //writeTxtFile( "final (WHITE first)% for ANN VS ANN is " + (float)getFinalWinner(),"testSimple.txt");
				  
					  System.out.println(getAWinner() + getBWinner() );
					  if(getAWinner()  + getBWinner()  < 45){
						  temp = Weight.betterlayers;
						  Weight.setBetterWeight(Weight.getWeight());
						  Weight.setWeight(Weight.randomLayer(Weight.betterlayers));		
						  break;
					  }	else if(getAWinner()  + getBWinner()  >= 45){
						  Weight.setWeight(Weight.randomLayer(Weight.betterlayers));
					  }

					  
					  round++;
				  }while(round < 100);
					 
				  //writeTxtFile("After " + wholeround + " times:" ,"testSimple.txt");
				  int index = 0;
				  setAWinner(0);
				  setBWinner(0);
				  do{
					  index++;
					  if(index%2 == 1){
						  testAGame(index,"ANN" ,"random");
					  }else{
						  testBGame(index,"random" ,"ANN");
					  }
					 
				  }while(index<count);
				  //"final (BLACK first)% for ANN VS random " +
				   writeTxtFile( ""+(getAWinner() + getBWinner()),"testRandom.txt");


				    index = 0;
				    setAWinner(0);
					setBWinner(0);
					  do{
						  index++;
						  if(index%2 == 1){
							  testAGame(index,"ANN" ,"simple");
						  }else{
							  testBGame(index,"simple" ,"ANN");
						  }
					  }while(index<count);
					  //"final (BLACK first)% for ANN VS simple " +
					  writeTxtFile( ""+(getAWinner() + getBWinner()),"testSimple.txt");
					   
				   
				   
				   String ann = "";
		        	for(int i = 0; i < Weight.betterlayers.size();i ++){
		      			 for(int j = 0; j < Weight.betterlayers.get(i).size(); j++){
		      				 for(int m = 0; m < Weight.betterlayers.get(i).get(j).size(); m++){
		      					 ann = ann + Weight.betterlayers.get(i).get(j).get(m) + "  ";
		      				 }
		      				 ann = ann + "\n";
		      			 }
		      			 ann = ann + "\n";
		      		 }
		      	  writeTxtFile("Time: " + wholeround +"\n" + ann, "ANN.txt");
				      wholeround++;
				  }while(wholeround < 100);
					 
				
//				  testAGame(0,"human" , "ANN");
		 }
			
		 public static void testAGame(int index,String A, String B) throws IOException {
			 backgammon bg = new backgammon();	 
			 //"random", "simple","human", "advanced" or "ANN"
			if(bg.run(index, A,B) == 0){
				setAWinner(getAWinner()+1);
			}
		       
		 }
		 
		 public static void testBGame(int index,String A, String B) throws IOException {
			 backgammon bg = new backgammon();	 
			 //"random", "simple","human", "advanced" or "ANN"
			if(bg.run(index, A,B) == 1){
				setBWinner(getBWinner()+1);
			}
		       
		 }
		 
		public static void setAWinner(int num){
			AWinner = num;
		}
		
		public static int getAWinner(){
			return AWinner;
		}
		
		public static void setBWinner(int num){
			BWinner = num;
		}
		
		public static int getBWinner(){
			return BWinner;
		}
		
		/** 
		* create a txt file
		* 
		* @throws IOException 
		*/ 
		public static boolean creatTxtFile(String name) throws IOException { 
		boolean flag = false; 
		String filenameTemp =  name + ".txt"; 
		File filename = new File(filenameTemp); 
		if (!filename.exists()) { 
		filename.createNewFile(); 
		flag = true; 
		} 
		return flag; 
		} 

		/** 
		* write file
		* 
		* @param newStr 
		*          
		* @throws IOException 
		*/ 
		public static boolean writeTxtFile (String newStr, String filename) throws IOException { 
		boolean flag = false; 
		String filein = newStr; 
		String temp = ""; 

		FileInputStream fis = null; 
		InputStreamReader isr = null; 
		BufferedReader br = null; 

		FileOutputStream fos = null; 
		PrintWriter pw = null; 
		try {
			File file = new File(filename); 
			fis = new FileInputStream(file); 
			isr = new InputStreamReader(fis); 
			br = new BufferedReader(isr); 
			StringBuffer buf = new StringBuffer(); 
			for (int j = 1; (temp = br.readLine()) != null; j++) { 
				buf = buf.append(temp); 
				buf = buf.append(System.getProperty("line.separator")); 
			} 
			buf.append(filein); 

			fos = new FileOutputStream(file); 
			pw = new PrintWriter(fos); 
			pw.write(buf.toString().toCharArray()); 
			pw.flush(); 
			flag = true; 
		} catch (IOException e1) { 
			throw e1; 
		} finally { 
			if (pw != null) { 
				pw.close(); 
			} 
			if (fos != null) { 
				fos.close(); 
			} 
			if (br != null) { 
				br.close(); 
			} 
			if (isr != null) { 
				isr.close(); 
			} 
			if (fis != null) { 
				fis.close(); 
			} 
			} 
			return flag; 
		} 
		
}

