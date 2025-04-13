/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.io.Serializable;

/**
 *
 * @author beu29
 */
public class RegistrationCreateError implements Serializable {

    private String usernameIsExisted;
    private String usernameLengthViolent;
    private String passwordViolent;
    private String firstNameLengthViolent;
    private String middleNameLengthViolent;
    private String lastNameLengthViolent;
    private String confirmNotMatch;

    public RegistrationCreateError() {
    }

    public RegistrationCreateError(String usernameIsExisted, String usernameLengthViolent, String passwordViolent, String firstNameLengthViolent, String middleNameLengthViolent, String lastNameLengthViolent, String confirmNotMatch) {
        this.usernameIsExisted = usernameIsExisted;
        this.usernameLengthViolent = usernameLengthViolent;
        this.passwordViolent = passwordViolent;
        this.firstNameLengthViolent = firstNameLengthViolent;
        this.middleNameLengthViolent = middleNameLengthViolent;
        this.lastNameLengthViolent = lastNameLengthViolent;
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getUsernameLengthViolent() {
        return usernameLengthViolent;
    }

    public void setUsernameLengthViolent(String usernameLengthViolent) {
        this.usernameLengthViolent = usernameLengthViolent;
    }

    public String getPasswordViolent() {
        return passwordViolent;
    }

    public void setPasswordViolent(String passwordViolent) {
        this.passwordViolent = passwordViolent;
    }

    public String getFirstNameLengthViolent() {
        return firstNameLengthViolent;
    }

    public void setFirstNameLengthViolent(String firstNameLengthViolent) {
        this.firstNameLengthViolent = firstNameLengthViolent;
    }

    public String getMiddleNameLengthViolent() {
        return middleNameLengthViolent;
    }

    public void setMiddleNameLengthViolent(String middleNameLengthViolent) {
        this.middleNameLengthViolent = middleNameLengthViolent;
    }

    public String getLastNameLengthViolent() {
        return lastNameLengthViolent;
    }

    public void setLastNameLengthViolent(String lastNameLengthViolent) {
        this.lastNameLengthViolent = lastNameLengthViolent;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    @Override
    public String toString() {
        return "RegistrationCreateError{" + "usernameIsExisted=" + usernameIsExisted + ", usernameLengthViolent=" + usernameLengthViolent + ", passwordViolent=" + passwordViolent + ", firstNameLengthViolent=" + firstNameLengthViolent + ", middleNameLengthViolent=" + middleNameLengthViolent + ", lastNameLengthViolent=" + lastNameLengthViolent + ", confirmNotMatch=" + confirmNotMatch + '}';
    }

}
