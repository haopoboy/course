import java.util.ArrayList;

public class Course {
	String department = new String();
	String depart_no = new String();
	String id = new String();
	String course_num = new String();
	String course_name = new String();
	String teacher = new String();
	int credit;
	String type = new String();		//是系上必修還是選修還是基礎國文還是人文學等等
	ArrayList<Integer> date = new ArrayList<Integer>();
	public Course()
	{
		
	}
	
	public Course(String department, String depart_no, String id, String course_num, String course_name, String teacher, int credit, 
			ArrayList<Integer>date, String type)
	{
		this.department = department;
		this.depart_no = depart_no;
		this.id = id;
		this.course_num = course_num;
		this.course_name = course_name;
		this.teacher = teacher;
		this.credit = credit;
		this.date = date;
		this.type = type;
		
	}
	
	public String getType()
	{
		return type;
	}
	public void alterType(String newType)
	{
		type = newType;
	}
	
	public String getDep()
	{
		return department;
	}
	public void alterDep(String newName)
	{
		department = newName;
	}
	
	public void alterDepNo(String newDepNo)
	{
		depart_no = newDepNo;
	}
	public String getDepNo()
	{
		return depart_no;
	}
	
	public void alterId(String newId)
	{
		id = newId;
	}
	public String getId()
	{
		return id;
	}
	
	public void alterCourseNum(String newNum)
	{
		course_num = newNum;
	}
	public String getCourseNum()
	{
		return course_num;
	}
	
	public void alterCourseName(String newName)
	{
		course_name = newName;
	}
	public String getCourseName()
	{
		return course_name;
	}
	
	public String getTeacher()
	{
		return teacher;
	}
	public void alterTeacher(String newTeacher)
	{
		teacher = newTeacher;
	}
	
	public void alterCredit(int newCredit)
	{
		credit = newCredit;
	}
	public int getCredit()
	{
		return credit;
	}
	
	public void alterWeek(ArrayList<Integer> newDate)
	{
		date = newDate;
	}
	public ArrayList<Integer> getDate()
	{
		return date;
	}
	public boolean sameCourse(String toCompare)
	{
		if(toCompare.equals(course_name))
			return true;
		return false;
	}
	
	public boolean coverTime(ArrayList<Integer>okTime)
	{
		for(int i = 0; i < date.size(); ++i)
		{
			if(!okTime.contains(date.get(i)))
			{
				return false;
			}
		}
		return true;
	}

	
}
