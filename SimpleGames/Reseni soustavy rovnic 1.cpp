//
//  main.cpp
//  Reseni soustavy rovnic
//
//  Created by Cagis on 13.10.12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#include <iostream>
using namespace std;

//definice funkci
void Zobraz();
void Spocitej();
void Prehod_radek(int,int);
void Nasob_radek(int,int);
void Odecti_radek(int,int);
void Substituce();

//definice promenych
int matice [4][4]; //? 4radek slouzi jako pomocny
int minimum,maximum,pom;
double x,y,z;
int i,j,ii,jj;
bool pokracuj = true;

int main (int argc, const char * argv[])
{
    cout << "Program pro vypocet soustavy rovnic o trech neznamych \n";
    cout << "-------------------------------------------------------\n";
    
    
    //nacteni rovnice
    for (j=0;j<=2;j++)
    {
        cout << "Zadejte "<<j+1 <<" rovnici, ve tvaru x+y+z=k \n";
        
        cout << "x="; cin >> matice[0][j]; 
        cout << "y="; cin >> matice[1][j]; 
        cout << "z="; cin >> matice[2][j]; 
        cout << "k="; cin >> matice[3][j];        
    }
    
    Spocitej();
    Substituce();
    
    if (pokracuj == false) cout << "Rovnice nejde vyresit, spatne zadani";
    return 0;
}

//funkce pro zobrazeni matice z nactenych prvku
void Zobraz()  
{
    cout << "\n";
    
    for (j=0;j<=2;j++)
    { for (i=0; i<=3; i++) 
            cout << matice[i][j] << " ";
        cout << "\n";
    }
}

void Spocitej()
{
    //pokusi se vyresit danou soustavu pomoci gaussovi eliminacni metody
    
    //osetri chybu zadavani vstupu kdy jsou vsechny 3 promenne x v 0
    for (j=0;j<=2;j++)
    if (matice[0][j]>0) Prehod_radek(j,0);
    if (matice[0][0] ==0) pokracuj = false;
    
    //nastaveni prvku pro promenou x na kladne hodnoty
    for (j=0;j<=2;j++) 
        if (matice[0][j] < 0) Nasob_radek(j, -1);
    
    if (pokracuj==true)
    {
    //urceni nejmensiho prvku, pripadne prehozeni radku aby byl vedouci prvek
    minimum=matice[0][0]; jj=0;
    for (j=0;j<=2;j++)
        if (matice [0][j] < minimum && matice[0][j]!=0) 
        {
            minimum=matice[0][j];
            jj = j;
        }
    Prehod_radek(jj,0);
    
        
    
    Zobraz();    
    //nastaveni prvku pod vedoucim prvkem x na nuly
    for (j=1;j<=2;j++)
    if (matice[0][j]!=0)
    {
        if (matice[0][j] % matice[0][0]!= 0) //pokud nejdou prvky jednoduse vydelit, nutne hledat nejvetsi spolecny nasobek 
        {   pom = matice[0][0]; //nutno vlozit o kolik se ma radek vynasobit, jeste pred prenasobenim radku
            Nasob_radek(0, matice[0][j]);
            Nasob_radek(j, pom);
        }
        
        pom = matice[0][j]/matice[0][0];
        for (i=0;i<=3;i++) matice[i][3] = matice [i][0]; //presune do pomocneho radku
        Nasob_radek(3,pom);
        Odecti_radek(3, j);
    }
        
    //nastaveni prvku pro promenou y na kladne hodnoty
    for (j=0;j<=2;j++) 
        if (matice[1][j] < 0) Nasob_radek(j, -1);
    
        
    //nastaveni prvku pod vedoucim prvkem y na nuly
    
    j=2;
    if (matice[1][j]!=0)
    {
        if (matice[1][j] % matice[1][j-1]!= 0) 
        {   pom = matice[1][j-1]; //nutno vlozit o kolik se ma radek vynasobit, jeste pred prenasobenim radku
            Nasob_radek(1, matice[1][j]);
            Nasob_radek(2, pom);
        }
        
            pom = matice[1][j]/matice[1][1];
            for (i=0;i<=3;i++) matice[i][3] = matice [i][1]; //presune do pomocneho radku
            Nasob_radek(3,pom);
            Odecti_radek(3, j);
    }
        
    }
    
    Zobraz();
    
    
    
}

//radkove operace
void Prehod_radek(int ktery,int kam)
{
    for (i=0;i<=3;i++) matice[i][3] = matice [i][kam]; // vlozeni radku do ktereho se bude presouvat do pomocne promenne
    for (i=0;i<=3;i++) matice[i][kam] = matice[i][ktery];  // radek ktery byl zvolen k presuvu se presune na nove misto
    for (i=0;i<=3;i++) matice[i][ktery] = matice[i][3]; // na puvodni misto se nahraje radek z pomocne matice
}
void Nasob_radek(int ktery,int nasobek)
{
    for (i=0;i<=3;i++) matice[i][ktery] = matice[i][ktery]*nasobek;
}
void Odecti_radek(int radek1,int radek2)
{
    //radek1: radek ktery  se bude pricitat
    //radek2: k tomuto radku odectu radek1
        for (i=0;i<=3;i++) matice[i][radek2] -= matice[i][radek1];
}

//zpetna substituce
void Substituce()
{
    z=matice[3][2]/matice[2][2];
    y=(matice[3][1]-z*matice[2][1])/matice[1][1];
    x=(matice[3][0]-z*matice[2][0]-y*matice[1][0])/matice[0][0];
    
    cout << "Reseni je:\n";
    cout << "x=" << x << "\n";
    cout << "y=" << y<< "\n";
    cout << "z=" << z<< "\n";
    
}

