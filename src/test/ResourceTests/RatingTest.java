package ResourceTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import resource.Rating;

public class RatingTest {

    @Test
    public void getDiffTestNull() {
        Rating.decreaseThreshold = 0;
        Rating.getDiff(14, 6);
    }

    @Test
    public void getDiffTest() {
        Rating.decreaseThreshold = 4;
        Rating.getDiff(3, 6);
    }

}
