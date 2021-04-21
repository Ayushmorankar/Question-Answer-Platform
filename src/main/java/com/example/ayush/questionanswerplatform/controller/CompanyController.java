package com.example.ayush.questionanswerplatform.controller;

import com.example.ayush.questionanswerplatform.dto.company.CompanyRequest;
import com.example.ayush.questionanswerplatform.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/companies")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Void> createCompany(@Valid @RequestBody CompanyRequest companyRequest){
        companyService.createCompany(companyRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{companyId}/{updatedName}")
    public ResponseEntity<Void> updateCompany(@PathVariable Long companyId, @PathVariable String updatedName){
        companyService.updateCompany(companyId, updatedName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
