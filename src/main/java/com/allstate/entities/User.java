package com.allstate.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.util.Date;

@Table(name ="users")
@Entity
@Data
public class User {

    public User(){

    }

    public User(String userName, int balance) {
        this.userName = userName;
        this.balance = balance;
    }

    @Column(unique = true, nullable = false)
    @Size(min = 3)
    private String userName;

    @DecimalMin(value = "0")
    private int balance;

    @Version
    private int version;

    @Id
    @GeneratedValue
    private int id;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;





}
