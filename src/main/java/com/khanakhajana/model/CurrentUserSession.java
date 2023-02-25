package com.khanakhajana.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currentId;
    private Integer userId;
    private String mobile;
    private String uuid;
    private LocalDateTime localDateTime;
    private UserType type;

    public CurrentUserSession(Integer userId, String mobile, String uuid, LocalDateTime localDateTime, UserType type) {
        this.userId = userId;
        this.mobile = mobile;
        this.uuid = uuid;
        this.localDateTime = localDateTime;
        this.type = type;
    }
}
