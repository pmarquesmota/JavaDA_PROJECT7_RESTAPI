package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BidListService {
    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> getBids(){
        return bidListRepository.findAll();
    }

    public  BidList getBid(Long id) throws NoSuchElementException {
        return bidListRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Bid " + id + " does not exist"));
    }

    public void setBid(BidList bid){
        bidListRepository.save(bid);
    }

    public void updateBid(Long id, BidList bidList) throws  NoSuchElementException{
        BidList bid = bidListRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Bid " + id + " does not exist"));
        bid = bidList.withId(bid.getId());
        bidListRepository.save(bid);
    }

    public void deleteBid(Long id){
        bidListRepository.deleteById(id);
    }
}
