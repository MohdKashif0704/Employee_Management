package com.springboot.Employee.service;

import java.util.List;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.aspectj.weaver.ast.Literal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.springboot.Employee.dao.AddEmplyee;
import com.springboot.Employee.entities.Employee;

@Service
public class AddEmpService {
	
	@Autowired
	private AddEmplyee addEmplyee;
	
	// add the employee information 
	public void addEmp(Employee employee)
	{
		this.addEmplyee.save(employee);
	}
	
	// get the employee information
	public List<Employee> getEmp()
	{
		List<Employee> l1=(List<Employee>)this.addEmplyee.findAll();
		return l1;
	}
	
	// delete Employee
	
	public void deleteEmp(int id)
	{
		this.addEmplyee.deleteById(id);
	}
	
	// get by Id
	public Employee  getById(int id)
	{
		Optional<Employee> l1=this.addEmplyee.findById(id);
		if(l1.isPresent())
		{
			return l1.get();
		}
		else {
			throw new RuntimeException("Employee with"+id+"is not found");
		}

	}
	
	public void updateEmp(int id,Employee employee)
	{
		Employee employee2=this.getById(id);
		employee2.setFirstName(employee.getFirstName());
		employee2.setLastName(employee.getLastName());
		employee2.setEmail(employee.getEmail());
		
		this.addEmplyee.save(employee2);
		
	}
	
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
	    // Convert 1-based index to 0-based index
	    PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
	    return this.addEmplyee.findAll(pageable);
	}

	

}
