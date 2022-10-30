package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Territories", schema = "WestWind")
public class Territory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TerritoryID", nullable = false, length = 20)
    private String id;
    @Basic
    @Column(name = "TerritoryDescription", nullable = false, length = 50)
    private String territoryDescription;
    @Basic
    @Column(name = "RegionID", nullable = false)
    private Integer regionId;
    @OneToMany(mappedBy = "territoriesByTerritoryId")
    private Collection<EmployeeTerritory> employeeTerritoriesByTerritoryId;
    @ManyToOne
    @JoinColumn(name = "RegionID", referencedColumnName = "RegionID", nullable = false, insertable = false, updatable = false)
    private Region regionsByRegionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Territory territory = (Territory) o;
        return Objects.equals(id, territory.id) && Objects.equals(territoryDescription, territory.territoryDescription) && Objects.equals(regionId, territory.regionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, territoryDescription, regionId);
    }

    public Collection<EmployeeTerritory> getEmployeeTerritoriesByTerritoryId() {
        return employeeTerritoriesByTerritoryId;
    }

    public void setEmployeeTerritoriesByTerritoryId(Collection<EmployeeTerritory> employeeTerritoriesByTerritoryId) {
        this.employeeTerritoriesByTerritoryId = employeeTerritoriesByTerritoryId;
    }

    public Region getRegionsByRegionId() {
        return regionsByRegionId;
    }

    public void setRegionsByRegionId(Region regionsByRegionId) {
        this.regionsByRegionId = regionsByRegionId;
    }
}
