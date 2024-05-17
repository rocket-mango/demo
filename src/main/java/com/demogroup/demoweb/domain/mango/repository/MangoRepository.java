package com.demogroup.demoweb.domain.mango.repository;

import com.demogroup.demoweb.domain.mango.domain.Mango;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangoRepository extends JpaRepository<Mango, Long> {
    List<Mango> findAllByUser_Uid(Long uid);

    @Modifying
    @Query(value = "select * from Mango where uid=?1 and location=?2",
            nativeQuery = true)
    List<Mango> findMangoByUidAndLocation(Long uid, String location);
}
