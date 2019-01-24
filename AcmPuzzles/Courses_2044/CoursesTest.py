import unittest;
from Courses import Courses;


class CoursesTest(unittest.TestCase):

        # Test verify existing combination for make committee
        def test_combination_build_committee_01(self):
                courses = Courses(count_students=1, count_courses=1);
                courses.assign_student_to_course(student_id=1, course_id=1);
                result = courses.exist_combination_of_students_for_build_committee();
                self.assertEqual("YES",result);

        def test_combination_build_committee_02(self):
                courses = Courses(count_students=3, count_courses=3);

                courses.assign_student_to_course(student_id=1, course_id=1);
                courses.assign_student_to_course(student_id=2, course_id=1);
                courses.assign_student_to_course(student_id=3, course_id=1);

                courses.assign_student_to_course(student_id=1, course_id=2);
                courses.assign_student_to_course(student_id=2, course_id=2);

                courses.assign_student_to_course(student_id=1, course_id=3);

                result = courses.exist_combination_of_students_for_build_committee();
                self.assertEqual("YES",result);                

        
        # Test verify does not existing combination for make committee
        def test_combination_not_build_committee_01(self):
                courses = Courses(count_students=1, count_courses=1);
                result = courses.exist_combination_of_students_for_build_committee();
                self.assertEqual("NO",result);

        def test_combination_not_build_committee_02(self):
                courses = Courses(count_students=2, count_courses=15);
                courses.assign_student_to_course(student_id=1, course_id=1);
                result = courses.exist_combination_of_students_for_build_committee();
                self.assertEqual("NO",result);

        def test_combination_not_build_committee_03(self):
                courses = Courses(count_students=3, count_courses=3);

                courses.assign_student_to_course(student_id=1, course_id=1);
                courses.assign_student_to_course(student_id=3, course_id=1);

                courses.assign_student_to_course(student_id=1, course_id=2);
                courses.assign_student_to_course(student_id=3, course_id=2);

                courses.assign_student_to_course(student_id=1, course_id=3);

                result = courses.exist_combination_of_students_for_build_committee();
                self.assertEqual("NO",result);   
		


if __name__ == "__main__":
        unittest.main();
