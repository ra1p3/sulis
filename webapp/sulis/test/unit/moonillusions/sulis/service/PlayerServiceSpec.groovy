package moonillusions.sulis.service

import moonillusions.sulis.domain.Player;
import grails.buildtestdata.mixin.Build;
import grails.test.mixin.TestFor
import spock.lang.Specification

import static spock.util.matcher.HamcrestSupport.that

import static org.hamcrest.Matchers.containsInAnyOrder

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PlayerService)
@grails.test.mixin.Mock(Player)
@Build(Player)
class PlayerServiceSpec extends spock.lang.Specification {

	def setup() {
	}

	def cleanup() {
	}

	void "list should return empty list if no players"() {
		when:
		def players = service.list()
		
		then:
		players.size() == 0
	}
	
	void "list should return all players"() {
		setup:
		def player1 = Player.build().save()
		def player2 = Player.build().save()
		
		when:
		def players = service.list()
		
		then:
		players containsInAnyOrder(player2, player1)
		
	}
}
