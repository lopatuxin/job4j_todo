package ru.job4j.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.job4j.model.Task;
import ru.job4j.model.User;

import java.time.ZoneId;
import java.util.TimeZone;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimezoneConverter {
    public static void setTimeZone(Task task, User user) {
        var defTz = TimeZone.getDefault().toZoneId();
        var userTimeZone = ZoneId.of(user.getTimezone());
        var dateTime = task.getCreated()
                .atZone(defTz)
                .withZoneSameInstant(userTimeZone)
                .toLocalDateTime();
        task.setCreated(dateTime);
    }
}
