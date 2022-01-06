import com.aor.Element.Hero
import com.aor.game.Position
import spock.lang.Specification

class ElementTest extends Specification {
    def "GetPosition"() {
        given:
            def Hero h = new Hero(2,3);

        when:
            def pos = new Position(2,3);

        then:
            pos == h.getPosition();

    }

    def "SetPosition"() {
        given:
            def h = new Hero(2,3);
            def pos = new Position(5,4);
            h.setPosition(pos);

        when:
            def posf = new Position(5,4);

        then:
            posf == h.getPosition();
    }

    def "moveUp"() {
        given:
            def h = new Hero(2,3);
            h.moveUp();
        when:
            def posf = new Position(2,2);

        then:
        posf == h.getPosition();
    }

    def "moveDown"() {
        given:
            def h = new Hero(2,3);
            h.moveDown();
        when:
            def posf = new Position(2,4);

        then:
            posf == h.getPosition();
    }

    def "moveLeft"() {
        given:
            def h = new Hero(2,3);
            h.moveLeft();
        when:
            def posf = new Position(1,3);

        then:
            posf == h.getPosition();
    }

    def "moveRight"() {
        given:
            def h = new Hero(2,3);
            h.moveRight();
        when:
            def posf = new Position(3,3);

        then:
            posf == h.getPosition();
    }
}
