package com.mobtecnica.wafiapps.model.signup;

/**
 * Created by SIby on 10-01-2017.
 */

public class SignUpRequest {
    String apiToken;
    String FirstName;
    String LastName;
    String Email;
    String Username;
    String Password;
    String Phone;
    int CountryID;
    String Gender;
    String DateOfBirthDay;
    String DateOfBirthMonth;
    String DateOfBirthYear;
    boolean newsletter = true;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }


    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getCountryID() {
        return CountryID;
    }

    public void setCountryID(int countryID) {
        CountryID = countryID;
    }

    public String getDateOfBirthDay() {
        return DateOfBirthDay;
    }

    public void setDateOfBirthDay(String dateOfBirthDay) {
        DateOfBirthDay = dateOfBirthDay;
    }

    public String getDateOfBirthMonth() {
        return DateOfBirthMonth;
    }

    public void setDateOfBirthMonth(String dateOfBirthMonth) {
        DateOfBirthMonth = dateOfBirthMonth;
    }

    public String getDateOfBirthYear() {
        return DateOfBirthYear;
    }

    public void setDateOfBirthYear(String dateOfBirthYear) {
        DateOfBirthYear = dateOfBirthYear;
    }
}
