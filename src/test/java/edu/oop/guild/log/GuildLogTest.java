package edu.oop.guild.log;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuildLogTest {
    @AfterEach
    void cleanUp() {
        GuildLog.getInstance().clear();
    }

    @Test
    void singletonReturnsSameInstance() {
        assertSame(GuildLog.getInstance(), GuildLog.getInstance());
    }

    @Test
    void recordIncreasesSize() {
        GuildLog.getInstance().record("Delivered moon cheese");
        assertEquals(1, GuildLog.getInstance().size());
    }

    @Test
    void entriesExposeRecordedText() {
        GuildLog.getInstance().record("Delivered moon cheese");
        assertEquals("Delivered moon cheese", GuildLog.getInstance().entries().get(0));
    }

    @Test
    void entriesCannotBeModifiedExternally() {
        assertThrows(UnsupportedOperationException.class, () -> GuildLog.getInstance().entries().add("Sneaky edit"));
    }

    @Test
    void rejectsNullEntries() {
        assertThrows(NullPointerException.class, () -> GuildLog.getInstance().record(null));
    }

    @Test
    void clearRemovesEntries() {
        GuildLog.getInstance().record("One");
        GuildLog.getInstance().clear();
        assertEquals(0, GuildLog.getInstance().size());
    }
}
