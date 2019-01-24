//
//  main.cpp
//  15_tka
//
//  Created by Cagis on 27.07.12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <ctime>
#include <string>
using namespace std;

//promenne

int hra [7][7];

int i,j,ii,jj,z,tah,vyhra;



//definice funkci
void Zobrazeni();
void Zamychani();
void Ovladani();
void Posuv();

int main (int argc, const char * argv[])
{
    for (i=1;i<=6;i++) //zobrazeni zakazaneho pasu
        for (j=1;j<=6;j++)
            hra[i][j]=99;
    
    
    vyhra=tah = 0;
    Zamychani();
    
    do 
    {
        Zobrazeni();
        Ovladani();
    } 
    while (vyhra == 0);
    
    Zobrazeni();
    return 0;
}

void Zobrazeni()
{
    cout << "------------ 15-tka ------------" << "\n\n\n";
    
    for (i=2;i<=5;i++) //zobrazeni herni plochy
    {for (j=2;j<=5;j++)
        if (hra[i][j]<=9)
            cout << "       " << hra[i][j] << "      ";
        else
            cout << "       " << hra[i][j] << "    ";
            cout << "\n";
    }
    cout << "\n\n Pocet tahu: " << tah; 
    if (vyhra == 0) cout << "\n Zvolte cislo s kterym chcete hybnout: \n";
    if (vyhra == 1) cout << "\n Blahopreji, uspesne si slozil. \n";
}
void Ovladani()
{
    int znak;
    //zadate cislo s kterym chcete posunout
    cin >> znak; 
    
    //vyhledani cisla, a ziskani jeho souradnic
    for (i=2;i<=5;i++)
    {for (j=2;j<=5;j++)
        if (hra[i][j] == znak) //stop vnitri cyklus
          break;
        if (hra[i][j] == znak)//stop vnejsi cyklus
            break;
    }
    
    //pokusi se s vybranym cisle posunout
    ii=1;jj=0;      Posuv();
    ii=-1;jj=0;     Posuv();
    ii=0;jj=1;     Posuv();
    ii=0;jj=-1;     Posuv();
    
    //kontrola
    vyhra =z =1;
    for (i=2;i<=5;i++)
        for (j=2;j<=5;j++)        
        {   
            if (hra[i][j] != z)
                vyhra =0;
            if (z<15) z++; else z=0;
        }
     
}
void Posuv()
{
    if (hra[i+ii][j+jj]==0)
    {
        hra[i+ii][j+jj] = hra[i][j];
        hra[i][j]=0;
        tah++;
    }
    

    
}
void Zamychani()
{
    srand(time(NULL));
    int cislo;
    int pocet = 16;
    int vyber[] = {0,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}; //nutno pouzit 2x nula, protoze vyber zaciname od prvku 1
    
    for (int i=2;i<=5;i++)
        for (int j=2;j<=5;j++)
        {
            cislo = (rand()%pocet)+1; 
            hra [i][j] = vyber [cislo];
            vyber[cislo]=vyber[pocet];
            pocet--;
        }
    
    /*
    //test slozi puzzle
    z=1;
    for (int i=2;i<=5;i++)
        for (int j=2;j<=5;j++)
        {hra[i][j]= z++;
            if (z==17) hra[i][j]=0;
        }*/
     
}

