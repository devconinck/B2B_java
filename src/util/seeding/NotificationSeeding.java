package util.seeding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domain.Company;
import domain.Notification;
import repository.GenericDao;
import repository.GenericDaoJpa;
import util.NotificationStatus;
import util.NotificationType;

public class NotificationSeeding {
	
	private GenericDao<Notification> notificationRepo;
	private List<Notification> notificationList = new ArrayList<>();
	private List<Company> companyList = new ArrayList<>();
	
	public NotificationSeeding(GenericDao<Notification> notificationRepo, List<Company> companyList) {
		this.notificationRepo = notificationRepo;
		this.companyList = companyList;
		seeding();
	}

	private void seeding() {
		notificationList.add(new Notification(NotificationType.PAYMENT_REQUEST, LocalDate.now(), "", "-1", NotificationStatus.NEW, companyList.get(0)));
		notificationList.stream().forEach(c -> {
			GenericDaoJpa.startTransaction();
			notificationRepo.insert(c);
			GenericDaoJpa.commitTransaction();
		});
	}

}
