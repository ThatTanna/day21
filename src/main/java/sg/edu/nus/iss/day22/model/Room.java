package sg.edu.nus.iss.day22.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private int id;
    private String roomType; //roomType -> JDBC room_type
    private int price;
}
