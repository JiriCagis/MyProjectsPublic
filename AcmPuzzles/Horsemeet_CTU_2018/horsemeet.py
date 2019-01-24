

def filter_moves_out_board(coordinates):
    result = [];
    for coordinate in coordinates:
        if coordinate[0] >= 1 and coordinate[0] <= 8:
            if coordinate[1] >= 1 and coordinate[1] <= 8:
                result.append(coordinate);
    return result;

def generate_possible_moves(coordinate):
    result = [];
    
    # $$
    # $
    # $
    result.append((coordinate[0]+1,coordinate[1]+2));
    
    # @@@
    # @
    result.append((coordinate[0]+2,coordinate[1]+1));

    # @@@ 
    #   @
    result.append((coordinate[0]+2,coordinate[1]-1));

    # @@
    #  @
    #  @
    result.append((coordinate[0]+1,coordinate[1]-2));

    # @@
    # @
    # @
    result.append((coordinate[0]-1,coordinate[1]-2));

    # @@@
    # @ 
    result.append((coordinate[0]-2,coordinate[1]-1));

    # @@@
    #   @
    result.append((coordinate[0]-2,coordinate[1]+1));

    # @@
    #  @
    #  @
    result.append((coordinate[0]-1,coordinate[1]+2));

    return filter_moves_out_board(result);



if __name__ == "__main__":
    white_start_coordinate = tuple(map(int,input().split()));
    black_start_coordinate = tuple(map(int,input().split()));

    white_moves = set();
    black_moves = set();

    white_moves.add(white_start_coordinate);
    black_moves.add(black_start_coordinate);

    while True:
        # play white knight
        new_white_moves = [];
        for coordinate in white_moves:
            new_white_moves.extend(generate_possible_moves(coordinate));

        for coordinate in new_white_moves:
            white_moves.add(coordinate);
        
        # check win white knight?
        if white_moves.intersection(black_moves):
            print ("white");
            break;


        # play black knight
        new_black_moves = [];
        for coordinate in black_moves:
            new_black_moves.extend(generate_possible_moves(coordinate));

        for coordinate in new_black_moves:
            black_moves.add(coordinate);
        
        # check win black knight
        if black_moves.intersection(white_moves):
            print ("black");
            break;

