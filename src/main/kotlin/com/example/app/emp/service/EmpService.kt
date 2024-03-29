package com.example.app.emp.service

import com.example.app.emp.domain.EmpEntity
import com.example.app.emp.dto.EmpDTO
import com.example.app.emp.repository.EmpRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

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

    @Transactional
    //등록
    fun add(emp: EmpDTO): EmpEntity {
        val addEmp = emp.createEntity()
        return empRepository.save(addEmp)
    }

    @Transactional
    //수정
    fun modify(emp: EmpDTO): EmpEntity? {
        val empEntity = empRepository.findByEmpId(emp.empId);
        if (empEntity != null) {
            empEntity.empNm = emp.empNm
            empEntity.empNo = emp.empNo
            empEntity.email = emp.email
            empEntity.modYmdt = LocalDateTime.now()
            return empEntity
        }
        return null
    }

    @Transactional
    //삭제
    fun delete(empId: String) {
        val empEntity = empRepository.findByEmpId(empId);
        if(empEntity != null)
        empRepository.delete(empEntity)
    }

}