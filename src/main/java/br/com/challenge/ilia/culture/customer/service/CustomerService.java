package br.com.challenge.ilia.culture.customer.service;

import br.com.challenge.ilia.culture.customer.entity.Customer;

import java.util.Optional;

public interface CustomerService {

    Customer create(Customer customer);
    Optional<Customer> read(long id);
    void update(Customer customer);
    void delete(Customer customer);
    Iterable<Customer> list();
    Optional<Customer> findByCpf(String cpf);

}
