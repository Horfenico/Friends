package com.example.eric.friends;

/**
 * Created by Eric on 3/24/2015.
 */
public class Friends {
    private int _id;
    private String _name;
    private String _email;
    private String _phoneno;

    public Friends() {

    }

    public Friends(int id, String name, String email, String phoneno) {
        this._id = id;
        this._name = name;
        this._email = email;
        this._phoneno = phoneno;
    }

    public Friends(String name, String email, String phoneno) {
        this._name = name;
        this._email = email;
        this._phoneno = phoneno;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getEmail() {
        return this._email;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    public String getPhoneNo() {
        return this._phoneno;
    }

    public void setPhoneNo(String phoneno) {
        this._phoneno = phoneno;
    }
}
