package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.TeamColor;

public class TeamColorTest {

    @Test
    public void testFromString() {
        assertEquals(TeamColor.UNKNOWN, TeamColor.fromString(null));
        assertEquals(TeamColor.UNKNOWN, TeamColor.fromString(""));
        assertEquals(TeamColor.UNKNOWN, TeamColor.fromString("  "));
        assertEquals(TeamColor.RED, TeamColor.fromString(" r"));
        assertEquals(TeamColor.BLUE, TeamColor.fromString(" b"));
        assertEquals(TeamColor.RED, TeamColor.fromString(" red"));
        assertEquals(TeamColor.BLUE, TeamColor.fromString(" blue"));

        assertEquals(TeamColor.RED, TeamColor.fromString("r "));
        assertEquals(TeamColor.RED, TeamColor.fromString("rr"));
        assertEquals(TeamColor.UNKNOWN, TeamColor.fromString("rb"));
        assertEquals(TeamColor.RED, TeamColor.fromString("rred"));
        assertEquals(TeamColor.BLUE, TeamColor.fromString("rblue"));

        assertEquals(TeamColor.BLUE, TeamColor.fromString("b "));
        assertEquals(TeamColor.UNKNOWN, TeamColor.fromString("br"));
        assertEquals(TeamColor.BLUE, TeamColor.fromString("bb"));
        assertEquals(TeamColor.RED, TeamColor.fromString("bred"));
        assertEquals(TeamColor.BLUE, TeamColor.fromString("bblue"));

        assertEquals(TeamColor.RED, TeamColor.fromString("red "));
        assertEquals(TeamColor.RED, TeamColor.fromString("redr"));
        assertEquals(TeamColor.RED, TeamColor.fromString("redb"));
        assertEquals(TeamColor.RED, TeamColor.fromString("redred"));
        assertEquals(TeamColor.UNKNOWN, TeamColor.fromString("redblue"));

        assertEquals(TeamColor.BLUE, TeamColor.fromString("blue "));
        assertEquals(TeamColor.BLUE, TeamColor.fromString("bluer"));
        assertEquals(TeamColor.BLUE, TeamColor.fromString("blueb"));
        assertEquals(TeamColor.UNKNOWN, TeamColor.fromString("bluered"));
        assertEquals(TeamColor.BLUE, TeamColor.fromString("blueblue"));
    }
    
    @Test
    public void testOpposite(){
        assertEquals(TeamColor.RED, TeamColor.BLUE.opposite());
        assertEquals(TeamColor.BLUE, TeamColor.RED.opposite());
        assertEquals(TeamColor.UNKNOWN, TeamColor.UNKNOWN.opposite());
    }

}
