package kr.osci.user.source.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_info")
public class SourceUserInfoEntity {

    @Id
    private Long empNo;

    private String firstName;

    private String lastName;

    private String deptCode;

    private String deptName;

    private String posName;

    private String emailAddress;

    private Boolean isLeader;

    private Boolean isActive;

    private Boolean isUpdated;

    public Long getEmpNo() {
        return empNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getPosName() {
        return posName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Boolean getLeader() {
        return isLeader;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Boolean getUpdated() {
        return isUpdated;
    }
}
