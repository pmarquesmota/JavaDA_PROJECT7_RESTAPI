package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CurvePointService {
    @Autowired
    private CurvePointRepository curvePointRepository;

    public List<CurvePoint> getCurvePoints(){
        return curvePointRepository.findAll();
    }

    public CurvePoint getCurvePoint(Long id) throws NoSuchElementException {
        return curvePointRepository.findById(id).orElseThrow(() -> new NoSuchElementException("CurvePoint " + id + " does not exist"));
    }

    public void setCurvePoint(CurvePoint curvePoint){
        curvePointRepository.save(curvePoint);
    }

    public void updateCurvePoint(Long id, CurvePoint curvePoint) throws  NoSuchElementException{
        CurvePoint oldCurvePoint = curvePointRepository.findById(id).orElseThrow(() -> new NoSuchElementException("CurvePoint " + id + " does not exist"));
        oldCurvePoint = curvePoint.withId(oldCurvePoint.getId());
        curvePointRepository.save(oldCurvePoint);
    }

    public void deleteCurvePoint(Long id){
        curvePointRepository.deleteById(id);
    }

}
