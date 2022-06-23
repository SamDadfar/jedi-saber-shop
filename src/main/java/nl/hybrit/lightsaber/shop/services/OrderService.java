package nl.hybrit.lightsaber.shop.services;

import nl.hybrit.lightsaber.shop.controller.model.OrderModel;
import nl.hybrit.lightsaber.shop.repository.model.OrderEntity;

import java.util.List;

public interface OrderService {

    OrderEntity seveOrder(OrderModel orderModel);

    List<OrderEntity> findAll();

    OrderEntity findById(long id);
}
