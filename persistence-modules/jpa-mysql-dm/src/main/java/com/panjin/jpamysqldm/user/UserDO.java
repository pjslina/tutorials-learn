package com.panjin.jpamysqldm.user;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author panjin
 */
@Entity
@Table(name="t_user", indexes = {@Index(name="idx_user_name", columnList="user_name")})
@Data
public class UserDO {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name", length=50)
    private String userName;

    @Column(name="email", nullable=false, unique=true)
    private String email;

    @Column(name="age")
    private Integer age;

    @Column(name="salary", precision=10, scale=2)
    private BigDecimal salary;

    @Column(name="birthday")
    private Date birthday;

    @Lob
    @Column(name="profile")
    private String profile;

    @Column(name="created_time")
    private LocalDateTime createdTime;

    @Column(name="updated_time")
    private ZonedDateTime updatedTime;
}
