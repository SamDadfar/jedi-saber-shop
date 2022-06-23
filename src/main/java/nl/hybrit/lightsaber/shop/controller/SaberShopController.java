package nl.hybrit.lightsaber.shop.controller;

import nl.hybrit.lightsaber.shop.controller.model.CalculatedForceModel;
import nl.hybrit.lightsaber.shop.repository.enums.CrystalEnum;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.services.PadawanService;
import nl.hybrit.lightsaber.shop.services.SaberService;
import nl.hybrit.lightsaber.shop.util.Formatter;
import nl.hybrit.lightsabershop.model.Sabers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("Jedisabershop")
public class SaberShopController {

    @Autowired
    private SaberService saberService;
    @Autowired
    private PadawanService padawanService;


    @GetMapping("/test")
    public String test() {
        return "controller works well.";
    }

    @PostMapping("/padawan/calculator")
    @ResponseBody
    public ResponseEntity<CalculatedForceModel> getUsage(@RequestBody Sabers sabers, @RequestParam Map<String, String> queries) {
        int id = 0;
        LocalDate dob = null;
        String name = "";
        Sabers.Saber saber = new Sabers.Saber();
        if (queries.containsKey("dob")) dob = Formatter.getDate(queries.get("dob"));
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
        PadawanEntity padawan = new PadawanEntity(dob);
        CalculatedForceModel response = new CalculatedForceModel();
        response.setForce(padawan.getPower());
        response.setNeededForce(padawanService.getNeededForce(padawan, crystalEnum));
        double crystalPrice = saberService.getSaberPrice(padawan, crystalEnum);
        response.setCrystalPrice(Formatter.getFormattedPrice(crystalPrice));
        response.setCrystalType(crystalEnum.getType());
        return ResponseEntity.ok(response);

    }


}
