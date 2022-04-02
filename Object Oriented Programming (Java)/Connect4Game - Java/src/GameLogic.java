  // CLASS: GameLogic
     //
     // Author: Mardel Maduro, 7760095
     //
     // REMARKS: To implement the logic that drives the backend of the connect 4 game
     //
     //-----------------------------------------

import java.util.Arrays;

public class GameLogic implements ConnectController{
	
	private GameDisplay gD;
	private final int WIDTH = 7;
	private final int HEIGHT = 6;
	private GameAi Ai;
	private int difLevel;
	private final int MAXLEVEL = 3;
	
	private Status[][] board =  new Status[HEIGHT][WIDTH];
	
	//------------------------------------------------------
    // Class (GameDisplay gD)
    //
    // PURPOSE:  constructor for game logic object
    // PARAMETERS:
    //   GameDisplay object which implements the interface for the game
    // Returns: GameLogic object
	public GameLogic(GameDisplay gD)
	{
		this.gD = gD;
		//difLevel = gD.promptForOpponentDifficulty(MAXLEVEL);
		this.reset();
		
		
		
	}

	//------------------------------------------------------
    //  addPeice(int col)
    //
    // PURPOSE:  the method informs the controller that a piece has been played in the column ‘col’. This represents the move by the human player.
    // PARAMETERS:
    //   int for the column choosen by the human player
    // Returns: boolean
	@Override
	public boolean addPiece(int col) {
		
		boolean valid = false;
		
		int row = gravCheck(col);
		int aiRow = 0;
		int aiCol = 0;
		
		if(this.isValid(col))
		{
		board[row][col] = Status.ONE;
		//Arrays.fill(board[row], col,col , Status.ONE);
		
		gD.updateBoard(this.board);
		
			if(isWin(row,col))
			{
				gD.gameOver(Status.ONE);
			}
			
			
			aiCol = Ai.makeMove(col);
			
			if(!this.isValid(aiCol)){
				while(!this.isValid(aiCol))
				{
					aiCol = Ai.makeMove(col);
				}
			}
			
			aiRow = gravCheck(aiCol);
			
			board[aiRow][aiCol] =  Status.TWO;
			gD.updateBoard(this.board);
			if(isWin(aiRow,aiCol))
			{
				gD.gameOver(Status.TWO);
			}
			
			
		valid = true;
		}
		
		return valid;
	}

	//------------------------------------------------------
    //  reset()
    //
    // PURPOSE:  resets the board for the game and reintializes the Ai
    // PARAMETERS:
    //   none
    // Returns: void
	@Override
	public void reset() {
		
		
		for (int i = 0; i < HEIGHT; i++) {
			Arrays.fill(board[i], Status.NEITHER);
		}

		difLevel = gD.promptForOpponentDifficulty(MAXLEVEL);
		
		if(difLevel < MAXLEVEL)
		{
			Ai = new RandomAi();
		}else
		{
			Ai = new AdvancedAi();
		}
		
		
		//gD.updateBoard(board);
		
	}
	
	//------------------------------------------------------
    //  gravCheck(int colNum)
    //
    // PURPOSE:  checks the lowest open position in the provided column
    // PARAMETERS:
    //   int
    // Returns: int
	public int gravCheck(int colNum)
	{
		int row = 5;
		
		while(board[row][colNum] != Status.NEITHER)
		{
			row--;
		}
		
		return row;
	}
	
	//------------------------------------------------------
    //  isValid(int col)
    //
    // PURPOSE:  checks to see if playing a piece in the provided column is valid given the open spaces in said column
    // PARAMETERS:
    //   int
    // Returns: boolean
	public boolean isValid (int col)
	{
		boolean isValid = false;
		
		if(gravCheck(col) >= 0)
		{
			isValid = true;
		}
		
		
		return isValid;
	}
	
	//------------------------------------------------------
    //  isWin(int col)
    //
    // PURPOSE:  checks to see if playing a piece in the provided column satifies win conditions
    // PARAMETERS:
    //   int
    // Returns: boolean
	public boolean isWin (int row, int col)
	{
		boolean isWin = false;
		
		if(check4Row(row,col) ||check4Diag(row,col) || check4Col(row,col) )
		{
			isWin = true;
		}
		
		return isWin;
	}
	
	//------------------------------------------------------
    //  check4Row(int row, int col)
    //
    // PURPOSE:  checks to see is the played piece gives a row of 4
    // PARAMETERS:
    //   int
    // Returns: boolean
	public boolean check4Row(int row, int col)
	{
		boolean rowFound = false;
		
		if(col<=board[0].length-4 && board[row][col] == board[row][col+1] && board[row][col+1] == board[row][col+2] && board[row][col+2] == board[row][col+3])
		{
			rowFound = true;
		}
		if((col<=board[0].length-1  && col >= 3) && (board[row][col] == board[row][col-1]) && (board[row][col-1] == board[row][col-2]) && (board[row][col-2] == board[row][col-3]))
		{
			rowFound = true;
		}
		if((col<=board[0].length-2 && col >= 2) && (board[row][col] == board[row][col+1]) && (board[row][col] == board[row][col-1]) && (board[row][col-1] == board[row][col-2]))
		{
			rowFound = true;
		}
		if((col<=board[0].length-3  && col >= 1) && (board[row][col] == board[row][col-1]) && (board[row][col] == board[row][col+1]) && (board[row][col+1] == board[row][col+2]))
		{
			rowFound = true;
		}
		
		return rowFound;
	}
	
	//------------------------------------------------------
    //  check4Diag(int row, int col)
    //
    // PURPOSE:  checks to see is the played piece gives a diagonal of 4
    // PARAMETERS:
    //   int
    // Returns: boolean
	public boolean check4Diag(int row, int col)
	{
		boolean diagFound = false;
		
		if((col<=board[0].length-4) && (row>=3 && row<=5) && (board[row][col] == board[row-1][col+1]) && (board[row-1][col+1] == board[row-2][col+2]) && (board[row-2][col+2] == board[row-3][col+3]))
		{
			diagFound = true;
		}
		if((col<=board[0].length-3 && col>=1) && (row>=2 && row<=4) && (board[row][col] == board[row+1][col-1]) && (board[row][col] == board[row-1][col+1]) && (board[row-1][col+1] == board[row-2][col+2]))
		{
			diagFound = true;
		}
		if((col<=board[0].length-2 && col>=2) &&(row>=1 && row<=3) && (board[row][col] == board[row+1][col-1]) && (board[row+1][col-1] == board[row+2][col-2]) && (board[row][col] == board[row-1][col+1]))
		{
			diagFound = true;
		}
		if((col<=board[0].length-1 && col>=3) && (row>=0 && row<=2) && (board[row][col] == board[row+1][col-1]) && (board[row+1][col-1] == board[row+2][col-2]) && (board[row+2][col-2] == board[row+3][col-3]))
		{
			diagFound = true;
		}
	
		
		return diagFound;
	}
	
	//------------------------------------------------------
    //  check4Col(int row, int col)
    //
    // PURPOSE:  checks to see is the played piece gives a column of 4
    // PARAMETERS:
    //   int
    // Returns: boolean
	public boolean check4Col(int row, int col)
	{
		boolean colFound = false;
		
		if((row>=0 && row <=2) && (board[row][col] == board[row+1][col]) && (board[row+1][col] == board[row+2][col]) && (board[row+2][col] == board[row+3][col]))
		{
		colFound = true;
		}
		
		return colFound;
	}
	
}
