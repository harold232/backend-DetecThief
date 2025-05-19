package com.group2.detecthief.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String nickname;
    private String email;
    private String nombre;
    private String apellido;
    private String rol;
    private String estadoSistema;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public void update(String nickname, String email, String nombre, String apellido) {
        if (nickname != null) this.nickname = nickname;
        if (email != null) this.email = email;
        if (nombre != null) this.nombre = nombre;
        if (apellido != null) this.apellido = apellido;
        this.actualizadoEn = LocalDateTime.now();
    }

    public void activate() {
        this.estadoSistema = "activo";
        this.actualizadoEn = LocalDateTime.now();
    }

    public void deactivate() {
        this.estadoSistema = "inactivo";
        this.actualizadoEn = LocalDateTime.now();
    }

    public void updateRol(String rol) {
        this.rol = rol;
        this.actualizadoEn = LocalDateTime.now();
    }
}