package softuni.webproject.services.services.doctor.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.IdentificationKey;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.IdentificationKeyRepository;
import softuni.webproject.services.services.doctor.IdentificationKeyService;

@Service
public class IdentificationKeyServiceImpl implements IdentificationKeyService {
    private final IdentificationKeyRepository keyRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public IdentificationKeyServiceImpl(IdentificationKeyRepository keyRepository, DoctorRepository doctorRepository) {
        this.keyRepository = keyRepository;
        this.doctorRepository = doctorRepository;
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
