//
//  main.cpp
//  Jezdcova prochazka2
//
//  Created by Jiří Cága on 2/14/13.
//  Copyright (c) 2013 VŠB. All rights reserved.
//

#include <iostream>
using namespace std;
void Zobraz(int **sachovnice,int N);
void Animace(int **sachovnice,int N,int pocet);
void Oznac(int **sachovnice,int N,int x,int y,int pocet);
int Mapovani(int **sachovnice,int N);


int main (int argc, const char * argv[])
{
    
    int **sachovnice;
    int N,x,y;
    
    cout << "Program Jezdcova prochazka\n";
    cout << "Program vypocte nejmensi pocet moznych tahu kone z mista A do mista B";
    cout <<"\n*********************************************************************\n";
    cout << "Zadejte velikost sachovnice (napr. 4 - vytvori sachov. 4x4\n";
    cin >>N;
    
    sachovnice = new int*[N]; //vytvori dynamicke pole
    for (int i=0; i<N; i++) 
        sachovnice[i] = new int[N];
    
    //inicializace sachovnice na same -1
    for (int i=0;i<N;i++)
        for (int j=0;j<N;j++)
            sachovnice[i][j]=-1;
    
    cout << "Pozn: x.. radek, y.. sloupec\n";
   
    
    cout << "Zadejte cil kone (x,y):\n";
    cin >> x>>y;
    sachovnice[x][y]=0; //misto B
    
    cout << "Zadejte misto startu kone (x,y):\n";
    cin >> x>>y;
    cout<<"\n";
    
    Mapovani(sachovnice, N);
    cout << "Pocet kroku: "<< sachovnice[x][y];
    
    cout << "\n (1) Zobrazit animaci kone  (2) Konec\n";
    int volba;
    cin >> volba;
    if (volba==1)
    
        Animace(sachovnice,N,sachovnice[x][y]);

    return 0;
}

/* Funkce se snazi ocislovat danou souradnici pokud je to mozne.
 Podminky jsou aby se nachazela uvnitr sachoveho pole, a zaroven byla
 jeste volna (obsahuje -1)*/
void Oznac(int **sachovnice,int N,int x,int y,int pocet)
{
    if (!(x>=N || x<0 || y>=N || y<0))
        if (sachovnice[x][y]==-1)
            sachovnice[x][y]=++pocet;
    
}

/* Funkce se snazi mapovat okoli ciloveho mista a cislovat tahy. 
 Kolem mista kde se nachazi udela kruh moznych tahu, dale pokracuje
 u policek ktere si oznaci jako mozny tah a vyhledava kolem nich dalsi 
 mozne tahy. Vstupem do funkce je pole sachovnice, a velikost sach. */
int Mapovani(int **sachovnice,int N)
{
    int pocet = 0;
    bool pokracuj = true;
    while(pokracuj)
    {
        pokracuj=false;
        
        for (int i=0;i<N;i++)
            for (int j=0;j<N;j++)
            {
                if (sachovnice[i][j]==-1)
                    pokracuj=true;
                
                if (sachovnice[i][j]==pocet)
                {
                    Oznac(sachovnice,N,i-2,j+1,pocet);
                    Oznac(sachovnice,N,i-1,j+2,pocet);
                    Oznac(sachovnice,N,i+1,j+2,pocet);
                    Oznac(sachovnice,N,i+2,j+1,pocet);
                    Oznac(sachovnice,N,i+2,j-1,pocet);
                    Oznac(sachovnice,N,i+1,j-2,pocet);
                    Oznac(sachovnice,N,i-1,j-2,pocet);
                    Oznac(sachovnice,N,i-2,j-1,pocet);
                }
            }
        pocet++;
    }
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

void Animace(int **sachovnice,int N,int pocet )
{
    
   
    
    int **pom = new int*[N]; //vytvori pom dynamicke pole
    for (int i=0; i<N; i++) 
        pom[i] = new int[N];
    
    //inicializace sachovnice na same 0
    for (int i=0;i<N;i++)
        for (int j=0;j<N;j++)
            pom[i][j]=0;
    
    while (pocet)
    {
        int vlozeno = 0;
        for (int i=0;i<N;i++)
            for (int j=0;j<N;j++)
               if (sachovnice[i][j]==pocet && vlozeno==0)
               {
                   pom[i][j]=pocet;
                   vlozeno=1;
               }        
        Zobraz(pom, N);
        pocet--;
    }
}




