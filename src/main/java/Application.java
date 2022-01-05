import com.aor.game.Game;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game();
        game.start();
        game.run();
        return;
    }
}
