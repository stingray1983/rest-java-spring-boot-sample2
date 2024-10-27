package com.example.controller;

import com.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<String> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public String addItem(@RequestBody String item) {
        return itemService.addItem(item);
    }

    @DeleteMapping("/{item}")
    public String deleteItem(@PathVariable String item) {
        return itemService.deleteItem(item);
    }
}