/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
*/

package com.mobilecommerce.sensorsimulation;

public class UserMovementDatabase {

    private static String startTime, activityType;

    public UserMovementDatabase(){} // constructor with no arguments

   public UserMovementDatabase(String startTime, String activityType){
       this.startTime = startTime;
        this.activityType = activityType;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

}

