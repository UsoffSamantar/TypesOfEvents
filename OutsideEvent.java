// Title: OutsideEvent
// Author: Usoff Samantar
// Course Section: CMIS202-ONL (Seidel) Fall 2022
// File: OutsideEvent.java
// Description: Predict weather events
// **********************************************************************************


package TypesOfEvents;
import java.text.SimpleDateFormat;
import java.time.*;
import java.text.DateFormat;

public class OutsideEvent extends Event {

    /* Brings in all instance variables from Event class:
       Super(eventType, nameOfEvent, currentDate, eventDate, isEventOutside, numberAttending, location, budget, eventSize, numOfEvents)
       In this class I will look at all the variables from class Event, but add in the variables that could affect an
       event located outside. In the JSON weather Query Builder, this will all be located under the "days": []
       LinkedList which will search for all these instance variables. I will use these as a map<K,V> to find value
       which will call get......();
     */
    private String eventLocation;
    private double temp;
    private double humidity;
    private double precip;
    private double precipprob;
    private double windspeed;
    private double snow;
    private String description;
    private boolean planEvent;
    private boolean rescheduleEvent;


    protected OutsideEvent() {
        super();
        this.eventLocation = "";
        this.temp = 0.0;
        this.humidity = 0.0;
        this.precip = 0.0;
        this.precipprob = 0.0;
        this.windspeed = 0.0;
        this.snow = 0.0;
        this.description = "";
        this.planEvent = true;
        this.rescheduleEvent = true;
    }

    protected OutsideEvent(String type, String eventName, LocalDate dateTime, String eDate, boolean isItOutside, int numAttending,
                           double eventBudget, String location, double tp, double hum, double per, double prob,
                           double speed, double sn, String scription, boolean plan, boolean reschedule) {
        super(type, eventName, dateTime, eDate, isItOutside, numAttending, eventBudget);
        this.eventLocation = location;
        this.temp = tp;
        this.humidity = hum;
        this.precip = per;
        this.precipprob = prob;
        this.windspeed = speed;
        this.snow = sn;
        this.description = scription;
        this.planEvent = plan;
        this.rescheduleEvent = reschedule;
    }
    // @Override //
    public void setEventLocation(String city, String stateAbbreviation) {
        this.eventLocation = (city + "," + stateAbbreviation.toUpperCase());
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setPercip(double precip) {
        this.precip = precip;
    }

    public void setPercipprob(double precipprob) {
        this.precipprob = precipprob;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public void setSnow(double snow) {
        this.snow = snow;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlanEvent(boolean planEvent) {
        this.planEvent = planEvent;
    }

    public void setRescheduleEvent(boolean rescheduleEvent) {
        this.rescheduleEvent = rescheduleEvent;
    }
    

    public double getTemp() {
        return this.temp;
    }

    public double getHumidity() {
        return this.humidity;
    }

    public double getPercip() {
        return this.precip;
    }

    public double getPercipprob() {
        return this.precipprob;
    }

    public double getWindspeed() {
        return this.windspeed;
    }

    public double getSnow() {
        return this.snow;
    }

    public String getDescription() {
        return "Prediction: \n" + this.description;
    }

    public boolean PlanEvent() {
        if(this.planEvent == true) {
            return this.planEvent;
        } else {
            return false;

        }
    }

    public boolean RescheduleEvent() {
        if(this.planEvent == false) {
            this.rescheduleEvent = true;
            return this.rescheduleEvent;
    }   else {
            this.rescheduleEvent = false;
            return this.rescheduleEvent;
        }
    }

    public String toString() {
        String outsideEventInfo=super.toString();
        outsideEventInfo+="Temperature Today: "+getTemp()+"\n";
        outsideEventInfo+="Humidity Today: "+getHumidity()+"\n";
        outsideEventInfo+="Percipitation Today: "+getPercip()+"\n";
        outsideEventInfo+="Percipitation Probabilty: "+getPercipprob()+"\n";
        outsideEventInfo+="Windspeed Today: "+getWindspeed()+"\n";
        outsideEventInfo+="Inches of Snow: "+getSnow()+"\n";
        outsideEventInfo+="Did you plan this event with the Orignal date?: "+PlanEvent()+"\n";
        outsideEventInfo+="Was this event rescheduled?: "+RescheduleEvent()+"\n";
        return outsideEventInfo;
    }
}

