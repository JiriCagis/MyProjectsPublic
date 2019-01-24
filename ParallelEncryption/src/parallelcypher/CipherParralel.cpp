//
//  CipherParralel.cpp
//  Zasifruj.to
//
//  Created by Jiri Caga on 4/5/15.
//  Copyright (c) 2015 Jiri Caga. All rights reserved.
//

#include "CipherParralel.h"

CipherParallel::CipherParallel(){
    encryptionStrategies = new EncryptionStrategy *[3];
    encryptionStrategies[0] = new SubstitutionEncryptionStrategy();
    encryptionStrategies[1] = new PermutationEncryptionStrategy();
    encryptionStrategies[2] = new ColumnEncryptionStrategy();
    
    decryptionStrategies = new DecryptionStrategy *[3];
    decryptionStrategies[2] = new SubstitutionDecryptionStrategy();
    decryptionStrategies[1] = new PermutationDecryptionStrategy();
    decryptionStrategies[0] = new ColumnDecryptionStrategy();
};

void CipherParallel::init(State state, CipherKey *key){
    this->state = state;
    this->key = key;
};

byte* CipherParallel::doFinal(byte *bytes, int size){
    
    BlockBytes block1(bytes,0,size/2);
    BlockBytes block2(bytes,size/2,size);
    
    #pragma omp parallel sections
    {
        #pragma omp section
        {
            //thread1
            if (state == ENCRYPTION)
            {
                for(int i=0; i<3;i++)
                    encryptionStrategies[i]->execute(&block1, key);
            }
            else
            {
                for(int i=0;i<3;i++)
                    decryptionStrategies[i]->execute(&block1, key);
            }
        }
        
        #pragma omp section
        {
            //thread2
            if (state == ENCRYPTION)
            {
                for(int i=0; i<3;i++)
                    encryptionStrategies[i]->execute(&block2, key);
            }
            else
            {
                for(int i=0;i<3;i++)
                    decryptionStrategies[i]->execute(&block2, key);
            }
        }
    }
   
    return bytes;
};