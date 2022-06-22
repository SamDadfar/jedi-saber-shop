package nl.hybrit.lightsaber.shop.controller;

import nl.hybrit.lightsaber.shop.repository.model.SaberEntity;
import nl.hybrit.lightsaber.shop.services.SaberService;
import nl.hybrit.lightsabershop.model.Sabers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Jedisabershop")
public class SaberController {

    @Autowired
    private SaberService saberService;

    @GetMapping("/saber")
    @ResponseBody
    public ResponseEntity<Sabers> getSabers() {

        return ResponseEntity.ok(saberService.findAll());
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

}
