/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
*/

package com.mobilecommerce.sensorsimulation;

public class UserMovementDatabase {

    private static long startTime;
    private static String activityType;

    public UserMovementDatabase(){} // constructor with no arguments

   public UserMovementDatabase(long startTime, String activityType){
       this.startTime = startTime;
        this.activityType = activityType;
    }

    public long getStartTime() {
        return startTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

}

