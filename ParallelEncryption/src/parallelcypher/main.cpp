//
//  main.cpp
//  Zasifruj.to
//
//  Created by Jiri Caga on 4/4/15.
//  Copyright (c) 2015 Jiri Caga. All rights reserved.
//

#include <iostream>
//#include "CipherKey.h"
//#include "Tests.h"
#include "CipherParralel.h"
#include "Timer.h"
using namespace std;


int main(int argc, const char * argv[]) {

  
    
    CipherKey key; //automaticky vygenerovany
	const int size = 1000000;
    byte bytes[size]={'a'};
    
	
    for(int i=0;i<size;i++)
        bytes[i]='a'+i;

	clock_t start,stop;
	
	start = clock();

	for(int i=0; i<10;i++)
	{
    CipherParallel cipher;
	//sifrovani
    cipher.init(ENCRYPTION, &key);
    cipher.doFinal(bytes, size);
    
    //desifrovani zpet
    cipher.init(DECRYPTION, &key);
    cipher.doFinal(bytes, size);
	}

	stop = clock();
	cout<<"Sifrovani a desifrovani trvalo: "<< (((float) stop - (float)start)/1000000.0F)*1000 << "ms";

	int a;
	cin >>a;
}
