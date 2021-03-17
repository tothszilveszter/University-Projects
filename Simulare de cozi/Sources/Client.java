
public class Client {
	private int id;
	private int arr_time;
	private int serv_time;
	
	public Client(int id, int arr_time, int serv_time) {
		super();
		this.id = id;
		this.arr_time = arr_time;
		this.serv_time = serv_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArr_time() {
		return arr_time;
	}

	public void setArr_time(int arr_time) {
		this.arr_time = arr_time;
	}

	public int getServ_time() {
		return serv_time;
	}

	public void setServ_time(int serv_time) {
		this.serv_time = serv_time;
	}
	
}
