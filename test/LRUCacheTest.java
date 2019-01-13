import org.junit.Test;
import static org.junit.Assert.*;

public class LRUCacheTest {

    private LRUCache<Integer, Integer> c;

    public LRUCacheTest() {
        try {
            this.c = new LRUCache<>(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartsEmpty() {
        assertNull(c.get(1));
    }

    @Test
    public void testBelowCapacity() {
        c.set(1, 1);
        assertEquals(c.get(1).intValue(), 1);
        assertNull(c.get(2));
        c.set(2, 4);
        assertEquals(c.get(1).intValue(), 1);
        assertEquals(c.get(2).intValue(), 4);
    }

    @Test
    public void testCapacity() {
        c.set(1, 1);
        c.set(2, 4);
        c.set(3, 9);
        assertNull(c.get(1));
        assertEquals(c.get(2).intValue(), 4);
        assertEquals(c.get(3).intValue(), 9);
    }

    @Test
    public void testGetRenewsEntry() {
        c.set(1, 1);
        c.set(2, 4);
        assertEquals(c.get(1).intValue(), 1);
        c.set(3, 9);
        assertEquals(c.get(1).intValue(), 1);
        assertNull(c.get(2));
        assertEquals(c.get(3).intValue(), 9);
    }

    @Test
    public void testSetRenewsEntry() {
        c.set(1, 1);
        c.set(2, 4);
        c.set(1, 100);
        c.set(3, 9);
        assertEquals(c.get(1).intValue(), 100);
        assertNull(c.get(2));
        assertEquals(c.get(3).intValue(), 9);
    }
}
