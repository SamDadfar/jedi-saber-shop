package nl.hybrit.lightsaber.shop.controller;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.repository.UserRepository;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.repository.model.UserEntity;
import nl.hybrit.lightsaber.shop.services.PadawanService;
import nl.hybrit.lightsaber.shop.services.UserService;
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
public class PadawanController extends ResouSu{

    @Autowired
    private PadawanService service;
    @Autowired
    private UserService userService;

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

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
}
