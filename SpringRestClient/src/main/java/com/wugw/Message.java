package com.wugw;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * message
 */
@Getter
@Setter
public class Message implements Serializable {
    private int no;

    private String detail;

    public Message() {
    }

    public Message(int no, String detail) {
        this.no = no;
        this.detail = detail;
    }

}
