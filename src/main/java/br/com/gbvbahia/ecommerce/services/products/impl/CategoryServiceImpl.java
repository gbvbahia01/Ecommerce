package br.com.gbvbahia.ecommerce.services.products.impl;

import br.com.gbvbahia.ecommerce.model.entity.commons.Parameter;
import br.com.gbvbahia.ecommerce.model.entity.products.Category;
import br.com.gbvbahia.ecommerce.repositories.products.CategoryRepository;
import br.com.gbvbahia.ecommerce.services.ServiceCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.products.CategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
@Service
public class CategoryServiceImpl extends ServiceCommon<Category, Long, JpaRepository<Category, Long>> implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(ParameterService parameterService,
                               CategoryRepository categoryRepository) {

        super(parameterService);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }

    @Override
    public List<Category> listCategoriesForMenu() {

        //Amount products in stock
        Parameter amountParameter = getParameterService().findById(ParameterService.AMOUNT_STOCK_CATEGORY).get();
        Integer amount = Integer.valueOf(amountParameter.getValue());
        final String limited;
        if (amountParameter.isActivated()) {
            limited = "limited";
        } else {
            limited = "unlimited";
        }
        //Limit of categories to show in menu
        Parameter menuMaxParameter = getParameterService().findById(ParameterService.AMOUNT_CATEGORY_MENU).get();
        Pageable limitMenu = PageRequest.of(0, Integer.valueOf(menuMaxParameter.getValue()));

        return categoryRepository.listAllForMenu(amount, limited, limitMenu);
    }

}
