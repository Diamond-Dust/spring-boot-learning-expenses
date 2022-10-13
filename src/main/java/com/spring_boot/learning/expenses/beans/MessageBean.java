package com.spring_boot.learning.expenses.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class MessageBean {
    @Getter
    @Setter
    private String message;

    public MessageBean(String message) {
        this.message = message;
    }

    public String toString() {
        return String.format("MessageBean [message=%s]", message);
    }
}
