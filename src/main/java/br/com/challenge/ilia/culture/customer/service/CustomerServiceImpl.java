package br.com.challenge.ilia.culture.customer.service;

import br.com.challenge.ilia.culture.customer.entity.Customer;
import br.com.challenge.ilia.culture.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> read(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Iterable<Customer> list() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf);
    }

}
