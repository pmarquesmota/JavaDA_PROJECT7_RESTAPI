package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
@WebMvcTest(CurveController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CurvePointControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    CurvePointService curvePointService;

    @Test
    public void failUrl() throws Exception {
            mvc.perform(get("/CurvePoint/toto"))
                    .andExpect(status().isNotFound());
    }

    @Test
    public void curvePointList() throws Exception {
        List<CurvePoint> curves = new ArrayList<>();
        when(curvePointService.getCurvePoints()).thenReturn(curves);
        mvc.perform(get("/CurvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"));
    }

    @Test
    public void curvePointAdd() throws Exception {
        mvc.perform(get("/CurvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    public  void curvePointValidateWihoutErrors() throws Exception {
            doNothing().when(curvePointService).setCurvePoint(any());

            mvc.perform(post("/CurvePoint/validate")
                            .param("curveId", "42"))
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/CurvePoint/list"));
     }

    @Test
    public  void curvePointValidateWithErrors() throws Exception {
            doNothing().when(curvePointService).setCurvePoint(any());

            mvc.perform(post("/CurvePoint/validate")
                            .param("curveId", "toto"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("curvePoint/add"));
    }

    @Test
    public void curvePointGetUpdateWithoutErrors() throws Exception {
        CurvePoint curve = new CurvePoint();
        when(curvePointService.getCurvePoint(anyLong())).thenReturn(curve);

        mvc.perform(get("/CurvePoint/update/42"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attribute("curvePoint", is(curve)));

    }

    @Test
    public void curvePointGetUpdateWithErrors() throws Exception {
        doThrow(new NoSuchElementException()).when(curvePointService).getCurvePoint(anyLong());

        mvc.perform(get("/CurvePoint/update/42"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/CurvePoint/list"))
                .andExpect(flash().attribute("message", "Cet id n'existe pas."));
    }

    @Test
    public void  curvePointPostUpdateWithoutErrors() throws Exception {
        doNothing().when(curvePointService).updateCurvePoint(anyLong(),any());

        mvc.perform(post("/CurvePoint/update/42")
                        .param("curveId", "42"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/CurvePoint/list"));
    }

    @Test
    public void  curvePointPostUpdateWithErrors() throws Exception {
        mvc.perform(post("/CurvePoint/update/42")
                        .param("curveId", "a"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));

    }
    @Test
    public void curvePointPostUpdateWithException() throws Exception {
        doThrow(new NoSuchElementException()).when(curvePointService).updateCurvePoint(anyLong(),any());

        mvc.perform(post("/CurvePoint/update/42")
                        .param("curveId", "42"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/CurvePoint/list"))
                .andExpect(flash().attribute("message", "Cet id n'existe pas."));

    }

    @Test
    public void curvePointDelete() throws Exception {
        doNothing().when(curvePointService).deleteCurvePoint(anyLong());

        mvc.perform(get("/CurvePoint/delete/42"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/CurvePoint/list"));

    }

}
