package dmit2015.repository;

import common.jpa.AbstractJpaRepository;
import dmit2015.entity.Department;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class DepartmentRepository extends AbstractJpaRepository<Department, Integer> {

    public DepartmentRepository() {
        super(Department.class);
    }

    public Map<String, Integer> selectItemMap() {
        return getEntityManager().createQuery("""
SELECT d.departmentId as d_id, d.name as d_name
FROM Department d
ORDER BY d.name
""", Tuple.class)
                .getResultStream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(1, String.class), // map key
                        tuple -> tuple.get(0, Integer.class), // map value
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

}