package com.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowseLog {
    private Integer id;
    private String userId;
    private Integer productId;
    private Integer browseDuration;
    private LocalDateTime timeStamp;
}
