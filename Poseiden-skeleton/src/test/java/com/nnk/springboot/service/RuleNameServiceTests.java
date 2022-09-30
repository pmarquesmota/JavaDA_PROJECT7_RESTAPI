package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class RuleNameServiceTests {
    @Mock
    RuleNameRepository ruleNameRepository;

    @InjectMocks
    RuleNameService ruleNameService;

    @Test
    public void getRuleNames_shouldReturnOK(){
        List<RuleName> ratings = new ArrayList<>();
        when(ruleNameRepository.findAll()).thenReturn(ratings);

        List<RuleName> ratings_to_compare = ruleNameService.getRuleNames();
        assertEquals(ratings, ratings_to_compare);
    }

    @Test
    public void getRuleName_shouldReturnOK(){
        RuleName rating = new RuleName();
        when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.of(rating));

        RuleName ratings_to_compare = ruleNameService.getRuleName(42L);
        assertEquals(rating, ratings_to_compare);
    }

    @Test
    public void getRuleName_shouldReturnException() {
        when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> ruleNameService.getRuleName(42L));
    }

    @Test
    public void setRuleName_shouldReturnOK(){
        RuleName rating = new RuleName();
        rating.setId(42L);
        when(ruleNameRepository.save(any())).thenReturn(rating);
        when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.of(rating));

        ruleNameService.setRuleName(rating);
        RuleName ratings_to_compare = ruleNameRepository.findById(42L).orElseGet(RuleName::new);
        assertEquals(rating, ratings_to_compare);
    }

    @Test
    public void updateRuleName_shouldReturnOK(){
        RuleName rating = new RuleName();
        when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.of(rating));
        when(ruleNameRepository.save(any())).thenReturn(rating);

        ruleNameService.updateRuleName(42L,rating);
        RuleName ratings_to_compare = ruleNameRepository.findById(42L).get();
        assertEquals(rating, ratings_to_compare);
    }

    @Test
    public void updateRuleName_shouldReturnException(){
        when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuleName rating = new RuleName();
        assertThrows(NoSuchElementException.class, () -> ruleNameService.updateRuleName(42L,rating));

    }

    @Test
    public void deleteRuleName_shouldReturnOk(){
        doThrow(new NoSuchElementException()).when(ruleNameRepository).findById(anyLong());
        doNothing().when(ruleNameRepository).deleteById(anyLong());

        ruleNameService.deleteRuleName(42L);
        assertThrows(NoSuchElementException.class, () -> ruleNameRepository.findById(42L));

    }

}
