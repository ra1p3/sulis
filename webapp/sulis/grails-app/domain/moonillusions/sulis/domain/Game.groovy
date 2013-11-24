package moonillusions.sulis.domain

import org.joda.time.LocalDate;

class Game {

	String player1
	String player2
	LocalDate date
	Integer points1
	Integer points2
	
	
    static constraints = {
		player1 nullable: false, blank: false
		player2 nullable: false, blank: false
		date nullable: false
		points1 nullable: false, min: 0
		points2 nullable: false, min: 0
    }
}