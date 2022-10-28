package dmit2015.repository;

import common.jpa.AbstractJpaRepository;
import dmit2015.entity.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class CategoryRepository extends AbstractJpaRepository<Category, Integer> {

    public CategoryRepository() {
        super(Category.class);
    }

    public List<String> findNamesByPattern(String namePattern) {
        return getEntityManager()
                .createQuery("""
                SELECT c
                FROM Category c
                WHERE c.categoryName LIKE :nameValue
                ORDER BY c.categoryName""", String.class)
                .setParameter("nameValue", "%" + namePattern + "%")
                .getResultList();
    }

    public Optional<Category> findByName(String name) {
        Optional<Category> optionalCategory;

        try {
            Category querySingleResult = getEntityManager()
                    .createQuery("""
                        SELECT c 
                        FROM Category c 
                        WHERE c.categoryName = :nameValue""", Category.class)
                    .setParameter("nameValue", name)
                    .getSingleResult();
            optionalCategory = Optional.of(querySingleResult);
        } catch (Exception ex) {
            optionalCategory = Optional.empty();
        }
        return optionalCategory;
    }

}