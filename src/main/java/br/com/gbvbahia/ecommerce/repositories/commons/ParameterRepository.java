package br.com.gbvbahia.ecommerce.repositories.commons;

import br.com.gbvbahia.ecommerce.model.entity.commons.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
public interface ParameterRepository extends JpaRepository<Parameter, String> {
}
