import org.junit.jupiter.api.Test;
import ru.spb.coverage.SortUtils;

import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class SortUtilsTest {

    @Test
    void shouldSortArrayByBubbleSort() {
        //when
        var random = new Random();
        var array = IntStream.generate(() -> random.nextInt(100))
                .limit(100)
                .toArray();

        //do
        SortUtils.bubbleSort(array);

        //verify
        assertThat(array)
                .isNotNull()
                .isNotEmpty()
                .hasSize(100)
                .isSorted();
    }

    @Test
    void shouldNotInsertSortNullArray() {
        //do
        SortUtils.insertSort(null);
    }
}
