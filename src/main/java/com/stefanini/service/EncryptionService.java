package com.stefanini.service;

import javax.enterprise.context.ApplicationScoped;
import java.util.Base64;

@ApplicationScoped
public class EncryptionService {

    public String encode(String src) {
        return Base64.getEncoder().encodeToString(src.getBytes());
    }

    public String decode(String encodedString) {
        var bytes = Base64.getDecoder().decode(encodedString);
        return new String(bytes);
    }
}
