package com.example.taxfile.services;

import com.example.taxfile.models.TaxDetails;
import com.example.taxfile.repositories.TaxDetailsRepository;
import com.example.taxfile.utils.PDFParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Form16ParserServiceImpl implements Form16ParserService{
    private TaxDetailsRepository taxDetailsRepository;

    @Autowired
    public Form16ParserServiceImpl(TaxDetailsRepository taxDetailsRepository){
        this.taxDetailsRepository=taxDetailsRepository;
    }

    @Override
    public TaxDetails parseForm16(MultipartFile form16File) throws IOException {
        String fileName = form16File.getOriginalFilename();
//        System.out.println("Received file: " + fileName);

        String extractedText = PDFParserUtil.extractText(form16File);

        TaxDetails taxDetails = new TaxDetails();
        System.out.println("-----------------------------------------------");
        System.out.println("Extract Text " +  extractedText);
        System.out.println("-----------------------------------------------");

        // Extract details from the text
        taxDetails.setPanNumber(extractPanNumber(extractedText));
        taxDetails.setIncome(extractIncome(extractedText));
        taxDetails.setDeductions(extractDeductions(extractedText));
        taxDetails.setTaxPaid(extractTaxPaid(extractedText));
        taxDetails.setForm16FileName(fileName);


        return this.taxDetailsRepository.save(taxDetails);
    }
    private String extractPanNumber(String text) {
        // Implement logic to extract PAN number from the text
        Pattern panPattern = Pattern.compile("PAN of Employee:\\s*(\\w+)");
        Matcher panMatcher = panPattern.matcher(text);
        if (panMatcher.find()) {
            System.out.println("Pan Number extracted : " + panMatcher.group(1));
            return panMatcher.group(1);

        }
        return null;
    }

    private double extractIncome(String text) {
        Pattern incomePattern = Pattern.compile("([\\d,\\.]+)\\s*Gross total income \\(6\\+8\\)");
        Matcher incomeMatcher = incomePattern.matcher(text);
        if (incomeMatcher.find()) {
            System.out.println("Matched value: " + incomeMatcher.group(1));
            return Double.parseDouble(incomeMatcher.group(1).replaceAll(",", ""));
        } else {
            System.out.println("Pattern not matched. Check extracted text formatting.");
            return -1; // or handle the case appropriately
        }
    }

    private double extractDeductions(String text) {
        Pattern deductionsPattern = Pattern.compile("Net tax payable \\s*₹?([\\d,\\.]+)");
        Matcher deductionsMatcher = deductionsPattern.matcher(text);
        if (deductionsMatcher.find()) {
            return Double.parseDouble(deductionsMatcher.group(1).replaceAll(",", ""));
        }
        return 0;
    }

    private double extractTaxPaid(String text) {
        //Pattern taxPaidPattern = Pattern.compile("Net tax payable \\(17-18\\)\\d+\\.\\s*(\\d+[\\.\\d]*)");
        Pattern taxPaidPattern = Pattern.compile("Total tax paid\\s*(\\d{1,3}(?:,\\d{3})*(?:\\.\\d+)?)");
        Matcher taxPaidMatcher = taxPaidPattern.matcher(text);
        if (taxPaidMatcher.find()) {
            System.out.println("Matched value: " + taxPaidMatcher.group(1));
            return Double.parseDouble(taxPaidMatcher.group(1).replaceAll(",", ""));
        }
        else{
            System.out.println("Pattern not matched. Debug text formatting.");
            return -1;
        }

    }
}
