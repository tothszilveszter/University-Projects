
public class MonitoredData {
	public String start_time;
	public String end_time;
	public String activity_label;
	public MonitoredData(String start_time, String end_time, String activity_label) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		this.activity_label = activity_label;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getActivity_label() {
		return activity_label;
	}
	public void setActivity_label(String activity_label) {
		this.activity_label = activity_label;
	}
	public String getDate(int i, int j) {
		String x=this.start_time.substring(i,j);
		return x;
	}
	
	
}
