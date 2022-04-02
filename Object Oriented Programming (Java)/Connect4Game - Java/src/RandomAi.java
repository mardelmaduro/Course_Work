  // CLASS: AdvancedAi
     //
     // Author: Mardel Maduro, 7760095
     //
     // REMARKS: To implement a GameAi that makes moves at random
     //
     //-----------------------------------------
public class RandomAi extends GameAi {
	
	
	//------------------------------------------------------
    //  getMove(int playerMove)
    //
    // PURPOSE:  to use the last move from the human to plan the next move of the Ai
    // PARAMETERS:
    //   int for the column choosen by the human player in his last move
    // Returns: int
	int getMove( int playerMove)
	{
	int aiMove = (int) (Math.random() * 6);
	
		return aiMove;
	}

	
	//------------------------------------------------------
    //  getMove(int playerMove)
    //
    // PURPOSE:  to use the last move from the human to play the next move of the Ai
    // PARAMETERS:
    //   int for the column choosen by the human player in his last move
    // Returns: int
	@Override
	public int makeMove(int lastCol) {
		// TODO Auto-generated method stub
		
		return getMove(lastCol);
	}

}
