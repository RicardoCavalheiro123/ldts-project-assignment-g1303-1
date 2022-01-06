import com.aor.Element.Hero
import com.aor.Positions.Position
import spock.lang.Specification

class HeroTest extends Specification {
    def "MoveUp"() {
        given:
            def hero = new Hero(1,1);
        when:
            hero.moveUp();
        then:
            hero.getPosition() == new Position(1,0);
    }

    def "MoveDown"() {
        given:
            def hero = new Hero(1,1);
        when:
            hero.moveDown();
        then:
            hero.getPosition() == new Position(1,2);
    }

    def "MoveLeft"() {
        given:
            def hero = new Hero(1,1);
        when:
            hero.moveLeft();
        then:
            hero.getPosition() == new Position(0,1);
    }

    def "MoveRight"() {
        given:
            def hero = new Hero(1,1);
        when:
            hero.moveRight();
        then:
            hero.getPosition() == new Position(2,1);
    }
}
