package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Regions", schema = "WestWind")
public class Region implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RegionID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "RegionDescription", nullable = false, length = 50)
    private String regionDescription;
    @OneToMany(mappedBy = "regionsByRegionId")
    private Collection<Territory> territoriesByRegionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(id, region.id) && Objects.equals(regionDescription, region.regionDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regionDescription);
    }

    public Collection<Territory> getTerritoriesByRegionId() {
        return territoriesByRegionId;
    }

    public void setTerritoriesByRegionId(Collection<Territory> territoriesByRegionId) {
        this.territoriesByRegionId = territoriesByRegionId;
    }
}
