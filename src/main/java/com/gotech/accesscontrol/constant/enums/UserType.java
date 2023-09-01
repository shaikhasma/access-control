package com.gotech.accesscontrol.constant.enums;

public enum UserType {
    ADMIN("ADMIN"),
    ACCOUNTANT("ACCOUNTANT"),
    WAREHOUSE_MANAGER("WAREHOUSE_MANAGER"),
    BRANCH_MANAGER("BRANCH_MANAGER"),
    SUPERVISOR("SUPERVISOR"),
    SALES_EXECUTIVE("SALES_EXECUTIVE"),
    COMPANY_REPRESENTATIVE("COMPANY_REPRESENTATIVE");

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return this.userType;
    }
}
