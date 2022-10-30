package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Products", schema = "WestWind")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ProductID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "ProductName", nullable = false, length = 40)
    private String productName;
    @Basic
    @Column(name = "SupplierID", nullable = false)
    private Integer supplierId;
    @Basic
    @Column(name = "CategoryID", nullable = false)
    private Integer categoryId;
    @Basic
    @Column(name = "QuantityPerUnit", nullable = false, length = 20)
    private String quantityPerUnit;
    @Basic
    @Column(name = "MinimumOrderQuantity", nullable = true)
    private Short minimumOrderQuantity;
    @Basic
    @Column(name = "UnitPrice", nullable = false)
    private BigDecimal unitPrice;
    @Basic
    @Column(name = "UnitsOnOrder", nullable = false)
    private Integer unitsOnOrder;
    @Basic
    @Column(name = "Discontinued", nullable = false)
    private Boolean discontinued;
    @OneToMany(mappedBy = "productsByProductId")
    private Collection<ManifestItem> manifestItemsByProductId;
    @OneToMany(mappedBy = "productsByProductId")
    private Collection<OrderDetail> orderDetailsByProductId;
    @ManyToOne
    @JoinColumn(name = "SupplierID", referencedColumnName = "SupplierID", nullable = false, insertable = false, updatable = false)
    private Supplier suppliersBySupplierId;
    @ManyToOne
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID", nullable = false, insertable = false, updatable = false)
    private Category categoriesByCategoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public Short getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    public void setMinimumOrderQuantity(Short minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(Integer unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(supplierId, product.supplierId) && Objects.equals(categoryId, product.categoryId) && Objects.equals(quantityPerUnit, product.quantityPerUnit) && Objects.equals(minimumOrderQuantity, product.minimumOrderQuantity) && Objects.equals(unitPrice, product.unitPrice) && Objects.equals(unitsOnOrder, product.unitsOnOrder) && Objects.equals(discontinued, product.discontinued);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, supplierId, categoryId, quantityPerUnit, minimumOrderQuantity, unitPrice, unitsOnOrder, discontinued);
    }

    public Collection<ManifestItem> getManifestItemsByProductId() {
        return manifestItemsByProductId;
    }

    public void setManifestItemsByProductId(Collection<ManifestItem> manifestItemsByProductId) {
        this.manifestItemsByProductId = manifestItemsByProductId;
    }

    public Collection<OrderDetail> getOrderDetailsByProductId() {
        return orderDetailsByProductId;
    }

    public void setOrderDetailsByProductId(Collection<OrderDetail> orderDetailsByProductId) {
        this.orderDetailsByProductId = orderDetailsByProductId;
    }

    public Supplier getSuppliersBySupplierId() {
        return suppliersBySupplierId;
    }

    public void setSuppliersBySupplierId(Supplier suppliersBySupplierId) {
        this.suppliersBySupplierId = suppliersBySupplierId;
    }

    public Category getCategoriesByCategoryId() {
        return categoriesByCategoryId;
    }

    public void setCategoriesByCategoryId(Category categoriesByCategoryId) {
        this.categoriesByCategoryId = categoriesByCategoryId;
    }
}
