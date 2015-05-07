package com.example.nguyen.ormlitestudent;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by nguyen on 01/05/2015.
 */
public class Student {

    @DatabaseField(generatedId = true)
    public int ID;

    @DatabaseField
    public String firstName;

    @DatabaseField
    public String lastName;

    @DatabaseField
    public String address;

    public Student(){
    }

    public Student(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName(){
        return firstName;

    }
}
