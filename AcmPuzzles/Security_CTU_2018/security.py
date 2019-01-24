def generate_possible_moves(coordinate):
    result = [];
    result.append((coordinate[0]+0,coordinate[1]+1));
    result.append((coordinate[0]+1,coordinate[1]+1));
    result.append((coordinate[0]+1,coordinate[1]+0));
    result.append((coordinate[0]+1,coordinate[1]-1));
    result.append((coordinate[0]+0,coordinate[1]-1));
    result.append((coordinate[0]-1,coordinate[1]-1));
    result.append((coordinate[0]-1,coordinate[1]+0));
    result.append((coordinate[0]-1,coordinate[1]+1));
    return result;



if __name__ == "__main__":
    base_attributes = list(map(int,input().split()));
    count_securitas = base_attributes[0];
    count_incidents = base_attributes[1];

    securitas = set();
    incidents = [];
    for i in range(0,count_securitas):
        security = tuple(map(int,input().split()));
        securitas.add(security);

    for i in range(0,count_incidents):
        incident = tuple(map(int,input().split()));
        incidents.append(incident);
    
    for incident in incidents:
        incident_range = set();
        incident_range.add(incident);
        count_steps = 0;

        while True:
            if securitas.intersection(incident_range):
                print(count_steps);
                break;
            else:
                new_incident_range = [];
                for coordinate in incident_range:
                    new_incident_range.extend(generate_possible_moves(coordinate));
                
                for coordinate in new_incident_range:
                    incident_range.add(coordinate);

                count_steps = count_steps + 1;