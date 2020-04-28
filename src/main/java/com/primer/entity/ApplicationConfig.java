package com.primer.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.soap.Text;

@Data
@Entity
@Table(name = "application_config")
public class ApplicationConfig {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "git_account")
  private String gitAccount;
  @Column(name = "git_password")
  private String gitPassword;
  @Column(name = "mr_title")
  private String mrTitle;
  @Column(name = "mr_desc")
  private String mrDesc;
  @Column(name = "mr_assignee")
  private Integer mrAssignee;
  @Column(name = "mr_milestone")
  private Integer mrMilestone;
  @Column(name = "project_select")
  private String projectSelect;

}
