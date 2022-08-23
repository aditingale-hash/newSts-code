package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Department;
import com.springboot.model.Employee;
import com.springboot.repository.DepartmentRepository;
import com.springboot.repository.EmployeeRepository;


@RestController
public class EmployeeController {
	
@Autowired
private EmployeeRepository employeerepository;


@Autowired
private DepartmentRepository departmentrepository;

/*
 * insert data in employee insertEmployee
 * 
 * */

@PostMapping("/employee/{did}")
public Employee insertEmployee(@RequestBody Employee employee,@PathVariable("did") Long did) {
	Department d=departmentrepository.getById(did);
	employee.setDepartment(d);
	return employeerepository.save(employee);
	
}
/*
 *get all employee
 * 
 * */


  @GetMapping("/employee") public List<Employee> getAllEmployee(
  
  @RequestParam(name="page",required = false,defaultValue = "0") Integer page,
  
  @RequestParam(name="size",required = false,defaultValue = "2") Integer size){
  Pageable pageable=PageRequest.of(page, size); 
  return employeerepository.findAll(pageable).getContent();
  
  
  
  }
	/*
	 * @GetMapping("/employee") public List<Employee> getAllemployee() {
	 * 
	 * return employeerepository.findAll();
	 * 
	 * 
	 * }
	 *//*
 *get employee by id
 * 
 * */

@GetMapping("/employee/{id}")
public Employee getemployee(@PathVariable("id") long id){	
	
	Employee emp=employeerepository.getById(id);
	return emp;
}	
/*
 * update exisiting employee
 * 
 * */
@PutMapping("/employee/{id}")
public Employee updateEmployee(@PathVariable("id") Long id,@RequestBody Employee employeeNew) {
	
	Employee empDB=employeerepository.getById(id);
	
	if(employeeNew.getName()!=null)
		empDB.setName(employeeNew.getName());
	if(employeeNew.getAge()!= 0)
		empDB.setAge(employeeNew.getAge());
	if(employeeNew.getCity()!=null)
		empDB.setCity(employeeNew.getCity());
	if(employeeNew.getSalary()!=0)
		empDB.setSalary(employeeNew.getSalary());
	if(employeeNew.getEmail()!=null)
		empDB.setEmail(employeeNew.getEmail());
	
	return employeerepository.save( empDB);
}

@DeleteMapping("/employee/{id}")	
public void deleteEmployee(@PathVariable("id") Long id) {
	employeerepository.deleteById(id);

}


//fetch list of employee having salary greater than 70000
@GetMapping("/employee/salary")
public List<Employee> getEmployeeBysalary(@RequestParam("salary") double salary) {
	List<Employee> list= employeerepository.findBySalaryGreaterThan(salary);
	return list;
	
}

@GetMapping("/employee/city")
public List<Employee> getEmployeeBycity(@RequestParam("city") String city) {
	List<Employee> list= employeerepository.findByCity(city);
	return list;
	
}

///fectch list of employee having age greater than 19
@GetMapping("/employee/age")
public List<Employee> getEmployeeByAge(@RequestParam("age") int age) {
	
	List<Employee>list=employeerepository.findByAgeGreaterThan(age);
	return list;
}

@GetMapping("/employee/department/{did}")
public List<Employee> getEmployeeByDepartment(@PathVariable("did") Long did) {
	
	List<Employee>list=employeerepository.findByDepartmentId(did);
	return list;
}

}
/*
 * fetch:getmapping
 *insert:post
 *update:put
 *delete:delete 
 */
