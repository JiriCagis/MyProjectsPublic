//
//  main.cpp
//  Sudoku resitel rekurze
//
//  Created by Jiří Cága on 3/19/13.
//  Copyright (c) 2013 VŠB. All rights reserved.
//

#include <iostream>
using namespace std;
const int N =9;

void Zobraz(int sudoku[N][N])
{
    for (int i=0;i<N;i++)
    {
        for (int j=0;j<N;j++)
            cout << sudoku[i][j];
        cout << "\n";
    }
}

bool Test (int sudoku [N][N],int cislo,int x, int y)
{
    
    for (int k=0;k<N;k++)//test radku a sloupce
    { 
        if (sudoku[k][y] == cislo || sudoku[x][k] == cislo ) return false;
    }
    
    //test bunek
    int bunka_x, bunka_y;
   
    //urceni bunky souradnice zacatku bunky
    for (int posuv=0;posuv<=6;posuv+=3)
       if (x>=0+posuv && x<=2+posuv) 
       {
           bunka_x=0+posuv;
           break;
       }
    
    for (int posuv=0;posuv<=6;posuv+=3)
       if (y>=0+posuv && y<=2+posuv) 
       {
           bunka_y=0+posuv;
           break;
       }
    
  
    for (int i=bunka_x;i<bunka_x+3;i++)
        for (int j=bunka_y;j<bunka_y+3;j++)
            if (sudoku[i][j]==cislo)
                return false;
    
    return true;
    
    
}

bool Resitel (int sudoku [N][N], int i, int j)
{
    
    int hodnota;
    
    if (j==9)
    {
        j=0;i++;
    }
    
    if (i==9)
        return 1;
    
    if (sudoku[i][j]!=0) {
        if (Resitel(sudoku, i, j+1))
            return 1;
    } 
    else 
        
    {
        for (hodnota = 1; hodnota < 10; hodnota++) 
            if (Test(sudoku,hodnota,i, j)) 
            {
                sudoku[i][j]=hodnota;

                if (Resitel(sudoku, i, j+1))
                    return 1;
            }
        sudoku[i][j]=0; 
    }
    return 0;
    
    
}


         
int main (int argc, const char * argv[])
{
    
    int sudoku [N][N] = {0};
    sudoku[1][1]=1;
    cout << Resitel(sudoku, 0, 0)<<"\n";
    Zobraz(sudoku);
    return 0;
}

