package com.demogroup.demoweb.domain.tip.repository;

import com.demogroup.demoweb.domain.tip.domain.FarmingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmingInfoRepository extends JpaRepository<FarmingInfo, Long> {
    List<FarmingInfo> findByCategory_Fcid(Long fcid);

    Optional<FarmingInfo> findByFid(Long fid);

}
