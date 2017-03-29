package back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class Weight {
	// determined  by us
	public static int[] li = {3,2,3};

	void randomModify(ArrayList<Double> li){
		int num = li.size();
		Random r = new Random();
		int selected = r.nextInt(num+1);
		double newWeight = r.nextDouble()*2 -1;
		li.remove(selected);
		li.add(selected, newWeight);
	}
	
	void randomLayer(Map<Integer,ArrayList<Double>> layers){
		int num = layers.size();
		Random r = new Random();
		int selected = r.nextInt(num)*(num);
		randomModify(layers.get(selected));
	}
	
	static void initLayer(){
		Map<Integer,ArrayList<Double>> layers = new HashMap<Integer,ArrayList<Double>>();
		for(int i = 0; i < li.length; i++){
			layers.put(i, initEachLayer(li[i]));			
		}

	}
	
	static ArrayList<Double> initEachLayer(int NUM){
		Random r = new Random();
		ArrayList<Double> lay = new ArrayList<Double>();
		for(int i = 0; i < NUM; i++){
			double weight = r.nextDouble()*2 -1;
			lay.add(weight);	
		}
		return lay;
	}
	
	
	
}
