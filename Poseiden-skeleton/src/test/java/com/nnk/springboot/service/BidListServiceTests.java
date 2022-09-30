package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BidListServiceTests {

    @Mock
    BidListRepository bidListRepository;

    @InjectMocks
    BidListService bidListService;

    @Test
    public void getBids_shouldReturnOK(){
        List<BidList> bids = new ArrayList<>();
        when(bidListRepository.findAll()).thenReturn(bids);

        List<BidList> bids_to_compare = bidListService.getBids();
        assertEquals(bids, bids_to_compare);
    }

    @Test
    public void getBid_shouldReturnOK(){
        BidList bidList = new BidList();
        when(bidListRepository.findById(anyLong())).thenReturn(Optional.of(bidList));

        BidList bid_to_compare = bidListService.getBid(42L);
        assertEquals(bidList, bid_to_compare);
    }

    @Test
    public void getBid_shouldReturnNotOK(){
        when(bidListRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> bidListService.getBid(42L));
    }

    @Test
    public void getBid_shouldReturnException() {
        doThrow(new NoSuchElementException()).when(bidListRepository).findById(anyLong());

        assertThrows(NoSuchElementException.class, () -> bidListService.getBid(42L));
    }

    @Test
    public void setBid_shouldReturnOK(){
        BidList bidList = new BidList();
        bidList.setId(42L);
        when(bidListRepository.save(any())).thenReturn(bidList);
        when(bidListRepository.findById(anyLong())).thenReturn(Optional.of(bidList));

        bidListService.setBid(bidList);
        BidList bid_to_compare = bidListRepository.findById(42L).get();
        assertEquals(bidList, bid_to_compare);
    }

    @Test
    public void updateBid_shouldReturnOK(){
        BidList bidList = new BidList();
        when(bidListRepository.findById(anyLong())).thenReturn(Optional.of(bidList));
        when(bidListRepository.save(any())).thenReturn(bidList);

        bidListService.updateBid(42L,bidList);
        BidList bid_to_compare = bidListRepository.findById(42L).get();
        assertEquals(bidList, bid_to_compare);
    }

    @Test
    public void updateBid_shouldReturnException(){
        when(bidListRepository.findById(anyLong())).thenReturn(Optional.empty());

        BidList bidList = new BidList();
        assertThrows(NoSuchElementException.class, () -> bidListService.updateBid(42L,bidList));

    }

    @Test
    public void deleteBid_shouldReturnOk(){
        doThrow(new NoSuchElementException()).when(bidListRepository).findById(anyLong());
        doNothing().when(bidListRepository).deleteById(anyLong());

        bidListService.deleteBid(42L);
        assertThrows(NoSuchElementException.class, () -> bidListRepository.findById(42L));

    }

}
