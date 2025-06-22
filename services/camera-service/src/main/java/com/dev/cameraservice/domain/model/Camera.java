package com.dev.cameraservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Camera {
    private Integer id;
    private String name;
    private String description;
    private String location;
    private String urlStream;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
