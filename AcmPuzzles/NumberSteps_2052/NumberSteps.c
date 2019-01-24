#include<iostream>
using namespace std;

int main()
{
	int countRepeats = 0;
	int repeat = 0;

	cin >> countRepeats;
	while(repeat++ < countRepeats)
	{
		int x = 0;
		int y = 0;
	
		cin >> x;
		cin >> y;

		if(x==y && x%2==0)
		{
			cout << x+y << "\n";
		}
		else if(x==y && x%2!=0)
		{
			cout << x+y-1 << "\n";
		}
		else if(x==y+2 && x%2==0)
		{
			cout << x+y << "\n";
		}
		else if(x==y+2 && x%2!=0)
		{
			cout << x+y-1 << "\n";
		}
		else 
		{
			cout << "No Number" << "\n";
		}

	}
	
	return 0;
}
