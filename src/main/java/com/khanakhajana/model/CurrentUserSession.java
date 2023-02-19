package com.khanakhajana.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CurrentUserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currentId;
    private String mobile;
    private String uuid;
    private LocalDateTime localDateTime;
    private UserType type;

}
