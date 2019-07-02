import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SampleTest {

    @Test
    public void add() {
        assertThat(Sample.add(2, 3), is(5));
    }
}