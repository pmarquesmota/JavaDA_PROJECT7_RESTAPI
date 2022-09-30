package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import com.nnk.springboot.service.RuleNameService;
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
@WebMvcTest(RuleNameController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RuleNameControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    RuleNameService ruleNameService;

    @Test
    public void failUrl() throws Exception {
            mvc.perform(get("/ruleName/toto"))
                    .andExpect(status().isNotFound());
       
    }

    @Test
    public void RuleNameList() throws Exception {
        List<RuleName> rules = new ArrayList<>();
        when(ruleNameService.getRuleNames()).thenReturn(rules);
        mvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"));
    }

    @Test
    public void RuleNameAdd() throws Exception {
        mvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    public  void RuleNameValidateWithoutErrors() throws Exception {
        doNothing().when(ruleNameService).setRuleName(any());

        mvc.perform(post("/ruleName/validate")
                        .param("name", "toto55"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/ruleName/list"));

    }

    @Test
    public  void RuleNameValidateWithErrors() throws Exception {
        doNothing().when(ruleNameService).setRuleName(any());

        mvc.perform(post("/ruleName/validate")
                        .param("name", "toto"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));

    }

    @Test
    public void RuleNameGetUpdateWithoutErrors() throws Exception {
        RuleName ruleName = new RuleName();
        when(ruleNameService.getRuleName(anyLong())).thenReturn(ruleName);

        mvc.perform(get("/ruleName/update/42"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attribute("ruleName", is(ruleName)));

    }

    @Test
    public void RuleNameGetUpdateWithErrors() throws Exception {
        doThrow(new NoSuchElementException()).when(ruleNameService).getRuleName(anyLong());

        mvc.perform(get("/ruleName/update/42"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(flash().attribute("message", "Cet id n'existe pas."));
    }

    @Test
    public void  RuleNamePostUpdateWithoutErrors() throws Exception {
        doNothing().when(ruleNameService).updateRuleName(anyLong(),any());

        mvc.perform(post("/ruleName/update/42")
                        .param("name", "toto55"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    public void  RuleNamePostUpdateWithErrors() throws Exception {
        mvc.perform(post("/ruleName/update/42")
                        .param("name", "toto"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));

    }
    @Test
    public void RuleNamePostUpdateWithException() throws Exception {
        doThrow(new NoSuchElementException()).when(ruleNameService).updateRuleName(anyLong(),any());

        mvc.perform(post("/ruleName/update/42")
                        .param("name", "toto55"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(flash().attribute("message", "Cet id n'existe pas."));

    }

    @Test
    public void RuleNameDelete() throws Exception {
        doNothing().when(ruleNameService).deleteRuleName(anyLong());

        mvc.perform(get("/ruleName/delete/42"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/ruleName/list"));

    }

}
