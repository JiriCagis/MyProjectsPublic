//
//  Cipher.cpp
//  Zasifruj.to
//
//  Created by Jiri Caga on 4/5/15.
//  Copyright (c) 2015 Jiri Caga. All rights reserved.
//

#include "Cipher.h"

Cipher::Cipher(){
    encryptionStrategies = new EncryptionStrategy *[3];
    encryptionStrategies[0] = new SubstitutionEncryptionStrategy();
    encryptionStrategies[1] = new PermutationEncryptionStrategy();
    encryptionStrategies[2] = new ColumnEncryptionStrategy();
    
    decryptionStrategies = new DecryptionStrategy *[3];
    decryptionStrategies[2] = new SubstitutionDecryptionStrategy();
    decryptionStrategies[1] = new PermutationDecryptionStrategy();
    decryptionStrategies[0] = new ColumnDecryptionStrategy();
};

void Cipher::init(State state, CipherKey *key){
    this->state = state;
    this->key = key;
};

byte* Cipher::doFinal(byte *bytes, int size){
    BlockBytes block(bytes,size);
    if (state == ENCRYPTION)
    {
        for(int i=0; i<3;i++)
            encryptionStrategies[i]->execute(&block, key);
    }
    else
    {
        for(int i=0;i<3;i++)
            decryptionStrategies[i]->execute(&block, key);
    }
    
    return block.getBytes();
};