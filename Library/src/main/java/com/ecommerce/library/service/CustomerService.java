package com.ecommerce.library.service;

import com.ecommerce.library.model.Customer;

public interface CustomerService {

    Customer findByUsername(String name);
}
