package com.spring_boot.learning.expenses.beans;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Date dateOfBirth;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
    }

    
}
