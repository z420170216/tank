import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {

    @Test
    void test() {
        try {
            BufferedImage read = ImageIO.read(new File("F:/学习资料/tank-master/tank/src/images/tank.png"));
            assert (read != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
