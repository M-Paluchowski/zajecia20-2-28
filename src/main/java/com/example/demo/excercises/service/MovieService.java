package com.example.demo.excercises.service;

import com.example.demo.excercises.model.Category;
import com.example.demo.excercises.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Movie> findAll() {
        TypedQuery<Movie> selectQuery = entityManager.createQuery("SELECT m FROM Movie m", Movie.class);
        return selectQuery.getResultList();
    }

    @Transactional
    public void save(Movie movie) {
        entityManager.persist(movie);
    }

    public Optional<Movie> findOneById(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        return Optional.ofNullable(movie);
    }

    public List<Movie> findAllByCategory(Category category) {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.category=?1", Movie.class);
        query.setParameter(1, category);
        return query.getResultList();
    }
}
