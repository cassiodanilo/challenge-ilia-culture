package br.com.challenge.ilia.culture.customer.controller;

import br.com.challenge.ilia.culture.customer.dto.NewCustomerDTO;
import br.com.challenge.ilia.culture.customer.entity.Customer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.challenge.ilia.culture.customer.util.JsonUtil.toJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
@WithMockUser(username = "user", password = "123")
class CustomerControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@Order(1)
	void shouldFetchAllCustomers() throws Exception {
		mvc.perform(get("/api/v1/customer").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(4)));
	}

	@Test
	void shouldNotCreateCustomerWithRepeatedCPF() throws Exception {
		NewCustomerDTO newClientDTO = new NewCustomerDTO("Cássio Cardoso", "519.568.730-74", "Rua Numero 0");
		mvc.perform(post("/api/v1/customer").contentType(MediaType.APPLICATION_JSON).content(toJson(newClientDTO)))
				.andExpect(status().isExpectationFailed());
	}

	@Test
	void shouldCreateNewCustomer() throws Exception {
		NewCustomerDTO newCustomerDTO = new NewCustomerDTO("Cássio Cardoso", "057.127.090-58", "Rua Numero 0");
		mvc.perform(post("/api/v1/customer").contentType(MediaType.APPLICATION_JSON).content(toJson(newCustomerDTO)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(5)));
	}

	@Test
	void shouldFetchSingleCustomer() throws Exception {
		mvc.perform(get("/api/v1/customer/3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Customer 3")))
				.andExpect(jsonPath("$.cpf", equalTo("197.499.470-88")))
				.andExpect(jsonPath("$.address", equalTo("Rua Numero 2")));
	}

	@Test
	void shouldUpdateCustomer() throws Exception {
		Customer client = new Customer(2L, "Cassio Santos", "860.738.380-93", "Rua Numero 0");
		mvc.perform(put("/api/v1/customer/2").contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(toJson(client)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", not(equalTo("Cássio Cardoso"))));
	}

	@Test
	void shouldDeleteCustomer() throws Exception {
		mvc.perform(delete("/api/v1/customer/4").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}