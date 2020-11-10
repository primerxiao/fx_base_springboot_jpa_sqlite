package com.primer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 程序涉及到使用其它数据源的配置
 *
 * @author HIFeng
 */
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbConfig {
    @Id
    @Column
    private String name;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String driverClassName;
    @Column
    private String url;
}
