
public class OrderedList {
	
protected Node first;


public void insertInOrder (Comparable data)
{
	Node newNode = new Node(data);
	Node curr = first;
	Node prev = null;
	Boolean done = false;
	
	if(first==null)
	{
		first = newNode;
	}else{
		
	while((curr != null) && !done )
	{
		if((curr.getData().compareTo(data))<0)
		{
			prev = curr;
			curr = curr.getNext();
		}else{
			
			done = true;
		}
	}
	newNode.setNext(curr);
		if(prev == null)
		{
			first =newNode;
		}else{
			prev.setNext(newNode);
		}
	
	}
}



}
