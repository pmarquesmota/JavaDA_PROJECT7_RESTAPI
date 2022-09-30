package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTests {
    @Mock
    CurvePointRepository curvePointRepository;

    @InjectMocks
    CurvePointService curvePointService;

    @Test
    public void getCurvePoints_shouldReturnOK(){
        List<CurvePoint> curvePoints = new ArrayList<>();
        when(curvePointRepository.findAll()).thenReturn(curvePoints);

        List<CurvePoint> curvePoints_to_compare = curvePointService.getCurvePoints();
        assertEquals(curvePoints, curvePoints_to_compare);
    }

    @Test
    public void getCurvePoint_shouldReturnOK(){
        CurvePoint curvePoint = new CurvePoint();
        when(curvePointRepository.findById(anyLong())).thenReturn(Optional.of(curvePoint));

        CurvePoint curvePoints_to_compare = curvePointService.getCurvePoint(42L);
        assertEquals(curvePoint, curvePoints_to_compare);
    }

    @Test
    public void getCurvePoint_shouldReturnException() {
        when(curvePointRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> curvePointService.getCurvePoint(42L));
    }

    @Test
    public void setBid_shouldReturnOK(){
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(42L);
        when(curvePointRepository.save(any())).thenReturn(curvePoint);
        when(curvePointRepository.findById(anyLong())).thenReturn(Optional.of(curvePoint));

        curvePointService.setCurvePoint(curvePoint);
        CurvePoint curvePoints_to_compare = curvePointRepository.findById(42L).orElseGet(CurvePoint::new);
        assertEquals(curvePoint, curvePoints_to_compare);
    }

    @Test
    public void updateBid_shouldReturnOK(){
        CurvePoint curvePoint = new CurvePoint();
        when(curvePointRepository.findById(anyLong())).thenReturn(Optional.of(curvePoint));
        when(curvePointRepository.save(any())).thenReturn(curvePoint);

        curvePointService.updateCurvePoint(42L,curvePoint);
        CurvePoint curvePoints_to_compare = curvePointRepository.findById(42L).get();
        assertEquals(curvePoint, curvePoints_to_compare);
    }

    @Test
    public void updateBid_shouldReturnException(){
        when(curvePointRepository.findById(anyLong())).thenReturn(Optional.empty());

        CurvePoint curvePoint = new CurvePoint();
        assertThrows(NoSuchElementException.class, () -> curvePointService.updateCurvePoint(42L,curvePoint));

    }

    @Test
    public void deleteBid_shouldReturnOk(){
        doThrow(new NoSuchElementException()).when(curvePointRepository).findById(anyLong());
        doNothing().when(curvePointRepository).deleteById(anyLong());

        curvePointService.deleteCurvePoint(42L);
        assertThrows(NoSuchElementException.class, () -> curvePointRepository.findById(42L));

    }
}
