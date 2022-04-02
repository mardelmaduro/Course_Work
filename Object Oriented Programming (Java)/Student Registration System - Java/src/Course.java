  // CLASS: Course
     //
     // Author: Mardel Maduro, 7760095
     //
     // REMARKS: Implements a course item for use in the course registration system.
     //
     //-----------------------------------------
public class Course implements Comparable {
	protected Class course;
	protected int capacity = 0;
	protected int currInClass = 0;
	protected List students = new List();
	protected List waitList = new List();
	
	//------------------------------------------------------
    // Course(String name)
    //
    // PURPOSE:  constructor for Course object using a String 
    // PARAMETERS:
    //   String
    // Returns: Course
	public Course(String name)
	{
		this.course = new Class(name);
	}
	
	//------------------------------------------------------
    // Course(Class name)
    //
    // PURPOSE:  constructor for Course object using a Class object
    // PARAMETERS:
    //   Class
    // Returns: Course
	public Course(Class name)
	{
		this.course = name;
	}
	
	//------------------------------------------------------
    // getCourse()
    //
    // PURPOSE:  gets Class for which the course is based on
    // PARAMETERS:
    //   None
    // Returns: Class
	public Class getCourse()
	{
		return this.course;
	}
	
	//------------------------------------------------------
    // getCourseName()
    //
    // PURPOSE:  gets the name of the Course
    // PARAMETERS:
    //   None
    // Returns: String 
	public String getCourseName()
	{
		return this.course.getClassName();
	}
	
	//------------------------------------------------------
    // addCapacity(int num)
    //
    // PURPOSE:  increases the capacity of the stuents list for the course by num
    // PARAMETERS:
    //   int
    // Returns: None
	public void addCapacity(int num)
	{
		Node curr = waitList.getFirst();
		capacity += num;
		Student currStud = null;
		
		if(!waitList.isEmpty())
		{
			while(curr != null && currInClass<capacity )
			{
				currStud = (Student) curr.getData();
				students.insert(currStud);
				waitList.remove(currStud);
				currInClass++;
			}
		}
		
	}
	
	//------------------------------------------------------
    // getCapacity()
    //
    // PURPOSE:  get the capacity of the course
    // PARAMETERS:
    //   None
    // Returns: int
	public int getCapacity()
	{
		return  capacity;
	}
	
	//------------------------------------------------------
    // addStudent(Student s)
    //
    // PURPOSE:  adds a student to the course
    // PARAMETERS:
    //   Student
    // Returns: None
	public void addStudent(Student s)
	{
		if(currInClass < capacity && !students.Contains(s))
		{
		s.addClass(course);
		this.students.insert(s);
		System.out.println("ADDED to the course");
		currInClass++;
		
		}else{
			
		s.addToWaitlisted(course);
		this.waitList.insert(s);
		System.out.print(s.getName()+" Has beed added to the wait list for "+this.getCourseName());
		}
	}
	
	//------------------------------------------------------
    // getPreReqs()
    //
    // PURPOSE:  gets the List containing the prerequisites for the Course
    // PARAMETERS:
    //   None
    // Returns: List
	public List getPreReqs()
	{
		return this.course.getPreReqs();
	}
	
	
	//------------------------------------------------------
    // printStudentList()
    //
    // PURPOSE:  prints out the List containing the students currently enrolled in the Course
    // PARAMETERS:
    //   None
    // Returns: None
	public void printStudentList()
	{
		students.print();
	}
	
	//------------------------------------------------------
    // printWaitList()
    //
    // PURPOSE:  prints out the List containing the Students currently on the waitlist for the Course
    // PARAMETERS:
    //   None
    // Returns: None
	public void printWaitList()
	{
		waitList.print();
	}
	
	
	//------------------------------------------------------
    // removeFromWaitList(int studNum)
    //
    // PURPOSE:  remoeves a student from the waitlist for the course
    // PARAMETERS:
    //   int
    // Returns: None
	public void removeFromWaitList(int studNum)
	{
		Node curr = waitList.getFirst();
		Student s  =  (Student) curr.getData();
		
		while((curr != null) && ((Student) curr.getData()).getStudNum() != studNum)
		{
			s = (Student) curr.getData();
			
			if(s.getStudNum() == studNum)
			{
				waitList.remove(s);
			}
			curr = curr.getNext();
		}
		
	}
	
	//------------------------------------------------------
    // failedCourse()
    //
    // PURPOSE:  sets status for the Course as FAIL if was CURRENT
    // PARAMETERS:
    //   Student
    // Returns: None
	public void failedCourse(Student s)
	{
		List transcript = s.getTranscript();
		
		Node curr = transcript.getFirst();
		if(course.getStatus() != "PASS")
		{
			while(curr != null)
			{
				if(curr.getData() == course)
				{
				((Class) curr.getData()).failedClass();
				continue;
				}
			
				curr = curr.getNext();
			}
		}
	}
	
	
	//------------------------------------------------------
    // passedCourse()
    //
    // PURPOSE:  sets status for the Course as PASS if was CURRENT or FAIL
    // PARAMETERS:
    //   Student
    // Returns: None
	public void passedCourse(Student s)
	{
		List transcript = s.getTranscript();
		
		Node curr = transcript.getFirst();
		
		while(curr != null)
		{
			if(curr.getData() == course)
			{
				((Class) curr.getData()).passedClass();
				continue;
			}
			
			curr = curr.getNext();
		}
	}
	
	//------------------------------------------------------
    // toString()
    //
    // PURPOSE:  overwrite the toString method from Comparable
    // PARAMETERS:
    //   None
    // Returns: String
	public String toString()
	{
		return this.getCourseName();
	}
	
	//------------------------------------------------------
    // printCourseStats()
    //
    // PURPOSE:  prints out List containing students currently enrolled in course as well as List containing Students on the wait-list
    // PARAMETERS:
    //   None
    // Returns: None
	public void printCourseStats()
	{
		System.out.println("STATS FOR "+this.course.getClassName());
		System.out.println("Students currently in this class are:" );
		students.print();
		System.out.println("Students currently on the waiting list are:" );
		waitList.print();
	}
	
	//------------------------------------------------------
    // getWaitList()
    //
    // PURPOSE:  gets the List containing the students currently on the wait list for the Course
    // PARAMETERS:
    //   None
    // Returns: List
	public List getWaitList()
	{
		return this.waitList;
	}
	
	//------------------------------------------------------
    // getStudentList()
    //
    // PURPOSE: gets the List containing the students currently enrolled in the course
    // PARAMETERS:
    //   None
    // Returns: List
	public List getStudentList()
	{
		return this.students;
	}
	
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
