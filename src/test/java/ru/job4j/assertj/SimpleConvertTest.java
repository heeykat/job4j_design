package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void whenCheckArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "third", "fourth", "fifth");
        assertThat(array)
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "sixth")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void whenCheckList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "third", "fourth", "fifth");
        assertThat(list)
                .startsWith("first")
                .doesNotContain("sixth")
                .containsSequence("third", "fourth")
                .filteredOn(e -> e.startsWith("s")).containsAnyOf("second", "sixth")
                .element(0).isNotNull();
    }

    @Test
    void whenCheckSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "third", "fourth", "fifth");
        assertThat(set)
                .containsOnly("fifth", "second", "first", "fourth", "third")
                .containsAnyOf("sixth", "tenth", "fifth")
                .anyMatch(e -> e.length() == 5)
                .noneMatch(e -> e.length() == 8);
    }

    @Test
    void whenCheckMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "third", "fourth", "fifth");
        assertThat(map)
                .hasSize(5)
                .containsKey("first")
                .containsValues(0, 2, 4)
                .containsEntry("third", 2)
                .allSatisfy((key, value) -> assertThat(value).isLessThan(6));

    }
}