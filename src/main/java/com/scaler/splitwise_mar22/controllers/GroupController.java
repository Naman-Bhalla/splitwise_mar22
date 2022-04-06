package com.scaler.splitwise_mar22.controllers;

import com.scaler.splitwise_mar22.dtos.SettleUpGroupRequestDTO;
import com.scaler.splitwise_mar22.dtos.SettleUpResponseDTO;
import com.scaler.splitwise_mar22.models.Transaction;
import com.scaler.splitwise_mar22.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    /**
     * Returns the list of transactions when
     * made will settle up every member in the
     * group with every other member of the group
     * for all the expenses that were made in the group.
     * @param request
     * @return
     * Sample Output:
     * User ID: 1
     * 1 -> 2: 300
     * 1 -> 4: 500
     * 4 -> 3: 21
     */
    public SettleUpResponseDTO settleUp(
            SettleUpGroupRequestDTO request) {
        List<Transaction> transactions = groupService
                .settleUp(request.getGroupId());

        SettleUpResponseDTO response = new SettleUpResponseDTO();
        response.setTransactions(transactions);

        return response;
    }
}
