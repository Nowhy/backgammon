package back;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class Weight {
	static Random r = new Random();
	static int LaySize = 24;
	public static int[] li = {2,1,2};
	public static Map<Integer,Map<Integer,ArrayList<Double>>> layers;
	public static Map<Integer,Map<Integer,ArrayList<Double>>> betterlayers;  

	
	public static double formatDouble(double d) {
	        return (double)Math.round(d*100000)/100000;
    }
	
	public static void randomModify(ArrayList<Double> lay){
		int selectedWeight = (int)r.nextInt(lay.size());
//		double newWeight = r.nextDouble()*2 -1;
		double a = 0;
		while(Math.abs(a) < 0.05){
			a = formatDouble((r.nextDouble()*2 -1)/20);
		}
		double newWeight = lay.get(selectedWeight) + a;
		if(newWeight >= 1 || newWeight <= -1){
			randomModify(lay);
		}
		//System.out.println(formatDouble(newWeight));
		lay.remove(selectedWeight);
		lay.add(selectedWeight, formatDouble(newWeight));
	}
	
	public static void randomEachLayer(Map<Integer,ArrayList<Double>> eachlay){
		int num = eachlay.size();
		int selected = r.nextInt(num);
		randomModify(eachlay.get(selected));
	}
	
	public static Map<Integer,Map<Integer,ArrayList<Double>>> randomLayer(Map<Integer,Map<Integer,ArrayList<Double>>> layers){
		int num = layers.size();
		int selected = r.nextInt(num);
		randomEachLayer(layers.get(selected));
		return layers;
	}
	
	
	public static void adjustWorse(ArrayList<Double> lay1, ArrayList<Double> lay2){
		int num = lay1.size();
		for(int i = 0; i < num; i++){
			double newWeight = lay1.get(i)*0.95 + lay2.get(i)*0.05;
			lay2.remove(i);
			lay2.add(i, newWeight);
		}
	}
	
	public static void adjustEachLayer(Map<Integer,ArrayList<Double>> eachlay1,Map<Integer,ArrayList<Double>> eachlay2){
		int num = eachlay1.size();
		for(int i = 0; i < num; i++){
			adjustWorse(eachlay1.get(i),eachlay2.get(i));
		}
	}
	
	public static Map<Integer,Map<Integer,ArrayList<Double>>> adjustLay(Map<Integer,Map<Integer,ArrayList<Double>>> layers1,Map<Integer,Map<Integer,ArrayList<Double>>> layers2){
		int num = layers1.size();
		for(int i = 0; i < num; i++){
			adjustEachLayer(layers1.get(i),layers2.get(i));
		}
		return layers;
	}
	
	static void initLayer() throws IOException{
		layers = new HashMap<Integer,Map<Integer,ArrayList<Double>>>();
		betterlayers = new HashMap<Integer,Map<Integer,ArrayList<Double>>>() ;
		for(int i = 0; i < li.length; i++){
			Map<Integer,ArrayList<Double>> eachLay = new HashMap<Integer,ArrayList<Double>>();
			for(int j = 0; j < li[i]; j ++){
				eachLay.put(j,initEachLayer(LaySize));
			}	
			LaySize = li[i];
			layers.put(i, eachLay);
		}
		test.creatTxtFile("ANN");
		
		 String ann = "";
		 for(int i = 0; i < layers.size();i ++){
			 for(int j = 0; j < layers.get(i).size(); j++){
				 for(int m = 0; m < layers.get(i).get(j).size(); m++){
					 ann = ann + layers.get(i).get(j).get(m) + "  ";
				 }
				 ann = ann + "\n";
			 }
			 ann = ann + "\n";
		 }
		 test.writeTxtFile(ann, "ANN.txt");
		 setBetterWeight(layers);
		 layers = new HashMap<Integer,Map<Integer,ArrayList<Double>>>();
		 setWeight(Weight.randomLayer(Weight.betterlayers));
	}
	
	private static ArrayList<Double> initEachLayer(int size) {
		ArrayList<Double> arr = new ArrayList<Double>();
		for(int i = 0; i < size; i++){
				//double weight = r.nextDouble()*2 -1;
				double weight = 0;
				arr.add(weight);
		}
		return arr;
		
	}
	
	
	public static  Map<Integer,Map<Integer,ArrayList<Double>>> getWeight(){
			return layers;
	}
	
	public static void setWeight(Map<Integer,Map<Integer,ArrayList<Double>>> old){
		layers = old;
	}
	
	public static void setBetterWeight(Map<Integer,Map<Integer,ArrayList<Double>>> old){
		betterlayers = old;
	}

	
	
	
}
