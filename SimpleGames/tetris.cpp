//
//  Tetris.cpp
//  Tetris
//
//  Created by Cagis on 11.08.12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <ctime>
#include <fstream>
#include <string>
using namespace std;

//definice funkci
void Zobrazeni();
void Prirad();
void Pohyb(int);
void Rotace();
void Kontrola();
void ZapisCteni_soubor(int);

//definice promennych

int hra[251];
int i,j,konec,znak,nahoda,skore,nejskore,pom;
int kostka1,kostka2,kostka3,kostka4,kostka3_zal,kostka4_zal;

int main (int argc, const char * argv[])
{
    ZapisCteni_soubor(1);//pokusi se nacist skore ze souboru
    ZapisCteni_soubor(2);//pokud nejde nacist, vytvori soubor s nejskore

    //priprava herni plochy, vykresleni zakazanych mist
    for (i=1;i<=250;i++)
        if (i%10==0 || (i-1)%10==0 || i>241) hra[i]=1; else hra[i]=0;
    
    //vykresleni kostky
    kostka1=35;kostka2=25;nahoda=15; Prirad();

    do {
        Zobrazeni();
        cin >> znak;
        if (znak == 1) Pohyb(-1); //posuv vlevo
        if (znak == 2) Pohyb(1);  //posuv vpravo
        if (znak == 4) Rotace();  //rotace kostky
        Pohyb(10);                //posuv kostek dolu
        Kontrola();
    } while (konec ==0);
    
    Zobrazeni();
    return 0;
}

void Zobrazeni()
{
    cout << "--------- Tetris ----------\nSkore    ";
    for (i=1;i<=250;i++)
    {
        if (hra[i] ==0) cout << "  "; else cout << "#";
        
        switch (i) {
            case 10:
                if (skore<10) cout << " Nejskore\n00" << skore << "       "; 
                if (skore>10 && skore<100) cout << " Nejskore\n0" << skore << "       ";
                if (skore>100) cout << " Nejskore\n" << skore << "       ";
                break;
                
            case 20:
                if (nejskore<10) cout << "         00" << nejskore << "\n             "; 
                if (nejskore>10 && nejskore<100) cout << "         0" << nejskore << "\n             ";
                if (nejskore>100) cout <<"         "<< nejskore << "\n             ";
                break;
                
            default:
                if (i%10==0) cout << "\n             ";
                break;
        }
    }
    cout << "\n (1)Vlevo, (2) Vpravo, (3) Pokracuj, (4) Rotace ";
    if (konec == 0) cout << "\n Zvolte jak dal: "; else cout << "\n Konec hry. ";
}

void Prirad()
{
    switch (nahoda) {
        case 0:kostka3=kostka1 +1;kostka4=kostka2-10;break;
        case 1:kostka3=kostka1-1;kostka4=kostka2-10;break;
        case 2:kostka3=kostka1+1;kostka4=kostka1+2;break;
        case 3:kostka3=kostka2+1;kostka4=kostka2+2;break;
        case 4:kostka3=kostka2-1;kostka4=kostka2-2;break;
        case 5:kostka3=kostka1-1;kostka4=kostka1-2;break;
        case 6:kostka3=kostka2-10;kostka4=kostka2-11;break;
        case 7:kostka3=kostka2-10;kostka4=kostka2-9;break;
            
        case 8:kostka3=kostka2-1;kostka4=kostka1+1;break;
        case 9:kostka3=kostka2+1;kostka4=kostka1-1;break;
        case 10:kostka3=kostka2+1;kostka4=kostka2-9;break;
        case 11:kostka3=kostka2-1;kostka4=kostka2-11;break;
        
        case 12:kostka3=kostka1-10;kostka4=kostka1-20;kostka2=kostka1-30; break;
        case 13:kostka3=kostka1-1;kostka4=kostka1+1;kostka2=kostka1+2;break;
        
        case 14:kostka3=kostka1-1;kostka4=kostka1+1;break;
        case 15:kostka3=kostka2-1;kostka4=kostka2+1;break;
        case 16:kostka3=kostka2-10;kostka4=kostka2+1;break;
        case 17:kostka3=kostka2-10;kostka4=kostka2-1;break;
            
        case 18:kostka3=kostka1+1;kostka4=kostka2+1;break;
    }
    
    if (hra[kostka3]>0 || hra[kostka4]>0)
    {
        kostka3= kostka3_zal;
        kostka4 = kostka4_zal;
    }
    
    hra[kostka1] = -1;hra[kostka2] = -1;hra[kostka3] = -1;hra[kostka4] = -1;//zobrazeni nove kostky do pole
}

