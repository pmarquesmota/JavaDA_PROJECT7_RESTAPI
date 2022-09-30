package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
@WebMvcTest(RatingController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RatingControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    RatingService ratingService;

    @Test
    public void failUrl() throws Exception {
            mvc.perform(get("/rating/toto"))
                    .andExpect(status().isNotFound());
    }

    @Test
    public void ratingList() throws Exception {
        List<Rating> ratings = new ArrayList<>();
        when(ratingService.getRatings()).thenReturn(ratings);
        mvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"));
    }

    @Test
    public void ratingAdd() throws Exception {
        mvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    public  void ratingValidateWithoutErrors() throws Exception {
            doNothing().when(ratingService).setRating(any());

            mvc.perform(post("/rating/validate")
                            .param("moodysRating", "toto55"))
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/rating/list"));
        
    }

    @Test
    public  void ratingValidateWithErrors() throws Exception {
            doNothing().when(ratingService).setRating(any());

            mvc.perform(post("/rating/validate")
                            .param("moodysRating", "toto"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("rating/add"));
        
    }

    @Test
    public void ratingGetUpdateWithoutErrors() throws Exception {
        Rating rating = new Rating();
        when(ratingService.getRating(anyLong())).thenReturn(rating);

        mvc.perform(get("/rating/update/42"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attribute("rating", is(rating)));

    }

    @Test
    public void ratingGetUpdateWithErrors() throws Exception {
        doThrow(new NoSuchElementException()).when(ratingService).getRating(anyLong());

        mvc.perform(get("/rating/update/42"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(flash().attribute("message", "Cet id n'existe pas."));
    }

    @Test
    public void  ratingPostUpdateWithoutErrors() throws Exception {
        doNothing().when(ratingService).updateRating(anyLong(),any());

        mvc.perform(post("/rating/update/42")
                        .param("moodysRating", "toto55"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    public void  ratingPostUpdateWithErrors() throws Exception {
        mvc.perform(post("/rating/update/42")
                        .param("moodysRating", "toto"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));

    }
    @Test
    public void ratingPostUpdateWithException() throws Exception {
        doThrow(new NoSuchElementException()).when(ratingService).updateRating(anyLong(),any());

        mvc.perform(post("/rating/update/42")
                        .param("moodysRating", "toto55"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(flash().attribute("message", "Cet id n'existe pas."));

    }

    @Test
    public void ratingDelete() throws Exception {
        doNothing().when(ratingService).deleteRating(anyLong());

        mvc.perform(get("/rating/delete/42"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/rating/list"));

    }

}
