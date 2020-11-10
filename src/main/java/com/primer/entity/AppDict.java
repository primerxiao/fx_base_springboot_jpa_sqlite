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
 * 程序涉及的字典表
 *
 * @author HIFeng
 */
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppDict {
    @Id
    @Column
    private String dictCode;
    @Column
    private String dictName;
    @Column
    private String groupCode;
    @Column
    private String groupName;
    @Column
    private long orderId;
    @Column
    private long status;

}
