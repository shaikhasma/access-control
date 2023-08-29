package com.gotech.accesscontrol.constant;

public enum UserType {
  ADMIN("ADMIN"),
  ACCOUNTANT("ACCOUNTANT"),
  WAREHOUSE_MANAGER("WAREHOUSE_MANAGER"),
  BRANCH_MANAGER("BRANCH_MANAGER"),
  SUPERVISOR("SUPERVISOR"),
  SALES_EXECUTIVE("SALES_EXECUTIVE"),
  COMPANY_REPRESENTATIVE("COMPANY_REPRESENTATIVE");

  private final String userType;
  private UserType(String userType){
    this.userType=userType;
  }

  public String getUserType(){   // returns type of user
    return this.userType;
  }
}
