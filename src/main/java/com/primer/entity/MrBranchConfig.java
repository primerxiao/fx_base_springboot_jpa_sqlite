package com.primer.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author HIFeng
 */
@Data
@Entity
@Table(name = "mr_branch_config")
public class MrBranchConfig {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sourceBranch;

    @Column
    private String targetBranch;

}