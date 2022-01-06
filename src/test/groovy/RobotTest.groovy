import com.aor.Element.Robot
import com.aor.game.Position
import spock.lang.Specification

class RobotTest extends Specification {
    def "MoveUp"() {
        given:
            def robot = new Robot(1,1);
        when:
            robot.moveUp();
        then:
            robot.getPosition() == new Position(1,0);
    }

    def "MoveDown"() {
        given:
            def robot = new Robot(1,1);
        when:
            robot.moveDown();
        then:
            robot.getPosition() == new Position(1,2);
    }

    def "MoveLeft"() {
        given:
            def robot = new Robot(1,1);
        when:
            robot.moveLeft();
        then:
            robot.getPosition() == new Position(0,1);
    }

    def "MoveRight"() {
        given:
            def robot = new Robot(1,1);
        when:
            robot.moveRight();
        then:
            robot.getPosition() == new Position(2,1);
    }
}
