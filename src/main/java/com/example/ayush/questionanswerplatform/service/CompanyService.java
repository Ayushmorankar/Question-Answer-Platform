package com.example.ayush.questionanswerplatform.service;

import com.example.ayush.questionanswerplatform.dao.CompanyDao;
import com.example.ayush.questionanswerplatform.dto.company.CompanyRequest;
import com.example.ayush.questionanswerplatform.models.Company;
import com.example.ayush.questionanswerplatform.models.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyDao companyDao;

    public void createCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        companyDao.save(company);
    }

    public void deleteCompany(Long id) {

        Company company = companyDao.findById(id)
                .orElseThrow(RuntimeException::new);

        Set<Question> questionSet = company.getQuestions();

        for(Question question: questionSet){
            question.removeCompany(company);
        }

        companyDao.delete(company);
    }

    public void updateCompany(Long companyId, String updatedName) {

        Company company = companyDao.findById(companyId)
                .orElseThrow(RuntimeException::new);

        company.setName(updatedName);
    }
}
