package com.demogroup.demoweb.domain.tip.repository;

import com.demogroup.demoweb.domain.tip.domain.FarmingInfoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmingInfoCategoryRepository extends JpaRepository<FarmingInfoCategory, Long> {

}
