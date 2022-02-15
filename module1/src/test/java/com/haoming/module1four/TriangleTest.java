package com.haoming.module1four;

import static org.junit.Assert.*;

public class TriangleTest {

    @org.junit.Test
    public void testGetArea() {
        // 5 8
        assertEquals((5 * 8)/2, Triangle.getArea(5, 8));
    }


}