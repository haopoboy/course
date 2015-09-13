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
	
	public School(List<String>departmentName, HashMap<String, ArrayList<Course>>otherType, ArrayList<String> cate)
	{
		department_name = new ArrayList<String>(departmentName);
		other_type = new HashMap<String, ArrayList<Course>>(otherType);
		cate_names = cate;
	}
	
	private ArrayList<Integer> convertSessToInt(ArrayList<Session>input)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < input.size(); ++i)
		{
			int week, sess;
			week = input.get(i).getWeek();
			switch (input.get(i).getSession()) {
			case '1':
				sess = 1;
				break;
			case '2':
				sess = 2;
				break;
			case '3':
				sess = 3;
				break;
			case '4':
				sess = 4;
				break;
			case 'N':
				sess = 5;
				break;
			case '5':
				sess = 6;
				break;
			case '6':
				sess = 7;
				break;
			case '7':
				sess = 8;
				break;
			case '8':
				sess = 9;
				break;
			case '9':
				sess = 10;
				break;
			case 'A':
				sess = 11;
				break;
			case 'B':
				sess = 12;
				break;
			case 'C':
				sess = 13;
				break;
			case 'D':
				sess = 14;
				break;
			case 'E':
				sess = 15;
				break;

			default:
				sess = -10000;
				break;
			}
			result.add(sess+(week-1)*15);
		}
		return result;
	}
	
	private ArrayList<Session> convertIntToSess(ArrayList<Integer>input)
	{
		ArrayList<Session> result = new ArrayList<Session>();
		for(int i = 0; i < input.size(); ++i)
		{
			Integer intt = input.get(i);
			int week = (intt/15)+1;
			char sess;
			switch (intt%15) {
			case 0:
				sess = 'E';
				break;
			case 1:
				sess = '1';
				break;
			case 2:
				sess = '2';
				break;
			case 3:
				sess = '3';
				break;
			case 4:
				sess = '4';
				break;
			case 5:
				sess = 'N';
				break;
			case 6:
				sess = '5';
				break;
			case 7:
				sess = '6';
				break;
			case 8:
				sess = '7';
				break;
			case 9:
				sess = '8';
				break;
			case 10:
				sess = '9';
				break;
			case 11:
				sess = 'A';
				break;
			case 12:
				sess = 'B';
				break;
			case 13:
				sess = 'C';
				break;
			case 14:
				sess = 'D';
				break;

			default:
				sess = 'Z';
				break;
			}
			Session tmpS = new Session(week, sess);
			result.add(tmpS);
		}
		return result;
	}
	
	
	public ArrayList<Course> request(Student student, Department dep, ArrayList<Session>ori_request_time, String department)
	{
		ArrayList<Course> result = new ArrayList<Course>();
		ArrayList<String>notPassYet = dep.notPassed(student);
		ArrayList<Integer>request_time = convertSessToInt(ori_request_time);
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
		for(int i = 0; i < notPassYet.size(); ++i)
		{
			if(notPassYet.get(i).equals("require") || notPassYet.get(i).equals("optional"))
				continue;
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
