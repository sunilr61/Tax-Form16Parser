package com.example.taxfile.dtos;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaxDetailsDto {
    private String panNumber;
    private double income;
    private double deductions;
    private double taxPaid;
    private ResponseStatus status;

    // Getter and Setter for panNumber
    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    // Getter and Setter for income
    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    // Getter and Setter for deductions
    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    // Getter and Setter for taxPaid
    public double getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(double taxPaid) {
        this.taxPaid = taxPaid;
    }

    // Getter and Setter for status
    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
