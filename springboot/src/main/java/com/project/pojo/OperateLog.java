package com.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    private Integer id;
    private String userId;
    private String role;
    private String ipAddress;
    private String operation;
    private LocalDateTime operationTime;
}
