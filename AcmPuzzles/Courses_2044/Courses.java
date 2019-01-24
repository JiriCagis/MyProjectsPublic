import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;


public class Courses {
	
	public static Map<Integer,Set<Integer>> courseToStudentsMap;

	public static boolean existCombinationOfStudentsForBuildCommittee(	int countCourses, 
																	  	int countStudents, 
																		boolean[] assignStudents, 
																		Map<Integer,Set<Integer>> courseToStudentsMap){
		if (countStudents < countCourses){
			return false;
		}

		long countAssignStudents = 0;
		for(boolean value:assignStudents){
			if(value==true){
				countAssignStudents++;
			}
		}
		if (countAssignStudents < countCourses){
			return false;
		}

		int[] committee = new int[countCourses];
		return decideExistCommitteeByRecursion(committee,0);
	}

	public static boolean decideExistCommitteeByRecursion(int[] committee,int courseId){
		if (courseId == committee.length){
			//System.out.println(Arrays.toString(committee));
			return true;
		}
		Set<Integer> students = courseToStudentsMap.get(courseId);
		Set<Integer> possibleStudents = new HashSet<>(students);
		for(int studentId:committee){
			possibleStudents.remove(studentId);
		}
		if (possibleStudents.size() == 0){
			return false;
		}

		for(Integer studentId:possibleStudents){
			committee[courseId] = studentId;
			boolean partialResult = decideExistCommitteeByRecursion(committee,courseId+1);
			if(partialResult == true){
				return true;
			}
		}
		
		committee[courseId] = 0;
		return false;	
	}
	
	public static void main(String args[]) throws Exception{
		BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
		int countRepeats = Integer.parseInt(reader.readLine());
		int repeat = 0;

		while(repeat++ < countRepeats){
			courseToStudentsMap = new HashMap<>();
			String rawInput = reader.readLine();
			int countCourses = Integer.parseInt(rawInput.split(" ")[0]);
			int countStudents = Integer.parseInt(rawInput.split(" ")[1]);

			boolean assignStudents[]  = new boolean[countStudents];
			for(int courseId = 0; courseId < countCourses; courseId++){
				String  rawStudents[]  = reader.readLine().split(" ");
				int countStudentsInCourse = Integer.parseInt(rawStudents[0]);
				Set<Integer> students = new HashSet<>();
				for(int i=1;i<rawStudents.length;i++){
					int studentId = Integer.parseInt(rawStudents[i]);
					students.add(studentId);
					assignStudents[studentId-1] = true;
				}
				courseToStudentsMap.put(courseId,students);
			}

			if(existCombinationOfStudentsForBuildCommittee(countCourses,countStudents,assignStudents,courseToStudentsMap)){
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}	

		reader.close();
	}
}
