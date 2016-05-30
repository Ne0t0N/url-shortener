package de.happycar.tryout.bean.type;

import java.util.Arrays;

/**
 * User: Ne0t0N
 * Date: 30.05.2016
 */
public enum Message {

    INCORRECT_LINK("Provided incorrect link"),
    NO_UTMS("Link does not contain all necessary UTM fields - " + Arrays.toString(UTM.values())),
    ALREADY_EXISTS("Link already registered"),
    NOT_REGISTERED("Link was not registered before"),
    OK("Link registered successfully");

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
