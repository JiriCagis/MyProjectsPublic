
class Courses:
	
        def __init__(self, count_students, count_courses):
                self.count_students = count_students;
                self.count_courses = count_courses;
                self.student_to_courses_map = {};
                self.course_to_students_map = {};
                
                self.fixed_committee = [];
                self.open_courses = set();
                self.assign_students = set();

        def assign_student_to_course(self, student_id, course_id):
                #if student_id in self.student_to_courses_map:
                #        set_of_courses = self.student_to_courses_map[student_id];
                #        set_of_courses.add(course_id);
                #else:
                #        set_of_courses = set();
                #        set_of_courses.add(course_id);
                #        self.student_to_courses_map[student_id] = set_of_courses;

                if course_id in self.course_to_students_map:
                        set_of_students = self.course_to_students_map[course_id];
                        set_of_students.add(student_id);
                else:
                        set_of_students = set();
                        set_of_students.add(student_id);
                        self.course_to_students_map[course_id] = set_of_students;

                self.open_courses.add(course_id);
                self.assign_students.add(student_id);
                

        def exist_combination_of_students_for_build_committee(self):
                if self.count_students < self.count_courses \
                   or not self.__are_open_all_courses() \
                   or not self.__is_assigned_more_or_equal_studens_as_courses():
                        return "NO";                

                self.fixed_committee = self.__compute_fixed_committee_of_courses_with_one_member();
                if self.__count_students_in_pernament_committee() == self.count_courses:
                        return "YES"

                # init blank committee before recursion decision
                committee = [];
                for i in range(0,self.count_courses+1):
                        committee.append(0);
                result = self.__decide_exist_committee_by_recursion(committee,1);
                
                if result == True:
                        return "YES";
                else:
                        return "NO";

        def __count_students_in_pernament_committee(self):
                count =0;
                for student_id in self.fixed_committee:
                        if student_id > 0:
                                count+=1;
                return count;
        
        def __are_open_all_courses(self):
                if self.count_courses == len(self.open_courses):
                        return True;
                else:
                        return False;

        def __is_assigned_more_or_equal_studens_as_courses(self):
                if len(self.assign_students) >= self.count_courses:
                        return True;
                else:
                        return False;

        def __compute_fixed_committee_of_courses_with_one_member(self):
                fixed_committee = [];
                for i in range(0,self.count_courses+1):
                        fixed_committee.append(0);

                exist_course_with_one_member = True;
                while exist_course_with_one_member:
                        exist_course_with_one_member = False;
                        for course_id in self.course_to_students_map:
                                students = self.course_to_students_map[course_id];
                                possible_students = students.difference(fixed_committee);
                                if len(possible_students) == 1:
                                        fixed_committee[course_id] = list(possible_students)[0];
                                        exist_course_with_one_member = True;

                return fixed_committee;
                                
                        
        def __decide_exist_committee_by_recursion(self,committee, course_id):

                if course_id > self.count_courses:
                        return True;

                if course_id not in self.course_to_students_map.keys():
                        return False;
                
                students = self.course_to_students_map[course_id];
                possible_students = students.difference(committee);
                possible_students = possible_students.difference(self.fixed_committee);

                if len(possible_students) == 0:
                        return False;

                for student_id in possible_students:
                        committee[course_id] = student_id;
                        partial_result = self.__decide_exist_committee_by_recursion(committee,course_id+1);
                        if partial_result == True:
                                return True;
                                


                committee[course_id] = 0;
                return False;
                



if __name__ == "__main__":
        count_repeats = int(input());
        repeat = 0;
        courses = None;
        while repeat < count_repeats:
                base_attributes = list(map(int,input().split()));
                count_courses = base_attributes[0];
                count_students = base_attributes[1];

                courses = Courses(count_students, count_courses);
                for course_id in range(1,count_courses+1):
                        students = list(map(int,input().split()));
                        students.pop();
                        for student_id in students:
                                courses.assign_student_to_course(student_id, course_id);
                repeat+=1;
                print (courses.exist_combination_of_students_for_build_committee());
                
