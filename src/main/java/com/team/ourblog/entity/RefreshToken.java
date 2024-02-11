package com.team.ourblog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
@AllArgsConstructor
@Entity
public class RefreshToken {

    protected RefreshToken(){}

    @Id
    @Column(name = "rt_key")
    private String key;

    @Column(name = "rt_value")
    private String value;


    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }
}
