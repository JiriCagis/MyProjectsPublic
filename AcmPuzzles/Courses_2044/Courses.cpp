#include<iostream>
#include<vector>
#include<map>
#include<algorithm>
using namespace std;

// Global variables
map<int,vector<int> > courseToStudentsMap; 




void printInputs(int countCourses, int countStudents, bool assignStudents[], map<int,vector<int> > courseToStudentsMap)
{
	cout << "Count courses: " << countCourses << "\n";
	cout << "Count students: " << countStudents << "\n";
	cout << "\n";
	
	cout << "Assigned students:\n";
	for (int i=1;i<=countStudents;i++)
	{
		cout << "Student id: " << i << " is assigned to any course: " << (assignStudents[i]==1?"True":"False") << "\n";
	}
	
	
	cout << "\nCourses:\n";
	for(map<int,vector<int> >::iterator it=courseToStudentsMap.begin(); it != courseToStudentsMap.end(); it++)
	{
		cout << "CourseId: " << it->first << "\n";
		cout << "*************************************\n";

		vector<int> students = it->second;
		for(vector<int>::iterator studentIt=students.begin(); studentIt != students.end(); studentIt++)
		{
			cout << "StudentId: " << *studentIt << "\n";
		}
		cout << "\n";
	}
}

bool decideExistCommitteeByRecursion(vector<int> committee, int courseId)
{
	/*	
	cout << "Committee: \n";
	for(vector<int>::iterator it = committee.begin(); it != committee.end();it++)
	{
		cout << *it << ", ";
	}
	cout << "\n";
	*/

	if (courseId == committee.size())
	{
		//cout << "CourseId:" << courseId << "\n";
		return true;
	}

	
	vector<int> students = courseToStudentsMap[courseId];
		
	/*
	cout << "Students: \n";
	for(const int &studentId : students)
	{	
		cout << studentId << ", ";
	}
	cout << "\n";
	*/
	vector<int> possibleStudents;

	vector<int> sortedCommittee(committee.begin(),committee.end());
	sort(sortedCommittee.begin(),sortedCommittee.end());

	set_difference(students.begin(),students.end(),sortedCommittee.begin(),sortedCommittee.end(),inserter(possibleStudents,possibleStudents.begin()));

	
	//cout << "Possible students:\n";
	//for(const int &studentId : possibleStudents)
	//{
	//	cout << studentId << ", ";
	//}
	//cout << "\n";
	

	if (possibleStudents.size() == 0)
	{
		return false;
	}	
	
	for(vector<int>::iterator it = possibleStudents.begin();it != possibleStudents.end();it++)
	{
		committee[courseId] = *it;
		bool partialResult = decideExistCommitteeByRecursion(committee,courseId+1);
		if (partialResult == true)
		{
			return true;
		}
	}

	
	committee[courseId] = 0;
	return false;
}

bool existCombinationOfStudentsForBuildCommittee(int countCourses, int countStudents, vector<bool> assignStudents, map<int,vector<int> > courseToStudentsMap)
{
	if (countStudents < countCourses)
	{
		return false;
	}
		
	int countAssignStudents = count(assignStudents.begin(),assignStudents.end(),true);
	if(countAssignStudents < countCourses)
	{	
		return false;
	}

	vector<int> committee(countCourses,0);
	return decideExistCommitteeByRecursion(committee,0);
}


int main()
{
	int countRepeats = 0;
	int repeat = 0;
	
	cin >> countRepeats;
	while(repeat++ < countRepeats)
	{

		// Load data from input
		int countStudents = 0;
		int countCourses = 0;
		
		cin >> countCourses;
		cin >> countStudents;
		
		vector<bool> assignStudents(countStudents,false);
		
		for(int courseId =0;courseId < countCourses;courseId++)
		{
			int countStudentsInCourse = 0;
			cin >> countStudentsInCourse;
			vector<int> students(countStudentsInCourse);
			for(int i=0;i<countStudentsInCourse;i++){
				int studentId = 0;
				cin >> studentId;
				students[i]  = studentId;
				assignStudents[studentId-1] = true;
			}
			
			courseToStudentsMap[courseId]=students;
		}
			
		// Compute if exist committee
		if(existCombinationOfStudentsForBuildCommittee(countCourses,countStudents,assignStudents,courseToStudentsMap))
		{
			cout << "YES" << "\n";
		}
		else
		{	
			cout << "NO" << "\n";
		}
		//Clear data
		courseToStudentsMap.clear();
		assignStudents.clear();	
	}
	
	return 0;
}
