//
//  main.cpp
//  Jezdcova prochazka
//
//  Created by Jiří Cága on 2/14/13.
//  Copyright (c) 2013 VŠB. All rights reserved.
//

#include <iostream>
using namespace std;
int Prochazka(int **sachovnice,int x, int y,int N);
void Zobraz(int **sachovnice,int N);

int main (int argc, const char * argv[])
{

    int **sachovnice;
    int N;
    int x,y;
    
    cout << "Program Jezdcova prochazka\n";
    cout << "Zadejte velikost sachovnice (napr. 4 - vytvori sachov. 4x4\n";
    cin >>N;
    
    sachovnice = new int*[N]; //vytvori dynamicke pole
    for (int i=0; i<N; i++) 
        sachovnice[i] = new int[N];
        
    cout << "Zadejte cil kone (x,y):\n";
    cin >> x>>y;
    sachovnice[x][y]=7; //misto B
    
    cout << "Zadejte misto startu kone (x,y):\n";
    cin >> x>>y;
    cout<<"\n";
    Prochazka(sachovnice, x, x,N);
    
    Zobraz(sachovnice, N);
    

   
    
    return 0;
}

// Funkce pro zobrazeni jednotlivych tahu na sachovninci
void Zobraz(int **sachovnice,int N)
{
    static int krok =0;
    cout <<"Krok: "<< krok++<<"\n******************\n";
    for (int i=0;i<N;i++)
    {
       for(int j=0;j<N;j++)
           cout << sachovnice[i][j] << " ";
        cout << "\n";
    }
    cout<< "\n";
            
}

/* Funkce prochazka predstavuje rekurzivni funkci, kun skace tak dlouho nez narazi na
   misto B nebo na nemozny tah a vraci se zpet ze zanorene funkce. Vstupnimi parametry
   jsou pole sachovnice, souradnice kone a velikost sachovnice. Vystup funkce je:
   0.. nemozny tah, bud uz na tom miste kun stal nebo je mimo sachovnici
   1.. nalezeno hledane policko b */
int Prochazka(int **sachovnice,int x,int y,int N)
{
    int tah1,tah2,tah3,tah4,tah5,tah6,tah7,tah8;
    tah1=tah2=tah3=tah4=tah5=tah6=tah7=tah8=3;
    
    
    if (x>=N || y>=N ||x<0 || y<0) //tah mimo sachovnici
        return 0;
    if (sachovnice[x][y]==7)//tah na hledane policko B
        return 1;
    if (sachovnice[x][y]!=0 && sachovnice[x][y]!=4)//nemoznost tahu
        return 0;
    sachovnice[x][y]=3; //chvilkove oznaceni tahu pro zobrazeni mista kam kun tahl
    Zobraz(sachovnice, N);
    sachovnice[x][y]=2;//oznaci si jiz provedeny tah
    
    
    tah1 = Prochazka(sachovnice, x-1, y-2,N);
    
    if (tah1==0)
     tah2 = Prochazka(sachovnice, x-2, y-1,N);
    
     if (tah2==0)
    tah3 = Prochazka(sachovnice, x-2, y+1,N);
    
     if (tah3==0)
    tah4 = Prochazka(sachovnice, x-1, y+2,N);
    
     if (tah4==0)
    tah5 = Prochazka(sachovnice, x+1, y+2,N);
    
     if (tah5==0)
    tah6 = Prochazka(sachovnice, x+1, y+2,N);
    
     if (tah6==0)
    tah7 = Prochazka(sachovnice, x+2, y-1,N);
    
     if (tah7==0)
    tah8 = Prochazka(sachovnice, x+1, y-2,N);
    
    if (tah1==0 && tah2==0 && tah3==0 && tah4==0 && tah5==0 && tah6==0 && tah7==0 && tah8==0)
        return 0;
    
    
    return 1;
    
}

