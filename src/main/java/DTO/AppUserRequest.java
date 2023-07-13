package DTO;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public class AppUserRequest {
    @NotEmpty(message = "First name should not be empty.")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty.")
    private String lastName;
    @NotEmpty(message = "Date of birth should not be empty.")
    private LocalDate dateOfBirth;
    @NotEmpty(message = "Phone number should not be empty.")
    private String phoneNumber;
    @NotEmpty(message = "Email address should not be empty.")
    private String emailAddress;

    public AppUserRequest() {
    }

    public AppUserRequest(String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

