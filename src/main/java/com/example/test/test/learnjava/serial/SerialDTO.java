package com.example.test.test.learnjava.serial;

import java.io.Serializable;

public class SerialDTO implements Serializable {
    private String bookName;
    transient private int bookOrder;
    private boolean bestSeller;
    private long soldPerDay;
    static final long serialVersionUID=1L;
    private String bookType = "IT";

    public SerialDTO(String bookName, int bookOrder, boolean bestSeller, long soldPerDay) {
        this.bookName = bookName;
        this.bookOrder = bookOrder;
        this.bestSeller = bestSeller;
        this.soldPerDay = soldPerDay;
    }

    @Override
    public String toString() {
        return "SerialDTO{" +
                "bookName='" + bookName + '\'' +
                ", bookOrder=" + bookOrder +
                ", bestSeller=" + bestSeller +
                ", soldPerDay=" + soldPerDay +
                ", bookType='" + bookType + '\'' +
                '}';
    }
}
