package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Products", schema = "WestWind")
public class Product implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ProductID", nullable = false)
    private Integer productId;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
        return Objects.equals(productId, product.productId) && Objects.equals(productName, product.productName) && Objects.equals(supplierId, product.supplierId) && Objects.equals(categoryId, product.categoryId) && Objects.equals(quantityPerUnit, product.quantityPerUnit) && Objects.equals(minimumOrderQuantity, product.minimumOrderQuantity) && Objects.equals(unitPrice, product.unitPrice) && Objects.equals(unitsOnOrder, product.unitsOnOrder) && Objects.equals(discontinued, product.discontinued);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, supplierId, categoryId, quantityPerUnit, minimumOrderQuantity, unitPrice, unitsOnOrder, discontinued);
    }
}
