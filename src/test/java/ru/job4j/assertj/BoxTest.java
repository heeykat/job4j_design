package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void whenWhatsThisShouldReturnSphere() {
        Box box = new Box(0, 10);
        String actual = box.whatsThis();
        assertThat(actual).isEqualTo("Sphere").isNotEmpty();
    }

    @Test
    void whenWhatsThisShouldReturnUnknownObject() {
        Box box = new Box(2, 2);
        String actual = box.whatsThis();
        assertThat(actual).isEqualTo("Unknown object").isNotEmpty();
    }

    @Test
    void whenGetNumberOfVerticesShouldReturn4() {
        Box box = new Box(4, 2);
        int actual = box.getNumberOfVertices();
        assertThat(actual).isEqualTo(4).isPositive();
    }

    @Test
    void whenGetNumberOfVerticesShouldReturnMinus1() {
        Box box = new Box(0, -2);
        int actual = box.getNumberOfVertices();
        assertThat(actual).isEqualTo(-1).isNotPositive();
    }

    @Test
    void whenIsExistShouldReturnTrue() {
        Box box = new Box(0, 10);
        boolean actual = box.isExist();
        assertThat(actual).isTrue().isNotEqualTo(false);
    }

    @Test
    void whenIsExistShouldReturnFalse() {
        Box box = new Box(1, 10);
        boolean actual = box.isExist();
        assertThat(actual).isFalse().isNotEqualTo(true);
    }

    @Test
    void whenGetAreaReturn0() {
        Box box = new Box(2, 10);
        double actual = box.getArea();
        double expected = 0;
        assertThat(actual).isEqualTo(expected).isNotPositive();
    }

    @Test
    void whenGetAreaReturnCubeArea() {
        Box box = new Box(8, 10);
        double actual = box.getArea();
        double expected = 6 * (10 * 10);
        assertThat(actual).isEqualTo(expected, withPrecision(0.006))
                .isCloseTo(expected, withPrecision(0.01d));
    }
}