package com.example.taxfile.repositories;

import com.example.taxfile.models.TaxDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxDetailsRepository extends JpaRepository<TaxDetails,Long> {
}
