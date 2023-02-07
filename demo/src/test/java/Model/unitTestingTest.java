package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class unitTestingTest {

    @Test
    void deposit() throws Exception {
        unitTesting test = new unitTesting("nandu" , 25.00);
       //
        double balance = test.deposit(25.00 , 50.00);
        assertEquals(75.00 , balance , 0);
        //we add the delta value to compare the accuracy here we need both the values to be 75 but we give delta as 1 then for 75 and 74 & 75 and 76 the output will be true
    }




}