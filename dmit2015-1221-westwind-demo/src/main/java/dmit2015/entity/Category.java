package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Categories", schema = "WestWind")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CategoryID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "CategoryName", nullable = false, length = 15)
    private String categoryName;
    @Basic
    @Column(name = "Description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "Picture", nullable = true)
    private byte[] picture;
    @Basic
    @Column(name = "PictureMimeType", nullable = true, length = 40)
    private String pictureMimeType;
    @OneToMany(mappedBy = "categoriesByCategoryId")
    private Collection<Product> productsByCategoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureMimeType() {
        return pictureMimeType;
    }

    public void setPictureMimeType(String pictureMimeType) {
        this.pictureMimeType = pictureMimeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(categoryName, category.categoryName) && Objects.equals(description, category.description) && Arrays.equals(picture, category.picture) && Objects.equals(pictureMimeType, category.pictureMimeType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, categoryName, description, pictureMimeType);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }

    public Collection<Product> getProductsByCategoryId() {
        return productsByCategoryId;
    }

    public void setProductsByCategoryId(Collection<Product> productsByCategoryId) {
        this.productsByCategoryId = productsByCategoryId;
    }
}
