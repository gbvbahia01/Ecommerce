package br.com.gbvbahia.ecommerce.controllers.helpers;

import br.com.gbvbahia.ecommerce.model.entity.commons.Parameter;
import br.com.gbvbahia.ecommerce.model.entity.products.Category;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.entity.products.SubCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public final class ItemFactory {

    private ItemFactory() {
    }

    public static ItemScreen buildItem(ProductImage productImage) {

        ItemScreen itemScreen = new ItemScreen(productImage.getClass(), productImage.getKeyPicture());

        itemScreen.setId(productImage.getId());

        String imgName = productImage.getNamePicture() == null
                ? productImage.getKeyPicture().getDefaultImg()
                : productImage.getNamePicture();
        itemScreen.setImgName(imgName);

        itemScreen.setPrice(productImage.getProduct().getCampaignPrice());
        itemScreen.setDescription(productImage.getProduct().getDescription());
        itemScreen.setName(productImage.getProduct().getName());
        return itemScreen;
    }

    public static ItemScreen buildItem(Category category) {
        ItemScreen itemScreen = new ItemScreen(category.getClass(), null);
        itemScreen.setDescription(category.getDescription());
        itemScreen.setId(category.getId());
        itemScreen.setName(category.getName());
        itemScreen.setSubItems(buildItemsFromSubCategory(category.getSubCategorys()));
        return itemScreen;
    }

    public static ItemScreen buildItem(SubCategory subCategory) {
        ItemScreen itemScreen = new ItemScreen(subCategory.getClass(), null);
        itemScreen.setName(subCategory.getName());
        itemScreen.setId(subCategory.getId());
        itemScreen.setDescription(subCategory.getDescription());
        return itemScreen;
    }

    public static ItemScreen buildItem(Parameter parameter) {
        ItemScreen item = new ItemScreen(parameter.getClass(), null);
        item.setDescription(parameter.getDescription());
        item.setName(parameter.getKey());
        item.setValue(parameter.getValue());
        item.setRendered(parameter.isActivated());
        return item;
    }

    public static List<ItemScreen> buildItemsFromCategory(Collection<Category> categories) {
        List<ItemScreen> items = new ArrayList<>();
        categories.stream().forEach(cat -> items.add(buildItem(cat)));
        return items;
    }

    public static List<ItemScreen> buildItemsFromSubCategory(Collection<SubCategory> subCategories) {
        List<ItemScreen> items = new ArrayList<>();
        subCategories.parallelStream().forEach(sub -> items.add(buildItem(sub)));
        return items;
    }

    public static List<ItemScreen> buildItemsFromProductImage(Collection<ProductImage> productImageList) {
        List<ItemScreen> items = new ArrayList<>(productImageList.size());

        productImageList.parallelStream().forEach( pi -> items.add(buildItem(pi)));

        return items;
    }

    public static List<ItemScreen> buildItemsFromParameters(Collection<Parameter> parameters) {
        List<ItemScreen> items = new ArrayList<>(parameters.size());
        parameters.parallelStream().forEach(p -> items.add(buildItem(p)));
        return items;
    }
}
