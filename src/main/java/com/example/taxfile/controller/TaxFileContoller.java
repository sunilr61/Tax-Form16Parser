package com.example.taxfile.controller;

import com.example.taxfile.dtos.ResponseStatus;
import com.example.taxfile.dtos.TaxDetailsDto;
import com.example.taxfile.models.TaxDetails;
import com.example.taxfile.services.Form16ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/tax")
public class TaxFileContoller {

    private Form16ParserService form16ParserService;
    @Autowired
    public TaxFileContoller(Form16ParserService form16ParserService){
        this.form16ParserService=form16ParserService;
    }

    @PostMapping("/uploadForm16")
    public TaxDetailsDto uploadForm16(@RequestParam("file") MultipartFile file) {
        TaxDetailsDto responseDto = new TaxDetailsDto();
        try{
            TaxDetails taxDetails = this.form16ParserService.parseForm16(file);
            responseDto.setPanNumber(taxDetails.getPanNumber());
            responseDto.setIncome(taxDetails.getIncome());
            responseDto.setDeductions(taxDetails.getDeductions());
            responseDto.setTaxPaid(taxDetails.getTaxPaid());
            responseDto.setStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            responseDto.setStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }

}
