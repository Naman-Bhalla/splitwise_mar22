package com.scaler.splitwise_mar22.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ResponseDTO<T> {
    private ResponseStatus status;
    private T data;
}

// {
//   status: SUCCESS,
//   data: {
//
//   }
// }
// ResponseDTO<Integer>
//
// class ArrayList<T>
