//
//  BlockBytes.cpp
//  Zasifruj.to
//
//  Created by Jiri Caga on 4/4/15.
//  Copyright (c) 2015 Jiri Caga. All rights reserved.
//

#include "BlockBytes.h"
#include <climits>

BlockBytes::BlockBytes(byte *bytes, int size){
    this->bytes = bytes;
    this->SIZE=size;
}

BlockBytes::BlockBytes(byte *bytes, int start, int end){
    this->bytes = bytes;
    this->bytes=this->bytes+start;
    this->SIZE = end-start;
}

int BlockBytes::getSize(){
    return SIZE;
}

byte* BlockBytes::getBytes() {
    return bytes;
}

byte BlockBytes::getByte(int index){
    return bytes[index];
}

void BlockBytes::setByte(int index,byte value){
    bytes[index] = value;
}

byte BlockBytes::getHighNibble(int index){
    int b = bytes[index];
    return (byte) (((b) >> 4) & 0x0F);
}


void BlockBytes::setHighNibble(int index,byte value){
    int nibble = ((int) (value))<<4; //posun zadanych 4 bitu na spravne misto
    int byteAsInteger = bytes[index];
    byteAsInteger=byteAsInteger&(INT_MAX&~(240)); //mazani stare hodnoty vrchnich 4 bitu na nulu
    byteAsInteger=byteAsInteger|nibble; //nastaveni nove hodnoty vrchnich 4 bitu (nibblu)
    bytes[index] = (byte) byteAsInteger;
}


byte BlockBytes::getLowNibble(int index){
    int b = bytes[index];
    return (byte) ((b) & 0x0F);
}

void BlockBytes::setLowNibble(int index,byte value){
    int nibble = ((int) (value));
    int byteAsInteger = bytes[index];
    byteAsInteger=byteAsInteger&(INT_MAX&~(15)); //mazani stare hodnoty spodnich 4 bitu na nulu
    byteAsInteger=byteAsInteger|nibble; //nastaveni nove hodnoty vrchnich 4 bitu (nibblu)
    bytes[index] = (byte) byteAsInteger;
}


bool BlockBytes::getBit(int index){
    /*
     * Metoda zjisteni vnitrni pozice bitu
     * prvni urci index v poli, a pote index prislusneho bitu
     * napr: mame ulozene 2 bajty, chceme bit cislo 9
     * 9/8=1 .. takze budeme prohledavat druhy byte
     * 9&8=1 .. zbytek po deleni 1, coz je index v hledanem bytu
     */
    int indexOfArrayByte = index/8;
    int indexForBit = index%8;
    
    /*
     * Bitova operace pro zjisteni bitu v bajtu
     * ukazka:
     * chceme ziskat 3 prvek
     * bajt[11001000]
     * maska[0001000]
     * vysledek
     * 		[11001000] &
     * 		[00001000]
     * 	=	[00001000] ..a ted pro urceni jestli je bit v jednicce staci porovnat jestli neni nula
     */
    int byteAsInteger = bytes[indexOfArrayByte];
    int mask = 1<<indexForBit;
    return (byteAsInteger&mask)!=0;
}


void BlockBytes::setBit(int index, bool value){
    /*
     * Metoda zjisteni vnitrni pozice bitu
     * prvni urci index v poli, a pote index prislusneho bitu
     * napr: mame ulozene 2 bajty, chceme bit cislo 9
     * 9/8=1 .. takze budeme prohledavat druhy byte
     * 9&8=1 .. zbytek po deleni 1, coz je index v hledanem bytu
     */
    int indexOfArrayByte = index/8;
    int indexForBit = index%8;
    
    int byteAsInteger = bytes[indexOfArrayByte];
    
    /*
     * Bitove operace pro ulozeni bitu v bajtu
     */
    if (value == true) //nastavujeme jednicku na dane misto
    {
        /*
         * Ukazka:
         * Chceme ulozit 1 na 3 pozici v bajtu
         * bajt: [10000000]
         * maska: [00001000]
         * vysledek [10000000] |
         * 			[00001000]
         * 	=		[10001000]
         */
        int mask = 1<<indexForBit;
        byteAsInteger = byteAsInteger|mask;
    }
    else //nastavuje nulu na dane misto
    {
        /*
         * Ukazka:
         * Chceme ulozit 0 na 3 pozici v bajtu
         * bajt: [10001000]
         * maska: [11110111]
         * vysledek [10001000] &
         * 			[11110111]
         * 	=		[10000000]
         */
        int mask = ~(1<<indexForBit);
        byteAsInteger = byteAsInteger&mask;
    }
    
    bytes[indexOfArrayByte] = (byte) byteAsInteger;
}

void BlockBytes::writeToConsole(){
        std::cout << "=========================\n";
        for (int i=0;i<SIZE;i++)
        {
            //zamaskuje vsechny bity integeru necha jen tech 8 pro bajt
            std::cout<< "Byte["<< i <<"] is binary: ";
            byteToBinary(i);
            std::cout<<"\n";
        }
        std::cout << "=========================\n";
}

void BlockBytes::byteToBinary(int position){
    for (int i=7;i>=0;i--)
    {
        if (getBit(i+(position*8)))
            std::cout<<"1";
        else
            std::cout<<"0";
    }
}


