package nl.hybrit.lightsaber.shop.controller;


import nl.hybrit.lightsaber.shop.controller.model.OrderModel;
import nl.hybrit.lightsaber.shop.controller.model.response.AddedOrderResponseModel;
import nl.hybrit.lightsaber.shop.repository.model.OrderEntity;
import nl.hybrit.lightsaber.shop.repository.model.SaberEntity;
import nl.hybrit.lightsaber.shop.services.OrderService;
import nl.hybrit.lightsabershop.model.Sabers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * orders resources
 */
@RestController
@RequestMapping("Jedisabershop/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize("hasAuthority('ADMIN_JEDI')")
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
        try {
            return  ResponseEntity.ok(service.findById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN_JEDI')")
    @PostMapping("/saber/{id}")
    @ResponseBody
    public ResponseEntity<AddedOrderResponseModel> addOrder(@Valid @RequestBody Sabers sabers, @PathVariable long id) {
        OrderModel order = new OrderModel();
        order.setSabers(sabers);
        order.setPadawanId(id);
        OrderEntity savedOrder = service.seveOrder(order);
        AddedOrderResponseModel response = new AddedOrderResponseModel();
        response.setLightSaberNames(savedOrder.getSabers().stream().map(SaberEntity::getCrystalName).collect(Collectors.toList()));
        response.setMessage("Order has been successfully created!");
        return ResponseEntity.status(201).body(response);
    }
}
