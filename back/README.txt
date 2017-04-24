This file introduces the meaning of each file and tells how to run these code


/**
Author: BAO Han

Updated Date: 23th, April, 2017

**/

1.   "ANN.java" mainly introduces the ANN player. Decide the next step based on the evaluation of all possible next steps.

2.   "backgammon.java" controls the backgammon game player with the inputs.

3.   "Board.java" focuses on each stone location and the actions of stones.

4.   "BoardDrawerCL.java" draws a picture of whole board.

5.   "Dices.java" creates two dice and each die has its available use-time and die number. 

6.   "Game.java" controls the player, the turn, the board and dice.

7.   "Human.java" is a player by typing inputs. So it is a human player.

8.   "RandomPlay.java" is an automatic player that choose the valid movements in random.

9.    "Simple.java" is an automatic player that choose the valid movements with a little 
	   intelligent strategy.

9.    "SimpleAdvanced.java" is an automatic player which is almost same as "Simple.java". 
	   So I did not write this part in report. But it still works.
	
10.   "Stones.java" creates stones with color.

11.   "test.java" is the main file controlling "backgammon.java"!

12.   "Weight.java" is related to "ANN.java". It is an ANN model.

13.    "ANN.txt" records the weights of ANN.

14.    "testRandom.txt" records the win-rate of ANN that competes with "random" during 
		the training.

15.	    "testSimple.txt" records the win-rate of ANN that competes with "simple" during 
		the training.
		
15.	    "test2.java" provides the game between human, random, simple and advanced. 
		
		
==========================================================================================
1. click "run" of "test.java" in eclipse.
2. If you wants to play games with other players, click "run" of "test2.java".
3. You cannot play with ANN in this file.  But you can uncomment the code in "test.java" 
to play game with ANN Line 117 after the training. 



