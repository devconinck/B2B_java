package util.seeding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


import domain.Notification;
import repository.GenericDao;
import repository.GenericDaoJpa;
import util.NotificationStatus;
import util.NotificationType;

public class NotificationSeeding {
	
	private GenericDao<Notification> notificationRepo;
	private List<Notification> notificationList = new ArrayList<>();
	
	public NotificationSeeding(GenericDao<Notification> notificationRepo) {
		this.notificationRepo = notificationRepo;
		seeding();
	}

	private void seeding() {
		notificationList.add(new Notification(NotificationType.PAYMENT_REQUEST, LocalDate.now(), "", "-1", NotificationStatus.NEW));
		notificationList.stream().forEach(c -> {
			GenericDaoJpa.startTransaction();
			notificationRepo.insert(c);
			GenericDaoJpa.commitTransaction();
		});
	}

}
