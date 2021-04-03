package com.example.algorithm.multibranchestree;

import java.io.Serializable;

/**
 *菜单实体
 */
public class Menu implements Serializable {
    private Integer id;
    private String name;

    public Menu(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Menu() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
