  // CLASS: regSystem
     //
     // Author: Mardel Maduro, 7760095
     //
     // REMARKS: Implements the actual course registration system.
     //
     //-----------------------------------------
public class regSystem {
	protected List allStudents = new List();
	protected List allCourses = new List();
	
	public regSystem()
	{
		
	}

	public void NEW (Student s)
	{
		if(allStudents.Contains(s))
		{
			System.out.println("DUPLICATE");
		}else{
			allStudents.insert(s);
			System.out.println(s.getName()+" Has been added to the System");
		}
	}
	
	public List getAllCourses()
	{
		return this.allCourses;
	}
	
	public List getAllStudents()
	{
		return this.allStudents;
	}
	
	public void COURSE(Course c)
	{
		if(!allCourses.Contains(c))
		{
		 allCourses.insert(c);
		 System.out.println("CONFIRMED COURSE "+c+ " Has been added to the course list ");
		}else{
			System.out.println(" DUPLICATE COURSE ");
		}
	}
	
	public void ADD (Student s, Course c)
	{
		Node curr = allCourses.getFirst();
		
		if( allStudents.Contains(s) && allCourses.Contains(c))
		{
			while(curr != null)
			{
				if(curr.getData() == c)
				{
				((Course) curr.getData()).addStudent(s);	
				}
			
			}
		}
	}
	
	public void REMOVE (Student s, Course c)
	{
		Node curr = allCourses.getFirst();
		
		if( allStudents.Contains(s) && allCourses.Contains(c))
		{
			while(curr != null)
			{
				if(curr.getData() == c)
				{
				((Course) curr.getData()).removeFromWaitList(s.getStudNum());
				System.out.println("CONFIRMED REMOVAL");
				}
			
			}
		}else{
			System.out.println("NOT FOUND");
		}
	}
	
	public void TRANSCRIPT(Student s, Class c, String status)
	{
		Node curr = s.getTranscript().getFirst();
		if(s.getTranscript().Contains(c))
		{
			while(curr!= null)
			{
				
				if(curr.getData() == c && status=="PASS")
				{
					((Class) curr.getData()).passedClass();
					System.out.println("HOPPPP");
					continue;
				}
				if(curr.getData() == c && status=="FAIL"){
					((Class) curr.getData()).failedClass();
					continue;
				}
				
				curr = curr.getNext();
			}
			
		}else{
			c.setStatus(status);
			s.addClass(c);
		}
		
	}
	
	public void CAPACITY(Course c, int n)
	{
		Node curr = allCourses.getFirst();
		
		if( allCourses.Contains(c))
		{
			while(curr != null)
			{
				if(curr.getData() == c)
				{
				((Course) curr.getData()).addCapacity(n);;	
				}
			}
		}
	}
	
	public void STATUS(Student s)
	{
		s.printStatus();
	}
	
	public void LIST(Course c)
	{
		if(allCourses.Contains(c))
		{
			c.printCourseStats();
		}
		else
		{
			System.out.println("NOT FOUND");
		}
	}
	
	public Student getStudentByStud_Num(int studNum)
	{
		Student s = null;
		Node curr = allStudents.getFirst();
		
		while(curr != null)
		{
			if(((Student) curr.getData()).getStudNum() == studNum)
			{
				s = (Student) curr.getData();
			}
			curr = curr.getNext();
		}
		
		return s;
	}
	public Course getCourseByName(String name)
	{
		Course c = null;
		Node curr = allCourses.getFirst();
		
		while(curr != null)
		{
			if(((Course) curr.getData()).getCourseName() == name)
			{
				c = (Course) curr.getData();
			}
			curr = curr.getNext();
		}
		
		return c;
	}
}
