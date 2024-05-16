package com.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginLog {
    private String role;
    private String ipAddress;
    private LocalDateTime loginTime;
    private LocalDateTime exitTime;
}
