package com.gotech.accesscontrol.constant;

public final class Constants {
    public static final String invalidFirstName =
            "user's first name should not contain any special character or digits. "
                    + "only alphabets and whitespace is allowed & first letter should be capital only. "
                    + "min 1 & max 19 letters allowed. ";
    public static final String invalidLastName =
            "user's last name should not contain any special character or digits. "
                    + "only alphabets and whitespace is allowed & first letter must be capital only. "
                    + "min 1 & max 19 letters allowed. ";
    public static final String invalidContactNo =
            "user's contact number can contain min 10 max 14 digits including + / whitespace / - "
                    + "can start with 0 / +91 / any valid indian mobile number. ";
    public static final String invalidEmailId =
            "user's email id format must be name + @ + domainName. ";
    public static final String invalidPassword =
            "user's password must contain Minimum eight characters "
                    + "at least one letter, one number and one special character. ";
    public static final String passwordMismatch =
            "user's password and confirm password must be exact same. ";
    public static final String userNotFound = "user not found for provided details. ";
    public static final String userAlreadyExist =
            "user is already exist in database for provided details. ";
    public static final String invalidUser =
            "every field of user should be valid. should not be empty / blank / null. ";
    public static final String firstName = "first name ";
    public static final String lastName = "last name ";
    public static final String contactNumber = "contact number ";
    public static final String emailId = "email id ";
    public static final String password = "password ";
    public static final String validMessage = "User is valid ";
    public static final String valid = " valid";
    public static final String duplicate = "duplicate user is present in database for ";
    public static final String notDuplicate = " no duplicate user found ";
    public static final String empty = "empty";
    public static final String notEmpty = "not empty";
    public static final String invalid = "invalid ";
    public static final String for_ = "for : ";
    public static final String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static final String mobileNoPattern = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$";
    public static final String namePattern = "^[a-zA-Z](?=.{0,19}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
    public static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
}
