// **********************************************************************************
// Title: InsideEvent
// Author: Usoff Samantar
// Course Section: CMIS202-ONL (Seidel) Fall 2022
// File: InsideEvent.java
// Description: Predict weather events
// **********************************************************************************




package TypesOfEvents;
import java.text.SimpleDateFormat;
import java.time.*;
import java.text.DateFormat;


public class InsideEvent extends Event {

    // Instance Variables //
    private final static int MIN_NUMBER_ATTENDING = 5;
    private final static int MAX_NUMBER_ATTENDING = 80;
    private final static double MIN_BUDGET = 250;
    private String eventLocation;


    // No-args constructor //
    protected InsideEvent() {
      super();
      this.eventLocation = "";
    }
    // Constructor //
    protected InsideEvent(String type, String eventName, LocalDate dateTime, String eDate, boolean isItOutside, int numAttending,
                          String eventLocation, double eventBudget, String size, String locationInside) {
      super(type, eventName, dateTime, eDate, isItOutside, numAttending, eventBudget);
      this.eventLocation = locationInside;  
    }

    // Mutators methods//
   
    public void setLocation(String locationInside) {
        locationInside = "This Event is Located Inside";
        this.eventLocation = locationInside;
    }
    
    public void setEventOutside(boolean isEventOutside) {
        isEventOutside = false;
            this.isEventOutside = isEventOutside;
    }
    public String getEventLocation() {
        return this.eventLocation;
    }

    public String toString() {
        String eventInfo ="Event name: " +getNameOfEvent()+"\n";
        eventInfo+="Current date: "+getDatetime()+"\n";
        eventInfo+="Event date: "+getEventDate()+"\n";
        eventInfo+="Event Inside/Outside: "+isEventOutside()+"\n";
        eventInfo+="Number of Attendees: "+getNumberAttending()+"\n";
        eventInfo+="Location of Event: "+getEventLocation()+"\n";
        eventInfo+="Budget for the Event: "+getBudget()+"\n";
        eventInfo+="Event Size: "+getEventSize()+"\n";
        return eventInfo;
    }
}
