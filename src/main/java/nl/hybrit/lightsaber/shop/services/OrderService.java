package nl.hybrit.lightsaber.shop.services;

import nl.hybrit.lightsaber.shop.controller.model.OrderModel;
import nl.hybrit.lightsaber.shop.repository.model.OrdersEntity;

import java.util.List;

public interface OrderService {

    OrdersEntity seveOrder(OrderModel orderModel);

    List<OrdersEntity> findAll();
}
