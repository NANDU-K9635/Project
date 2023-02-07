package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserData {
    private Integer UserId;
    private String Username;
    private String Address;
    private String PhoneNumber;
    private String createdBy;
    private Date createdTimestamp;
    private String modifiedBy;
    private Date modifiedTimestamp;
    public static Date getCurrentTimeStamp() {
        Date today = new Date();
        return new java.sql.Timestamp(today.getTime());

    }
}

