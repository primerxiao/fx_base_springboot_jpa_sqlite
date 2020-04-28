package com.primer.entity;

import javax.persistence.*;

@Entity//注明是实体类
@Table(name = "demo")//对应表名
public class DemoEntity {
    @Id
    @Column(name = "id")//对应字段名
    private Long id;

    @Column(name = "text")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}