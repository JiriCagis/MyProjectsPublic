//
//  BlockBytes.h
//  Zasifruj.to
//
//  Created by Jiri Caga on 4/4/15.
//  Copyright (c) 2015 Jiri Caga. All rights reserved.
//
#include <stdio.h>
#include <iostream>


/**
 * Trida urcena pro ulozeni bloku bajtu s moznosti prace s blokem jako celkem a
 * zaroven umoznuje pracovat s jednotlivymi nibbly, bity bloku.
 * @author jiricaga
 *
 */
typedef char byte;

class BlockBytes{
private:
    byte *bytes; //pole bytu
    int SIZE;
    void byteToBinary(int positon);
public:
    /**
     Konstruktor vytvarejici blok bytu z pole bajtu, musime predat i velikost pole.
     */
    BlockBytes(byte *bytes, int size);
    
    /**
     Konstruktor vytvarejici blok bytu z pole bajtu, kde muzeme specifikovat start a konec pole.
     */
    BlockBytes(byte *bytes, int start, int end);
    
    /**
     * Velikost bloku
     * @return pocet bajtu v bloku
     */
    int getSize();
    
    /**
     Metoda vrati vsechny bajty v bloku.
     */
    byte* getBytes();
    
    /**
     Metoda vrati bajt na urcite pozici.
     */
    byte getByte(int index);
    
    /**
     Metoda nastavi bajkt v bloku na urcite pozici.
     */
    void setByte(int index,byte value);
    
    /**
     * Metoda ziska vrchni skupinu 4 bitu (high nibble) z bajtu dle indexu.
     * Ukazka:
     * bajt: [10101100]
     * navret: [00001010]
     * @param index bajtu v bloku bajtu
     * @return horni nibble
     */
    byte getHighNibble(int index);
    
    /**
     * Metoda nastavi vrchni skupinu 4 bitu(high nibble) bajtu dle indexu.
     * Ukazka:
     * bajt: [10000001]
     * value: [00000010]
     * vysledny obsah bajtu: [10100001]
     * @param index bajtu v bloku bajtu
     * @param value hodnota high nibble
     */
    void setHighNibble(int index,byte value);
    
    /**
     * Metoda ziska spodni skupinu 4 bitu (high nibble) z bajtu dle indexu.
     * Ukazka:
     * bajt: [10101100]
     * navret: [00001100]
     * @param index bajtu v bloku bajtu
     * @return dolni nibble
     */
    byte getLowNibble(int index);
    
    /**
     * Metoda nastavi spodni skupinu 4 bitu(high nibble) bajtu dle indexu.
     * Ukazka:
     * bajt: [11100001]
     * value: [00000010]
     * vysledny obsah bajtu: [111000011]
     * @param index bajtu v bloku bajtu
     * @param value hodnota spodniho nibble
     */
    void setLowNibble(int index,byte value);
    
    /**
     * Metoda k ziskani bitu na urcite pozici, metoda pristupuje k bloku bitu jako k celku.
     * Napr:  mame ulozene 2 byty v bloku
     * 0..7 prvni byte v poli
     * 8..15 druhy byte v poli
     * @param index urcuje pozici bitu
     * @return bit na specificke pozici, vraci true pokud je bit v log.1 jinak false
     */
    bool getBit(int index);
    
    /**
     * Metoda k nastaveni bitu na urcite pozici, metoda pristupuje k bloku bitu jako celku.
     * Napr:  mame ulozene 2 byty v bloku
     * 0..7 prvni byte v poli
     * 8..15 druhy byte v poli
     * @param index umisteni v bloku bitu
     * @param value true logicka 1, false logicka 0
     */
    void setBit(int index, bool value);
    
    
    /**
     Vypise obsah bloku do konzole.
     */
    void writeToConsole();
    
    
};