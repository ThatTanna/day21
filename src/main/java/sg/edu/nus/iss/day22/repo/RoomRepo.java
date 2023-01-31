package sg.edu.nus.iss.day22.repo;

import java.util.List;

import sg.edu.nus.iss.day22.model.Room;

public interface RoomRepo {
    Integer count();

    // Create
    Boolean save(Room room);

    // Read All
    List<Room> findAll();

    // Read one record
    Room findById(Integer id);

    // Update
    Integer update(Room room);

    // Delete
    Integer deleteById(Integer id);
    
}
