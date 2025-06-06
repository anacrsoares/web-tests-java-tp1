package tests.main;
import main.ScientificCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScientificCalculatorTest {
    // SETUP
    private ScientificCalculator calcTest;
    @BeforeEach
    void setUp(){
        calcTest = new ScientificCalculator();
    }

    // Ex02
    // EXECUTION - ADD
    @CsvSource(value = {
            "2.0, 3.0, 5.0",
            "-2.0, -3.0, -5.0",
            "5.0, -3.0, 2.0",
            "0.0, 7.0, 7.0",
            "7.0, 0.0, 7.0",
            "0.0, 0.0, 0.0",
            "1.5, 2.3, 3.8"
    })

    // ASSERTION - ADD
    @ParameterizedTest(name = "add({0}, {1}) = {2}")
    void testAddition(double firstEntry, double secondEntry, double expectedResult){
        assertEquals(expectedResult, calcTest.add(firstEntry, secondEntry), 0.000001);
    };

    // EX03
    // EXECUTION - SUBTRACT
    @CsvSource(value = {
            "2.0, 3.0, -1.0",
            "-2.0, -3.0, 1.0",
            "5.0, -3.0, 8.0",
            "0.0, 7.0, -7.0",
            "7.0, 0.0, 7.0",
            "0.0, 0.0, 0.0",
            "1.5, 2.3, -0.8"
    })

    // ASSERTION - SUBTRACT
    @ParameterizedTest(name = "subtract({0}, {1}) = {2}")
    void testSubtraction(double firstEntry, double secondEntry, double expectedResult){
        assertEquals(expectedResult, calcTest.subtract(firstEntry, secondEntry), 0.000001);
    };

    // EX05
    // EXECUTION - SQRT ROOT POSITIVE
    @CsvSource(value = {
            "4.0, 2.0",
            "9.0, 3.0",
    })

    // ASSERTION - SQRT ROOT POSITIVE
    @ParameterizedTest(name = "sqrt({0}) = {1}")
    void testSquareRootPositive(double entry, double expected) {
        assertEquals(expected, calcTest.squareRoot(entry));
    }

    // EX06
    // EXECUTION - SQRT NEGATIVE
    @CsvSource(value = {
            "-16.0",
            "-25.0"
    })

    // ASSERTION - SQRT ROOT NEGATIVE
    @ParameterizedTest(name = "sqrt({0}) throws IllegalArgumentException")
    void testSquareRootNegative(double input) {
        assertThrows(IllegalArgumentException.class, () -> calcTest.squareRoot(input));
    }

    // EX07
    // EXECUTION - DIV 0
    @CsvSource(value = {
            "10.0",
            "25.0"
    })

    // ASSERTION - DIV 0
    @ParameterizedTest(name = "divide({0}, 0) throws IllegalArgumentException")
    void testDivideByZero(double numerator) {
        assertThrows(IllegalArgumentException.class, () -> calcTest.divide(numerator, 0.0));
    }

    // EX08
    // EXECUTION - LOG
    @ParameterizedTest(name = "log({0}) = {1}")
    @CsvSource({
            "1.0, 0.0",
            "10.0, 2.302585",
            "100.0, 4.605170"
    })

    // ASSERTION - LOG
    void testLog(double input, double expected) {
        assertEquals(expected, calcTest.log(input), 0.00001);
    }

    // EXECUTION - LOG CONSTANTS
    static Stream<Arguments> provideLogConstants() {
        return Stream.of(
                Arguments.of(Math.E, 1.0),
                Arguments.of(Math.PI, 1.1447298858494002),
                Arguments.of(Math.pow(Math.E, 2), 2.0),
                Arguments.of(Math.pow(Math.E, 3), 3.0),
                Arguments.of(Math.sqrt(Math.E), 0.5)
        );
    }

    // ASSERTION - LOG CONSTANTS
    @ParameterizedTest(name = "log({0}) = {1}")
    @MethodSource("provideLogConstants")
    void testLogConstants(double input, double expected) {
        assertEquals(expected, calcTest.log(input), 0.00001);
    }

    // EXECUTION - SIN
    @ParameterizedTest(name = "sin({0}) = {1}")
    @CsvSource({
            "0.0, 0.0",
            "30.0, 0.5",
            "90.0, 1.0",
            "180.0, 0.0",
            "270.0, -1.0",
            "360.0, 0.0"
    })

    // ASSERTION - SIN
    void testSin(double degrees, double expected) {
        assertEquals(expected, calcTest.sin(degrees), 0.00001);
    }
}
