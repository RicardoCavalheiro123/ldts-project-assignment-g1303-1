import com.aor.Game.Game;

import java.awt.*;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException, FontFormatException {
        Game game = new Game();
        game.start();
        game.run();
        return;
    }
}
