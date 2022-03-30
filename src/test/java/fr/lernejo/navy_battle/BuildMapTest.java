package fr.lernejo.navy_battle;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuildMapTest {

    private final BuildMap BuildMap = new BuildMap();

    @Test
    void StringToJSON() {
        Map<String, String> vide = BuildMap.transform(null);
        Assertions.assertAll(
            () -> Assertions.assertNotNull(vide),
            () -> Assertions.assertNull(vide.get("cell"))
        );

        Map<String, String> cell = BuildMap.transform("cell=B2");
        Assertions.assertAll(
            () -> Assertions.assertNotNull(cell),
            () -> Assertions.assertEquals(cell.get("cell"), "B2")
        );
        Map<String, String> cell2 = BuildMap.transform("cell=B2&ici=J10");
        Assertions.assertAll(
            () -> Assertions.assertNotNull(cell2),
            () -> Assertions.assertEquals(cell2.get("cell"), "B2"),
            () -> Assertions.assertEquals(cell2.get("ici"), "J10")
        );
    }
}
