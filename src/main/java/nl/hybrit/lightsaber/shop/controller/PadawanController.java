package nl.hybrit.lightsaber.shop.controller;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.services.PadawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Padawan resources
 */
@RestController
@RequestMapping("Jedisabershop/padawan")
public class PadawanController {

    @Autowired
    private PadawanService service;


    @PostMapping
    @ResponseBody
    public ResponseEntity<PadawanEntity> addPadawan(@RequestBody @Valid PadawanUserModel model) {
        return ResponseEntity.ok(service.save(model));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PadawanEntity>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
