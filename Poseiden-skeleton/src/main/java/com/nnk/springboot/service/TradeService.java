package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> getTrades(){
        return tradeRepository.findAll();
    }

    public  Trade getTrade(Long id) throws NoSuchElementException {
        return tradeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Trade " + id + " does not exist"));
    }

    public void setTrade(Trade trade){
        tradeRepository.save(trade);
    }

    public void updateTrade(Long id, Trade trade) throws  NoSuchElementException{
        Trade oldTrade = tradeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Trade " + id + " does not exist"));
        trade = trade.withTradeId(oldTrade.getTradeId());
        tradeRepository.save(trade);
    }

    public void deleteTrade(Long id){
        tradeRepository.deleteById(id);
    }
}