void Rotace()
{
    kostka3_zal = kostka3; kostka4_zal = kostka4;//uchovani hodnoty kostek pro pripad ze by neslo rotovat
    
    hra[kostka1]=0;hra[kostka2]=0;hra[kostka3]=0;hra[kostka4]=0; //smazani predesle pozice
    srand(time(NULL));
    if (nahoda>= 0 && nahoda <=7) nahoda = (rand()%8);
    if (nahoda>= 8 && nahoda <=11) nahoda = (rand()%4)+8;
    if (nahoda>= 12 && nahoda <=13) nahoda = (rand()%2)+12;
    if (nahoda>= 14 && nahoda <=17) nahoda = (rand()%4)+14;
    
    Prirad();
}

void Pohyb(int ii)
{
    if (hra[kostka1+ii] <=0 && hra[kostka2+ii] <=0 && hra[kostka3+ii] <=0 && hra[kostka4+ii] <=0)
    {
        hra[kostka1]=0;hra[kostka2]=0;hra[kostka3]=0;hra[kostka4]=0; //smazani predesle pozice
        kostka1+=ii; kostka2+=ii; kostka3+=ii; kostka4+=ii;         //posuv kostek
        hra[kostka1]=-1;hra[kostka2]=-1;hra[kostka3]=-1;hra[kostka4]=-1; //vykresleni nove pozice
    }
    else
        if (ii==10) 
        {
            srand(time(NULL));
            nahoda = (rand()%19);
            //zmena stavu kostek
            hra[kostka1] = 1;
            hra[kostka2] = 1;
            hra[kostka3] = 1;
            hra[kostka4] = 1;
          
            kostka1=35;kostka2=25; 
            Prirad(); 
            skore++;
            if (skore > nejskore)
                nejskore = skore;
            ZapisCteni_soubor(2);  
        }
    
}

void Kontrola()
{
    int z;
    //kontroluje rady, jestli neni plna, pokud ano radu smaze a provede posuv kostek
    // smerem dolu
    
    for (z=10;z<=230;z+=10)
        if (hra[1+z]==1 && hra [2+z]==1 && hra [3+z]==1 && hra [4+z]==1 && hra [5+z]==1 && hra [6+z]==1
            && hra [7+z]==1 && hra [8+z]==1 && hra [9+z]==1)
        {
            for (i=2;i<10;i++) hra[i+z]=0; // smaze radu
    
            for (i=250;i>10;i--) //posuv dolu
            if (hra[i] == 0 && hra[i-10]!=-1)
            {
            hra[i]= hra[i-10];
            hra[i-10]=0;
            }
        }
       
    //kontrola jestli nebyl prekrocent limit
    for (i=32;i<40;i++)
        if (hra[i] == 1)
            konec =1;
}

void ZapisCteni_soubor(int vyber)
{
    if (vyber == 1) //cteni
    {
        ifstream soubor1;
        soubor1.open("skore.txt",ios::in);
        soubor1 >> nejskore;
        soubor1.close();
    }
    else //zapis
    {
        ofstream soubor2;
        soubor2.open("skore.txt",ios::out);
        soubor2 << nejskore;
        soubor2.close();
    }
}



