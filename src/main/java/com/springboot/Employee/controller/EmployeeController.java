package com.springboot.Employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.Employee.entities.Employee;
import com.springboot.Employee.service.AddEmpService;

@Controller
public class EmployeeController {
	
	@Autowired
	private AddEmpService addEmpService; 
	
	@GetMapping(value = "chat1")
	public String chatbot()
	{
		return "chatbot";
	}
	@GetMapping(value = "sendForm")
	public ModelAndView sendForm()
	{
		ModelAndView mView=new ModelAndView();
		mView.addObject("employee",new Employee());
		mView.setViewName("newEmp");
		return mView;
	}
	
	@GetMapping(value = "getEmp")
	public ModelAndView getEmp()
	{
//		List<Employee> l1=this.addEmpService.getEmp();
//		ModelAndView mView=new ModelAndView();
//		mView.addObject("employeeList",l1);
//		mView.setViewName("index");
//		return mView;
		return findPaginated(1);
		
	}
	
	@PostMapping(value = "addEmp")
	public String addEmp(@ModelAttribute("employee") Employee employee) {
	    this.addEmpService.addEmp(employee);
	    return "redirect:/getEmp"; // Correct redirect syntax
	}

	
	@GetMapping(value = "addById/{id}")
	public Employee getById(@PathVariable("id") int id)
	{
		return this.addEmpService.getById(id);
	}
	
	@GetMapping(value = "deleteById/{id}")
	public String deleteById(@PathVariable("id") int id)
	{
		this.addEmpService.deleteEmp(id);
		return "redirect:/getEmp";
	}
	
	@GetMapping(value = "updateEmp/{id}")
	public ModelAndView updateEmp(@PathVariable("id") int id) {
	    Employee employee = this.addEmpService.getById(id);
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("employee", employee);
	    modelAndView.setViewName("update");
	    return modelAndView;
	}

	@PostMapping(value = "updateEmp/{id}")
	public String saveUpdatedEmp(@PathVariable("id") int id, @ModelAttribute Employee employee) {
	    this.addEmpService.updateEmp(id, employee);
	    return "redirect:/getEmp";
	}

	@GetMapping("/page/{pageNo}")
	public ModelAndView findPaginated(@PathVariable("pageNo") int pageNo)
	
	{
		ModelAndView modelAndView=new ModelAndView();
		int pageSize=5;
		Page<Employee> p1=this.addEmpService.findPaginated(pageNo, pageSize);
		List<Employee> l1=p1.getContent();
		modelAndView.addObject("currentPage",pageNo);
		modelAndView.addObject("totalPages",p1.getTotalPages());
		modelAndView.addObject("totalItems",p1.getNumberOfElements());
		modelAndView.addObject("employeeList",l1);
		modelAndView.setViewName("index");
		return modelAndView;
		
		
		
		
		
		
	}
	


}







