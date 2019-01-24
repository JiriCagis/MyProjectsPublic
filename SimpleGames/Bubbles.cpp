//
//  main.cpp
//  Bubless
//
//  Created by Cagis on 20.08.12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#include <iostream>
using namespace std;
//definice promennych
int hra [11][11];
int i,j,ii,jj,z,pom,konec,vyhra,skore;

//definice funkci
void Zobrazeni();
void Ovladani();
void Vyber();
void Oznac();
void Posuv();

int main (int argc, const char * argv[])
{
    srand(time(NULL));
    vyhra=skore=0;
    //generator nahodnych cisel
    for (i=1;i<=10;i++)
        for (j=1;j<=10;j++)
            hra[i][j] = (rand()%4+1);
            //hra[i][j] = 1;
    
    Zobrazeni();
    
    do {
        Ovladani();
        Vyber();
        Posuv();
        Zobrazeni();
    } while (vyhra != 1);
    
    return 0;
}

void Zobrazeni()
{
    cout << "---------- Bubless --------- \n\n       ";
    
    for (i=1;i<=10;i++)
        cout << " "<< i << " ";
    cout << "\n       ___________________________\n  1  |";
    
    for (j=1;j<=10;j++)
    {for (i=1;i<=10;i++)
    {
        if (hra[i][j] == 0) cout << " - ";
        if (hra[i][j] == 1) cout << " 6 ";
        if (hra[i][j] == 2) cout << " 3 ";
        if (hra[i][j] == 3) cout << " 4 ";
        if (hra[i][j] == 4) cout << " 1 ";
        
    }

         if (j<10) 
             if (j==9)
                 cout << "\n " << j+1 << " |";
            else
                cout << "\n  " << j+1 << "  |";
    }
        
    if (vyhra == 0) cout << "\n\n Zvolte souradnice dalsiho tahu: \n";
    if (vyhra == 1) cout << "\n\n Blahopreji, vyhral si !!! \n";
}

void Ovladani()
{
    cin >> i;
    cin >> j;
}

void Vyber()
{
    pom = hra [i][j]; //nastavi jako pom vybranou souradnici
    
    //pokud vedle oznaceneho se nachazi stejne cislo, nastavi oznacene do 0
    if (hra[i+1][j]==pom || hra [i-1][j]==pom || hra [i][j+1]==pom || hra [i][j+1]==pom)
        hra[i][j]=0;
    
    //Oznaci dalsi stejne cisla jako -2, pro dalsi zpracovani
    Oznac();
    
    //zpracuje vsechny oznacene, a pokusi se najit dalsi stejne v blizkosti oznacenych
    konec=0;
    do {
        for (i=1;i<=10;i++) //pokusi se najit v poli cisla -2
        {for (j=1;j<=10;j++)
            if (hra[i][j]==-2) break;
            if (hra[i][j]==-2) break;
        }
        
        if (hra[i][j] == -2) //pokud najde cislo -2 premeni na 0 a pokusi se najit dalsi
        {
            hra [i][j] =0;
            Oznac();
        }
        else
            konec =1; //pokud se nenaslo zadne cislo -2, ukonci cyklus 
    } while (konec !=1);
}
void Oznac()
{
    for (z=i+1;z<=10;z++) // vpravo
       if (hra[z][j] == pom) 
            hra [z][j]=-2;
        else break;
    
    for (z=i-1;z>=1;z--) // vlevo
        if (hra[z][j]== pom) 
            hra [z][j]=-2;
        else break;
    
    for (z=j+1;z<=10;z++) // dolu
        if (hra[i][z]== pom) 
            hra [i][z]=-2;
        else break;
    
    for (z=j-1;z>=1;z--) // nahoru
        if (hra[i][z]== pom) 
            hra [i][z]=-2;
        else break;

}

void Posuv()
{
    for (z=1;z<=10;z++)
        for (i=1;i<=10;i++)
        for (j=1;j<=10;j++)  
            if (hra[i][j]==0)
            {hra[i][j]= hra[i][j-1];
             hra[i][j-1] = 0;
            }
    j=10;konec=1;
    for (i=1;i<=10;i++)
        if (hra[i][j]!=0)
            konec = 0;
}

