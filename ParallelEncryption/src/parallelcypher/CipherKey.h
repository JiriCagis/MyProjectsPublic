//
//  CipherKey.h
//  Zasifruj.to
//
//  Created by Jiri Caga on 4/4/15.
//  Copyright (c) 2015 Jiri Caga. All rights reserved.
//

/**
 * Trida predstavujici klic pro sifrovani a desifrovani.
 * Pravidla:
 * 	klic se sklada ze 128 cisel,
 * 	rozsah cisel je 0-127,
 * 	kazde cislo je v klici jenom jednou.
 * @author jiricaga
 *
 */
class CipherKey{
private:
    int numbers[128];
    
    /**
    Metoda generujici nahodne cisla pro klic.
     */
    void generateKey();
    
public:
    static const int countNumbers = 128; //velikost pole cisel
    
    /**
    Defaultni konstruktor, vytvori klic z nahodnych cisel.
    */
    CipherKey();
    
    /**
    Konstruktor vytvari klic z pole cisel. Pole cisel musi
    mit 128 prvku jinak vypise do terminalu chybovou zpravu a
    inicializuje klic samymi nulami.
    */
    CipherKey(int *numbers);
    
    /**
    Metoda vrati cislo z klice na urcite pozici.
     */
    int getNumber(int index);
    
    /**
     Metoda porovna dva klice, pokud jsou stejne vrati boolevsky
     vyraz true jinak vrati boolovky vyraz false.
     */
    static bool compare(CipherKey &key1, CipherKey &key2){
        for (int i=0;i<countNumbers;i++){
            if(key1.getNumber(i) != key2.getNumber(i))
                return false;
        }
        return true;
    }
};
