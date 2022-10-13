package dmit2015.repository;

import dmit2015.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieRepository {

    @PersistenceContext(name = "h2database-jpa-pu")
    private EntityManager em;

    @Transactional
    public void add(Movie newMovie) {
        em.persist(newMovie);
    }

    public Movie findById(Long MovieID) {
        return em.find(Movie.class, MovieID);
    }

    public Optional<Movie> findOptionalById(Long MovieID) {
        Optional<Movie> optionalMovie = Optional.empty();
        try {
            Movie querySingleResult = findById(MovieID);
            if (querySingleResult != null) {
                optionalMovie = Optional.of(querySingleResult);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optionalMovie;
    }

    public List<Movie> findAll() {
        return em.createQuery("SELECT o FROM Movie o ", Movie.class)
                .getResultList();
    }

    @Transactional
    public void update(Movie updatedMovie) {
        Optional<Movie> optionalMovie = findOptionalById(updatedMovie.getId());
        if (optionalMovie.isPresent()) {
            // Update only properties that is editable by the end user
            Movie existingMovie = optionalMovie.get();
            // TODO: Copy each edit property from updatedMovie to existingMovie
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setRating(updatedMovie.getRating());
            existingMovie.setPrice(updatedMovie.getPrice());
            existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
            existingMovie.setGenre(updatedMovie.getGenre());

            em.merge(existingMovie);
        }
    }

    @Transactional
    public void delete(Movie existingMovie) {
        if (em.contains(existingMovie)) {
            em.remove(existingMovie);
        } else {
            em.remove(em.merge(existingMovie));
        }
    }

    @Transactional
    public void deleteById(Long MovieID) {
        Optional<Movie> optionalMovie = findOptionalById(MovieID);
        if (optionalMovie.isPresent()) {
            Movie existingMovie = optionalMovie.get();
            em.remove(existingMovie);
        }
    }

    public long count() {
        return em.createQuery("SELECT COUNT(o) FROM Movie o", Long.class).getSingleResult().longValue();
    }

    @Transactional
    public void deleteAll() {
        em.flush();
        em.clear();
        em.createQuery("DELETE FROM Movie").executeUpdate();
    }

    public List<Movie> findByPartialTitle(String partialTitle) {
        List<Movie> queryResultList = em.createQuery("""
            SELECT m 
            FROM Movie m
            WHERE m.title LIKE :titleValue
            """, Movie.class)
                .setParameter("titleValue", "%" + partialTitle + "%")
                .getResultList();
        return queryResultList;
    }

}