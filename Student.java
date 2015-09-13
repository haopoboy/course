import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	ArrayList<Course> require = new ArrayList<Course>();	//目前修過哪些必修
	ArrayList<Course> optional = new ArrayList<Course>();	//目前修過哪些系上選修
	int total_required_num;			//目前必修已經有幾學分了
	int total_own_optional_num;		//目前系上選修已經有幾學分了
	ArrayList<Integer> other_type_num;	//目前每一大類各幾學分了
	HashMap<String, HashMap<String, Integer>> other_type = new HashMap<String, HashMap<String,Integer>>();
	
	public static void main(String[] args) {
		Student s = new Student();
		
	}
	
	public Student()
	{
		//require
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(23);
		arr.add(24);
		arr.add(25);
		Course course = new Course("資訊系", "F7", "10", "11110", "程式設計（一）", "張燕光", 2, true, arr);
		require.add(course);
		arr = new ArrayList<Integer>();
		arr.add(47);
		arr.add(48);
		arr.add(49);
		course = new Course("資訊系", "F7", "5", "111001", "計算機概論", "藍崑展", 2, true, arr);
		require.add(course);
		
		//other
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("公民與歷史(憲政民主體制)	", 2);
		other_type.put("公民與歷史", map);
		map = new HashMap<String, Integer>();
		map.put("動物寄生蟲與生活	", 2);
		other_type.put("生命科學與健康", map);
		
	}
	public boolean alreadyPass(Course c)
	{
		String type = c.getType();
		if(type.equals("require"))
		{
			for(int i = 0; i < require.size(); ++i)
			{
				if(c.equals(require.get(i)))
				{
					return true;
				}
			}
			return false;
		}
		else if(type.equals("optional"))
		{
			for(int i = 0; i < optional.size(); ++i)
			{
				if(c.equals(optional.get(i)))
				{
					return true;
				}
			}
			return false;
		}
		else
		{
			HashMap<String, Integer> tmpList = other_type.get(type);
			if(tmpList.containsKey(c.getCourseName()))
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