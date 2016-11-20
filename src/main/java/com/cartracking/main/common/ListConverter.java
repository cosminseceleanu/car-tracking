package com.cartracking.main.common;

import java.util.ArrayList;
import java.util.List;

public class ListConverter<T> {

    private Iterable<T> items;

    public ListConverter(Iterable<T> items) {
        this.items = items;
    }

    public List<T> convert() {
        List<T> list = new ArrayList<>();
        for (T item : items) {
            list.add(item);
        }

        return list;
    }
}
