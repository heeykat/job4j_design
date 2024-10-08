package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void whenGetMapShouldThrowCollectionContainsNoData() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenParseShouldThrowNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] array = new String[0];
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void whenParseShouldThrowNameDoesNotContainTheSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] array = new String[] {"keyvalue"};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("keyvalue does not contain the symbol '='");
    }

    @Test
    void whenParseShouldThrowNameDoesNotContainAKey() {
        NameLoad nameLoad = new NameLoad();
        String[] array = new String[] {"= value"};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("= value does not contain a key");
    }

    @Test
    void whenParseShouldThrowNameDoesNotContainAValue() {
        NameLoad nameLoad = new NameLoad();
        String[] array = new String[] {"key ="};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key = does not contain a value");
    }
}