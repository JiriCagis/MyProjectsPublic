//
//  EncryptionStrategy.h
//  Zasifruj.to
//
//  Created by Jiri Caga on 4/4/15.
//  Copyright (c) 2015 Jiri Caga. All rights reserved.
//

#include <stdio.h>
#include <map>


class EncryptionStrategy{
public:
    virtual void execute(BlockBytes *blockBytes,CipherKey *key)=0;
};

/*
 * Provadi posun prvku ve sloupcich o zvoleny pocet dle klice. (posun dolu)
 * Priklad:  posun prvku v prvnim sloupci o 2
 * 123		423
 * 456	=>	756
 * 789		189
 */
class ColumnEncryptionStrategy: public EncryptionStrategy{
    
public:
    void execute(BlockBytes *block,CipherKey *key){
        for (int i=0;i<(block->getSize()/16);i++)
        {
            int matrixStart = 16*i;
            columnSwift(block, matrixStart, key->getNumber(0));
            columnSwift(block, matrixStart+1, key->getNumber(1));
            columnSwift(block, matrixStart+2,key->getNumber(2));
            columnSwift(block, matrixStart+3, key->getNumber(3));
        }
    }
    
private:
    void columnSwift(BlockBytes *block,int columnStart,int countSwift){
        for (int i=0;i<countSwift;i++)
        {
            byte firstByteInColumn = block->getByte(columnStart);
            block->setByte(columnStart,block->getByte(columnStart+4));
            block->setByte(columnStart+4,block->getByte(columnStart+8));
            block->setByte(columnStart+8,block->getByte(columnStart+12));
            block->setByte(columnStart+12,firstByteInColumn);
        }
    }
};

/**
 * @author cag0008
 * Permutuje bity v bloku bitu dle klice.
 * Priklad: (ukazka na bloku o 4bitech)
 * klic: 4132
 * blok: 1010->(prehodim 1 s 4 bitem) 0011->(prehodim 2 s 1 bitem)-> 0011
 * 		-> (prehodim 3 s 3 bitem) -> 0011 ->(prehodim 4 s 2 bitem) 0110
 */
class PermutationEncryptionStrategy: public EncryptionStrategy{
    
public:
    void execute(BlockBytes *block, CipherKey *key){
        //cyklus se provede pouze tolikrat kolik je v bloku podbloku obsahujici 128bitu
        for (int count=0;count<block->getSize()/16;count++)
        {
            int position = count*16; //pozice v bloku, urcujici podblok s kterym se ma ted pracovat
            for (int i=0;i<key->countNumbers;i++)
            {
                int indexFromKey = key->getNumber(i)+position;
                bool tmp = block->getBit(i+position);
                block->setBit(i+position, block->getBit(indexFromKey));
                block->setBit(indexFromKey, tmp);
            }
        }
    }
};

/**
 * @author cag0008
 * Prochazi postupne blok bytu, bere si jejich spodni a horni nibble
 * a ten nahrani za nibble ktery je v hash tabulce.
 */
class SubstitutionEncryptionStrategy:public EncryptionStrategy{
    
public:
    void execute(BlockBytes *block, CipherKey *key){
        int subkey[16]; //podklic obsahujici pouze cislo od 0-15 z klice
        
        //vytvoreni subklice
        int positionInSubkey = 0;
        for (int i=0;i<128;i++)
        {
            int number = key->getNumber(i);
            if (number>=0 && (number<16))
                subkey[positionInSubkey++] = number;
        }
        
        //vytvoreni hash mapy dle klice
        std::map<byte,byte> map;
        for (int i=0;i<16;i++)
        {
            map[(byte) i]= (byte) subkey[i];
        }
        
        //provadeni substituce jednotlivych niblu
        for (int i =0; i<block->getSize();i++)
        {
            byte lowNible = block->getLowNibble(i);
            byte ciherLowNible = map[lowNible];
            
            byte highNible = block->getHighNibble(i);
            byte ciherHighNible = map[highNible];
            
            block->setLowNibble(i, ciherLowNible);
            block->setHighNibble(i, ciherHighNible);
        }
    }
};
