//
//  main.cpp
//  AnalysisText
//
//  Created by Jiri Caga on 6/22/14.
//  Copyright (c) 2014 Jiri Caga. All rights reserved.
//

#include <iostream>
#include <ctype.h>
#include <stdio.h>
using namespace std;

bool IsVowel(char c);
void AnalysisText(char text[]);


int main(int argc, const char * argv[])
{
    AnalysisText("Karel nese asi caj,ale ja ho vyliju.");
}

void AnalysisText(char array[])
{
    unsigned int N = strlen(array);
    unsigned int syllables = 0;
    unsigned int sentences = 0;
    unsigned int words =0;
    unsigned int positionSpace = 0;
    
    for (int i=0;i<N;i++)
    {
        char c = array[i];
        
        if (c=='.' || c=='?' || c=='!') //end sentences
        {
            sentences++;
            words++;
            positionSpace = i;
            continue;
        }
        
        if (c==' ' || c==',') //space between word
        {
            words++;
            positionSpace=i;
            continue;
        }
        
        if (IsVowel(c)) syllables++;
        
        if ((c=='a' || c=='o') && (i<N-1 && array[i+1]=='u' && (i-positionSpace)!=2 &&(i-positionSpace)!=3))
        {
            syllables-=2;
            continue;
        }
        if((c=='l'||c=='r')&&(i>0 && i<N-1)&&(!IsVowel(array[i-1])&&!IsVowel(array[i+1])))
            syllables++;
    }
    
    double avgWordInSentence = words/(double) sentences;
    double avgSyllabesInWord = syllables/ (double) words;
    double indexReadAbility = 206.835 - (1.015*avgWordInSentence)-(84.6*avgSyllabesInWord);
    
    cout<<array<<"\n";
    cout<<"Count words: "<< words<<"\n";
    cout<<"Count sentences: "<< sentences<<"\n";
    cout<<"Count syllables: "<< syllables<<"\n";
    cout<<"Index readability:" <<indexReadAbility;
}

inline bool IsVowel(char c) {
    return (c=='a'||c=='e'||c=='i'||c=='o'||c=='u');
}



