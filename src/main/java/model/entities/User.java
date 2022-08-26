package model.entities;

import java.util.Objects;

public class User {
    private String fName;
    private String lName;
    private String pw;
    private int phone;
    private String address;

    public User(String fName, String lName, String pw, int phone, String address) {
        this.fName = fName;
        this.lName = lName;
        this.pw = pw;
        this.phone = phone;
        this.address = address;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", pw='" + pw + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return phone == user.phone && fName.equals(user.fName) && lName.equals(user.lName) && pw.equals(user.pw) && address.equals(user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, lName, pw, phone, address);
    }
}

