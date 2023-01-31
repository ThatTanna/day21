package sg.edu.nus.iss.day22.repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day22.model.Room;

@Repository
public class RoomRepoImpl implements RoomRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    String countSQL = "SELECT count(*) from room";
    String selectSQL = "SELECT * from room";
    String selectByIdSQL = "SELECT * from room where id = ?";
    String insertSQL = "INSERT into room (room_type, price) values (?, ?)";
    String updateSQL = "UPDATE room set room_type = ?, price = ? where id = ?";
    String deleteSQL = "DELETE from room where id = ?";

    @Override
    public Integer count() {
        Integer result = 0;
        result = jdbcTemplate.queryForObject(countSQL, Integer.class);
        if (result == null) {
            return 0;
        } else {
            return result;
        }

    }

    @Override
    public Boolean save(Room room) {
        Boolean saved = false;

        // "INSERT into room (room_type, price) values (?, ?)";
        // "INSERT into room (room_type, price) values ('deluxe', 500)";
        saved = jdbcTemplate.execute(insertSQL, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, room.getRoomType());
                ps.setInt(2, room.getPrice());
                Boolean result = ps.execute();
                return result;

            }
        });
        return saved;
    }

    @Override
    public List<Room> findAll() {
        List<Room> resultList = new ArrayList<Room>();
        resultList =  jdbcTemplate.query(selectSQL, BeanPropertyRowMapper.newInstance(Room.class));

        return resultList;
    }

    @Override
    public Room findById(Integer id) {
        // String selectByIdSQL = "SELECT * from room where id = ?";
        return jdbcTemplate.queryForObject(selectByIdSQL, BeanPropertyRowMapper.newInstance(Room.class), id);
    }

    @Override
    public Integer update(Room room) {
        int updated = 0;

        // String updateSQL = "UPDATE room set room_type = ?, price = ? where id = ?";
        jdbcTemplate.update(updateSQL, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, room.getRoomType());
                ps.setInt(2, room.getPrice());
                ps.setInt(3, room.getId());
            }
        });
        return updated;
    }

    @Override
    public Integer deleteById(Integer id) {
        int deleted = 0;

        PreparedStatementSetter pss = new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                // String deleteSQL = "DELETE from room where id = ?";
                ps.setInt(1, id);
            }
            
        };

        deleted = jdbcTemplate.update(deleteSQL, pss);


        return deleted;
    }

}
