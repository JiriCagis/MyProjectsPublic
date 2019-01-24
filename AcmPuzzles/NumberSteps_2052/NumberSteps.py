
class Point:

    def __init__(self, x, y):
        self.x = x;
        self.y = y;

    def make_instance_from_raw_string(rawString):
        x = int(rawString.split(" ")[0]);
        y = int(rawString.split(" ")[1]);
        return Point(x,y);

    def __str__(self):
        return "Point has [x=" + self.x + ",y="+ self.y + "]";



class NumberSteps:

    def get_number_on(self,point):
        if point.x == point.y and self.__is_number_even(point.x):
            return point.x + point.y;
        if point.x == point.y and not self.__is_number_even(point.x):
            return point.x + point.y - 1;
        if point.x == (point.y + 2) and self.__is_number_even(point.x):
            return point.x + point.y
        if point.x == (point.y + 2) and not self.__is_number_even(point.x):
            return point.x + point.y - 1
        return "No Number";

    def __is_number_even(self,number):
        return number%2==0;



if __name__ == '__main__':
    numberSteps = NumberSteps();
    countRepeats = int(input());
    repeat = 0;
    while repeat < countRepeats:
        rawString = input();
        point = Point.make_instance_from_raw_string(rawString);
        print(numberSteps.get_number_on(point));
        repeat+=1;
