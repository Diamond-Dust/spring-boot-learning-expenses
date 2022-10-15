package com.spring_boot.learning.expenses.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    @Getter
    @Setter
    Date timestamp;
    @Getter
    @Setter
    String message;
    @Getter
    @Setter
    String detail;
}
