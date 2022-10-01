package westwind.repository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.testing.transaction.TransactionUtil;
import westwind.entity.Category;

import java.util.List;
import java.util.Optional;

public class CategoryJavaSeRepository {

    private EntityManagerFactory _emf;

    public CategoryJavaSeRepository() {
        // https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1.html#obtaining-an-entity-manager-factory-in-a-java-se-environment
        _emf = Persistence.createEntityManagerFactory("local-mssql-westwind-jpa-pu");
    }

    public EntityManagerFactory entityManagerFactory() {
        return _emf;
    }

    public List<Category> findAll() {
        return TransactionUtil.doInJPA(this::entityManagerFactory, entityManager -> {
           return entityManager
                   .createQuery("SELECT c FROM Category c ORDER BY c.name", Category.class)
                   .getResultList();
        });
    }

    public Optional<Category> findById(Long id) {
        Optional<Category> optionalCategory = TransactionUtil.doInJPA(this::entityManagerFactory, entityManager -> {
           Category querySingleResult = entityManager.find(Category.class, id);
           if (querySingleResult != null) {
               return Optional.of(querySingleResult);
           } else {
               return Optional.empty();
           }
        });

        return optionalCategory;
    }

    public Category add(Category newCategory) {
        return TransactionUtil.doInJPA(this::entityManagerFactory, entityManager -> {
            entityManager.persist(newCategory);
            return newCategory;
        });
    }
}
