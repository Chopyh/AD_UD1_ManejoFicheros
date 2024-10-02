package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class BinaryObjectTest {

    @Test
    void serializeAndDeserializeObject() throws IOException, ClassNotFoundException {
        Player player = new Player(1, "PlayerOne", 100, 50);
        BinaryObject.serialize(player);
        Player deserializedPlayer = BinaryObject.deserialize();
        assertEquals(player.getId(), deserializedPlayer.getId());
        assertEquals(player.getNick_name(), deserializedPlayer.getNick_name());
        assertEquals(player.getExperience(), deserializedPlayer.getExperience());
        assertEquals(player.getCoins(), deserializedPlayer.getCoins());
    }

    @Test
    void serializeNullObjectThrowsException() {
        assertThrows(NullPointerException.class, () -> BinaryObject.serialize(null));
    }
}