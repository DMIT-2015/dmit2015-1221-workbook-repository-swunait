package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "EmployeeTerritories", schema = "WestWind")
@IdClass(EmployeeTerritoryPK.class)
public class EmployeeTerritory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EmployeeID", nullable = false)
    private Integer employeeId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TerritoryID", nullable = false, length = 20)
    private String territoryId;
    @ManyToOne
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID", nullable = false, insertable = false, updatable = false)
    private Employee employeesByEmployeeId;
    @ManyToOne
    @JoinColumn(name = "TerritoryID", referencedColumnName = "TerritoryID", nullable = false, insertable = false, updatable = false)
    private Territory territoriesByTerritoryId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        this.territoryId = territoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeTerritory that = (EmployeeTerritory) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(territoryId, that.territoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, territoryId);
    }

    public Employee getEmployeesByEmployeeId() {
        return employeesByEmployeeId;
    }

    public void setEmployeesByEmployeeId(Employee employeesByEmployeeId) {
        this.employeesByEmployeeId = employeesByEmployeeId;
    }

    public Territory getTerritoriesByTerritoryId() {
        return territoriesByTerritoryId;
    }

    public void setTerritoriesByTerritoryId(Territory territoriesByTerritoryId) {
        this.territoriesByTerritoryId = territoriesByTerritoryId;
    }
}
