package com.gotech.accesscontrol.constant;

public enum UserType {    // types of user
  ADMIN("Admin"),
  ACCOUNTANT("Accountant"),
  WAREHOUSE_MANAGER("Warehouse Manager"),
  BRANCH_MANAGER("Branch Manager"),
  SUPERVISOR("Supervisor"),
  SALES_EXECUTIVE("Sales Executive"),
  COMPANY_REPRESENTATIVE("Company Representative");

  private final String userType;
  private UserType(String userType){
    this.userType=userType;
  }

  public String getUserType(){   // returns type of user
    return this.userType;
  }
}
