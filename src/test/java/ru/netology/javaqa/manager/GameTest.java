package ru.netology.javaqa.manager;

import org.junit.jupiter.api.Test;
import ru.netology.javaqa.domain.Player;
import ru.netology.javaqa.exception.NoRegisteredException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    Game service = new Game();
    Player player1 = new Player(1, "Masha", 1);
    Player player2 = new Player(2, "Misha", 3);
    Player player3 = new Player(3, "Katya", 2);
    Player player4 = new Player(4, "Petya", 3);

    @Test

    public void shouldRegisterOnePlayer() {
        service.register(player2);

        boolean expected = true;
        boolean actual = service.getPlayers().contains(player2);

        assertEquals(expected, actual);
    }


    @Test
    public void shouldAddSeveralPlayers() {

        service.register(player1);
        service.register(player2);
        service.register(player3);
        service.register(player4);

        List<Player> expected = Arrays.asList(new Player[]{player1, player2, player3, player4});
        ArrayList<Player> actual = service.getPlayers();

        assertEquals(expected, actual);
    }

    @Test

    public void shouldWinFirstPlayer() {

        service.register(player3);
        service.register(player2);

        int expected = 1;
        int actual = service.round("Misha", "Katya");

        assertEquals(expected, actual);

    }

    @Test
    public void shouldWinSecondPlayer() {

        service.register(player3);
        service.register(player2);

        int expected = 2;
        int actual = service.round("Katya", "Misha");

        assertEquals(expected, actual);

    }

    @Test
    public void shouldNobodyWin() {

        service.register(player2);
        service.register(player4);

        int expected = 0;
        int actual = service.round("Misha", "Petya");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotRegisteredExceptionForTheFirstPlayer() {
        service.register(player1);

        assertThrows(NoRegisteredException.class, () -> {
            service.round("Ira", "Masha");
        });
    }

    @Test
    public void shouldThrowNotRegisteredExceptionForTheSecondPlayer() {
        service.register(player1);

        assertThrows(NoRegisteredException.class, () -> {
            service.round("Masha", "Ira");
        });
    }

    @Test
    public void shouldThrowNotRegisteredExceptionForBothPlayers() {

        assertThrows(NoRegisteredException.class, () -> {
            service.round("Masha", "Misha");
        });
    }
}






