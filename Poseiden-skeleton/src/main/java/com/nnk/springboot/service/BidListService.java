package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BidListService {
    @Autowired
    BidListRepository bidListRepository;

    public void updateBid(Long id, BidList bidList) throws  NoSuchElementException{
        BidList bid = bidListRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Bid " + id + " does not exist"));
        bid = bidList.withId(bid.getId());
        bidListRepository.save(bid);
    }
}
