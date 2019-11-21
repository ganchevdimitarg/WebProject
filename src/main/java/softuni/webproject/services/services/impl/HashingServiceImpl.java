package softuni.webproject.services.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import softuni.webproject.services.services.HashingService;

@Service
public class HashingServiceImpl implements HashingService {
    @Override
    public String hash(String str) {
        return DigestUtils.sha256Hex(str);
    }
}