package com.example.app.emp.api

import com.example.app.emp.dto.EmpDTO
import com.example.app.emp.service.EmpService
import com.google.gson.Gson
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class EmpApiTest (

    @Autowired
    private var mockMvc: MockMvc,

    @Autowired
    private var gson : Gson,

    @Autowired
    private var empService : EmpService

){

    @Test
    fun getAllListTest(){

        //when
        addEmp()

        val result = mockMvc.perform(
                get("/api/emp/getAllList")
        )
        //then
        result.andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
    }

    @Test
    fun getEmpTest(){

        val addEmp = getAddEmpDTO()
        val url = "/api/emp/" + "collenkim"

        //when
        addEmp()

        val result = mockMvc.perform(
                get(url)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )

        //then
        result.andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empId", equalTo(addEmp.empId)))
                .andExpect(jsonPath("$.empNm", equalTo(addEmp.empNm)))
                .andExpect(jsonPath("$.empNo", equalTo(addEmp.empNo)))
                .andExpect(jsonPath("$.email", equalTo(addEmp.email)))
                .andDo(print())
    }

    @Test
    fun addEmpTest(){

        val addEmp = getAddEmpDTO()

        //when
        val result = mockMvc.perform(
                post("/api/emp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(addEmp))
        )
        //then
        result.andExpect(status().isCreated)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empId", equalTo(addEmp.empId)))
                .andExpect(jsonPath("$.empNm", equalTo(addEmp.empNm)))
                .andExpect(jsonPath("$.empNo", equalTo(addEmp.empNo)))
                .andExpect(jsonPath("$.email", equalTo(addEmp.email)))
                .andDo(print())
    }

    @Test
    fun modifyEmpTest(){

        val modifyEmp = getModifyEmpDTO()

        //when
        addEmp()

        val modifyUrl = "/api/emp/" + modifyEmp.empId;

        val result = mockMvc.perform(
                put(modifyUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(modifyEmp))
        )
        //then
        result.andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empId", equalTo(modifyEmp.empId)))
                .andExpect(jsonPath("$.empNm", equalTo(modifyEmp.empNm)))
                .andExpect(jsonPath("$.empNo", equalTo(modifyEmp.empNo)))
                .andExpect(jsonPath("$.email", equalTo(modifyEmp.email)))
                .andDo(print())
    }

    @Test
    fun deleteEmpTest(){

        val empId = "collenkim"

        //when
        addEmp()

        val deleteUrl = "/api/emp/" + empId;

        val result = mockMvc.perform(
                delete(deleteUrl)
        )

        //then
        result.andExpect(status().isOk)
                .andExpect(content().string("Emp deleted successfully"))
                .andDo(print())
    }

    fun getAddEmpDTO() : EmpDTO{

        val reqEmpId = "collenkim"
        val reqEmpNm = "test1"
        val reqEmpNo = "T001"
        val reqEmail = "test@naver.com"
        val reqEmpDTO = EmpDTO(
                empId = reqEmpId,
                empNm = reqEmpNm,
                empNo = reqEmpNo,
                email = reqEmail
        )

        return reqEmpDTO;
    }

    fun getModifyEmpDTO() : EmpDTO {

        val reqEmpId = "collenkim"
        val reqEmpNm = "test2"
        val reqEmpNo = "T002"
        val reqEmail = "test2@naver.com"
        val reqEmpDTO = EmpDTO(
                empId = reqEmpId,
                empNm = reqEmpNm,
                empNo = reqEmpNo,
                email = reqEmail
        )

        return reqEmpDTO
    }

    fun addEmp(){
        val addEmp = getAddEmpDTO()
        empService.add(addEmp)
    }
}