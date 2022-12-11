// Title: Event
// Author: Usoff Samantar
// Course Section: CMIS202-ONL (Seidel) Fall 2022
// File: Event.java
// Description: Predict weather events
// **********************************************************************************
package TypesOfEvents;
import java.text.SimpleDateFormat; 
import java.time.*;
import java.text.DateFormat;
public class Event {
    
    // Instance Variables //
    public static int numOfEvents = 0;
    private String eventType;
    private String nameOfEvent;
    // Class variable //
    private LocalDate datetime;
    private String eventDate;
    protected boolean isEventOutside;
    private int numberAttending;
    private double budget;
    private String eventSize;

    // No-args constructor //
    protected Event() {
        this.eventType = "";
        this.nameOfEvent = "";
        this.datetime = LocalDate.now();
        this.eventDate = "";
        this.isEventOutside = true;
        this.numberAttending = 0;
        this.budget = 0.0;
        this.eventSize = "";
        numOfEvents++;
    }
    // Constructor //
    protected Event(String type, String eventName, LocalDate dateTime, String eDate, boolean isItOutside, int numAttending,                                                                                 
               double eventBudget) {
        this.eventType = type;
        this.nameOfEvent = eventName;
        this.datetime = dateTime;
        this.eventDate = eDate;
        this.isEventOutside = isItOutside;
        this.numberAttending = numAttending;
        this.budget = eventBudget;
        setEventSize();
    }

    // Mutators methods //

    public static void setNumOfEvents(int numOfEvents) {
        Event.numOfEvents = numOfEvents;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setNameOfEvent(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
    }
    public void setDatetime(String datetime) {
        LocalDate current = LocalDate.now();
        this.datetime = current.now();
    }

    public void setEventDate(String eventDate) {
        String[] dateArray = eventDate.split("-");
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);
        LocalDate futureDate = LocalDate.of(year, month, day);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        eventDate = dateFormat.format(futureDate);
        this.eventDate = eventDate;
    }

    public void setEventOutside(boolean isEventOutside) {
        this.isEventOutside = isEventOutside;
    }
      
    public void setNumberAttending(int numberAttending) {
        this.numberAttending = numberAttending;
    }


    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setEventSize() {
        if(getNumberAttending() <= 25) {
            this.eventSize = "Small";
        }else if(getNumberAttending() <= 50) {
            this.eventSize = "Mid-sized";
        }else if(getNumberAttending() <= 100) {
            this.eventSize = "Large";
        }else {
            this.eventSize = "Massive";
        }
    }
    // Accessors //

    public static int getNumOfEvents() {
        return numOfEvents;
    }

    public String getEventType() {
        return this.eventType;
    }

    public String getNameOfEvent() {
        return this.nameOfEvent;
    }

    public LocalDate getDatetime() {
        return this.datetime;
    }

    public String getEventDate() {
        return this.eventDate;
    }

    public boolean isEventOutside() {
        return this.isEventOutside;
    }

    public int getNumberAttending() {
        return this.numberAttending;
    }

    public double getBudget() {
        return this.budget;
    }

    public String getEventSize() {
        return this.eventSize;
    }

    public String toString() {
        String eventInfo ="Type of Event: "+getEventType()+"\n";
        eventInfo+="Event name: " +getNameOfEvent()+"\n";
        eventInfo+="Current date: "+getDatetime()+"\n";
        eventInfo+="Event date: "+getEventDate()+"\n";
        eventInfo+="Event Inside/Outside: "+isEventOutside()+"\n";
        eventInfo+="Number of Attendees: "+getNumberAttending()+"\n";
        eventInfo+="Budget for the Event: "+getBudget()+"\n";
        eventInfo+="Event Size: "+getEventSize()+"\n";
        return eventInfo;
    }
}
