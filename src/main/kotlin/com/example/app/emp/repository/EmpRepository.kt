package com.example.app.emp.repository

import com.example.app.emp.domain.EmpEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EmpRepository : JpaRepository<EmpEntity, String> {

    fun findByEmpId(empId : String) : EmpEntity?

    fun deleteByEmpId(empId : String) : EmpEntity?
}