package com.scaler.splitwise_mar22.dtos;

import com.scaler.splitwise_mar22.models.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpResponseDTO {
    private List<Transaction> transactions;
}
