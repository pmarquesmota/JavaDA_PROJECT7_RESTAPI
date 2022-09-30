package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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
public class RatingServiceTests {
    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    RatingService ratingService;

    @Test
    public void getRatings_shouldReturnOK(){
        List<Rating> ratings = new ArrayList<>();
        when(ratingRepository.findAll()).thenReturn(ratings);

        List<Rating> ratings_to_compare = ratingService.getRatings();
        assertEquals(ratings, ratings_to_compare);
    }

    @Test
    public void getRating_shouldReturnOK(){
        Rating rating = new Rating();
        when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));

        Rating ratings_to_compare = ratingService.getRating(42L);
        assertEquals(rating, ratings_to_compare);
    }

    @Test
    public void getRating_shouldReturnException() {
        when(ratingRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> ratingService.getRating(42L));
    }

    @Test
    public void setRating_shouldReturnOK(){
        Rating rating = new Rating();
        rating.setId(42L);
        when(ratingRepository.save(any())).thenReturn(rating);
        when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));

        ratingService.setRating(rating);
        Rating ratings_to_compare = ratingRepository.findById(42L).orElseGet(Rating::new);
        assertEquals(rating, ratings_to_compare);
    }

    @Test
    public void updateRating_shouldReturnOK(){
        Rating rating = new Rating();
        when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));
        when(ratingRepository.save(any())).thenReturn(rating);

        ratingService.updateRating(42L,rating);
        Rating ratings_to_compare = ratingRepository.findById(42L).get();
        assertEquals(rating, ratings_to_compare);
    }

    @Test
    public void updateRating_shouldReturnException(){
        when(ratingRepository.findById(anyLong())).thenReturn(Optional.empty());

        Rating rating = new Rating();
        assertThrows(NoSuchElementException.class, () -> ratingService.updateRating(42L,rating));

    }

    @Test
    public void deleteRating_shouldReturnOk(){
        doThrow(new NoSuchElementException()).when(ratingRepository).findById(anyLong());
        doNothing().when(ratingRepository).deleteById(anyLong());

        ratingService.deleteRating(42L);
        assertThrows(NoSuchElementException.class, () -> ratingRepository.findById(42L));

    }

}
