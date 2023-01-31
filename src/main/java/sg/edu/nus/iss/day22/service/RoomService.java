package sg.edu.nus.iss.day22.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day22.model.Room;
import sg.edu.nus.iss.day22.repo.RoomRepo;

@Service
public class RoomService {
    
    @Autowired
    RoomRepo roomRepo;

    public Integer count() {
        return roomRepo.count();
    }

    public Boolean save(Room room) {
        return roomRepo.save(room);
    }

    public List<Room> findAll() {
        return roomRepo.findAll();
    }

    public Room findById(int id) {
        return roomRepo.findById(id);
    }

    public int update(Room room) {
        return roomRepo.update(room);
    }

    public int deleteById(int id) {
        return roomRepo.deleteById(id);
    }

    
}
