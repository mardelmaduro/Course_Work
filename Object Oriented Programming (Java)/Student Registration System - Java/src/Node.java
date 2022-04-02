  // CLASS: NODE
     //
     // Author: Mardel Maduro, 7760095
     //
     // REMARKS: Implements a node class for use in creating linked lists
     //
     //-----------------------------------------
public class Node {
	protected Comparable data;
	protected Node next;

	//------------------------------------------------------
    // Node()
    //
    // PURPOSE:  constructor for Node Class
    // PARAMETERS:
    //    None
    // Returns: empty Node
	public Node()
	{
		this.next=null;
	}
	
	//------------------------------------------------------
    // Node(Comparable data)
    //
    // PURPOSE:  constructor for Node Class
    // PARAMETERS:
    //    Comparable
    // Returns: Node with data
	public Node(Comparable data)
	{
		this.data = data;
	}
	
	//------------------------------------------------------
    // getData()
    //
    // PURPOSE:  gets data from Node
    // PARAMETERS:
    //    None
    // Returns: Comparable (whatever type is within the Node)
	public Comparable getData()
	{
		return data;
	}
	
	//------------------------------------------------------
    // getNext()
    //
    // PURPOSE:  gets next Node
    // PARAMETERS:
    //    None
    // Returns: Node
	public Node getNext()
	{
		return next;
	}
	//Auto generated (I think)
	public Comparable getComparable()
	{
		return data;
	}
	
	//------------------------------------------------------
    // setNext(Node n)
    //
    // PURPOSE:  gets what Node comes next
    // PARAMETERS:
    //    Node
    // Returns: None
	public void setNext(Node n)
	{
		next = n;
	}
	
	//------------------------------------------------------
    // print()
    //
    // PURPOSE:  prints out string representation for Node
    // PARAMETERS:
    //    None
    // Returns: None
	public void print()
	{
		System.out.println(data.toString());
	}
}


