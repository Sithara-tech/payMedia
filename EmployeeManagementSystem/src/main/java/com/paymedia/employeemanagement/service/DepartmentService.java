package com.paymedia.employeemanagement.service;

import com.paymedia.employeemanagement.exception.EntityNotFoundException;
import com.paymedia.employeemanagement.model.Department;
import com.paymedia.employeemanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with ID: " + id));
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        Department existingDepartment = getDepartmentById(id);
        existingDepartment.setName(updatedDepartment.getName());
        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {
        Department existingDepartment = getDepartmentById(id);
        departmentRepository.delete(existingDepartment);
    }

    public Page<Department> getAllDepartments(Pageable pageable) {

        return departmentRepository.findAll(pageable);
    }

    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with name: " + name));
    }
}
