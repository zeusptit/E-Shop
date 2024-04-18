package com.ecommerce.library.service;

import com.ecommerce.library.dto.CustomerDto;
import com.ecommerce.library.model.Customer;

public interface CustomerService {

    Customer findByUsername(String name);

    Customer save(CustomerDto customerDto);

    CustomerDto getCustomer(String username);

    Customer update(CustomerDto customerDto);

    Customer changePass(CustomerDto customerDto);
}
