package br.com.gbvbahia.ecommerce.services.products.impl;

import br.com.gbvbahia.ecommerce.model.entity.commons.Parameter;
import br.com.gbvbahia.ecommerce.model.entity.products.Category;
import br.com.gbvbahia.ecommerce.repositories.products.CategoryRepository;
import br.com.gbvbahia.ecommerce.services.ServiceCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.products.CategoryService;
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

    private final ParameterService parameterService;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(ParameterService parameterService, CategoryRepository categoryRepository) {
        this.parameterService = parameterService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }

    @Override
    public List<Category> listCategoriesForMenu() {
        Optional<Parameter> amount = parameterService.findById(ParameterService.AMOUNT_STOCK_CATEGORY);
        return categoryRepository.listAllForMenu(Integer.valueOf(amount.get().getValue().trim()));
    }

}
