package kazantsev.entity;

public class User {
    private int id;
    private String name;
    private String sureName;
    private int phone;
    private UserRole role;
    private String address;
    private String login;
    private String password;

    public User( String name, String sureName, UserRole role, int phone,String address, String login, String password) {

        this.name = name;
        this.sureName = sureName;
        this.role = role;
        this.phone = phone;
        this.address=address;
        this.login=login;
        this.password=password;
    }

    public String getAddress() {
        return address;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSureName() {
        return sureName;
    }

    public int getPhone() {
        return phone;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sureName='" + sureName + '\'' +
                ", phone=" + phone +
                ", role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}'+'\n';
    }
}
