//
//  CipherParralel.h
//  Zasifruj.to
//
//  Created by Jiri Caga on 4/5/15.
//  Copyright (c) 2015 Jiri Caga. All rights reserved.
//

//
//  Cipher.h
//  Zasifruj.to
//
//  Created by Jiri Caga on 4/5/15.
//  Copyright (c) 2015 Jiri Caga. All rights reserved.
//
#include <stdio.h>
#include "CipherKey.h"
#include "BlockBytes.h"
#include "EncryptionStrategy.h"
#include "DecryptionStrategy.h"

enum State{ENCRYPTION,DECRYPTION};

/**
 Trida slouzi pro symetricke sifrovani pole bytu na vicejadrovych procesorech.
 Pred sifrovanim se rozdeli pole bytu do dvou skupin, kdy kazda skupina bezi na 
 zvlast jadre procesoru. K sifrovani pouziva tri algoritmy a substituce, permutace
 a maticove operace. Pred zacatkem je nutne tridu inicializovat
 klicem a indikatorem znacici jestli se ma provest sifrovani
 nebo desifrovani
 */
class CipherParallel{
private:
    State state;
    CipherKey *key;
    EncryptionStrategy **encryptionStrategies;
    DecryptionStrategy **decryptionStrategies;
    
public:
    CipherParallel();
    void init(State state,CipherKey *key);
    
    /**
     * Metoda provede sifrovani pro dany vstup
     *
     * @param bytes
     *            pole bytu
     * @return zasifrovane pole bytu
     */
    byte* doFinal(byte *bytes,int size);
    
};
