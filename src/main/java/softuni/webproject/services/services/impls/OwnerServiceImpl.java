package softuni.webproject.services.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.webproject.data.entities.Owner;
import softuni.webproject.data.repositories.OwnerRepository;
import softuni.webproject.services.models.OwnerServiceModel;
import softuni.webproject.services.services.OwnerService;

public class OwnerServiceImpl implements OwnerService {
    private final ModelMapper modelMapper;
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(ModelMapper modelMapper, OwnerRepository ownerRepository) {
        this.modelMapper = modelMapper;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public OwnerServiceModel save(OwnerServiceModel model) {
        this.ownerRepository.save(this.modelMapper.map(model, Owner.class));
        return model;
    }
}
