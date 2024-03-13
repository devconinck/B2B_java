package domain;

public interface Observer {
	public void update(Company c);
	public void update(Order c);
}