package util;

public enum NotificationStatus {
    READ("Read"),
    UNREAD("Unread"),
    NEW("New");

    private final String value;

    private NotificationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
