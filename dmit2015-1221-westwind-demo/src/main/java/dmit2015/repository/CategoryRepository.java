package dmit2015.repository;

import common.jpa.AbstractJpaRepository;
import dmit2015.entity.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class CategoryRepository extends AbstractJpaRepository<Category, Integer> {

    public CategoryRepository() {
        super(Category.class);
    }

    public List<String> findNamesByPattern(String namePattern) {
        return getEntityManager()
                .createQuery("""
                SELECT c.categoryName
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

    public BigDecimal categoryRevenueByYearAndMonth(Integer categoryId, int year, int month) {
        BigDecimal categoryRevenue;

        try {
            Double querySingleResult = getEntityManager().createQuery(
                            """
                        SELECT SUM(od.unitPrice * od.quantity * (1 - od.discount))
                        FROM Order o JOIN o.orderDetailsByOrderId od JOIN od.productsByProductId p JOIN p.categoriesByCategoryId c
                        WHERE c.id = :categoryIdValue AND YEAR(o.orderDate) = :yearValue AND MONTH(o.orderDate) = :monthValue
                        """, Double.class)
                    .setParameter("categoryIdValue", categoryId)
                    .setParameter("yearValue", year)
                    .setParameter("monthValue", month)
                    .getSingleResult();
            categoryRevenue = BigDecimal.valueOf(querySingleResult);
        } catch (Exception ex) {
            categoryRevenue = BigDecimal.ZERO;
        }

        return categoryRevenue;
    }
    public Map<String, BigDecimal> categorySalesRevenueMap() {
        return getEntityManager().createQuery(
                        """
                        SELECT c.categoryName as key, SUM(od.unitPrice * od.quantity * (1 - od.discount)) as value
                        FROM OrderDetail od JOIN od.productsByProductId p JOIN p.categoriesByCategoryId c JOIN od.ordersByOrderId o
                        GROUP BY c.categoryName
                        ORDER BY c.categoryName
                        """, Tuple.class)
                .getResultStream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get("key", String.class),
                        tuple -> tuple.get("value", BigDecimal.class),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public Map<String, Integer> selectItemMap() {
        return getEntityManager().createQuery(
                        """
                        SELECT c.categoryName as mapKey, c.id as mapValue
                        FROM Category c
                        ORDER BY c.categoryName
                        """, Tuple.class)
                .getResultStream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get("mapkey", String.class),
                        tuple -> tuple.get("mapvalue", Integer.class),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

}