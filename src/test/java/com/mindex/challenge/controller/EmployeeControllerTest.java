package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @Test
    public void testReportingStructure() {
        Employee employee = employeeController.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(employee);
        ReportingStructure reportingStructure = employeeController.getReportingStructure(employee.getEmployeeId());
        assertNotNull(reportingStructure);
        assertEquals(4, reportingStructure.getNumberOfReports());
    }

    @Test
    public void testCompensation() {
        final String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        List<Compensation> compList;
        Compensation comp;

        Employee employee = employeeController.read(employeeId);
        assertNotNull(employee);

        try {
            compList = employeeController.getCompensation(employeeId);
            fail("Should have thrown exception for invalid id since no compensations exist for this employee yet");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        comp = new Compensation();
        comp.setEmployee(employeeId);
        comp.setSalary(100.00);
        comp.setEffectiveDate(now());
        employeeController.create(comp);

        compList = employeeController.getCompensation(employeeId);
        assertEquals(1, compList.size());

        comp = new Compensation();
        comp.setEmployee(employeeId);
        comp.setSalary(200.00);
        comp.setEffectiveDate(now());
        employeeController.create(comp);

        compList = employeeController.getCompensation(employeeId);
        assertEquals(2, compList.size());
    }
}
