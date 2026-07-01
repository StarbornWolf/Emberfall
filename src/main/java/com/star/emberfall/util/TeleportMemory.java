package com.star.emberfall.util;

import java.util.HashMap;
import java.util.UUID;

public class TeleportMemory {

    private static final HashMap<UUID, TeleportData> RETURN_MAP = new HashMap<>();

    public static void saveReturn(UUID uuid, TeleportData data) {
        RETURN_MAP.put(uuid, data);
    }

    public static TeleportData getReturn(UUID uuid) {
        return RETURN_MAP.get(uuid);
    }

    public static boolean hasReturn(UUID uuid) {
        return RETURN_MAP.containsKey(uuid);
    }

    public static void clear(UUID uuid) {
        RETURN_MAP.remove(uuid);
    }
}