import unittest;
from NumberSteps import Point;
from NumberSteps import NumberSteps;


class NumberStepsTest(unittest.TestCase):

    def setUp(self):
        self.number_steps = NumberSteps();


    # Step one tests
    def test_get_number_on_step_one_00(self):
        test_point = Point(0,0);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(0,number);

    def test_get_number_on_step_one_01(self):
        test_point = Point(1,1);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(1,number);

    def test_get_number_on_step_one_02(self):
        test_point = Point(2,2);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(4,number);

    def test_get_number_on_step_one_03(self):
        test_point = Point(3,3);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(5,number);

    def test_get_number_on_step_one_04(self):
        test_point = Point(4,4);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(8,number);

    def test_get_number_on_step_one_05(self):
        test_point = Point(5,5);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(9,number);

    def test_get_number_on_step_one_06(self):
        test_point = Point(6,6);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(12,number);



    # Step two tests
    def test_get_number_on_step_two_00(self):
        test_point = Point(2,0);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(2,number);

    def test_get_number_on_step_two_01(self):
        test_point = Point(3,1);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(3,number);

    def test_get_number_on_step_two_02(self):
        test_point = Point(4,2);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(6,number);

    def test_get_number_on_step_two_03(self):
        test_point = Point(5,3);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(7,number);

    def test_get_number_on_step_two_04(self):
        test_point = Point(6,4);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(10,number);

    def test_get_number_on_step_two_05(self):
        test_point = Point(7,5);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual(11,number);


    # Empty fields tests
    def test_get_not_number_on_empty_field_00(self):
        test_point = Point(0,4);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual("No Number",number);

    def test_get_not_number_on_empty_field_01(self):
        test_point = Point(3,2);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual("No Number",number);

    def test_get_not_number_on_empty_field_02(self):
        test_point = Point(1,5);
        number = self.number_steps.get_number_on(test_point);
        self.assertEqual("No Number",number);



if __name__ == "__main__":
    unittest.main();
