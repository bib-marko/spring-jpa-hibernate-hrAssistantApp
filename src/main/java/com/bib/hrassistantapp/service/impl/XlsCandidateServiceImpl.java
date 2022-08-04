package com.bib.hrassistantapp.service.impl;

import com.bib.hrassistantapp.advice.CustomResponseMessage;
import com.bib.hrassistantapp.advice.InvalidExcelException;
import com.bib.hrassistantapp.model.Candidate;
import com.bib.hrassistantapp.repository.XlsCandidateRepository;
import com.bib.hrassistantapp.service.XlsCandidateService;
import com.bib.hrassistantapp.utils.ExcelUtility;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.*;

@Service
public class XlsCandidateServiceImpl implements XlsCandidateService {

    private final XlsCandidateRepository xlsCandidateRepository;

    public XlsCandidateServiceImpl(XlsCandidateRepository xlsCandidateRepository) {
        this.xlsCandidateRepository = xlsCandidateRepository;
    }


    @Override
    public List<Candidate> findAll() {
        return xlsCandidateRepository.findAll();
    }

    @Override
    public ResponseEntity<CustomResponseMessage> importExcel(MultipartFile file) throws IOException {
        List<Candidate> candidates = new ArrayList<>();
        List<Candidate> invalidCandidates = new ArrayList<>();
        List<List> allCandidates;

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

        XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFRow header = sheet.getRow(0);

        if(ExcelUtility.checkExcelHeaders(header)) {
            for (int index = 1; index < sheet.getPhysicalNumberOfRows(); index++) {

                XSSFRow row = sheet.getRow(index);

                Candidate candidate = new Candidate();

                candidate.setUuid(Long.valueOf(ExcelUtility.getCellValue(row, 0)));
                candidate.setFullName(ExcelUtility.getCellValue(row, 1));
                candidate.setPosition(ExcelUtility.getCellValue(row, 2));
                candidate.setContactNumber(ExcelUtility.getCellValue(row, 3));
                candidate.setEmail(ExcelUtility.getCellValue(row, 4));

                candidate.setDateEndorsed((ExcelUtility.getDateValue(workbook, row, 5)));

                candidate.setOverallStatus(ExcelUtility.getCellValue(row, 6));
                candidate.setHiringManager(ExcelUtility.getCellValue(row, 7));
                candidate.setPaperScreeningStatus(ExcelUtility.getCellValue(row, 8));

                candidate.setTechInterviewSchedule(ExcelUtility.getDateValue( workbook,row, 9));

                candidate.setInterviewResult(ExcelUtility.getCellValue(row, 10));
                candidate.setOfferStatus(ExcelUtility.getCellValue(row, 11));

                candidate.setOfferDate(ExcelUtility.getDateValue( workbook,row, 12));

                candidate.setIsRejectionEmailSent(Boolean.valueOf(ExcelUtility.getCellValue(row, 13)));

                candidate.setOnBoardingDate(ExcelUtility.getDateValue( workbook,row, 15));


                candidates.add(updateIfExisting(candidate));

            }
            workbook.close();
        }
        else{
            throw new InvalidExcelException("The excel you tried to import is invalid.");
        }
        allCandidates = checkIfValid(candidates, invalidCandidates);
        xlsCandidateRepository.saveAll(allCandidates.get(0));

        return new ResponseEntity<>(createCustomResponse(allCandidates), HttpStatus.OK);
    }

    public Candidate updateIfExisting(Candidate candidate){
        Optional<Candidate> existingCandidate = xlsCandidateRepository.findByUuid(candidate.getUuid());
        if(!existingCandidate.isPresent())
            return candidate;
        candidate.setId(existingCandidate.get().getId());
        candidate.setUuid(existingCandidate.get().getUuid());
        candidate.setCreatedAt(existingCandidate.get().getCreatedAt());
        candidate.setUpdatedAt(new Date());
        return candidate;

    }
    private List<List> checkIfValid(List<Candidate> candidates, List<Candidate> invalidCandidates){
        List<List> superList = new ArrayList<>();
        List<String> invalidList = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        for (Candidate candidate: candidates) {
            Set<ConstraintViolation<Candidate>> violations = validator.validate(candidate);
            if(violations.size() != 0) {
                String message = String.format("Candidate with ExcelID [%d] - This candidate has invalid field/s: ",candidate.getUuid());
                for (ConstraintViolation<Candidate> violation : violations) {
                    message += violation.getPropertyPath()+", ";
                }
                invalidCandidates.add(candidate);
                invalidList.add(message);
            }
        }
        candidates.removeAll(invalidCandidates);
        superList.add(candidates);
        superList.add(invalidList);
        return superList;
    }

    private CustomResponseMessage createCustomResponse(List<List> allCandidates){
        CustomResponseMessage cm = new CustomResponseMessage();
        cm.setValid(allCandidates.get(0));
        cm.setInvalid(allCandidates.get(1));
        cm.setTimestamp(new Date());
        return cm;
    }


}
