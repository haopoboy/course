import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class School {
	ArrayList<String> department_name = new ArrayList<String>();	
	public static ArrayList<String> cate_names = new ArrayList<String>();
	public static HashMap<String, ArrayList<Course>> other_type = new HashMap<String, ArrayList<Course>>();
	
	public School() {
		department_name = null;
		other_type = null;
		
	}
	
	public School(List<String>departmentName, HashMap<String, ArrayList<Course>>otherType)
	{
		department_name = new ArrayList<String>(departmentName);
		other_type = new HashMap<String, ArrayList<Course>>(otherType);
	}
	public ArrayList<Course> request(ArrayList<Integer>request_time, String department)
	{
		ArrayList<Course> result = new ArrayList<Course>();
		Student student = new Student();
		Department dep = new Department("資訊系");
		ArrayList<String>notPassYet = dep.notPassed(student);
		if(notPassYet.contains("require"))
		{
			ArrayList<Course> requiredCourseList = dep.getRequiredCourseList();
			for(int i = 0; i < requiredCourseList.size(); ++i)
			{
				if(!student.alreadyPass(requiredCourseList.get(i)))
				{
					if(requiredCourseList.get(i).coverTime(request_time))
						result.add(requiredCourseList.get(i));
				}
			}
		}
		if(notPassYet.contains("optional"))
		{
			ArrayList<Course> optionalCourseList = dep.getOptionalCourseList();
			for(int i = 0; i < optionalCourseList.size(); ++i)
			{
				if(!student.alreadyPass(optionalCourseList.get(i)))
				{
					if(optionalCourseList.get(i).coverTime(request_time))
						result.add(optionalCourseList.get(i));
				}
			}
		}
		for(int i = 2; i < notPassYet.size(); ++i)
		{
			ArrayList<Course> courseList = other_type.get(notPassYet.get(i));
			for(int j = 0; j < courseList.size(); ++j)
			{
				if(!student.alreadyPass(courseList.get(j)))
				{
					if(courseList.get(j).coverTime(request_time))
						result.add(courseList.get(j));
				}
			}
		}
		return result;
		
		
		
	}
	
	
	public void add_type(String type_name, ArrayList<Course>course_list)
	{
		other_type.put(type_name, course_list);
	}
	
	public void delete_type(String type_name)
	{
		other_type.remove(type_name);
	}
	
	public void add_other_course(Course newCourse)
	{
		String type = newCourse.getType();
		other_type.get(type).add(newCourse);
	}
	
	public void delete_course(Course toRemove)
	{
		String type = toRemove.getType();
		ArrayList<Course> goalList = other_type.get(type);
		goalList.remove(goalList.indexOf(toRemove));
	}
	
}
