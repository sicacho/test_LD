package com.company.lenderrate.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 10/21/2016.
 */
public class Row {
    String ID;
    String name;
    String PID;
    List<Row> children;

    public Row(String ID, String name, String PID) {
        this.ID = ID;
        this.name = name;
        this.PID = PID;
    }



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public List<Row> getChildren() {
        if(children==null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<Row> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Row row = (Row) o;

        return !(name != null ? !name.equals(row.name) : row.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
