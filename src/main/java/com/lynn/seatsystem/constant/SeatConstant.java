package com.lynn.seatsystem.constant;

import java.util.List;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午9:14
 */
public class SeatConstant {
    public static final String SEAT_TYPE_SINGLE = "single";
    public static final String SEAT_TYPE_DOUBLE = "double";
    public static final String SEAT_TYPE_GROUP = "group";

    public static final List<String> SEAT_TYPE_LIST = List.of(
            SEAT_TYPE_SINGLE, SEAT_TYPE_DOUBLE, SEAT_TYPE_GROUP
    );

    public static final String SEAT_STATUS_AVAILABLE = "available";
    public static final String SEAT_STATUS_RESERVED = "reserved";
    public static final String SEAT_STATUS_OCCUPIED = "occupied";

    public static final List<String> SEAT_STATUS_LIST = List.of(
            SEAT_STATUS_AVAILABLE,
            SEAT_STATUS_RESERVED,
            SEAT_STATUS_OCCUPIED
    );

    public static final String RESERVATION_STATUS_PENDING = "pending";
    public static final String RESERVATION_STATUS_CONFIRMED = "confirmed";
    public static final String RESERVATION_STATUS_CANCELLED = "cancelled";
    public static final String RESERVATION_STATUS_COMPLETED = "completed";

    public static final List<String> RESERVATION_STATUS_LIST = List.of(
            RESERVATION_STATUS_PENDING,
            RESERVATION_STATUS_CONFIRMED,
            RESERVATION_STATUS_CANCELLED,
            RESERVATION_STATUS_COMPLETED
    );

    public static final long SEAT_OCCUPIED_TIMEOUT_HOUR = 24;
}
