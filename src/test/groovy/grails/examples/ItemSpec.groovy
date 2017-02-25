package grails.examples

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@Integration
@Rollback
class ItemSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        given:
        for (int i = 0; i < 10; i++) {
            def item = new Item(name:"item-${i}")
            item.save(flush:true)
        }
        expect:
        //select * from test.items limit 10 offset 0;
        Item.createCriteria().list ([offset: 0, max: 10]) {}
        //select * from test.items limit 10 offset 1;
        Item.createCriteria().list ([offset: 1, max: 10]) {}
        true
    }
}
