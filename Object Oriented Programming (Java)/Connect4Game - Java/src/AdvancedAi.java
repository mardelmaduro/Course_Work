  // CLASS: AdvancedAi
     //
     // Author: Mardel Maduro, 7760095
     //
     // REMARKS: To implement a more complex implementation of the gameAi
     //
     //-----------------------------------------

public class AdvancedAi extends GameAi {
	int [][] board =  new int [7][6];
	int [] gravCheck = new int [7];
	int [] colWorth = new int [7];
	
	//------------------------------------------------------
    // AdvancedAi()
    //
    // PURPOSE:  constructor for an AdvancedAi object
    // PARAMETERS:
    //   none
    // Returns: AdvancedAi object
	AdvancedAi()
	{
		for (int i = 0; i < board.length; i++)
		{
			for (int j =0; j < board[0].length; j++)
			{
				board[i][j] = 0;
			}
		}
		
		for (int k = 0; k < gravCheck.length; k++)
		{
			gravCheck[k] = 5;
		}
		for (int l = 0; l < gravCheck.length; l++)
		{
			colWorth[l] = 0;
		}
	}

	
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
	
	
	board[gravCheck[playerMove]][playerMove] = 4;
	gravCheck[playerMove]--;
	
	if(playerMove < board.length-2)
	{
	board[gravCheck[playerMove]][playerMove+1] += 4;
	}
	
	if(playerMove < board.length-1 && gravCheck[playerMove] > 0 )
	{
	board[gravCheck[playerMove]-1][playerMove+1] += 4;
	}
	
	if(gravCheck[playerMove] > 0)
	{
	board[gravCheck[playerMove]+1][playerMove] += 4;
	}
	
	if((playerMove > 0) && (gravCheck[playerMove] > 0) )
	{
	board[gravCheck[playerMove]-1][playerMove-1] += 4;
	}
	
	if(playerMove > 0)
	{
	board[gravCheck[playerMove]][playerMove-1] += 4;
	}
	
	
	for (int a = 0; a < colWorth.length -1; a++)
	{
		colWorth[a] =  board[a][gravCheck[a]];
	}
	
	aiMove = minMax(colWorth);
	
	board[aiMove][gravCheck[aiMove]] += -4;
	colWorth[aiMove] += -4;

		return aiMove;
	}
	
	//------------------------------------------------------
    //  minMax(int [] array)
    //
    // PURPOSE:  uses a copy of the game board augmented with weights to find a good move
    // PARAMETERS:
    //   int array containing weights for each cell
    // Returns: int
	int minMax( int [] array)
	{
		int move = 0;
		int max = 0;
		
		for(int i = 0; i < array.length-1; i++)
		{
			if(array[i]>max  && gravCheck[i]>=0)
			{
				max = array[i];
				move  = i;
			}
		}
		
		if(max < 5)
		{
			move = (int) (Math.random() * 6);
		}
		
		
		return move;
	
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
