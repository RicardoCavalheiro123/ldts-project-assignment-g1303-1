import com.aor.BomberMan;
import com.aor.Leaderboard.Lead;
import com.aor.Leaderboard.Leaderboard;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException, InterruptedException {
        Leaderboard l = new Leaderboard("src/main/resources/leaderboard/leaderboard.txt");
        ArrayList<Lead> lss = new ArrayList<Lead>();
        lss = l.getLeaderboardsList();
        BomberMan game = BomberMan.getInstance();
        game.start();
    }
}
