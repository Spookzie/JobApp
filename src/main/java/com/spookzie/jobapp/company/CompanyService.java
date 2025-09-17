package com.spookzie.jobapp.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService
{
    List<Company> findAll();

    Optional<Company> findById(Long id);

    Company createCompany(Company company);

    Company updateCompany(Long id, Company company);

    boolean deleteById(Long id);
}