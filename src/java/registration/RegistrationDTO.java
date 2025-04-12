/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

/**
 *
 * @author beu29
 */
public class RegistrationDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean role;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, String firstName, String lastName, boolean role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" + "username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + '}';
    }

}
