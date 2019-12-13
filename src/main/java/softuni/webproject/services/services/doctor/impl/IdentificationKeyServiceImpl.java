package softuni.webproject.services.services.doctor.impl;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.IdentificationKey;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.IdentificationKeyRepository;
import softuni.webproject.services.models.IdentificationKeyServiceModel;
import softuni.webproject.services.services.doctor.IdentificationKeyService;

@Service
@AllArgsConstructor
public class IdentificationKeyServiceImpl implements IdentificationKeyService {
    private final IdentificationKeyRepository keyRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    public IdentificationKeyServiceModel findByKeyName(String keyName) {
        return modelMapper.map(keyRepository.findByLogKey(keyName), IdentificationKeyServiceModel.class);
    }

    @Override
    public void generateKey() {
        if (keyRepository.findAll().size() <= doctorRepository.findAll().size()){
            for (int i = 0; i < 10; i++) {
                IdentificationKey key = new IdentificationKey();
                key.setLogKey(randomKey());
                key.setFree(true);
                keyRepository.save(key);
            }
        }
    }

    @Override
    public void update(boolean state, String key) {
        keyRepository.update(state, key);
    }


    private String randomKey(){
        StringBuilder result = new StringBuilder(6);

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        for (int i = 0; i < 6; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            result.append(AlphaNumericString.charAt(index));
        }
        return result.toString();
    }
}
