package tests.main;

import main.ScientificCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestAddition {
    @Test
    void testAddition(){
        // ARRANGE
        ScientificCalculator calcAdd = new ScientificCalculator();

        // ACT
        double actual = calcAdd.add(1.0, 1.0);
        double expected = 2.0;
        double unexpected = 2.1;

        // ASSERT
        Assertions.assertEquals(actual, expected);
        Assertions.assertEquals(actual, unexpected);
    }
}
