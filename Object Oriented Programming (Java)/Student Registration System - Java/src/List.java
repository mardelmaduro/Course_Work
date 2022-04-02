  // CLASS: List
     //
     // Author: Mardel Maduro, 7760095
     //
     // REMARKS: Implements a linked list for the use of storing collections of items
     //
     //-----------------------------------------
public class List {
	protected Node first;
	int size = 0;
	
	//------------------------------------------------------
    // insert(Comparable data)
    //
    // PURPOSE:  inserts a Comparable into the List
    // PARAMETERS:
    //   Comparable
    // Returns: None
	public void insert(Comparable data)
	{
		Node newNode = new Node(data);
		Node curr = first;
		Node prev = null;
		//Boolean done = false;
		
		if(first == null)
		{
			first  = newNode;
		}else{
			while((curr != null))
			{
				prev = curr;
				curr = curr.getNext();
			}
			prev.setNext(newNode);	
		}
		size++;
	}
	
	//------------------------------------------------------
    // print()
    //
    // PURPOSE:  prints out contents of List
    // PARAMETERS:
    //   None
    // Returns: None
	public void print()
	{
		if(first == null)
		{
			System.out.println("List is Empty");
		}else{
			
			Node curr = first;
			while(curr != null)
			{
				curr.print();
				curr = curr.getNext();
			}
		}
	}
	
	//------------------------------------------------------
    // remove(Comparable data)
    //
    // PURPOSE:  removes a Comparable from the List
    // PARAMETERS:
    //   Comparable
    // Returns: None
	public void remove(Comparable data)
	{
		Node prev = null;
		Node curr = first;
		Node next = null;
		
		if(first == null)
		{
			System.out.println("Nothing to remove");
		}else{
			
			while((curr != null) && curr.getData() != data)
			{
				prev = curr;
				curr = curr.getNext();
				next = curr.getNext();
				
			}
			prev.setNext(next);
		}
		
	}
	
	//------------------------------------------------------
    // isEmpty()
    //
    // PURPOSE:  checks to see if the List is empty
    // PARAMETERS:
    //   Comparable
    // Returns: Boolean 
	public boolean isEmpty()
	{
		boolean empty = false;
		
		if(this.first == null)
		{
			empty = true;
		}
		return empty;
	}
	
	//------------------------------------------------------
    // Contains(Comparable data)
    //
    // PURPOSE:  checks to see if the entered Comparable exists within the list
    // PARAMETERS:
    //   Comparable
    // Returns: Boolean
	public boolean Contains(Comparable data)
	{
		Boolean contains =  false;
		
		Node curr = first;
		Node prev = null;
		
		if(first == null)
		{
			contains = false;
		}else{
			while(curr != null)
			{
				if(curr.getData() == data)
				{
					contains = true;
				}
				
				prev = curr;
				curr = curr.getNext();
			}
		}
		return contains;
	}
	
	//------------------------------------------------------
    // getFirst()
    //
    // PURPOSE:  get Node at the beginning of the list, used for list getting and traversal
    // PARAMETERS:
    //   None
    // Returns: Node
	public Node getFirst()
	{
		return first;
	}
	
	
}
