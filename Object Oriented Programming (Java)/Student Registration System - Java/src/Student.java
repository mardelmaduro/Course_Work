  // CLASS: Student
     //
     // Author: Mardel Maduro, 7760095
     // REMARKS: Implements student objects for us in the course registration system
     //
     //-----------------------------------------
public class Student implements Comparable {
	protected String name;
	protected int stud_num;
	protected List transcript = new List();
	protected List waitListed = new List();
	
	 //------------------------------------------------------
    //Student
    //
    // PURPOSE:    Constructor for student objects
    // PARAMETERS:
    //     reads in an int for student number and a string for student name 
    // Returns: a Student object
    //------------------------------------------------------
	public Student(int stud_num, String name)
	{
		this.stud_num = stud_num;
		this.name = name;
	}
	  //------------------------------------------------------
    // getName()
    //
    // PURPOSE:    retrieves the name of the Student
    // PARAMETERS:
    //     none
    // Returns: String which contains the name of the Student
    //------------------------------------------------------
	public String getName()
	{
	return name;	
	}
	  //------------------------------------------------------
    // getStudNum()
    //
    // PURPOSE:   retrieves the student number of the Student
    // PARAMETERS:
    //     None
    // Returns: returns an int corresponding to the Student's student number
    //------------------------------------------------------
	public int getStudNum()
	{
		return stud_num;
	}
	  //------------------------------------------------------
    // hasPreReqs(Class c)
    //
    // PURPOSE:   checks to see if the Student has the necessary prerequisites for a class (and by extension a course)
    // PARAMETERS:
    //    A Class object
    // Returns: a true or false based on whether or not the Student has the prerequisites for the class (and by extension a course)
    //------------------------------------------------------
	public boolean hasPreReqs( Class c)
	{
		boolean hasPreReq = true;
		List preReqs = c.getPreReqs();
		Node curr = preReqs.getFirst();
		Node prev = null;
		String className = null;
		
		while(curr != null)
		{
			name = curr.toString();
			if(!transcript.Contains(name))
			{
				hasPreReq = false;
			}
			prev =curr;
			curr = curr.getNext();
		}
		
		
		return hasPreReq;
		
	}
	 //------------------------------------------------------
    // addClass(Class c)
    //
    // PURPOSE:   adds a Class to the Student's transcript 
    // PARAMETERS:
    //    A Class
    // Returns: None
    //------------------------------------------------------
	public void addClass(Class c)
	{
		if(transcript.Contains(c))
		{
			System.out.println("DUPLICATE COURSE");
		}else{
			if(hasPreReqs(c))
			{
				transcript.insert(c);
				System.out.println("CONFIRMED ADD " +this.name+ "Has been added to "+c);
			}else{
				System.out.println("NO PREREQ");
			}
		}
	}
	//------------------------------------------------------
    // printTranscript
    //
    // PURPOSE:   Prints out Students transcript (for past classes and current courses)
    // PARAMETERS:
    //    None
    // Returns: None
    //------------------------------------------------------
	public void printTranscript()
	{
		//transcript.print();
		
		Node curr = transcript.getFirst();
		
		while(curr != null)
		{
			System.out.print("For the course ");
			System.out.print(((Class) curr.getData()).getClassName());
			System.out.print(" The Status of Course is ");
			System.out.print(((Class) curr.getData()).getStatus());
			System.out.println();
			
			curr = curr.getNext();
		}
		
	}
	//------------------------------------------------------
    // addToWaitListed
    //
    // PURPOSE:   Adds a Class to the Student's list of  waitlisted courses
    // PARAMETERS:
    //    Class 
    // Returns: None
	public void addToWaitlisted( Class c)
	{
		waitListed.insert(c);
	}
	//------------------------------------------------------
    // toString
    //
    // PURPOSE:   overwrites toString from Comparable
    // PARAMETERS:
    //    None
    // Returns: String representation of Student (student name)
	public String toString()
	{
		return this.name;
	}
	//------------------------------------------------------
    // getTranscript()
    //
    // PURPOSE:  gets transcript of Student
    // PARAMETERS:
    //    None
    // Returns: transcript list for Student
	public List getTranscript()
	{
		return transcript;
	}
	
	//------------------------------------------------------
    // printStatus()
    //
    // PURPOSE:  prints out status of student, transcript of past and current courses/classes and waitlist
    // PARAMETERS:None
    //    None
    // Returns: None
	public void printStatus()
	{
		System.out.println(this.name+"'s transcript is as follows ");
		printTranscript();
		System.out.println("And is currently on the waiting list for the following ");;
		waitListed.print();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
