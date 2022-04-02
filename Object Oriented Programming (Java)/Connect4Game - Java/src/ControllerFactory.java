  // Class: ConnectControllerFactory
     //
     // Author: Mike Domaratzki?? (Provided by assignment)
     //
     // REMARKS: Used to implement the ConnectController Factory class
     //
     //-----------------------------------------
public class ControllerFactory {
	
public static ConnectController makeController(GameDisplay
gd) 
{
return new GameLogic(gd);
}

}
