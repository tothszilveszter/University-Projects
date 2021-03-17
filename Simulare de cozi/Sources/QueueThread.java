
public class QueueThread extends Thread {

	private boolean open;
	private Client client;

	public QueueThread(boolean open, Client client) {
		super();
		this.open = open;
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void run() {

		if (client.getServ_time() != 0) {
			int p = client.getServ_time();
			p--;
			client.setServ_time(p);
			this.interrupt();
		} else {
			client = null;
			open = false;
		}
	}

}
