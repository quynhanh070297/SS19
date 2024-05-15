package rikkei.thymeleafbaitap.dao;

import rikkei.thymeleafbaitap.entity.Employee;

import java.util.List;

public interface IDaoEmployee
{
    public List<Employee> getEmployeeList();

    public Employee getEmployeeById(int id);

    public void addEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public void  deleteEmployee(int id);

}
