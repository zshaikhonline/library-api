package com.transistorwebservices.libraryapi.user;

import com.transistorwebservices.libraryapi.model.common.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @Column(name = "User_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserId_Generator")
    @SequenceGenerator(name = "UserId_Generator", sequenceName = "user_sequence", allocationSize = 1)
    private int userId;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "Date_Of_Birth")
    private LocalDate dateOfBirth;

    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Email_Id")
    private String emailId;

    @Column(name = "Role")
    private String role;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String firstName,
                      String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String emailId, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.role = role;
    }

    public int getUserId() {
        return userId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
