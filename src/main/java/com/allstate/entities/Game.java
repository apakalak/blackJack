package com.allstate.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.Date;

public class Game {

    @DecimalMin(value = "0")
    private int betAmount;

    @Version
    private int version;

    @Id
    @GeneratedValue
    private int id;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User user;

    @Column(columnDefinition = "ENUM('WON','LOST')")
    private String gameOverStatus;

    private String gameOverAmount;

}
