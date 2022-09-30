package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BidListController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BidListControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    BidListService bidListService;

    @Test
    public void failUrl() throws Exception {
            mvc.perform(get("/bidList/toto"))
                    .andExpect(status().isNotFound());
    }

    @Test
    public void bidListList() throws Exception {
        List<BidList> bids = new ArrayList<>();
        when(bidListService.getBids()).thenReturn(bids);
        mvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"));
    }

    @Test
    public void bidListAdd() throws Exception {
        mvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    public  void bidListValidateWithoutErrors() throws Exception {
            doNothing().when(bidListService).setBid(any());

            mvc.perform(post("/bidList/validate")
                            .param("account", "toto55"))
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/bidList/list"));

    }

    @Test
    public  void bidListValidateWithErrors() throws Exception {
            doNothing().when(bidListService).setBid(any());

            mvc.perform(post("/bidList/validate")
                        .param("account", "toto"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("bidList/add"));

    }

    @Test
    public void bidListGetUpdateWithoutErrors() throws Exception {
        BidList bid = new BidList();
        when(bidListService.getBid(anyLong())).thenReturn(bid);

        mvc.perform(get("/bidList/update/42"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("bidList/update"))
                    .andExpect(model().attribute("bidList", is(bid)));

    }

    @Test
    public void bidListGetUpdateWithErrors() throws Exception {
        doThrow(new NoSuchElementException()).when(bidListService).getBid(anyLong());

        mvc.perform(get("/bidList/update/42"))
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/bidList/list"))
                    .andExpect(flash().attribute("message", "Cet id n'existe pas."));
    }

    @Test
    public void  bidListPostUpdateWithoutErrors() throws Exception {
        doNothing().when(bidListService).updateBid(anyLong(),any());

        mvc.perform(post("/bidList/update/42")
                        .param("account", "toto55"))
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    public void  bidListPostUpdateWithErrors() throws Exception {
        mvc.perform(post("/bidList/update/42")
                            .param("account", "toto"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("bidList/update"));

    }
    @Test
    public void bidListPostUpdateWithException() throws Exception {
        doThrow(new NoSuchElementException()).when(bidListService).updateBid(anyLong(),any());

        mvc.perform(post("/bidList/update/42")
                    .param("account", "toto55"))
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/bidList/list"))
                    .andExpect(flash().attribute("message", "Cet id n'existe pas."));

    }

    @Test
    public void bidListDelete() throws Exception {
        doNothing().when(bidListService).deleteBid(anyLong());

        mvc.perform(get("/bidList/delete/42"))
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/bidList/list"));

    }
}
