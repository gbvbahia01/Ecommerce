package br.com.gbvbahia.ecommerce.model.embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Project: ecommerce
 *
 * @author bahiafolder
 * @version 1.0
 * @since 20/05/18
 */
@Embeddable
public class ProductSubCategoryPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "product_id", nullable = false)
    @NotNull
    private Long idProduct;

    @Column(name = "sub_category_id", nullable = false)
    @NotNull
    private Long idSubCategory;

    public ProductSubCategoryPK() {
        super();
    }

    public ProductSubCategoryPK(@NotNull Long idProduct,
                                @NotNull Long idSubCategory) {
        this.idProduct = idProduct;
        this.idSubCategory = idSubCategory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("idProduct", idProduct)
                .append("idSubCategory", idSubCategory)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSubCategoryPK that = (ProductSubCategoryPK) o;
        return Objects.equals(idProduct, that.idProduct) &&
                Objects.equals(idSubCategory, that.idSubCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubCategory);
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public Long getIdSubCategory() {
        return idSubCategory;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public void setIdSubCategory(Long idSubCategory) {
        this.idSubCategory = idSubCategory;
    }
}

