package com.demogroup.demoweb.domain.tip.service;

import com.demogroup.demoweb.domain.tip.domain.FarmingInfo;
import com.demogroup.demoweb.global.exception.AppException;
import com.demogroup.demoweb.global.exception.ErrorCode;
import com.demogroup.demoweb.domain.tip.repository.FarmingInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public List<FarmingInfo> findForHomeInfos(){
        List<FarmingInfo> list=new ArrayList<>();
        FarmingInfo farmingInfo1 = farmingInfoRepository.findById(10l)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND, ""));
        FarmingInfo farmingInfo2 = farmingInfoRepository.findById(3l)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND, ""));
        list.add(farmingInfo1);
        list.add(farmingInfo2);
        return list;
    }
}
