package br.com.challenge.ilia.culture.customer.controller;

import br.com.challenge.ilia.culture.customer.dto.ForUpdateCustomerDTO;
import br.com.challenge.ilia.culture.customer.dto.NewCustomerDTO;
import br.com.challenge.ilia.culture.customer.entity.Customer;
import br.com.challenge.ilia.culture.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

import static br.com.challenge.ilia.culture.customer.converter.CustomerConverter.*;

@Api(value = "/customer")
@RestController
@RequestMapping( path = "/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation("Add a new customer")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Customer created successfully"),
    })
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody NewCustomerDTO newCustomerDTO) {
        Customer customer = toCustomer(newCustomerDTO);
        Optional<Customer> optionalClient = customerService.findByCpf(customer.getCpf());
        if(optionalClient.isPresent()) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(toCreatedCustomerDTO(
                        customerService.create(customer)),
                    HttpStatus.CREATED);
    }


    @ApiOperation("Fetches a single customer by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id") @Min(1) long id) {
        Optional<Customer> client = customerService.read(id);
        return client.<ResponseEntity<?>>map(
                customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Update an existing customer")
    @ApiResponses(value = {
            @ApiResponse(code = 406, message = "CPF cannot be changed"),
            @ApiResponse(code = 200, message = "Customer updated successfully"),
            @ApiResponse(code = 404, message = "Customer not found"),
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") @Min(1) long id, @Valid @RequestBody ForUpdateCustomerDTO forUpdateCustomer){
        Optional<Customer> oldCustomer = customerService.read(id);
        if(oldCustomer.isPresent()){
            Customer customer = oldCustomer.get();
            if(!customer.getCpf().equals(forUpdateCustomer.getCpf())) {
                Optional<Customer> optionalClient = customerService.findByCpf(forUpdateCustomer.getCpf());
                if(optionalClient.isPresent()) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }
            customer.setName(forUpdateCustomer.getName());
            customer.setCpf(forUpdateCustomer.getCpf());
            customer.setAddress(forUpdateCustomer.getAddress());
            customerService.update(customer);
            return new ResponseEntity<>(toUpdatedCustomerDTO(customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Deletes a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer deleted successfully"),
            @ApiResponse(code = 404, message = "Customer not found"),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remove(@PathVariable(value = "id") @Min(1) long id) {
        Optional<Customer> customer = customerService.read(id);
        if(customer.isPresent()){
            customerService.delete(customer.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Fetches a list of customer")
    @GetMapping
    public Iterable<Customer> list() {
        return customerService.list();
    }
}
