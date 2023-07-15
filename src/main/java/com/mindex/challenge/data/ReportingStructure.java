package com.mindex.challenge.data;

import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

public class ReportingStructure {

    private Employee employee;
    private AtomicInteger numberOfReports = new AtomicInteger();

    @Autowired
    private EmployeeService employeeService;

    public ReportingStructure() {}
    public ReportingStructure(EmployeeService employeeService, Employee employee) {
        this.employeeService = employeeService;
        this.employee = employee;
        getNumberOfReports(employee);
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getNumberOfReports() {
        return numberOfReports.get();
    }

    private void getNumberOfReports(final Employee emp) {
        // query the number of reports this employee has and increment the count
        // recursively call with the list of direct reports to get the full tree
        if (emp.getDirectReports() != null) {
            emp.getDirectReports().forEach(e -> {
                // get the employee from the db since the emp passed in only has the id
                final Employee employee = employeeService.read(e.getEmployeeId());
                numberOfReports.getAndIncrement();
                getNumberOfReports(employee);
            });
        }
    }
}
