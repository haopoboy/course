import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Department {
	ArrayList<Course> require = new ArrayList<Course>();					//系上必修
	ArrayList<Course> optional = new ArrayList<Course>();					//系上選修
	int total_optional_num;
	HashMap<String, Integer> rule = new HashMap<String, Integer>();				//各要多少學分
	public Department(){}
	
	public Department(String s)
	{
		//test
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		
		require.add(new Course(s, "F7", "000", "F7-001", "C#", "Linda", 2, false, arr));
	}
	
	public Department(List<Course> require, List<Course> optional, List<String> other_type, List<Integer> other_need, int total_optional_num, int optional_num)
	{
		this.require = new ArrayList<Course>(require);
		this.optional = new ArrayList<Course>(optional);
		this.total_optional_num = total_optional_num;
		
		//initial rule
		rule.put("require", this.require.size());
		rule.put("optional", optional_num);		//optional_num 系上必修至少多少學分
		for(int i = 0; i < other_need.size(); ++i)
		{
			rule.put(other_type.get(i), other_need.get(i));
		}
	}
	
	public ArrayList<Course> getRequiredCourseList()
	{
		return require;
	}
	public ArrayList<Course> getOptionalCourseList()
	{
		return optional;
	}
	
	
	public void add_course(Course newCourse) 
	{
		if(newCourse.getIsRequired())
		{
			if(!require.contains(newCourse))
			{
				require.add(newCourse);
			}
		}
		else
		{
			if(!optional.contains(newCourse))
			{
				optional.add(newCourse);
			}
		}
	}
	
	public void delete_course(Course toDelete) 
	{
		if(toDelete.getIsRequired())
		{
			require.remove(toDelete);
		}
		else
		{
			optional.remove(toDelete);
		}
	}
	
	public ArrayList<String> notPassed(Student s)
	{
		ArrayList<String> result = new ArrayList<String>();
		if(s.getRequiredNum() < rule.get("require"))
		{
			result.add("require");
		}
		if(s.getOptionalNum() < rule.get("optional"))
		{
			result.add("optional");
		}
		ArrayList<Integer>	otherNum = s.getOtherNum();
		for(int i = 2; i < rule.size(); ++i)
		{
			if(otherNum.get(i) < rule.get(School.cate_names.get(i-2)))
			{
				result.add(School.cate_names.get(i-2));
			}
		}
		return result;	
	}
}
