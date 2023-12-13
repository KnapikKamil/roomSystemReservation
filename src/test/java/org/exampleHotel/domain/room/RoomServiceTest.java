package org.exampleHotel.domain.room;

import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.util.SystemUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomServiceTest {

    @Test
    public void convertFromIntOptionsIntoBedTypesTest(){

        RoomService roomService = ObjectPool.getRoomService();

        List<String> bedTypeOptions = new ArrayList<>();
        bedTypeOptions.add(SystemUtils.SINGLE_BED);
        bedTypeOptions.add(SystemUtils.DOUBLE_BED);
        bedTypeOptions.add(SystemUtils.KING_SIZE);

        roomService.getBedTypes(bedTypeOptions);

        assertEquals(3, bedTypeOptions.size());
        assertEquals("Pojedyńcze", SystemUtils.SINGLE_BED);
        assertEquals("Podwójne", SystemUtils.DOUBLE_BED);
        assertEquals("Królewskie", SystemUtils.KING_SIZE);

    }
}
