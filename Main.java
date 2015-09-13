import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {
	
	public static void main(String[] args) {
		String[] all_department = {"資訊系"};
		String[] all_other_cate = {"國際語言", "公民與歷史"};
		String departmentName = "資訊系";
		String departmentNo = "F7";
		String[] courseNum = {"11110","111001", "003", "004", "005"};
		String[] courseId = {"10","5","3","4","2"};
		String[] courseName = {"程式設計（一）","計算機概論","課程三","課程四","課程五"};
		String[] courseTeacher = {"張燕光","藍崑展","老師3","老師4","老師5"};
		String[] courseType = {"require", "require", "optional", "optional", "optional"};
		int[] courseCredit = {3,3,2,2,2};
		ArrayList<ArrayList<Session>> courseDateSession = new ArrayList<ArrayList<Session>>();
		
		for(int i = 0; i < courseName.length; ++i)
		{
			Session s = new Session(i+1, '1');
			courseDateSession.add(new ArrayList<Session>());
			courseDateSession.get(i).add(s);
			s = new Session(i+1, '2');
			courseDateSession.get(i).add(s);
		}
		ArrayList<ArrayList<Integer>> courseDate = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < courseName.length; ++i)
			courseDate.add(convertSessToInt(courseDateSession.get(i)));
		ArrayList<String> all_department_list = new ArrayList<String>();
		for(int i = 0; i < all_department.length; ++i)	
			all_department_list.add(all_department[i]);
		ArrayList<String> other = new ArrayList<String>();
		for(int i = 0; i < all_other_cate.length; ++i)	
			other.add(all_other_cate[i]);
		
		ArrayList<ArrayList<Course>> allCourses = new ArrayList<ArrayList<Course>>();
		allCourses.add(new ArrayList<Course>());
		int index = 0;
		for(int i = 0; i < courseName.length; ++i)
		{
			allCourses.get(index).add(new Course(departmentName, departmentNo, courseId[i], courseNum[i], courseName[i], courseTeacher[i], courseCredit[i], courseDate.get(i), courseType[i]));
		}
		allCourses.add(new ArrayList<Course>());
		
//		allCourses.get(1).add(new Course(department, depart_no, id, course_num, course_name, teacher, credit, date, type))
		
		
		String[] department = {"資訊系","資訊系","資訊系","資訊系","資訊系","國際語言","國際語言","公民與歷史"};
		String[] depart_no = {"F7","F7","F7","F7","F7","A1","A1","A9"}; 
		String[] course_num = {"11110","111001", "003", "004", "005","006","007","008"};
		String[] id = {"10","5","3","4","2","6","7","8"};
		String[] course_name = {"程式設計（一）","計算機概論","課程三","課程四","課程五","英文","口說英文","公民與歷史(憲政民主體制)"};
		String[] teacher = {"張燕光","藍崑展","老師3","老師4","老師5","Cindy","Mandy","公民老師"}; 
		String[] types = {"require", "require", "optional", "optional", "optional","國際語言", "國際語言", "公民與歷史"};
		int[] credit = {3,3,2,2,2,2,2,2}; 
	
		ArrayList<ArrayList<Integer>> date = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 8; ++i)
		{
			date.add(new ArrayList<Integer>());
			date.get(i).add(1+i*2);
			date.get(i).add(2+i*2);
		}
		
		ArrayList<Course> all = new ArrayList<Course>();
		for(int i = 0; i < 8; ++i)
		{
			all.add(new Course(department[i], depart_no[i], id[i], course_num[i], course_name[i], teacher[i], credit[i], date.get(i), types[i]));
		}
		
		ArrayList<Course> eng_course = new ArrayList<Course>();
		eng_course.add(all.get(5));
		eng_course.add(all.get(6));
		ArrayList<Course> civil_course = new ArrayList<Course>();
		civil_course.add(all.get(7));
		HashMap<String, ArrayList<Course>> all_other_course = new HashMap<String, ArrayList<Course>>();
		for(int i = 0; i < all_other_cate.length; ++i)
		{
			all_other_course.put(all_other_cate[i], eng_course);
		}
		all_other_course.put("國際語言", eng_course);
		all_other_course.put("公民與歷史", civil_course);
		ArrayList<Integer> atLeastNum = new ArrayList<Integer>();
		atLeastNum.add(4);
		atLeastNum.add(2);
		
		School school = new School(all_department_list, all_other_course, other);
		Department dep = new Department(all.subList(0, 2), all.subList(2, 5), (List<Integer>)atLeastNum, 5, 3);
		Student student = new Student();

		ArrayList<Session>request_time = new ArrayList<Session>();
		Session ss = new Session(1, '6');
		request_time.add(ss);
		ss = new Session(1, '7');
		request_time.add(ss);
		ss = new Session(1, '8');
		request_time.add(ss);
		ArrayList<Course> result = school.request(student, dep, request_time, "資訊系");
		for(int i = 0; i < all.size(); ++i)
		{
			System.out.println(all.get(i).getCourseName());
			for(int j = 0; j < all.get(i).getDate().size(); ++j)
				convertIntToSess(all.get(i).getDate()).get(j).show();
		}
		System.out.println();
		for(int i = 0; i < result.size(); ++i)
		{
			System.out.println(result.get(i).getCourseName());
			for(int j = 0; j < result.get(i).getDate().size(); ++j)
				convertIntToSess(result.get(i).getDate()).get(j).show();
		}
	}

	
	public static ArrayList<Session> convertIntToSess(ArrayList<Integer>input)
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
	
	public static ArrayList<Integer> convertSessToInt(ArrayList<Session>input)
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
}
