package com.example.lab2.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Departments {

    @Id
    @Column(nullable=false)
    private int department_id;
    @Column(nullable=false)
    private  String department_name;
    private String manager_id;
    private int location_id;
    @Column(name="department_short_name")
    private String departmentshortname;

    public String getDepartmentshortname() {
        return departmentshortname;
    }

    public void setDepartmentshortname(String departmentshortname) {
        this.departmentshortname = departmentshortname;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }



}
