import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	ArrayList<Course> require = new ArrayList<Course>();	//目前修過哪些必修
	ArrayList<Course> optional = new ArrayList<Course>();	//目前修過哪些系上選修
	int total_required_num;			//目前必修已經有幾學分了
	int total_own_optional_num;		//目前系上選修已經有幾學分了
	ArrayList<Integer> other_type_num = new ArrayList<Integer>();	//目前每一大類各幾學分了
	HashMap<String, ArrayList<Course>> other_type = new HashMap<String, ArrayList<Course>>();
	
	public Student()
	{
		//require
		total_own_optional_num = 0;
		total_required_num = 2;
		other_type_num.add(0);
		other_type_num.add(0);
		other_type.put("國際語言", new ArrayList<Course>());
		other_type.put("公民與歷史", new ArrayList<Course>());
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		Course course = new Course("資訊系", "F7", "10", "11110", "程式設計（一）", "張燕光", 2, arr, "require");
		require.add(course);
		arr = new ArrayList<Integer>();
		arr.add(4);
		arr.add(5);
		arr.add(6);
		course = new Course("資訊系", "F7", "5", "111001", "計算機概論", "藍崑展", 2, arr, "require");
		require.add(course);
	}
	public boolean alreadyPass(Course c)
	{
		String type = c.getType();
		if(type.equals("require"))
		{
			if(require.contains(c))
				return true;
			return false;
		}
		else if(type.equals("optional"))
		{
			if(optional.contains(c))
				return true;
			return false;
		}
		else
		{
			ArrayList<Course> tmpList = other_type.get(type);
			
			if(tmpList.contains(c))
				return true;
			return false;
		}
	}
	
	public int getRequiredNum()
	{
		return total_required_num;
	}
	
	public int getOptionalNum()
	{
		return total_own_optional_num;
	}
	
	public ArrayList<Course> getRequiredList()
	{
		return require;
	}
	
	public ArrayList<Course> getOptionalList()
	{
		return optional;
	}
	
	public ArrayList<Integer> getOtherNum()
	{
		return other_type_num;
	}
}