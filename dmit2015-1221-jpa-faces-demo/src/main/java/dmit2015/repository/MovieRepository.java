package dmit2015.repository;

import dmit2015.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.SecurityContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieRepository {

    @Inject
    private SecurityContext _securityContext;
    @PersistenceContext(name = "h2database-jpa-pu")
    private EntityManager em;

    @Transactional
    public void add(Movie newMovie) {
        String username = _securityContext.getCallerPrincipal().getName();
        newMovie.setUsername(username);
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
        List<Movie> resultList = null;
        if (_securityContext.getCallerPrincipal() == null ) {
            resultList = new ArrayList<>();
        } else if (_securityContext.isCallerInRole("Sales") ) {
            String username = _securityContext.getCallerPrincipal().getName();
            resultList = em.createQuery(
                            "SELECT m FROM Movie m WHERE m.username = :usernameValue ", Movie.class)
                    .setParameter("usernameValue", username).getResultList();
        } else {
            resultList = em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
        }
        return resultList;
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