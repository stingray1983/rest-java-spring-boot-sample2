package com.example.controller;

import com.example.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    public void testGetAllItems() throws Exception {
        when(itemService.getAllItems()).thenReturn(Arrays.asList("Item1", "Item2"));

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"Item1\", \"Item2\"]"));
    }

    @Test
    public void testAddItem() throws Exception {
        String item = "NewItem";
        when(itemService.addItem(item)).thenReturn("Item added: " + item);

        mockMvc.perform(post("/items").content(item).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("Item added: NewItem"));
    }

    @Test
    public void testDeleteItem() throws Exception {
        String item = "Item1";
        when(itemService.deleteItem(item)).thenReturn("Item deleted: " + item);

        mockMvc.perform(delete("/items/{item}", item))
                .andExpect(status().isOk())
                .andExpect(content().string("Item deleted: Item1"));
    }
}