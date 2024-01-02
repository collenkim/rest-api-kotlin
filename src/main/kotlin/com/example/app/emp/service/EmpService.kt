package com.example.app.emp.service

import com.example.app.emp.domain.EmpEntity
import com.example.app.emp.dto.EmpDTO
import com.example.app.emp.repository.EmpRepository
import org.springframework.stereotype.Service

@Service
class EmpService (private val empRepository : EmpRepository) {

    //목록 전체 조회
    fun getAllList(): List<EmpEntity> {
        return empRepository.findAll()
    }

    //단건 조회
    fun getOne(empId: String): EmpEntity? {
        return empRepository.findByEmpId(empId)
    }

    //등록
    fun add(emp: EmpEntity): EmpEntity {
        return empRepository.save(emp)
    }

    //수정
    fun modify(emp: EmpDTO): EmpEntity? {
        val empEntity = empRepository.findByEmpId(emp.empId);
        if (empEntity != null) {
            val modifyEmp = emp.modify(empEntity.regYmdt)
            return empRepository.save(modifyEmp)
        }
        return null
    }

    //삭제
    fun delete(empId: String) {
        val empEntity = empRepository.findByEmpId(empId);
        if(empEntity != null)
        empRepository.deleteByEmpId(empId)
    }

}