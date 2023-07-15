package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {

    private String employeeId;
    private double salary;
    private Date effectiveDate;


    public Compensation() {
    }

    public void setEmployee(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployee() {
        return employeeId;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public double getSalary() {
        return this.salary;
    }

    public void setEffectiveDate(Date effectiveDate){
        this.effectiveDate = effectiveDate;
    }
    public Date getEffectiveDate() {
        return effectiveDate;
    }
}
