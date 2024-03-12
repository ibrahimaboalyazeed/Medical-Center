package com.global.medical.enums;

public enum Shift {
	
	   MORNING("09:00:00", "15:00:00"),
	    EVENING("15:00:00", "21:00:00");
    
    private final String startTime;
    private final String endTime;

    Shift(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

}
