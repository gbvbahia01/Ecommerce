package br.com.gbvbahia.ecommerce.model.entity.products;

import br.com.gbvbahia.ecommerce.model.cotract.Model;
import br.com.gbvbahia.ecommerce.model.embeddable.ProductSubCategoryPK;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Project: ecommerce
 *
 * @author bahiafolder
 * @version 1.0
 * @since 20/05/18
 */
@Entity
@Table(name = "product_sub_category", schema = "products")
public class ProductSubCategory implements Model<ProductSubCategoryPK> {

    @EmbeddedId
    private ProductSubCategoryPK id = new ProductSubCategoryPK();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Product product;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sub_category_id", referencedColumnName = "id", updatable = false, insertable = false)
    private SubCategory subCategory;

    public ProductSubCategory() {
        super();
    }

    public ProductSubCategory(@NotNull Product product,
                              @NotNull SubCategory subCategory) {
        this.product = product;
        this.subCategory = subCategory;
        id.setIdProduct(product.getId());
        id.setIdSubCategory(subCategory.getId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .toString();
    }

    @Override
    public ProductSubCategoryPK getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setId(ProductSubCategoryPK id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}

