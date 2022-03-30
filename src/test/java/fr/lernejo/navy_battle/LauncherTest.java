package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;

class LauncherTest {

    @org.junit.jupiter.api.Test
    void launcherGoodArgs() {
        String[] args = {"9876"};
        Assertions.assertDoesNotThrow(() -> Launcher.main(args));
    }

    @org.junit.jupiter.api.Test
    void launcherEmptyArgs() {
        String[] args1 = {};
        Assertions.assertThrows(
            NullPointerException.class,
            () -> Launcher.main(args1), "Usage: Launcher [port]"
        );
    }


    @org.junit.jupiter.api.Test
    void launcherLetterArgs() {
        String[] args1 = {"api"};

        Assertions.assertThrows(
            NumberFormatException.class,
            () -> Launcher.main(args1)
        );}
    @org.junit.jupiter.api.Test
    void launcherTooMuchArgs() {
        String[] args1 = {"9876","http://localhost:9876"};

        Assertions.assertDoesNotThrow(() -> Launcher.main(args1));
    }
}
