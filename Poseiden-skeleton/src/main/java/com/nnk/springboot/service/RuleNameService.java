package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RuleNameService {
    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleName> getRuleNames(){
        return ruleNameRepository.findAll();
    }

    public  RuleName getRuleName(Long id) throws NoSuchElementException {
        return ruleNameRepository.findById(id).orElseThrow(() -> new NoSuchElementException("RuleName " + id + " does not exist"));
    }

    public void setRuleName(RuleName ruleName){
        ruleNameRepository.save(ruleName);
    }

    public void updateRuleName(Long id, RuleName ruleName) throws  NoSuchElementException{
        RuleName oldRuleName = ruleNameRepository.findById(id).orElseThrow(() -> new NoSuchElementException("RuleName " + id + " does not exist"));
        ruleName = ruleName.withId(oldRuleName.getId());
        ruleNameRepository.save(ruleName);
    }

    public void deleteRuleName(Long id){
        ruleNameRepository.deleteById(id);
    }

}
