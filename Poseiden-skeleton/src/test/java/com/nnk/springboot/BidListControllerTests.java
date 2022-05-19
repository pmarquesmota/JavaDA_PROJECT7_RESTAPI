package com.nnk.springboot;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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

import static org.mockito.Mockito.when;
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

    @MockBean
    BidListRepository bidListRepository;

    @Test
    public void showAddTodoForm() throws Exception {
        List<BidList> bids = new ArrayList<>();
        when(bidListRepository.findAll()).thenReturn(bids);

        mvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"));
               // .andExpect(forwardedUrl("/WEB-INF/jsp/todo/add.jsp"))
               // .andExpect(model().attribute("bids", hasProperty("id", nullValue())))
               // .andExpect(model().attribute("bids", hasProperty("description", isEmptyOrNullString())))
               // .andExpect(model().attribute("bids", hasProperty("title", isEmptyOrNullString())));
    }

}
