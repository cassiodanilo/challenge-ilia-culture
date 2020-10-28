package br.com.challenge.ilia.culture.customer.converter;

import br.com.challenge.ilia.culture.customer.dto.CreatedCustomerDTO;
import br.com.challenge.ilia.culture.customer.dto.NewCustomerDTO;
import br.com.challenge.ilia.culture.customer.dto.UpdatedCustomerDTO;
import br.com.challenge.ilia.culture.customer.entity.Customer;
import br.com.challenge.ilia.culture.util.GenericBuilder;

public class CustomerConverter {

    public static CreatedCustomerDTO toCreatedCustomerDTO(Customer customer) {
        return GenericBuilder.of(CreatedCustomerDTO::new)
                .with(CreatedCustomerDTO::setId, customer.getId())
                .with(CreatedCustomerDTO::setName, customer.getName())
                .with(CreatedCustomerDTO::setCpf, customer.getAddress())
                .with(CreatedCustomerDTO::setAddress, customer.getAddress())
                .build();
    }

    public static UpdatedCustomerDTO toUpdatedCustomerDTO(Customer customer) {
        return GenericBuilder.of(UpdatedCustomerDTO::new)
                .with(UpdatedCustomerDTO::setId, customer.getId())
                .with(UpdatedCustomerDTO::setName, customer.getName())
                .with(UpdatedCustomerDTO::setCpf, customer.getAddress())
                .with(UpdatedCustomerDTO::setAddress, customer.getAddress())
                .build();
    }

    public static Customer toCustomer(NewCustomerDTO newCustomerDTO) {
        return GenericBuilder.of(Customer::new)
                .with(Customer::setName, newCustomerDTO.getName())
                .with(Customer::setCpf, newCustomerDTO.getCpf())
                .with(Customer::setAddress, newCustomerDTO.getAddress())
                .build();
    }

}
