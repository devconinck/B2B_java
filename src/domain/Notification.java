package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;
import util.NotificationStatus;
import util.NotificationType;

@Entity
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    private LocalDate date;
    private String text;
    private String orderID;
    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

	//Voor testen
    protected Notification() {
    }

	public Notification(NotificationType notificationType, LocalDate date, String text, String orderID,
			NotificationStatus notificationStatus) {
		this.notificationType = notificationType;
		this.date = date;
		this.text = text;
		this.orderID = orderID;
		this.notificationStatus = notificationStatus;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public String getOrderID() {
		return orderID;
	}

	public NotificationStatus getNotificationStatus() {
		return notificationStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, notificationType, orderID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		return Objects.equals(date, other.date) && notificationType == other.notificationType
				&& Objects.equals(orderID, other.orderID);
	}
}