package com.softwarica.employeeapi.API;

import com.softwarica.employeeapi.model.Employee;
import com.softwarica.employeeapi.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeAPI {

    //Get all the employees
    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    //Get employee on basis of EmpID
    @GET("employee/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empId);

    //create employee
    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);


}
