package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TradeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TradeControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    TradeService tradeService;

    @Test
    public void failUrl() throws Exception {
        mvc.perform(get("/trade/toto"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void tradeList() throws Exception {
        List<Trade> trades = new ArrayList<>();
        when(tradeService.getTrades()).thenReturn(trades);
        mvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"));
    }

    @Test
    public void tradeAdd() throws Exception {
        mvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    public  void tradeValidateWithoutErrors() throws Exception {
        doNothing().when(tradeService).setTrade(any());

        mvc.perform(post("/trade/validate")
                        .param("account", "toto55"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/trade/list"));

    }

    @Test
    public  void tradeValidateWithErrors() throws Exception {
        doNothing().when(tradeService).setTrade(any());

        mvc.perform(post("/trade/validate")
                        .param("account", "toto"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));

    }

    @Test
    public void tradeGetUpdateWithoutErrors() throws Exception {
        Trade trade = new Trade();
        when(tradeService.getTrade(anyLong())).thenReturn(trade);

        mvc.perform(get("/trade/update/42"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"))
                .andExpect(model().attribute("trade", is(trade)));

    }

    @Test
    public void tradeGetUpdateWithErrors() throws Exception {
        doThrow(new NoSuchElementException()).when(tradeService).getTrade(anyLong());

        mvc.perform(get("/trade/update/42"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(flash().attribute("message", "Cet id n'existe pas."));
    }

    @Test
    public void  tradePostUpdateWithoutErrors() throws Exception {
        doNothing().when(tradeService).updateTrade(anyLong(),any());

        mvc.perform(post("/trade/update/42")
                        .param("account", "toto55"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    public void  tradePostUpdateWithErrors() throws Exception {
        mvc.perform(post("/trade/update/42")
                        .param("account", "toto"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));

    }
    @Test
    public void tradePostUpdateWithException() throws Exception {
        doThrow(new NoSuchElementException()).when(tradeService).updateTrade(anyLong(),any());

        mvc.perform(post("/trade/update/42")
                        .param("account", "toto55"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(flash().attribute("message", "Cet id n'existe pas."));

    }

    @Test
    public void tradeDelete() throws Exception {
        doNothing().when(tradeService).deleteTrade(anyLong());

        mvc.perform(get("/trade/delete/42"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/trade/list"));

    }

}
