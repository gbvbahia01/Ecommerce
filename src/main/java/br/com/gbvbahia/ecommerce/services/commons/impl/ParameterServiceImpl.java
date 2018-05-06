package br.com.gbvbahia.ecommerce.services.commons.impl;

import br.com.gbvbahia.ecommerce.model.entity.commons.Parameter;
import br.com.gbvbahia.ecommerce.repositories.commons.ParameterRepository;
import br.com.gbvbahia.ecommerce.services.ServiceCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
public class ParameterServiceImpl extends ServiceCommon<Parameter, String, JpaRepository<Parameter, String>> implements ParameterService {

    private final ParameterRepository parameterRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository) {

        super(null);
        this.parameterRepository = parameterRepository;
    }

    @Override
    protected JpaRepository<Parameter, String> getRepository() {
        return this.parameterRepository;
    }

    @Override
    public String getValueByKey(String key) {
        Optional<Parameter> optional = parameterRepository.findById(key);
        return optional.isPresent() ? optional.get().getValue() : null;
    }

    @Override
    public Number getValueByKeyAsNumber(String key) {
        String value = getValueByKey(key);
        if (value == null) {
            return null;
        }
        return new Double(value);
    }

    @Override
    public List<Parameter> listByRange(String... keys) {
        return parameterRepository.findAllById(Arrays.asList(keys));
    }
}
