import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {
	
	public static void main(String[] args) {
		String[] all_department = {"資訊系"};
		String[] all_other_cate = {"國際語言", "公民與歷史"};
		String departmentName = "資訊系", departmentName2 = "國際語言", departmentName3 = "公民與歷史";
		String departmentNo = "F7", departmentNo2 = "A1", departmentNo3 = "A3";
		String[] courseNum = {"11110","111001", "003", "004", "005"};
		String[] courseNum2 = {"111","001"};
		String[] courseNum3 = {"320"};
		String[] courseId = {"10","5","3","4","2"};
		String[] courseId2 = {"6","7"};
		String[] courseId3 = {"8"};
		String[] courseName = {"程式設計（一）","計算機概論","課程三","課程四","課程五"};
		String[] courseName2 = {"英語口說訓練","科技英文"};
		String[] courseName3 = {"民主憲政"};
		String[] courseTeacher = {"張燕光","藍崑展","老師3","老師4","老師5"};
		String[] courseTeacher2 = {"Cindy","Meggy"};
		String[] courseTeacher3 = {"公民老師"};
		String[] courseType = {"require", "require", "optional", "optional", "optional"};
		int[] courseCredit = {3,3,2,2,2};
		int[] courseCredit2 = {2,2};
		int[] courseCredit3 = {2};
		
		
		//	public Course(String department, String depart_no, String id, String course_num, String course_name, String teacher, int credit, 
		//ArrayList<Integer>date, String type)
		
		ArrayList<ArrayList<Course>> whole_school_courses = new ArrayList<ArrayList<Course>>();
		
		

		//public School(List<String>departmentName, HashMap<String, ArrayList<Course>>otherType, ArrayList<String> cate)
		ArrayList<String> all_department_list = new ArrayList<String>();	
		//記一所學校裡面所有的系所名稱
		for(int i = 0; i < all_department.length; ++i)
		{
			all_department_list.add(all_department[i]);
		}
		
		HashMap<String, ArrayList<Course>> all_other_courses = new HashMap<String, ArrayList<Course>>();
		//記錄所有非系所的課程 ex: 國際語言 - 國際語言下所有的課
		ArrayList<String> cate_names = new ArrayList<String>();
		for(int i = 0; i < all_other_cate.length; ++i)
		{
			cate_names.add(all_other_cate[i]);
			all_other_courses.put(all_other_cate[i], new ArrayList<Course>());
		}
		ArrayList<ArrayList<Integer>> date = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < courseName2.length; ++i)
		{
			date.add(new ArrayList<Integer>());
			date.get(i).add(7);
			date.get(i).add(8);
			all_other_courses.get(all_other_cate[0]).add(new Course(departmentName2, departmentNo2, courseId2[i], courseNum2[i], courseName2[i], courseTeacher2[i], courseCredit2[i], date.get(i), departmentName2));
		}
		date = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < courseName3.length; ++i)
		{
			date.add(new ArrayList<Integer>());
			date.get(i).add(9);
			date.get(i).add(10);
			all_other_courses.get(all_other_cate[1]).add(new Course(departmentName3, departmentNo3, courseId3[i], courseNum3[i], courseName3[i], courseTeacher3[i], courseCredit3[i], date.get(i), departmentName3));
		}
		
		//Department(List<Course> require, List<Course> optional, List<Integer> other_need, int total_optional_num, int optional_num)
		ArrayList<Course> requireCourses = new ArrayList<Course>();
		ArrayList<Course> optionalCourses = new ArrayList<Course>();
		date = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < courseName.length; ++i)
		{
			date.add(new ArrayList<Integer>());
			date.get(i).add(i*15+6);
			date.get(i).add(i*15+7);
		}
		for(int i = 0; i < courseName.length; ++i)
		{
			if(courseType[i].equals("require"))
			{
				requireCourses.add(new Course(departmentName, departmentNo, courseId[i], courseNum[i], courseName[i], courseTeacher[i], courseCredit[i], date.get(i), courseType[i]));
			}
			else
			{
				optionalCourses.add(new Course(departmentName, departmentNo, courseId[i], courseNum[i], courseName[i], courseTeacher[i], courseCredit[i], date.get(i), courseType[i]));
			}
		}
		
		School school = new School(all_department_list, all_other_courses, cate_names);
		ArrayList<Integer> atLeastNum = new ArrayList<Integer>();	//紀錄除了系上必選修外  其餘類別都各需多少學分
		for(int i = 0; i < all_other_cate.length; ++i)
		{
			atLeastNum.add(4);
		}		
		Department dep = new Department(requireCourses, optionalCourses, (List<Integer>)atLeastNum, 20, 3);
		Student student = new Student();

		ArrayList<Session>request_time = new ArrayList<Session>();
		Session ss = new Session(1, '6');
		request_time.add(ss);
		ss = new Session(1, '7');
		request_time.add(ss);
		ss = new Session(1, '8');
		request_time.add(ss);
		ArrayList<Course> result = school.request(student, dep, request_time, "資訊系");
		ArrayList<Course> tmpR = dep.getRequiredCourseList();
		ArrayList<Course> tmpO = dep.getOptionalCourseList();
		for(int i = 0; i < tmpR.size(); ++i)
		{
			System.out.println(tmpR.get(i).getCourseName());
			for(int j = 0; j < tmpR.get(i).getDate().size(); ++j)
				convertIntToSess(tmpR.get(i).getDate()).get(j).show();
		}
		for(int i = 0; i < tmpO.size(); ++i)
		{
			System.out.println(tmpO.get(i).getCourseName());
			for(int j = 0; j < tmpO.get(i).getDate().size(); ++j)
				convertIntToSess(tmpO.get(i).getDate()).get(j).show();
		}
		for(int i = 0; i < all_other_cate.length; ++i)
		{
			ArrayList<Course> tmp = all_other_courses.get(all_other_cate[i]);
			for(int k = 0; k < tmp.size(); ++k)
			{
				System.out.println(tmp.get(k).getCourseName());
				for(int j = 0; j < tmp.get(k).getDate().size(); ++j)
					convertIntToSess(tmp.get(k).getDate()).get(j).show();
			}
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
