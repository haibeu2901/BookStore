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
public class RegistrationUpdateError {

    private String passwordViolent;

    public RegistrationUpdateError() {
    }

    public RegistrationUpdateError(String passwordViolent) {
        this.passwordViolent = passwordViolent;
    }

    public String getPasswordViolent() {
        return passwordViolent;
    }

    public void setPasswordViolent(String passwordViolent) {
        this.passwordViolent = passwordViolent;
    }

    @Override
    public String toString() {
        return "RegistrationUpdateError{" + "passwordViolent=" + passwordViolent + '}';
    }

}
