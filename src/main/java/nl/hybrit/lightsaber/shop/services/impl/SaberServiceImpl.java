package nl.hybrit.lightsaber.shop.services.impl;

import nl.hybrit.lightsaber.shop.mapper.SaberMapper;
import nl.hybrit.lightsaber.shop.repository.SaberRepository;
import nl.hybrit.lightsaber.shop.repository.enums.CrystalEnum;
import nl.hybrit.lightsaber.shop.repository.model.SaberEntity;
import nl.hybrit.lightsaber.shop.services.SaberService;
import nl.hybrit.lightsabershop.model.Sabers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaberServiceImpl implements SaberService {

    @Autowired
    private SaberMapper mapper;
    @Autowired
    private SaberRepository saberRepository;

    @Override
    public List<SaberEntity> save(Sabers sabers) {
        final List<SaberEntity> list = new ArrayList<>(sabers.getSaber().size());
        sabers.getSaber().forEach(saber -> {
            list.add(saberRepository.findBySaberId(saber.getId())
                    .map((SaberEntity s) -> {
                        s.setName(saber.getName());
                        s.setAvailable(saber.getAvailable());
                        s.setCrystalName(saber.getCrystal().getName());
                        s.setCrystalType(CrystalEnum.fromValue(saber.getCrystal().getColor().value()));
                        return saberRepository.save(s);})
                    .orElseGet(() -> saberRepository.save(mapper.map(saber))));
        });
        return list;
    }

    @Override
    public Sabers findAll() {
        return mapper.toSaberList(saberRepository.findAll());
    }

    @Override
    public Sabers.Saber findById(long id) {
        SaberEntity entity = saberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return mapper.map(entity);
    }

    @Override
    public Sabers.Saber findBySaberId(int saberId) {
        SaberEntity entity = saberRepository.findBySaberId(saberId).orElseThrow(IllegalArgumentException::new);
        return mapper.map(entity);
    }
}
