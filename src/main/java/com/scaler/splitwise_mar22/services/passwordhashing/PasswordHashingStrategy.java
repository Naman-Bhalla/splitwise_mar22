package com.scaler.splitwise_mar22.services.passwordhashing;

public interface PasswordHashingStrategy {
    String hash(String originalPassword);
}
