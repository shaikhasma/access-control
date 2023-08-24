package com.gotech.accesscontrol.model.dto;

import com.sun.istack.NotNull;

public class ChangePasswordRequest {

    @NotNull
    private String emailId;

    @NotNull
    private String contactNo;

    @NotNull
    private String password;

    @NotNull
    private String newPassword;
}
