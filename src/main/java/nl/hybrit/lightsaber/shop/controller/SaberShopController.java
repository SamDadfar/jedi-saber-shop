package nl.hybrit.lightsaber.shop.controller;

import nl.hybrit.lightsaber.shop.controller.model.AddedOrderResponseModel;
import nl.hybrit.lightsaber.shop.controller.model.CalculatedForceModel;
import nl.hybrit.lightsaber.shop.controller.model.OrderModel;
import nl.hybrit.lightsaber.shop.repository.enums.CrystalEnum;
import nl.hybrit.lightsaber.shop.repository.model.OrdersEntity;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.repository.model.SaberEntity;
import nl.hybrit.lightsaber.shop.services.OrderService;
import nl.hybrit.lightsaber.shop.services.PadawanService;
import nl.hybrit.lightsaber.shop.services.SaberService;
import nl.hybrit.lightsaber.shop.util.Formatter;
import nl.hybrit.lightsabershop.model.Sabers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Jedisabershop")
public class SaberShopController {

    @Autowired
    private SaberService saberService;
    @Autowired
    private PadawanService padawanService;
    @Autowired
    private OrderService orderService;


    @GetMapping("/test")
    public String test() {
        return "controller works well.";
    }

    @PostMapping("/padawan/calculator")
    @ResponseBody
    public ResponseEntity<CalculatedForceModel> getUsage(@RequestBody Sabers sabers, @RequestParam Map<String, String> queries) {
        int age = 0, id = 0;
        String name = "";
        Sabers.Saber saber = new Sabers.Saber();
        if (queries.containsKey("z")) age = Integer.parseInt(queries.get("z"));
        if (queries.containsKey("name")) name = queries.get("name");
        if (queries.containsKey("id")) id = Integer.parseInt(queries.get("id"));
        if (name.isEmpty() && id != 0) {
            int finalId = id;
            saber = sabers.getSaber().stream()
                    .filter(s -> s.getId() == finalId)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        } else if (!name.isEmpty() && id == 0) {
            String finalName = name;
            saber = sabers.getSaber().stream()
                    .filter(s -> s.getName().equalsIgnoreCase(finalName))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
        CrystalEnum crystalEnum = CrystalEnum.fromValue(saber.getCrystal().getColor().value());
        PadawanEntity padawan = new PadawanEntity(age);
        CalculatedForceModel response = new CalculatedForceModel();
        response.setForce(padawan.getForce());
        response.setNeededForce(padawanService.getNeededForce(padawan, crystalEnum));
        double crystalPrice = saberService.getSaberPrice(padawan, crystalEnum);
        response.setCrystalPrice(Formatter.getFormattedPrice(crystalPrice));
        response.setCrystalType(crystalEnum.getType());
        return ResponseEntity.ok(response);

    }

    @GetMapping("/saber")
    @ResponseBody
    public ResponseEntity<Sabers> getSabers() {

        return ResponseEntity.ok(saberService.findAll());
    }

    @GetMapping("/order")
    @ResponseBody
    public ResponseEntity<List<OrdersEntity>> getOrders() {

        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/saber/{id}")
    @ResponseBody
    public ResponseEntity<Sabers.Saber> getSaberById(@PathVariable long id) {
        try {
            Sabers.Saber saber = saberService.findById(id);
            return ResponseEntity.ok(saber);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/saber")
    @ResponseBody
    public ResponseEntity<List<SaberEntity>> addSabers(@RequestBody Sabers sabers) {
        List<SaberEntity> list = saberService.save(sabers);
        return ResponseEntity.status(205).body(list);
    }

    @PostMapping("/order/saber/{id}")
    @ResponseBody
    public ResponseEntity<AddedOrderResponseModel> addOrder(@Valid @RequestBody Sabers sabers, @PathVariable long id) {
        OrderModel order = new OrderModel();
        order.setSabers(sabers);
        order.setPadawanId(id);
        OrdersEntity savedOrder = orderService.seveOrder(order);
        AddedOrderResponseModel response = new AddedOrderResponseModel();
//        response.setLightSaberNames(savedOrder.getSabers().stream().map(SaberEntity::getCrystalName).collect(Collectors.toList()));
        response.setMessage("order successful");
        return ResponseEntity.status(201).body(response);
    }


}
