package com.example.app.emp.dto

import com.example.app.emp.domain.EmpEntity
import java.time.LocalDateTime

data class EmpDTO (

        var empId : String,
        var empNm : String,
        var empNo : String,
        var email : String

){
    fun createEntity() : EmpEntity {
        return EmpEntity(this.empId, this.empNm, this.empNo, this.email, LocalDateTime.now(), LocalDateTime.now())
    }

}