package game;

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
import java.util.Scanner;

public class test2 {

		 public static int AWinner=0,BWinner=0;
		 private static Scanner sc;
		 
		 public static void main(String[] args) throws IOException{
			 	  
				System.out.println("Please choose human, random, simple, advanced (in small letter and a blank space,such as human random): ");
				System.out.println("if you type wrong words or other unexcept words, this kind of word would be used as human");
				sc = new Scanner(System.in);
				String input = sc.nextLine();
				String a = "";
				String b = "";
				
				
				if(input.equals("")){
					System.out.println("Error input!");
				}else if(input.split(" ").length == 2){
					a = input.split(" ")[0];
					b = input.split(" ")[1];
					
				}else{
					System.out.println("Error input!");
				}
				int index = 0;
				setAWinner(0);
				setBWinner(0);
				  do{
					  index++;
					  if(index%2 == 1){
						  testAGame(index,a ,b);
					  }else{
						  testBGame(index,b ,a);
					  }
					 
				  }while(index<100);
				  

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

