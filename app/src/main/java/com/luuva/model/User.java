package com.luuva.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable{
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private int gender;
    private Timestamp date_of_brith;
    private int role_id;
    private Timestamp date_create;
    private int active;

    public User() {
    }

    public User(int id, String username, String password, String fullname, String email, String phone, String address, int gender, Timestamp date_of_brith, int role_id, Timestamp date_create, int active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.date_of_brith = date_of_brith;
        this.role_id = role_id;
        this.date_create = date_create;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Timestamp getDate_of_brith() {
        return date_of_brith;
    }

    public void setDate_of_brith(Timestamp date_of_brith) {
        this.date_of_brith = date_of_brith;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public Timestamp getDate_create() {
        return date_create;
    }

    public void setDate_create(Timestamp date_create) {
        this.date_create = date_create;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", date_of_brith=" + date_of_brith +
                ", role_id=" + role_id +
                ", date_create=" + date_create +
                ", active=" + active +
                '}';
    }
}
