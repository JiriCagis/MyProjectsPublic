//  --------------------------------------------------------------
//  Piskvorky_3x3
//  --------------------------------------------------------------

//  Created by Cagis on 25.07.12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.

#include <iostream>
using namespace std;

//definovani promennych
int hra [11][11];
int utok_obrana [11][11];
int predtah[11];
int i,j,ix,jx,pom,vyhra;

//definovani funkci
void Zobrazeni (int);
void Hrej_Hrac();
void Hrej_PC();
void Rozhodni (int);
void Funkce1(int,int,int,int);


int main (int argc, const char * argv[])
{
Start:    
    //smaze a pripravy pole pro hru
    for (i=3;i<8;i++)
        for (j=3;j<8;j++)
            if (i==3 || i==7 || j==3 || j==7)
                hra[i][j]=8;
    else
                hra[i][j]=0;

    for (int z=0;z<=4;z++)
    {
        Zobrazeni(1);
        Hrej_Hrac();
        if (vyhra >0) break;
        Hrej_PC();
        if (vyhra >0) break;      
    }
    
    Zobrazeni(1);
    cin.get();cin.get();
    vyhra = 0;
    goto Start;
}

void Zobrazeni(int vyber)
{
    // 1.. zobrazi hru
    // 2.. zobrazi utok nebo obranu obranu
    cout << "--------------------------";
    cout << "Piskvorky 3x3 by Juraj Caga";
    cout << "-------------------------- \n\n";
    
    for (i=4;i<7;i++) //zobrazeni herni plochy
    {
        cout << "                                                                   ";
        for (j=4;j<7;j++)
            if (vyber == 1) cout << hra[i][j]; else cout << utok_obrana[i][j];
            cout << "\n";
    }
    
    if (vyhra == 0)     cout << "\nSes na tahu: \n";
    if (vyhra == 1)     cout << "\nPocitac vyhral \n";
    if (vyhra == 2)     cout << "\nHrac vyhral \n";
    if (vyhra >0)       cout << "Pro novou hru stiskni Enter. \n";
}

void Rozhodni(int vyber)
{    
    for (i=4;i<=6;i++)
        for (j=4;j<=6;j++)
        {
            utok_obrana[i][j]=0; //mazani predeslych tahu
            if (hra[i][j]==0)
            {
                int n=0;

                for( int ii=-1;ii<=1;ii++)//projde smery kde by bylo mozne tahnout
                 for( int jj=-1;jj<=1;jj++)
                     Funkce1(ii,jj,n++,vyber);

                for (n = 0;n<=9;n++) //prirazeni nejvetsiho utoku ci obrany v dane souradnici
                    if (predtah[n]>utok_obrana[i][j])
                        utok_obrana[i][j] = predtah[n];
                
                if (utok_obrana[i][j]==2) //pokud muze vyhrat ci zabranit protihraci ve 2 koleckach vlozi krizek
                {
                    hra[i][j]=1;
                    pom=1;
                    if (vyber ==1) vyhra=1;
                    goto Pryc;
                }
            }
        }
Pryc: i=i; //prazdna instrukce nutno pro vyjeti ze smicky
}
void Funkce1(int ii ,int jj,int n,int vyber)
{
    predtah[n]=0; //maze predesle predtahy
    ix= ii*(-1);  //pomocne souradnice
    jx= jj *(-1); 
    
    if (hra[i+ii][j+jj] == vyber) // volne misto >> je vedle krizek(kolecko)?
    {
        if (hra[i+ii+ii][j+jj+jj]==0 || hra[i+ix][j+jx]==0) //je za krizkem(koleckem) volne misto [_X?] a [X_?] 
            predtah[n]=1;
        if (hra[i+ii+ii][j+jj+jj]==vyber || hra[i+ix][j+jx]==vyber) // je za krizkem(koleckem) dalsi [_X?] a [?_X]
            predtah[n]=2;
    }
    
    if (hra[i+ii][j+jj]==0)// Je na vedlejsi souradnici prazdno [_?]
        if (hra[i+ii+ii][j+jj+jj]==vyber || hra[i+ix][j+jx]==vyber) //je za prazdnym dalsi volne misto [_ _ ?] [? _ _]
            predtah[n]=1;        
}

void Hrej_Hrac()
{
Znova:    
    cin >> j >> i; //nacte vstup od uzivatele
    i+=3;j+=3;     //pricte konstantu aby se tah zobrazil na herni plose
    
    if (hra [i][j] != 0) //pokud je misto uz obsazeno zadavas znova
        goto Znova;
    hra [i][j] = 2;
    
    //kontrola jsetli hrac nevyhral
    
    //rady
    for (j=4;j<=6;j++)
        if (hra[4][j] == 2 && hra[5][ j] == 2 && hra[6][ j] == 2)
            vyhra = 2;
    
    //sloupce
    for (i = 4; i <= 6; i++)
        if (hra[i][ 4] == 2 && hra[i][ 5] == 2 && hra[i][ 6] == 2)
            vyhra = 2;
    
    //kriz
    if ((hra[4][ 4] == 2 && hra[5][ 5] == 2 && hra[6][ 6] == 2) || (hra[6][ 4] == 2 && hra[4][ 6] == 2)) 
        vyhra = 2;
}
void Hrej_PC()
{
    pom=0;
    Rozhodni(1); //zkouma jestli muze dat 3 krizek
    if (pom == 0) //dalsi moznosti pokud nemuze vyhrat
        Rozhodni(2); //nema protihrac nekde 2 kolecka?
    
    if (pom == 0) //Pokud nema, zapise krizek tam kde je utok 1
    {
        Rozhodni(1);
        for(i=3;i<=7;i++)
            for(j=3;j<=7;j++)
                if (utok_obrana[i][j]==1)
                    if (pom == 0)
                {
                    hra[i][j]=1;
                    pom=1;
                }
    }
    
    if (pom == 0)//pokud nemuze zautocit zapise krizek kamkoli
    {
        for(i=3;i<=7;i++)
            for(j=3;j<=7;j++)
                if(hra[i][j]==0)
                    if (pom == 0)
                    {
                        hra[i][j]=1;
                        pom=1;
                    }
    }
    
}
         
         

