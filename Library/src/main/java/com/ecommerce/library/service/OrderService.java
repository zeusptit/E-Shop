package com.ecommerce.library.service;

import com.ecommerce.library.model.Order;
import com.ecommerce.library.model.ShoppingCart;

import java.util.List;

public interface OrderService
{
    List<Order> findAllOrders();

    Order acceptOrder(Long id);

    void cancelOrder(Long id);

    Order save(ShoppingCart cart);

    List<Order> findAll(String username);
}
