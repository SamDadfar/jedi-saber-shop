package nl.hybrit.lightsaber.shop.services.impl;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.mapper.PadawanMapper;
import nl.hybrit.lightsaber.shop.repository.PadawanRepository;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.services.PadawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class PadawanServiceImpl implements PadawanService {

    @Autowired
    private PadawanRepository padawanRepository;
    @Autowired
    private PadawanMapper mapper;

    public final static float ENOUGH_FORCE_AS_JEDI = 93.2f;
    public final static int ADULT_AGE = 18;
    public final static int JEDI_DISSOLVE_CONSTANT = 140;
    /**
     * {@inheritDoc}
     */
    @Override
    public PadawanEntity findById(long id) {
        return padawanRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public PadawanEntity save(PadawanUserModel model) {
        return padawanRepository.save(mapper.map(model));
    }
}
