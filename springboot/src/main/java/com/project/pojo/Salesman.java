package com.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salesman {
    private Integer id;
    private String username;
    private String password;
    private Integer type;

    public Salesman(String password, Integer type) {
        this.password = password;
        this.type = type;
    }
}
