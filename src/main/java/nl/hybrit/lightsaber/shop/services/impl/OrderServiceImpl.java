package nl.hybrit.lightsaber.shop.services.impl;

import nl.hybrit.lightsaber.shop.controller.model.OrderModel;
import nl.hybrit.lightsaber.shop.mapper.SaberMapper;
import nl.hybrit.lightsaber.shop.repository.OrderRepository;
import nl.hybrit.lightsaber.shop.repository.PadawanRepository;
import nl.hybrit.lightsaber.shop.repository.SaberRepository;
import nl.hybrit.lightsaber.shop.repository.model.OrderEntity;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.repository.model.SaberEntity;
import nl.hybrit.lightsaber.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Transactional
    public OrderEntity seveOrder(OrderModel model) {
        OrderEntity orderEntity = new OrderEntity();
        List<SaberEntity> saberEntities = new ArrayList<>();
        PadawanEntity padawan = padawanRepository.findById(model.getPadawanId()).orElseThrow(IllegalArgumentException::new);
        model.getSabers().getSaber().forEach((saber) -> {
            SaberEntity entity = saberRepository.findBySaberId(saber.getId())
                    .orElseThrow(IllegalArgumentException::new);
            entity.setAvailable(entity.getAvailable() - 1);
            padawan.getSabers().add(entity);
            saberEntities.add(entity);
        });
        orderEntity.setPadawan(padawan);
        orderEntity.setSabers(saberEntities);
        orderEntity.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity findById(long id) {
        return orderRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
