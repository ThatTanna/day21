package sg.edu.nus.iss.day22.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day22.model.Room;
import sg.edu.nus.iss.day22.service.RoomService;

@RequestMapping("/api/rooms")
@RestController
public class RoomController {
    
    @Autowired
    RoomService roomService;

    @GetMapping ("/count")
    public Integer getRoomCount() {

        Integer roomCount = roomService.count();
        return roomCount;
    }

    @GetMapping("/")
    public ResponseEntity<List<Room>> retrieveAllrooms() {

        List<Room> rooms = new ArrayList<Room>();
        rooms = roomService.findAll();

        if (rooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> retrieveRoomById(@PathVariable("id") int id) {

        return null;
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> createRoom(@RequestBody Room room) {
        Room rm = room;
        Boolean result = roomService.save(rm);

        System.out.println(result);
        if(result) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    public ResponseEntity<Integer> updateRoom(@RequestBody Room room) {
        Room rm = room;
        int updated = roomService.update(rm);
        System.out.println(updated);

        if (updated == 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteRoom(@PathVariable("id") Integer id) {
        int deleteResult = 0;

        deleteResult = roomService.deleteById(id);

        if (deleteResult == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(1, HttpStatus.OK);
        }
    }

}
