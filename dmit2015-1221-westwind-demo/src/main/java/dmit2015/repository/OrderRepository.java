package dmit2015.repository;

import common.jpa.AbstractJpaRepository;
import dmit2015.entity.Order;

import java.util.List;

public class OrderRepository extends AbstractJpaRepository<Order, Integer> {
	
	public OrderRepository() {
		super(Order.class);
	}
	
	public List<Integer> findYearsWithOrders() {
		return getEntityManager().createQuery(
				"""
      SELECT YEAR(o.orderDate) As OrderYear
      FROM Order o
      GROUP BY YEAR(o.orderDate)
      ORDER BY YEAR(o.orderDate) DESC
      """, Integer.class)
			.getResultList();
	}
}
