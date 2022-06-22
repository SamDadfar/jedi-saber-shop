package nl.hybrit.lightsaber.shop.services.impl;

import nl.hybrit.lightsaber.shop.controller.model.OrderModel;
import nl.hybrit.lightsaber.shop.mapper.SaberMapper;
import nl.hybrit.lightsaber.shop.repository.OrderRepository;
import nl.hybrit.lightsaber.shop.repository.PadawanRepository;
import nl.hybrit.lightsaber.shop.repository.SaberRepository;
import nl.hybrit.lightsaber.shop.repository.model.OrdersEntity;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.repository.model.SaberEntity;
import nl.hybrit.lightsaber.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of OrderService
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PadawanRepository padawanRepository;
    @Autowired
    private SaberMapper saberMapper;
    @Autowired
    private SaberRepository saberRepository;

    @Override
    public OrdersEntity seveOrder(OrderModel model) {
        OrdersEntity orderEntity = new OrdersEntity();
        PadawanEntity padawan = padawanRepository.findById(model.getPadawanId()).orElseThrow(IllegalArgumentException::new);
        model.getSabers().getSaber().forEach((saber) -> {
            SaberEntity entity = saberRepository.findBySaberId(saber.getId())
                    .orElseThrow(IllegalArgumentException::new);
            entity.setAvailable(entity.getAvailable() - 1);
            saberRepository.save(entity);
            padawan.getSabers().add(entity);
        });
        padawanRepository.save(padawan);
        orderEntity.setPadawan(padawan);
        orderEntity.setSabers(saberMapper.toSaberList(model.getSabers()));
        orderEntity.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(orderEntity);
    }

    @Override
    public List<OrdersEntity> findAll() {
        return orderRepository.findAll();
    }
}
