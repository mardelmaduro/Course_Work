  // Interface: GameDisplay
     //
     // Author: Mike Domaratzki?? (Provided by assignment)
     //
     // REMARKS: Used to implement the interface for GameDisplays
     //
     //-----------------------------------------
public interface GameDisplay {
	
public void gameOver(Status PlayerNumber);

public void updateBoard(Status[][] board);

public int promptForOpponentDifficulty(int maxDifficulty);

}
