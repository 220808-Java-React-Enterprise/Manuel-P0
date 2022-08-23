package com.revature.LettuceInn.services;

import com.revature.LettuceInn.daos.PaintingDAO;
import com.revature.LettuceInn.utils.custom_exceptions.InvalidPaintingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PaintingServiceTest {

    /* dependency injection */
    private PaintingService sut; // sut = system under test
    private final PaintingDAO mockPaintingDAO = mock(PaintingDAO.class);

    /* jank constructor */
    @Before
    public void setup() {
        sut = new PaintingService(mockPaintingDAO);
    }

    /*
        Common JUnit annotations:
            - @Test (marks a method as a test case)
            - @Ignore (tells JUnit to skip this test case)
            - @Before (logic that runs once before every test case)
            - @After (logic that runs once after every test case)
            - @BeforeClass (logic that runs only once before all test cases)
            - @AfterClass (logic that runs only once after all test cases)
     */
/*
    @Test
    public void test_isValidUsername_givenCorrectUsername() {
        // Arrange
        String validUsername = "bduong0929";

        // Act
        boolean flag = sut.isValidUsername(validUsername);

        // Assert
        Assert.assertTrue(flag);
    }


 */
    @Test
    public void test_isValidCost_givenCorrectNumber(){
        double valid = 1234.48;
        boolean flag = sut.isValidCost(valid);

        Assert.assertTrue(flag);
    }

    @Test
    public void test_isValidCost_givenNoDecimals(){
        double validDouble = 1234;
        boolean flag = sut.isValidCost(validDouble);
        Assert.assertTrue(flag);

    }

    @Test(expected = InvalidPaintingException.class)
    public void test_isValidCost_givenInvalidLargeDecimal(){
        double invalidDouble = 1234.48201;
        sut.isValidCost(invalidDouble);

    }

    @Test
    public void test_isValidName_givenValidName(){
        String validName = "Manuel Lopez";
        boolean flag = sut.isValidName(validName);
        Assert.assertTrue(flag);
    }

    @Test(expected = InvalidPaintingException.class)
    public void test_isValidName_givenInvalidName(){
        String invalidName = "sada!@3123mas";
        sut.isValidCreator(invalidName);
    }

    @Test(expected = InvalidPaintingException.class)
    public void test_isValidName_givenNothing(){
        String invalidName = "";
        sut.isValidCreator(invalidName);
    }

}