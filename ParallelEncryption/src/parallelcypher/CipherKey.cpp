//
//  CipherKey.cpp
//  Zasifruj.to
//
//  Created by Jiri Caga on 4/4/15.
//  Copyright (c) 2015 Jiri Caga. All rights reserved.
//

#include "CipherKey.h"
#include <vector>       // std::vector
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include <algorithm>

CipherKey::CipherKey(){
    generateKey();
}

CipherKey::CipherKey(int *numbers){
    for(int i=0;i<128;i++)
        this->numbers[i] = numbers[i];
}

void CipherKey::generateKey(){
    /* initialize random seed: */
    srand (time(NULL));
    
    std::vector<int> myvector;
    
    // set some values:
    for (int i=0; i<128; ++i) myvector.push_back(i);
    
    // using myrandom:
    std::random_shuffle ( myvector.begin(), myvector.end());
    
    int position = 0;
    for (std::vector<int>::iterator it=myvector.begin(); it!=myvector.end(); ++it)
        numbers[position++] = *it;
    

}

int CipherKey::getNumber(int index){
    return (index>=0 && index<128) ? numbers[index] : -1;
}



