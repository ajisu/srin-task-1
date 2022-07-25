package com.srin.task.one.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "phone_book")
@Table(name = "phone_book")
public class PhoneBook {
    @Id
    @Column(name = "phone_book_id", columnDefinition = "uniqueidentifier", length = 16, nullable = false, unique = true)
    private String phoneBookId;
    @Column(name = "first_name", length = 255)
    private String firstName;
    @Column(name = "last_name", length = 255)
    private String lastName;
    @Column(name = "phone_number", columnDefinition = "int")
    private int phoneNumber;

    public String getPhoneBookId() {
        return phoneBookId;
    }
    public void setPhoneBookId(String phoneBookId) {
        this.phoneBookId = phoneBookId;
    }
    public String getFirstName() {
        return firstName;
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
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "PhoneBook [firstName=" + firstName + ", lastName=" + lastName + ", phoneBookId=" + phoneBookId
                + ", phoneNumber=" + phoneNumber + "]";
    }
}
