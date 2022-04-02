
  // Class: GameAi
     //
     // Author: Mardel Maduro, 7760095
     //
     // REMARKS: Used to implement the abstract class for GameAi objects
     //
     //-----------------------------------------
public abstract class GameAi implements ConnectPlayer{
	
	
abstract int getMove( int playerMove );

@Override
public int makeMove(int lastCol) {
	// TODO Auto-generated method stub
	
	return getMove(lastCol);
}

}
