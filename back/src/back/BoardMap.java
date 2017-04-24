package back;

public class BoardMap {

	
	static void getMap0(Stone.Color[] stoneColors, int[] stoneCounts){
		stoneCounts[0] = 2; stoneColors[0] = Stone.Color.BLACK;
		stoneCounts[5] = 3; stoneColors[5] = Stone.Color.BLACK;
		stoneCounts[7] = 5; stoneColors[7] = Stone.Color.BLACK;
		stoneCounts[11] = 5; stoneColors[11] = Stone.Color.BLACK;
		stoneCounts[12] = 5; stoneColors[12] = Stone.Color.WHITE;
		stoneCounts[16] = 5; stoneColors[16] = Stone.Color.WHITE;
		stoneCounts[18] = 3; stoneColors[18] = Stone.Color.WHITE;
		stoneCounts[23] = 2; stoneColors[23] = Stone.Color.WHITE;
		
	}
	
	static void getMap1(Stone.Color[] stoneColors, int[] stoneCounts){
		stoneCounts[16] = 5; stoneColors[16] = Stone.Color.WHITE;
		stoneCounts[18] = 10; stoneColors[18] = Stone.Color.WHITE;
		stoneCounts[7] = 5; stoneColors[7] = Stone.Color.BLACK;
		stoneCounts[5] = 10; stoneColors[5] = Stone.Color.BLACK;
		stoneCounts[0] = 1; stoneColors[0] = Stone.Color.WHITE;
		stoneCounts[23] = 1; stoneColors[23] = Stone.Color.BLACK;
	}
	
	static void getMap2(Stone.Color[] stoneColors, int[] stoneCounts){
		stoneCounts[16] = 5; stoneColors[16] = Stone.Color.WHITE;
		stoneCounts[18] = 10; stoneColors[18] = Stone.Color.WHITE;
		stoneCounts[7] = 5; stoneColors[7] = Stone.Color.BLACK;
		stoneCounts[5] = 10; stoneColors[5] = Stone.Color.BLACK;
		stoneCounts[0] = 2; stoneColors[0] = Stone.Color.WHITE;
		stoneCounts[23] = 2; stoneColors[23] = Stone.Color.BLACK;
	}
	
	static void getMap3(Stone.Color[] stoneColors, int[] stoneCounts){
		stoneCounts[16] = 12; stoneColors[16] = Stone.Color.WHITE;
		stoneCounts[12] = 3; stoneColors[12] = Stone.Color.BLACK;
		stoneCounts[11] = 3; stoneColors[11] = Stone.Color.WHITE;
		stoneCounts[7] = 12; stoneColors[7] = Stone.Color.BLACK;
	}
	

	static void getMap15(Stone.Color[] stoneColors, int[] stoneCounts){
		stoneCounts[0] = 2; stoneColors[0] = Stone.Color.WHITE;
		stoneCounts[11] = 5; stoneColors[11] = Stone.Color.WHITE;
		stoneCounts[16] = 5; stoneColors[16] = Stone.Color.WHITE;
		stoneCounts[18] = 3; stoneColors[18] = Stone.Color.WHITE;
		stoneCounts[23] = 2; stoneColors[23] = Stone.Color.BLACK;
		stoneCounts[12] = 5; stoneColors[12] = Stone.Color.BLACK;
		stoneCounts[7] = 5; stoneColors[7] = Stone.Color.BLACK;
		stoneCounts[5] = 3; stoneColors[5] = Stone.Color.BLACK;
	}
}
