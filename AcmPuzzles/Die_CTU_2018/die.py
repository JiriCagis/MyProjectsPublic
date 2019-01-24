

def decideNumber(row1,row2,row3):
    
    # number1
    if row1 == ":::" and row2 == ":o:" and row3 == ":::":
        return 1;
   
    # number 2
    if row1 == "::o" and row2 == ":::" and row3 == "o::":
        return 2;
    if row1 == "o::" and row2 == ":::" and row3 == "::o":
        return 2;

    # number3
    if row1 == "o::" and row2 == ":o:" and row3 == "::o":
        return 3;
    if row1 == "::o" and row2 == ":o:" and row3 == "o::":
        return 3;
    
    # number4
    if row1 == "o:o" and row2 == ":::" and row3 == "o:o":
        return 4;
     
    # number5 
    if row1 == "o:o" and row2 == ":o:" and row3 == "o:o":
        return 5;
   
    # number6
    if row1 == "ooo" and row2 == ":::" and row3 == "ooo":
        return 6;
    
    if row1 == "o:o" and row2 == "o:o" and row3 == "o:o":
        return 6;
    
    return "unknown";



if __name__ == "__main__":
    row1 = input();
    row2 = input();
    row3 = input();

    print(decideNumber(row1,row2,row3));
