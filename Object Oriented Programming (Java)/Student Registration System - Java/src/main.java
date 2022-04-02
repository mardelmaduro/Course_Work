     //-----------------------------------------
     // NAME		: Mardel Maduro 
     // STUDENT NUMBER	: 7760095
     // COURSE		: COMP 2150
     // INSTRUCTOR	: Mike Domaratzki
     // ASSIGNMENT	: assignment #1  
     // 
     // REMARKS: Purpose of program is to implement a course registration system which works by reading in commands from a text file
     //
     //
     //-----------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		regSystem rS = new regSystem();
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);

        File file = chooser.getSelectedFile();
        

        try{
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
        String line = reader.readLine();
        while (line != null){
           // System.out.print(line);
        	String[] splited = line.split("\\s+");
            
        	if(line.startsWith("#"))
        	{
        		System.out.println("COMMENT: "+line);
        	}
        	
        	
        	if(line.startsWith("NEW"))
            {
            	int studNum = Integer.parseInt(splited[1]);
            	int x = splited[0].length()+splited[1].length()+2;
            	String name = line.substring(x, line.length() - 1);
            	Student s = new Student(studNum, name);
            	rS.NEW(s);
            	 
            }
            if(line.startsWith("COURSE"))
            {
            	String name = splited[1]+splited[2];
            	Course c = new Course(name);
            	Course preReq = null;
            	
            	if(splited.length > 5)
            	{
            		name = splited[3]+splited[4];
            		Class temp = new Class(name);
            		c.getCourse().addPreReq(temp);
            	}
            	if(splited.length > 7)
            	{
            		name = splited[5]+splited[6];
            		Class temp = new Class(name);
            		preReq = new Course(temp);
            		
            		if(rS.getAllCourses().Contains(preReq))
            		{
            		c.getCourse().addPreReq(temp);
            		}else{
            			System.out.println("NOT FOUND");
            			continue;
            		}
            	}
            	if(splited.length > 9)
            	{
            		name = splited[7]+splited[8];
            		Class temp = new Class(name);
            		preReq = new Course(temp);
            		
            		if(rS.getAllCourses().Contains(preReq))
            		{
            		c.getCourse().addPreReq(temp);
            		}else{
            			System.out.println("NOT FOUND");
            			continue;
            		}
            	}
            	rS.COURSE(c);
            }
            if(line.startsWith("ADD"))
            {
            	int studNum = Integer.parseInt(splited[1]);
            	Student s = rS.getStudentByStud_Num(studNum);
            	String courseName = splited[2]+splited[3];
            	Course c = rS.getCourseByName(courseName);
            	rS.ADD(s, c);
            }
            if(line.startsWith("REMOVE"))
            {
            	int studNum = Integer.parseInt(splited[1]);
            	Student s = rS.getStudentByStud_Num(studNum);
            	String courseName = splited[2]+splited[3];
            	Course c = rS.getCourseByName(courseName);
            	if(rS.getAllStudents().Contains(s) && rS.getAllCourses().Contains(c))
            	{
            	c.removeFromWaitList(studNum);
            	}else{
            		System.out.println("NOT FOUND");
            	}
            }
            if(line.startsWith("TRANSCRIPT"))
            {
            	int studNum = Integer.parseInt(splited[1]);
            	Student s = rS.getStudentByStud_Num(studNum);
            	String className = splited[2]+splited[3];
            	Class c = new Class (className);
            	String status = splited[4];
            	rS.TRANSCRIPT(s, c, status);
            	
            }
            if(line.startsWith("CAPACITY"))
            {
            	String courseName = splited[1]+splited[2];
            	int capacity = Integer.parseInt(splited[3]);
            	Course c = rS.getCourseByName(courseName);
            	rS.CAPACITY(c, capacity);
            }
            if(line.startsWith("STATUS"))
            {
            	int studNum = Integer.parseInt(splited[1]);
            	Student s = rS.getStudentByStud_Num(studNum);
            	rS.STATUS(s);
            }
            if(line.startsWith("LIST"))
            {
            	String courseName = splited[1]+splited[2];
            	Course c = rS.getCourseByName(courseName);
            	rS.LIST(c);
            }
            if(line.startsWith("QUIT"))
            {
            	System.out.println("DONE");
            	System.exit(0);
            }
            
            line = reader.readLine();
        }
        //try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		/*Student bob = new Student(1234,"Bob");
		Class Comp2150 = new Class("Comp 2150");
		Class Comp1010 = new Class("Comp 1010");
		Class Comp1020 = new Class("Comp 1020");
		
		Course Comp_2150 = new Course(Comp2150);
		Comp_2150.setCapacity(1);
		Comp_2150.addStudent(bob);
		
		bob.addClass(Comp1010);
		bob.addClass(Comp1020);
		Comp2150.addPreReq(Comp1020);
		Comp_2150.passedCourse(bob);
		Comp_2150.failedCourse(bob);
		
		
		//bob.addClass(Comp2150);
		bob.printTranscript();
		Comp_2150.printStudentList();
		Comp_2150.printWaitList();*/
		
		
	}

}
