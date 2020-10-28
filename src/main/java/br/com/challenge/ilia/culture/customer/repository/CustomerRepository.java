package br.com.challenge.ilia.culture.customer.repository;

import br.com.challenge.ilia.culture.customer.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByCpf(String cpf);
}
