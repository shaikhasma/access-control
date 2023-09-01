package com.gotech.accesscontrol.constants;

public final class TestConstants {
    public static final String duplicateTest = "duplicate user is present in database for " +
            "User(userId=null, firstName=Vinay, lastName=Shinde, mobileNumber=8847592130, " +
            "emailId=vs@gmail.com, password=vs@1234, userType=BRANCH_MANAGER)";

    public static final String invalidTest = "invalid user's last name should not contain any special character " +
            "or digits. only alphabets and whitespace is allowed & first letter must be capital only. " +
            "min 1 & max 19 letters allowed. User(userId=null, firstName=Vinay, lastName=Shinde, " +
            "mobileNumber=8847592130, emailId=vs@gmail.com, password=vs@1234, userType=BRANCH_MANAGER)";
}