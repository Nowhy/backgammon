package back;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


import back.backgammon;

public class test {
		private static String filenameTemp; 

		 public static int finalWinner;
		 public static void testGame(int index, backgammon bg) throws IOException {
			 
			if(bg.run("random&machine",index) == 1) setFinalWinner(getFinalWinner()+1);
		       
		 }
		 
		public static void setFinalWinner(int num){
			finalWinner = num;
		}
		
		public static int getFinalWinner(){
			return finalWinner;
		}
		
		/** 
		* create a txt file
		* 
		* @throws IOException 
		*/ 
		public static boolean creatTxtFile(String name) throws IOException { 
		boolean flag = false; 
		filenameTemp =  name + ".txt"; 
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
		public static boolean writeTxtFile (String newStr) throws IOException { 
		boolean flag = false; 
		String filein = newStr; 
		String temp = ""; 

		FileInputStream fis = null; 
		InputStreamReader isr = null; 
		BufferedReader br = null; 

		FileOutputStream fos = null; 
		PrintWriter pw = null; 
		try {
			File file = new File(filenameTemp); 
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

		 
		public static void main(String[] args) throws IOException{
			 creatTxtFile("testSimple");
			  writeTxtFile("testID    " + "winner   " +  " count    " + "value" );
			  backgammon bg = new backgammon();
			  int index = 0;
			  setFinalWinner(0);
			  final int count = 100;
			  do{
			   index++;
			   testGame(index, bg);
			  }while(index<count);
			  
			  writeTxtFile( "final % is " + (float)getFinalWinner());//(float)count);
		 }
}
