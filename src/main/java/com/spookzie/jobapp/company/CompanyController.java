package com.spookzie.jobapp.company;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController
{
    private final CompanyService companyService;


    /*  GET */
    @GetMapping
    public ResponseEntity<List<Company>> findAll()
    {
        return new ResponseEntity<>(
                this.companyService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id)
    {
        Optional<Company> companyFound = this.companyService.findById(id);
        return companyFound.map(
                company -> new ResponseEntity<>(
                        company, HttpStatus.OK)
        ).orElseGet(
                () -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
        );
    }


    /*  POST    */
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company)
    {
        return new ResponseEntity<>(
                this.companyService.createCompany(company),
                HttpStatus.CREATED
        );
    }


    /*  PUT */
    @PutMapping("/{id}")
    public ResponseEntity<Company> fullUpdateCompany(@PathVariable Long id, @RequestBody Company company)
    {
        return new ResponseEntity<>(
                this.companyService.updateCompany(id, company),
                HttpStatus.OK
        );
    }


    /*  DELETE  */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id)
    {
        boolean isDeleted = this.companyService.deleteById(id);
        if(isDeleted)
            return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);

        return new ResponseEntity<>("Company doesn't exist", HttpStatus.NOT_FOUND);
    }
}