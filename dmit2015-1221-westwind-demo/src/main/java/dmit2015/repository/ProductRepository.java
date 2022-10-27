package dmit2015.repository;

import common.jpa.AbstractJpaRepository;
import dmit2015.entity.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class ProductRepository extends AbstractJpaRepository<Product, Integer> {

    public ProductRepository() {
        super(Product.class);
    }

    public List<String> findProductNameByPattern(String productNamePattern) {
        return getEntityManager().createQuery("""
SELECT p.productName
FROM Product p
WHERE productName LIKE :searchValue
""", String.class)
                .setParameter("searchValue","%" + productNamePattern + "%")
                .getResultList();
    }

    public List<Product> findByProductNameByPattern(String productNamePattern) {
        return getEntityManager().createQuery("""
SELECT p
FROM Product p
WHERE productName LIKE :searchValue
""", Product.class)
                .setParameter("searchValue","%" + productNamePattern + "%")
                .getResultList();
    }

}