package com.demogroup.demoweb.service;

import com.demogroup.demoweb.domain.FarmingInfo;
import com.demogroup.demoweb.exception.AppException;
import com.demogroup.demoweb.exception.ErrorCode;
import com.demogroup.demoweb.repository.FarmingInfoRepository;
import com.demogroup.demoweb.utils.MakeJsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmingInfoService {

    private final FarmingInfoRepository farmingInfoRepository;

    //전체 팁 조회하기
    @Transactional
    public List<FarmingInfo> findAllFarmingInfo(){
        return farmingInfoRepository.findAll();
    }

    @Transactional
    public FarmingInfo findFarmingInfoById(Long fid){
        FarmingInfo info=farmingInfoRepository.findById(fid)
                .orElseThrow(()->new AppException(ErrorCode.ENTITY_NOT_FOUND,"요청하신 fid에 해당하는 farminginfo 객체를 찾을 수 없습니다."));
        return info;
    }

}
