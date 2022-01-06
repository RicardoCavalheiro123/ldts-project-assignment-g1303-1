import com.aor.Element.Robot
import com.aor.Positions.Position
import spock.lang.Specification

class RobotTest extends Specification {
    private robot;

    def setup(){
        robot = new Robot(1,1)
    }
    def "MoveUp"() {
        when:
            robot.moveUp();
        then:
            robot.getPosition() == new Position(1,0);
    }

    def "MoveDown"() {
        when:
            robot.moveDown();
        then:
            robot.getPosition() == new Position(1,2);
    }

    def "MoveLeft"() {
        when:
            robot.moveLeft();
        then:
            robot.getPosition() == new Position(0,1);
    }

    def "MoveRight"() {
        when:
            robot.moveRight();
        then:
            robot.getPosition() == new Position(2,1);
    }
}
