package dmit2015.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "Categories", schema = "WestWind", catalog = "DMIT2015_1221_A01_swuUser2015")
public class Category implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CategoryID", nullable = false)
    private Integer categoryId;
    @Basic
    @Column(name = "CategoryName", nullable = false, length = 15)
    private String categoryName;
    @Basic
    @Column(name = "Description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "Picture", nullable = true)
    @Lob
    private byte[] picture;
    @Basic
    @Column(name = "PictureMimeType", nullable = true, length = 40)
    private String pictureMimeType;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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
        return Objects.equals(categoryId, category.categoryId) && Objects.equals(categoryName, category.categoryName) && Objects.equals(description, category.description) && Arrays.equals(picture, category.picture) && Objects.equals(pictureMimeType, category.pictureMimeType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(categoryId, categoryName, description, pictureMimeType);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}
