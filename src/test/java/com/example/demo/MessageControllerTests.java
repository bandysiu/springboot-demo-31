package com.example.demo;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MessageControllerTests {

    public static final String BE_SUCCESS_MESSAGE = "BE works";
    public static final String DB_SUCCESS_MESSAGE = "DB success message";
    public static final String DB_ERROR_MESSAGE = "DB error message";
    @Mock
    private MessageRepository repository;

    @InjectMocks
    private MessageController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldBEandDBWork() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Message(BE_SUCCESS_MESSAGE)));
        assertEquals(MessageController.BE_SUCCESS_MESSAGE + MessageController.DB_SUCCESS_MESSAGE + DB_SUCCESS_MESSAGE, controller.getMessage());
    }
    @Test
    public void shouldWorkOnlyBE() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(MessageController.DB_ERROR_MESSAGE, controller.getMessage());
    }
}
