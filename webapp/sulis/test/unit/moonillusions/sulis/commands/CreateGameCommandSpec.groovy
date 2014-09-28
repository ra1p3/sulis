package moonillusions.sulis.commands

import static moonillusions.grails.testing.matchers.FieldErrors.fieldErrors
import static moonillusions.grails.testing.matchers.FieldErrors.noFieldErrors
import grails.test.mixin.*
import moonillusions.sulis.controllers.GameController

import org.joda.time.LocalDate

import spock.lang.Specification

@TestFor(GameController)
class CreateGameCommandSpec extends Specification {

    CreateGameCommand validCommand

    def setup() {
        validCommand = new CreateGameCommand(
                newServingPlayer: "John",
                newReceivingPlayer: "Jane",
                date: new LocalDate(2015,10,10).toDate(),
                servingPlayerPoints: 21,
                receivingPlayerPoints:10,
                servingPlayerId: 1,
                receivingPlayerId: 2)
    }


    void "request parameter binding"() {

        setup:
        def params = [:]
        params['newServingPlayer']= "John"
        params['newReceivingPlayer'] = "Jane"
        params['date'] = "12.5.2014"
        params['servingPlayerPoints'] = 10
        params['receivingPlayerPoints'] = 21
        params['servingPlayerId'] = 2
        params['receivingPlayerId'] = 100

        when:
        CreateGameCommand command = new CreateGameCommand()
        controller.bindData(command, params)
        command.validate()

        then:
        !command.hasErrors()
        command.newServingPlayer == "John"
        command.newReceivingPlayer == "Jane"
        command.date == (new LocalDate(2014,5,12)).toDate()
        command.servingPlayerPoints == 10
        command.receivingPlayerPoints == 21
        command.servingPlayerId == 2
        command.receivingPlayerId == 100
    }

    void "minimum size allowed for new player names"() {
        when:
        validCommand.newServingPlayer = "abc"
        validCommand.newReceivingPlayer = "def"
        validCommand.validate()

        then:
        assertThat(validCommand, noFieldErrors())
    }

    void "maximum size allowed for new player names"() {
        when:
        validCommand.newServingPlayer = "12345678901234567890"
        validCommand.newReceivingPlayer = "12345678901234567890"
        validCommand.validate()

        then:
        assertThat(validCommand, noFieldErrors())
    }


    void "minimum size constraints for new player names"() {
        when:
        validCommand.newServingPlayer = "ab"
        validCommand.newReceivingPlayer = "cd"
        validCommand.validate()

        then:
        assertThat(validCommand, fieldErrors(newServingPlayer: "size.toosmall", newReceivingPlayer: "size.toosmall"));
    }

    void "maximum size constraints for new player names"() {
        when:
        validCommand.newServingPlayer = validCommand.newServingPlayer = "12345678901234567890a"
        validCommand.newReceivingPlayer = validCommand.newServingPlayer = "12345678901234567890b"
        validCommand.validate()

        then:
        assertThat(validCommand, fieldErrors(newServingPlayer: "size.toobig", newReceivingPlayer: "size.toobig"));
    }

    void "null allowed for new player names"() {
        when:
        validCommand.newServingPlayer = null
        validCommand.newReceivingPlayer = null
        validCommand.validate()

        then:
        assertThat(validCommand, noFieldErrors())
    }

    void "not null constraint for date" () {
        when:
        validCommand.date = null;
        validCommand.validate()

        then:
        assertThat(validCommand, fieldErrors(date: "nullable"));
    }

    void "not null constraints for points"() {
        when:
        validCommand.receivingPlayerPoints = null
        validCommand.servingPlayerPoints = null
        validCommand.validate()

        then:
        assertThat(validCommand, fieldErrors(
                receivingPlayerPoints: "nullable",
                servingPlayerPoints: "nullable"));
    }

    void "minimum allowed for points"() {
        when:
        validCommand.receivingPlayerPoints = 0
        validCommand.servingPlayerPoints = 0
        validCommand.validate()

        then:
        assertThat(validCommand, noFieldErrors())
    }

    void "maximum allowed for points"() {
        when:
        validCommand.receivingPlayerPoints = 25
        validCommand.servingPlayerPoints = 25
        validCommand.validate()

        then:
        assertThat(validCommand, noFieldErrors())
    }

    void "minimum size constraints for points"() {
        when:
        validCommand.receivingPlayerPoints = -1
        validCommand.servingPlayerPoints = -1
        validCommand.validate()

        then:
        assertThat(validCommand, fieldErrors(
                receivingPlayerPoints: "range.toosmall",
                servingPlayerPoints: "range.toosmall"));
    }

    void "maximum size constraints for points"() {
        when:
        validCommand.receivingPlayerPoints = 26
        validCommand.servingPlayerPoints = 26
        validCommand.validate()

        then:
        assertThat(validCommand, fieldErrors(
                receivingPlayerPoints: "range.toobig",
                servingPlayerPoints: "range.toobig"));
    }

    void "null allowed for player ids"() {
        when:
        validCommand.servingPlayerId = null
        validCommand.receivingPlayerId = null
        validCommand.validate()

        then:
        assertThat(validCommand, noFieldErrors())
    }
}
