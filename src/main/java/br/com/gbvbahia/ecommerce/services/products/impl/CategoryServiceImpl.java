package br.com.gbvbahia.ecommerce.services.products.impl;

import br.com.gbvbahia.ecommerce.services.dto.commons.ParameterDTO;
import br.com.gbvbahia.ecommerce.services.dto.products.CategoryDTO;
import br.com.gbvbahia.ecommerce.model.entity.products.Category;
import br.com.gbvbahia.ecommerce.repositories.products.CategoryRepository;
import br.com.gbvbahia.ecommerce.services.ServiceCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.products.CategoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CategoryServiceImpl extends ServiceCommon<CategoryDTO, Category, Long, JpaRepository<Category, Long>> implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(DozerBeanMapper dozer,
                               ParameterService parameterService,
                               CategoryRepository categoryRepository) {

        super(parameterService, dozer, CategoryDTO.class);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }

    @Override
    public List<CategoryDTO> listCategoriesForMenu() {

        //Amount products in stock
        ParameterDTO amountParameter = getParameterService().findById(ParameterService.AMOUNT_STOCK_CATEGORY);
        Integer amount = Integer.valueOf(amountParameter.getValue());
        final String limited;
        if (amountParameter.isActivated()) {
            limited = "limited";
        } else {
            limited = "unlimited";
        }

        //Limit of categories to show in menu
        ParameterDTO menuMaxParameter = getParameterService().findById(ParameterService.AMOUNT_CATEGORY_MENU);
        Pageable limitMenu = PageRequest.of(0, Integer.valueOf(menuMaxParameter.getValue()));

        List<Category> categories = categoryRepository.listAllForMenu(amount, limited, limitMenu);

        return convert(categories);
    }

}
