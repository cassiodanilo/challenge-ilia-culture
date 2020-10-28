package br.com.challenge.ilia.culture.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@ApiModel(description="For Update Customer")
public class ForUpdateCustomerDTO {

    @ApiModelProperty("Full name")
    @NotBlank(message= "{name.not.blank}")
    private String name;
    @ApiModelProperty("CPF")
    @NotBlank(message= "{cpf.not.blank}")
    @CPF
    private String cpf;

    @ApiModelProperty("Address")
    private String address;

    public ForUpdateCustomerDTO() {}

    public ForUpdateCustomerDTO(@NotBlank(message = "{name.not.blank}") String name,
                          @NotBlank(message = "{cpf.not.blank}") @CPF String cpf, String address) {
        this.name = name;
        this.cpf = cpf;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
