import com.aor.BomberMan;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        BomberMan game = BomberMan.getInstance();
        game.start();
    }
}
