package com.example.demo.excercises.controller;

import com.example.demo.excercises.model.Category;
import com.example.demo.excercises.model.Movie;
import com.example.demo.excercises.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    String main(@RequestParam(required = false) Category category, Model model) {

        List<Movie> movies;
        if (category != null) {
            movies = movieService.findAllByCategory(category);
        } else {
            movies = movieService.findAll();
        }

        model.addAttribute("movies", movies);
        model.addAttribute("movie", new Movie());
        return "home";
    }

    @GetMapping("/movie")
    String getMovie(@RequestParam Long id, Model model) {
        Optional<Movie> movie = movieService.findOneById(id);
        if (movie.isPresent()) {
            model.addAttribute("movie", movie.get());
            return "movie";
        }
        return "redirect:/";
    }

    @PostMapping("/movie/add")
    String addMovie(Movie movie) {
        movieService.save(movie);
        return "redirect:/";
    }
}
