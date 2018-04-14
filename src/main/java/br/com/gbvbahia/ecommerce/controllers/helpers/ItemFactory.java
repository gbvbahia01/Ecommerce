package br.com.gbvbahia.ecommerce.controllers.helpers;

import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;

import java.util.ArrayList;
import java.util.List;

public final class ItemFactory {

    private ItemFactory() {
    }

    public static ItemScreen buildItem(ProductImage productImage) {

        ItemScreen<Long> itemScreen = new ItemScreen(productImage.getClass(), productImage.getKeyPicture());

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

    public static List<ItemScreen> buildItems(List<ProductImage> productImageList) {
        List<ItemScreen> arrayList = new ArrayList<>(productImageList.size());

        productImageList.parallelStream().forEach( pi -> arrayList.add(buildItem(pi)));

        return arrayList;
    }
}
