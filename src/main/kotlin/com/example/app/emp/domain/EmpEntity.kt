package com.example.app.emp.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class EmpEntity (

    @Id
    @Column(name = "emp_id", nullable =false)
    val empId: String,

    @Column(name = "emp_nm", nullable =false)
    val empNm: String,

    @Column(name = "emp_no", nullable =false)
    val empNo: String,

    @Column(name = "email", nullable =false)
    val email: String,

    @Column(name = "reg_ymdt", nullable =false)
    val regYmdt : LocalDateTime,

    @Column(name = "mod_ymdt", nullable =false)
    val modYmdt : LocalDateTime
)