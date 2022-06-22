package nl.hybrit.lightsaber.shop.services.impl;

import nl.hybrit.lightsaber.shop.mapper.SaberMapper;
import nl.hybrit.lightsaber.shop.repository.SaberRepository;
import nl.hybrit.lightsaber.shop.repository.model.SaberEntity;
import nl.hybrit.lightsaber.shop.services.SaberService;
import nl.hybrit.lightsabershop.model.Sabers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaberServiceImpl implements SaberService {

    @Autowired
    private SaberMapper mapper;
    @Autowired
    private SaberRepository saberRepository;

    @Override
    public List<SaberEntity> save(Sabers sabers) {
        List<SaberEntity> list = mapper.toSaberList(sabers);
        return list.stream()
                .map(saber -> saberRepository.save(saber))
                .collect(Collectors.toList());
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
