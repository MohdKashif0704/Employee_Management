package com.springboot.Employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Employee.entities.Employee;

@Repository
public interface AddEmplyee extends JpaRepository<Employee, Integer> {

}
