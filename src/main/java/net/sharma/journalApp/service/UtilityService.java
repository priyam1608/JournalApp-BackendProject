package net.sharma.journalApp.service;

import net.sharma.journalApp.entity.FrequentlyUsedData;
import net.sharma.journalApp.repository.FrequentlyUsedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtilityService {

    @Autowired
    FrequentlyUsedDataRepository frequentlyUsedDataRepository;

    public Map<String,String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<FrequentlyUsedData> data = frequentlyUsedDataRepository.findAll();
        for(FrequentlyUsedData frequentlyUsedData: data){
            appCache.put(frequentlyUsedData.getKey(), frequentlyUsedData.getValue());
        }
    }
}
