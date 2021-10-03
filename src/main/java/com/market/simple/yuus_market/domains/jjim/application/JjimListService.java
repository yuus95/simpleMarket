package com.market.simple.yuus_market.domains.jjim.application;

import com.market.simple.yuus_market.domains.jjim.application.dto.JjimListResponse;
import com.market.simple.yuus_market.domains.jjim.domain.JjimRepository;
import com.market.simple.yuus_market.domains.jjim.domain.JjimStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JjimListService {
    private final JjimRepository jjimRepository;

    public List<JjimListResponse> getList(Long userIdx){
        Sort createDate = Sort.by("createDate").descending();
        List<JjimListResponse> collect = jjimRepository.findAllByMemberId(userIdx, createDate).stream()
                .filter(o -> o.getJjimStatus() == JjimStatus.JJIM)
                .map(o -> JjimListResponse.from(o))
                .collect(Collectors.toList());

        return collect;
    }
}
