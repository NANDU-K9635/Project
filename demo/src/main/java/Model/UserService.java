package Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


   public void addUser1 (UserData userdata ) {

        jdbcTemplate.update(Repository.save(), userdata.getUserId(), userdata.getUsername(), userdata.getAddress(), userdata.getPhoneNumber() , userdata.getCreatedBy() , userdata.getCurrentTimeStamp() , userdata.getModifiedBy() , userdata.getModifiedTimestamp());

       }


    public void deleteUser(String name) {
        //UserData userdata  = new UserData();
        jdbcTemplate.update(Repository.deleteUser() , name);
    }
    public UserData retrieveData(String name) {
       return (UserData) jdbcTemplate.queryForObject(
                Repository.retrievedata(),  new Object[]{name} ,new BeanPropertyRowMapper(UserData.class));

    }
    public List<UserData> RetrieveAllData( ){
        return jdbcTemplate.query(Repository.retrieveAllData(), BeanPropertyRowMapper.newInstance(UserData.class));

    }

}
