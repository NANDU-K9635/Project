package Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class Repository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static java.sql.Timestamp getCurrentTimeStamp() {
        Date today = new Date();
        return new java.sql.Timestamp(today.getTime());

    }
    public static String save() {

       // UserData userdata = new UserData();
        String SQL = "INSERT INTO userdata(UserId ,  Username , Address , PhoneNumber , createdBy , createdTimestamp , modifiedBy , modifiedTimestamp) VALUES(?,?,?,?,?,?,?,?)";
        return SQL;
    }

    public static String deleteUser() {
       // UserData userdata  = new UserData();
        String SQL = "DELETE FROM userdata WHERE Username=?";
        return SQL;
    }
    public static String retrievedata() {
        String sql = "SELECT * from userdata WHERE Username=?";
        return sql;
    }
    public static String retrieveAllData() {
        String sql = "SELECT * from userdata ";
        return sql;
    }
    public static String auditInsert() {
        String sql = "INSERT INTO auditfields(createdBy, createdTimestamp, modifiedBy, modifiedTimestamp) VALUES(?,?,?,?)";
        return sql;
    }

}
