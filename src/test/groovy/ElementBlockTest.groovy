import com.aor.Element.Hero
import com.aor.ElementBlock.ConcreteBlock
import com.aor.game.Position
import spock.lang.Specification

class ElementBlockTest extends Specification {
    def "GetPosition"() {
        given:
        def ConcreteBlock c = new ConcreteBlock(2,3);

        when:
        def pos = new Position(2,3);

        then:
        pos == c.getPosition();

    }

    def "SetPosition"() {
        given:
        def ConcreteBlock c = new ConcreteBlock(2,3);
        def pos = new Position(5,4);
        c.setPosition(pos);

        when:
        def posf = new Position(5,4);

        then:
        posf == c.getPosition();
    }
}
