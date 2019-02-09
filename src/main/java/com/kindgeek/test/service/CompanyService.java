package com.kindgeek.test.service;

import com.kindgeek.test.entity.Company;
import com.kindgeek.test.service.request.CompanyRequest;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public interface CompanyService {

    Company createCompany();
    Company getCompany(int companyId);
    Company addPersonToCompany(CompanyRequest companyRequest);
}
