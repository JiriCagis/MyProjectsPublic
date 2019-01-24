//
//  main.cpp
//  Bludiste
//
//  Created by Jiri Caga on 12/25/13.
//  Copyright (c) 2013 VSB. All rights reserved.
//

#include <iostream>
using namespace std;

class Bod
{
private:
    int ukazatele[4];
    bool stav;

public:
    Bod()
    {
        for (int i=0;i<4;i++)
            ukazatele[i] = -1;
        stav = false;
    }
    
    void set_stav(bool stav);
    bool get_stav();
    
    void set_ukazatel(int index,int hodnota);
    int get_ukazatel(int index);
};

class Bludiste
{
private: 
    int *pole;
    int velikost;
    int sirka;
    
    bool rekurze(int x, int y);
public: 
    Bludiste (int *uk_pole,int velikost,int sirka);
    
   
    
    void zobraz()
    {
        for (int i=0; i<velikost; i++) 
             (i%sirka == 0) ? cout<<"\n" << pole[i] << "\t":cout << pole[i] << "\t";
    }
    
    bool najdi_cestu();
    
};

Bludiste::Bludiste(int *uk_pole,int velikost,int sirka)
{
    this->velikost = velikost;
    this->sirka = sirka;
    
    pole = new int[velikost];
    
    for (int i=0;i<velikost;i++)
        pole[i] = uk_pole[i];
}

bool Bludiste::najdi_cestu()
{
    //ocisluje mozne body
    int pocet = 1;
    for (int i =1; i<sirka;i++)
        for (int j =1; j<sirka; j++) 
        {
           if (pole[i*sirka+j] == -1)
           {
               int pocet2 = 0;
               if (pole[i*sirka+j+1] == -1 || pole[i*sirka+j+1] == -3 || pole[i*sirka+j+1] >= 0) pocet2++;
               if (pole[i*sirka+j-1] == -1 || pole[i*sirka+j-1] == -3 || pole[i*sirka+j-1] >= 0) pocet2++;
               if (pole[(i+1)*sirka+j] == -1 || pole[(i+1)*sirka+j] == -3 || pole[(i+1)*sirka+j] >= 0) pocet2++;
               if (pole[(i-1)*sirka+j] == -1 || pole[(i-1)*sirka+j] == -3 || pole[(i-1)*sirka+j] >= 0) pocet2++;
               
               if (pocet2 == 1 || pocet2 >2)
                   pole[i*sirka+j] = pocet++;
           }
        }
    
    //vytvori pole bodu
    Bod *body = new Bod[pocet+1];
    int index =0;
    for (int i =1; i<sirka;i++)
        for (int j =1; j<sirka; j++)
        {
            if (pole[i*sirka+j] >=0)
            {
                
            }
        }
    
    return true;
}

int main (int argc, const char * argv[])
{
    
    // insert code here...
    std::cout << "Bludiste\n";
    //pole(i*100+j)
    const int N = 25; //velikost pole (m*n)
    const int M = 5; //sirka jednoho radku
    
    int *pole = new int[N];
    //smaze cele pole
    for (int i=0;i<N;i++)
    {
        pole[i] = -1;
    }
    
    //tvorba zdi
    
    //zdi okolo bludiste (mozne pouzit i pro jine bludiste)
    for (int i=0;i<M;i++)
    {
        pole[i] = -2;
        pole[(M*(M-1))+i] = -2;
        pole[i*M] = -2;
        pole[i*M+(M-1)] = -2;
    }
    
    //zdi uprostred
    pole[(2*M)+1] = -2;
    pole[(2*M)+3] = -2;
    
    
    //cil
    pole[1*M+4] = -3;
    
    //start
    pole[3*M+3] = 0;
    
    Bludiste objekt1(pole,N,M); 
    objekt1.najdi_cestu();
    objekt1.zobraz();
    
    delete pole;
    return 0;
}

