/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author oussa
 */
public class User {
    private ImageView photo;
    private int id;
    private String firstname;
    private String lastname;
    private String password;

    public ImageView getPhoto() {
        return photo;
    }
    private String role;

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
    private String username;
    private String email;
    private String gender;
    private String address;
    private String nationality;
    private int phone ;
    private java.sql.Date created_at ;
    private String image ;

    public void setImage(String image) {
        this.image = image;
    }

    public User(String firstname, String lastname, String role, String username, String email, String gender,String address,String nationality,int phone,java.sql.Date created_at,String image,ImageView photo) {
        this.address=address;
        this.phone=phone;
        this.nationality=nationality;
        this.created_at=created_at;
        
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.image=image;
        this.photo=photo;
    }

    public String getImage() {
        return image;
    }

    public User() {
     
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getAddress() {
        return address;
    }

    public String getNationality() {
        return nationality;
    }

    public int getPhone() {
        return phone;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password + ", role=" + role + ", username=" + username + ", email=" + email + ", gender=" + gender + '}';
    }

}
