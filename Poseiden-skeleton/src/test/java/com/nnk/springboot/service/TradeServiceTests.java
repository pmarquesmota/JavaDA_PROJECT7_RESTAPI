package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeServiceTests {
    @Mock
    TradeRepository tradeRepository;

    @InjectMocks
    TradeService tradeService;

    @Test
    public void getTrades_shouldReturnOK(){
        List<Trade> trades = new ArrayList<>();
        when(tradeRepository.findAll()).thenReturn(trades);

        List<Trade> trades_to_compare = tradeService.getTrades();
        assertEquals(trades, trades_to_compare);
    }

    @Test
    public void getTrade_shouldReturnOK(){
        Trade trade = new Trade();
        when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));

        Trade trades_to_compare = tradeService.getTrade(42L);
        assertEquals(trade, trades_to_compare);
    }

    @Test
    public void getTrade_shouldReturnException() {
        when(tradeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> tradeService.getTrade(42L));
    }

    @Test
    public void setTrade_shouldReturnOK(){
        Trade trade = new Trade();
        trade.setTradeId(42L);
        when(tradeRepository.save(any())).thenReturn(trade);
        when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));

        tradeService.setTrade(trade);
        Trade trades_to_compare = tradeRepository.findById(42L).orElseGet(Trade::new);
        assertEquals(trade, trades_to_compare);
    }

    @Test
    public void updateTrade_shouldReturnOK(){
        Trade trade = new Trade();
        when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));
        when(tradeRepository.save(any())).thenReturn(trade);

        tradeService.updateTrade(42L,trade);
        Trade trades_to_compare = tradeRepository.findById(42L).get();
        assertEquals(trade, trades_to_compare);
    }

    @Test
    public void updateTrade_shouldReturnException(){
        when(tradeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Trade trade = new Trade();
        assertThrows(NoSuchElementException.class, () -> tradeService.updateTrade(42L,trade));

    }

    @Test
    public void deleteTrade_shouldReturnOk(){
        doThrow(new NoSuchElementException()).when(tradeRepository).findById(anyLong());
        doNothing().when(tradeRepository).deleteById(anyLong());

        tradeService.deleteTrade(42L);
        assertThrows(NoSuchElementException.class, () -> tradeRepository.findById(42L));

    }
}
