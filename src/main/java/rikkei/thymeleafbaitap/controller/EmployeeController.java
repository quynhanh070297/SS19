package rikkei.thymeleafbaitap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.thymeleafbaitap.dao.EmployeeDao;
import rikkei.thymeleafbaitap.entity.Employee;

import java.util.List;

@Controller
public class EmployeeController
{
    @Autowired
    private EmployeeDao employeeDao;
    @RequestMapping( value = {"/","/Employee"})
    public  String home(Model model){
        List<Employee> list = employeeDao.getEmployeeList();
        model.addAttribute("employeeList", list);
        return "Employee";
    }

    @RequestMapping("/add")
    public String addEmployee(Model model)
    {
        model.addAttribute("employee", new Employee());
        return "add";
    }
    @PostMapping("/addAfter")
    public String saveEmployee(@ModelAttribute ("employee") Employee employee){
        Employee employee1 = employee;
        employeeDao.addEmployee(employee1);
        return "redirect:/Employee";

    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("deleteId") int id)
    {
        employeeDao.deleteEmployee(id);
        return "redirect:/Employee";
    }

    @GetMapping("/Edit")
    public String editEmployee(@RequestParam("editId") int id,Model model)
    {
     model.addAttribute("employee", employeeDao.getEmployeeById(id));
        return "Edit";
    }

    @PostMapping("/Update")
    public String updateEmployee(@ModelAttribute("Employee") Employee employee, Model model){
        employeeDao.updateEmployee(employee);
        return "redirect:/Employee";
    }



}
