package tests.main;

import main.ScientificCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ScientificCalculatorTest11 {

    // SETUP
    private ScientificCalculator calcTest;

    @BeforeEach
    void setUp() {
        calcTest = new ScientificCalculator();
    }

    // =====================================
    // TESTES BÁSICOS: SOMA E SUBTRAÇÃO
    // =====================================

    @ParameterizedTest(name = "add({0}, {1}) = {2}")
    @CsvSource({
            "2.0, 3.0, 5.0",
            "-2.0, -3.0, -5.0",
            "5.0, -3.0, 2.0",
            "0.0, 7.0, 7.0",
            "7.0, 0.0, 7.0",
            "0.0, 0.0, 0.0",
            "1.5, 2.3, 3.8"
    })
    void testAddition(double a, double b, double expected) {
        assertEquals(expected, calcTest.add(a, b), 0.000001);
    }

    @ParameterizedTest(name = "subtract({0}, {1}) = {2}")
    @CsvSource({
            "2.0, 3.0, -1.0",
            "-2.0, -3.0, 1.0",
            "5.0, -3.0, 8.0",
            "0.0, 7.0, -7.0",
            "7.0, 0.0, 7.0",
            "0.0, 0.0, 0.0",
            "1.5, 2.3, -0.8"
    })
    void testSubtraction(double a, double b, double expected) {
        assertEquals(expected, calcTest.subtract(a, b), 0.000001);
    }

    // =====================================
    // TESTES DE RAIZ QUADRADA
    // =====================================

    @ParameterizedTest(name = "sqrt({0}) = {1}")
    @CsvSource({
            "4.0, 2.0",
            "9.0, 3.0"
    })
    void testSquareRootPositive(double input, double expected) {
        assertEquals(expected, calcTest.squareRoot(input), 0.000001);
    }

    @ParameterizedTest(name = "sqrt({0}) throws IllegalArgumentException")
    @CsvSource({
            "-16.0",
            "-25.0"
    })
    void testSquareRootNegative(double input) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calcTest.squareRoot(input);
        });
        System.out.println("Mensagem de exceção: " + exception.getMessage());
        assertEquals("Negative number", exception.getMessage());
    }

    // =====================================
    // TESTES DE DIVISÃO
    // =====================================

    @ParameterizedTest(name = "divide({0}, 0) throws IllegalArgumentException")
    @CsvSource({
            "10.0",
            "25.0"
    })
    void testDivideByZero(double numerator) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calcTest.divide(numerator, 0.0);
        });
        System.out.println("Mensagem de exceção: " + exception.getMessage());
        assertEquals("Division by zero", exception.getMessage());
    }

    // =====================================
    // TESTES DE LOGARITMO
    // =====================================

    @ParameterizedTest(name = "log({0}) = {1}")
    @CsvSource({
            "1.0, 0.0",
            "10.0, 2.302585",
            "100.0, 4.605170"
    })
    void testLog(double input, double expected) {
        assertEquals(expected, calcTest.log(input), 0.00001);
    }

    static Stream<Arguments> provideLogConstants() {
        return Stream.of(
                Arguments.of(Math.E, 1.0),
                Arguments.of(Math.PI, 1.1447298858494002),
                Arguments.of(Math.pow(Math.E, 2), 2.0),
                Arguments.of(Math.pow(Math.E, 3), 3.0),
                Arguments.of(Math.sqrt(Math.E), 0.5)
        );
    }

    @ParameterizedTest(name = "log({0}) = {1}")
    @MethodSource("provideLogConstants")
    void testLogConstants(double input, double expected) {
        assertEquals(expected, calcTest.log(input), 0.00001);
    }

    @Test
    void testLogWithNegativeInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calcTest.log(-1);
        });
        System.out.println("Mensagem de exceção: " + exception.getMessage());
        assertEquals("Log of non-positive number", exception.getMessage());
    }

    // =====================================
    // TESTES DE FUNÇÕES TRIGONOMÉTRICAS
    // =====================================

    @ParameterizedTest(name = "sin({0}) = {1}")
    @CsvSource({
            "0.0, 0.0",
            "30.0, 0.5",
            "90.0, 1.0",
            "180.0, 0.0",
            "270.0, -1.0",
            "360.0, 0.0"
    })
    void testSin(double degrees, double expected) {
        assertEquals(expected, calcTest.sin(degrees), 0.00001);
    }
}
