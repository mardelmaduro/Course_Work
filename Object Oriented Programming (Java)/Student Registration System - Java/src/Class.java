  // CLASS: Class
     //
     // Author: Mardel Maduro, 7760095
     //
     // REMARKS: To implement an object that allows for a more efficient linking between students and courses
     //
     //-----------------------------------------
public class Class implements Comparable {
	protected String name;
	protected String status;
	protected List preReq = new List();
	
	//------------------------------------------------------
    // Class (String name)
    //
    // PURPOSE:  constructor for Class object using a string for name
    // PARAMETERS:
    //   String containing class name
    // Returns: Class object
	public Class (String name)
	{
		this.name = name;
	}
	
	//------------------------------------------------------
    // Class (String name, String status)
    //
    // PURPOSE:  constructor for Class object using a String for name and for status
    // PARAMETERS:
    //   String , String
    // Returns: Class
	public Class (String name , String status)
	{
		this.name = name;
		this.status = status;
	}
	
	//------------------------------------------------------
    // getClassName()
    //
    // PURPOSE:  gets class name
    // PARAMETERS:
    //   None
    // Returns: String
	public String getClassName()
	{
		return this.name;
	}
	
	//------------------------------------------------------
    // toString()
    //
    // PURPOSE:  overwrites toString method from Comparable
    // PARAMETERS:
    //   None
    // Returns: String representation of Class (class name)
	public String toString()
	{
		return name;
	}
	
	//------------------------------------------------------
    // print()
    //
    // PURPOSE:  prints out String representation of Class
    // PARAMETERS:
    //   None
    // Returns: None
	public void print()
	{
		System.out.println(name);
	}
	
	//------------------------------------------------------
    // addPreReq(Class c)
    //
    // PURPOSE:  adds a prerequisite for the class (and by extension the course
    // PARAMETERS:
    //   Class
    // Returns: None
	public void addPreReq(Class c)
	{
		preReq.insert(c);
	}
	
	//------------------------------------------------------
    // passedClass()
    //
    // PURPOSE:  sets status of class to PASS if was previously FAIl or CURRENT
    // PARAMETERS:
    //   None
    // Returns: None
	public void passedClass()
	{
		status = "pass";
	}
	
	//------------------------------------------------------
    // failedClass()
    //
    // PURPOSE:  sets status of class to FAIL if was previously CURRENT
    // PARAMETERS:
    //   None
    // Returns: None
	public void failedClass()
	{
		if(status != "pass")
		{
			status = "fail";
		}
	}
	
	//------------------------------------------------------
    // currentlyIn()
    //
    // PURPOSE:  sets status of class to CURRENT
    // PARAMETERS:
    //   None
    // Returns: None
	public void currentlyIn()
	{
		status = "current";
	}

	//------------------------------------------------------
    // getPreReqs()
    //
    // PURPOSE:  gets the List containing the prerequisites for the Class
    // PARAMETERS:
    //   None
    // Returns: List
	public List getPreReqs()
	{
		return preReq;
	}
	
	//------------------------------------------------------
    // getStatus()
    //
    // PURPOSE:  gets the Status of the Class
    // PARAMETERS:
    //   None
    // Returns: String
	public String getStatus()
	{
		return this.status;
	}
	
	//------------------------------------------------------
    // setStatus(String s)
    //
    // PURPOSE:  sets status of class to what ever s is
    // PARAMETERS:
    //   None
    // Returns: None
	public void setStatus(String s)
	{
		this.status = s;
	}
	

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}

