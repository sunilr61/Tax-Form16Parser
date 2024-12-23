package com.example.taxfile.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TaxDetails extends BaseModel{
    private String form16FileName;
    private String panNumber;
    private double income;
    private double deductions;
    private double taxPaid;

    @ManyToOne
    private User user;
    // Getter and Setter for id

    // Getter and Setter for form16FileName
    public String getForm16FileName() {
        return form16FileName;
    }

    public void setForm16FileName(String form16FileName) {
        this.form16FileName = form16FileName;
    }

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

    // Getter and Setter for user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
