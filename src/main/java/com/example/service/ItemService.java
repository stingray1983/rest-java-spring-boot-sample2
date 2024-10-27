package com.example.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private List<String> items = new ArrayList<>();

    public List<String> getAllItems() {
        return items;
    }

    public String addItem(String item) {
        items.add(item);
        return "Item added: " + item;
    }

    public String deleteItem(String item) {
        if (items.remove(item)) {
            return "Item deleted: " + item;
        } else {
            return "Item not found: " + item;
        }
    }
}