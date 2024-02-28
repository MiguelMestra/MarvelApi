package com.example.marvel.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class HashUtilTest {
    @Test
    void getMD5() {
        String testMessage = "this is a text example for MD5 encode";
        assertEquals("d326ddc2c0610732225cc9a7569fa413",HashUtil.getMD5(testMessage));

    }
}