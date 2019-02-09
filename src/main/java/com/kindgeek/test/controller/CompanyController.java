package com.kindgeek.test.controller;

import com.kindgeek.test.entity.Company;
import com.kindgeek.test.service.CompanyService;
import com.kindgeek.test.service.request.CompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;


    @PostMapping("/createCompany")
    public Company saveCompany() {
        return companyService.createCompany();
    }

    @GetMapping("/getCompany/{companyId}")
    public Company getCompany(@PathVariable int companyId) {
        return companyService.getCompany(companyId);

    }
    @PostMapping("/add")
    public Company getWithProject(@RequestBody CompanyRequest companyRequest){
       return companyService.addPersonToCompany(companyRequest);
    }
}
