package paymentSystemOO;

class Schedule {
	private int schedule_id;
	private int schedule_type; // 1 - Weekly / 2 - Monthly / 3 - BiWeekly
	private String schedule_option; // day of the week OR day of the month
	
	int getSchedule_id() {
		return schedule_id;
	}
	void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	int getSchedule_type() {
		return schedule_type;
	}
	void setSchedule_type(int schedule_type) {
		this.schedule_type = schedule_type;
	}
	String getSchedule_option() {
		return schedule_option;
	}
	void setSchedule_option(String schedule_option) {
		this.schedule_option = schedule_option;
	}
	
	void printSchedule()
	{
		System.out.printf("       Schedule ID: %d   / Schedule Type: ", getSchedule_id());
        switch(getSchedule_type())
        {
        	case 1:
        		System.out.printf("Weekly / BiWeekly");
        		break;
        		
        	case 2:
        		System.out.printf("Monthly");
        		break;
        }
        System.out.printf("   / Schedule Option: %s\n", getSchedule_option());

	}
}
