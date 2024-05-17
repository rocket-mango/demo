package com.demogroup.demoweb.domain.mango.repository;

import com.demogroup.demoweb.domain.mango.domain.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    Optional<Disease> findByEname(String diseaseName);

}
