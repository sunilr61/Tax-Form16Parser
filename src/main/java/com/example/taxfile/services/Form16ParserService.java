package com.example.taxfile.services;

import com.example.taxfile.models.TaxDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface Form16ParserService {
    TaxDetails parseForm16(MultipartFile form16File) throws IOException;
}
