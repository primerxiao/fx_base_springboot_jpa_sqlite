package com.primer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppConfig {
    @Id
    @Column
    private String configCode;
    @Column
    private String configClass;
    @Column
    private String configValue;
}
