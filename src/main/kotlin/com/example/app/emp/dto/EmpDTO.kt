package com.example.app.emp.dto

import com.example.app.emp.domain.EmpEntity
import java.time.LocalDateTime

data class EmpDTO (

        var empId : String,
        var empNm : String,
        var empNo : String,
        var email : String

){
    fun create() : EmpEntity {
        return EmpEntity(this.empId, this.empNm, this.empNo, this.email, LocalDateTime.now(), LocalDateTime.now())
    }

    fun modify(regYmdt : LocalDateTime) : EmpEntity{
        return EmpEntity(this.empId, this.empNm, this.empNo, this.email, regYmdt, LocalDateTime.now())
    }
}