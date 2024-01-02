package com.example.app.emp.api

import com.example.app.emp.domain.EmpEntity
import com.example.app.emp.dto.EmpDTO
import com.example.app.emp.service.EmpService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/emp")
class EmpApi (private val empService : EmpService){

    @GetMapping("/getAllList")
    fun getAllList(): List<EmpEntity> {
        return empService.getAllList()
    }


    @GetMapping("/{empId}")
    fun getOne(@PathVariable empId: String): ResponseEntity<EmpEntity> {
        val getEmp = empService.getOne(empId)
        return if (getEmp != null) {
            ResponseEntity(getEmp, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun add(@RequestBody emp : EmpDTO) : ResponseEntity<EmpEntity>{
        val addEmp = empService.add(emp)
        return ResponseEntity(addEmp, HttpStatus.CREATED)
    }

    @PutMapping("/{empId}")
    fun modify(
            @PathVariable empId: String,
            @RequestBody emp: EmpDTO) : ResponseEntity<EmpEntity>{


        val modifyEmp = empService.modify(emp)
        return if (modifyEmp != null) {
            ResponseEntity(modifyEmp, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{empId}")
    fun delete(
            @PathVariable empId: String): ResponseEntity<String> {
        empService.delete(empId)
        return ResponseEntity("Employee deleted successfully", HttpStatus.OK)
    }
}