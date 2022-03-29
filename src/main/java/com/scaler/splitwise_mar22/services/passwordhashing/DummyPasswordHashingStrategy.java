package com.scaler.splitwise_mar22.services.passwordhashing;

import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class DummyPasswordHashingStrategy implements PasswordHashingStrategy {
    @Override
    public String hash(String originalPassword) {
        return originalPassword + "hashed";
    }
}
