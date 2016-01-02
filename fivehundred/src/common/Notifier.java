package common;

public interface Notifier {

	public void addListener(Listener newListener);
	
	public void notify_all();
}
