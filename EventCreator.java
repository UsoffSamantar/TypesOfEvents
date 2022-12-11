// **********************************************************************************
// Title: EventCreator
// Author: Usoff Samantar
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022
// File: EventCreator.java
// Description: Predict weather event
// **********************************************************************************

package TypesOfEvents;
//Json Java API Package//
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonStructure;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonNumber;
import javax.json.JsonString;
import javax.json.JsonWriter;
import javax.json.JsonException;
import javax.json.stream.JsonParsingException;
import javax.json.stream.JsonParser;
// Java I/O----------------- //
import java.io.StringWriter;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
// Java Date & Time--------- //
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.chrono.*;
import java.text.DateFormat;
// Java Util----------------//
import java.util.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map.Entry;
// Java FX------------------ //
import javafx.application.Application;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class EventCreator extends Application { 
   public static MyDoublyLinkedList tempAndPrecip = new MyDoublyLinkedList();
   public static ArrayList<JsonObject> targetPattern = new ArrayList<JsonObject>();      
   private static LinkedList<OutsideEvent> outsideEvents = new LinkedList<OutsideEvent>(); 
   private static LinkedList<InsideEvent> insideEvents = new LinkedList<InsideEvent>();
   public static JsonObject rebuildObject = Json.createObjectBuilder().build();
   private static int currentEvent = -1;
   
   public static void main(String[] args) throws Exception, IOException, FileNotFoundException, JsonException, JsonParsingException {
      launch(args);
   
  
   }

   Stage stage;
   TextField evtName;
   LocalDate t;
   LocalDate ed;
   TextField evtType;
   TextField numOfGuests;
   TextField evtBudget;
   RadioButton evtInside;
   RadioButton evtOutside;
   
   @Override
   public void start(Stage primaryStage) throws Exception, FileNotFoundException, JsonException, JsonParsingException {  
      stage = primaryStage;
      TilePane r = new TilePane();
      
      Label lblEvtName = new Label("Event Name:");
      evtName = new TextField();
      evtName.setMinWidth(100);
      evtName.setPrefWidth(150);
      evtName.setMaxWidth(160);
      evtName.setPromptText("Enter the name of the event:");
      
      Label lblTdDate = new Label("Today's Date:");
      DatePicker td = new DatePicker();
      
      Label lblEvtDate = new Label("Event Date:");
      DatePicker ev = new DatePicker();
      
      Label LblEvtType = new Label("Occasion:");
      evtType = new TextField();
      evtType.setMinWidth(100);
      evtType.setPrefWidth(200);
      evtType.setMaxWidth(300);
      evtType.setPromptText("Enter the type of event:");
      
      Label LblNumOfGuests = new Label("Guest Count:");
      numOfGuests = new TextField();
      numOfGuests.setMinWidth(100);
      numOfGuests.setPrefWidth(200);
      numOfGuests.setMaxWidth(300);
      numOfGuests.setPromptText("Enter # of guests:");
      
      Label LblEvtBudget = new Label("Budget:");
      evtBudget = new TextField();
      evtBudget.setMinWidth(100);
      evtBudget.setPrefWidth(200);
      evtBudget.setMaxWidth(300);
      evtBudget.setPromptText("Enter your budget for the event:");
      
      Label LblEvtL = new Label("Event Setting");
      evtInside = new RadioButton("Inside");
      evtOutside = new RadioButton("Outside");
      evtOutside.setSelected(true); 
      ToggleGroup group = new ToggleGroup();
      evtInside.setToggleGroup(group);
      evtOutside.setToggleGroup(group);
      
      VBox paneLocation = new VBox(LblEvtL, evtInside, evtOutside);
      paneLocation.setSpacing(10);
      
      
      Button btnNextStep = new Button("Create");
      btnNextStep.setPrefWidth(300);
      btnNextStep.setOnAction(e -> CreateEvent_Click());
      
      Button viewWeather = new Button("View Weather");
      viewWeather.setPrefWidth(300);      
      viewWeather.setOnAction(e-> {
      try {
       getWeather();
      } catch (FileNotFoundException f) {
         System.out.println(f.getMessage());
      } 
      }
      );    
      
      Button exitApp = new Button("Exit");
      exitApp.setPrefWidth(300);
      exitApp.setOnAction(e -> ExitApp_Click());
      HBox controlBtn = new HBox(10, btnNextStep, viewWeather, exitApp);

      
      // Creating the grid layout //
      GridPane infoGrid = new GridPane();
      infoGrid.setPadding(new Insets(10));
      infoGrid.setHgap(10);
      infoGrid.setVgap(10);
      infoGrid.setMinWidth(500);
      infoGrid.setPrefWidth(500);
      infoGrid.setMaxWidth(800);
      
      // Nodes added to Pane //
      infoGrid.addRow(0, lblEvtName, evtName);      
      infoGrid.addRow(1, lblTdDate, td);      
      infoGrid.addRow(2, lblEvtDate, ev);      
      infoGrid.addRow(3, LblEvtType, evtType);      
      infoGrid.addRow(4, LblNumOfGuests, numOfGuests);      
      infoGrid.addRow(5, LblEvtBudget, evtBudget);      
      infoGrid.addRow(6, paneLocation);
      infoGrid.addRow(7, controlBtn);
      
      
      // Set how they are alligned //
      GridPane.setHalignment(lblEvtName, HPos.RIGHT);
      GridPane.setHalignment(lblTdDate, HPos.RIGHT);
      GridPane.setHalignment(lblEvtDate, HPos.RIGHT);
      GridPane.setHalignment(LblEvtType, HPos.RIGHT);
      GridPane.setHalignment(LblNumOfGuests, HPos.RIGHT);
      GridPane.setHalignment(LblEvtBudget, HPos.RIGHT);
      GridPane.setColumnSpan(evtName, 2);
      GridPane.setColumnSpan(td, 2);
      GridPane.setColumnSpan(ev, 2);
      GridPane.setColumnSpan(evtType, 2);
      GridPane.setColumnSpan(numOfGuests, 2);
      GridPane.setColumnSpan(evtBudget, 2);
      
      ColumnConstraints c1 = new ColumnConstraints();
      c1.setPercentWidth(33);
      ColumnConstraints c2 = new ColumnConstraints();
      c2.setPercentWidth(33);
      ColumnConstraints c3 = new ColumnConstraints();
      c3.setPercentWidth(33);
      infoGrid.getColumnConstraints().addAll(c1, c2, c3);
      
      // Create the scene //
      Scene scene = new Scene(infoGrid);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Event Creator");
      primaryStage.setMinWidth(1000);
      primaryStage.setMaxWidth(1500);
      primaryStage.show();
      
      
      
      EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // Date todays picker value //
                LocalDate t = td.getValue();
  
                // Date picker value //
                lblEvtDate.setText("Today's Date :" + t);
            }
        };
        
      EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // Date event picker value //
                LocalDate ed = ev.getValue();
  
                // Event picker value //
                lblEvtDate.setText("Event Date :" + ed);
            }
        };
      
      
 }
      
 
         
          
   public static void getWeather() throws FileNotFoundException {
      // Map/ArrayList/DoublyLinkedList/JsonObject //
      JsonBuilderFactory factory = Json.createBuilderFactory(rebuildObject);
      String API_Key = "GW4267MR27NGP7JH2XVMMMFGP";
      String location = "Frederick,MD";
      LocalDate startDateMinus14 = LocalDate.now().minusDays(14); 
      LocalDate startDate = LocalDate.now();
      String urlWeather = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/"+
                              "timeline/"+location+"/"+startDateMinus14+"/"+startDate+"?key="+API_Key+
                              "&include=days&elements=datetime,temp,humidity,precip,precipprob,windspeed,snow"+
                              ",description";
         try {
             // Build the String //
             StringBuilder onlineData = new StringBuilder();
             // Create the URL //
             URL url = new URL(urlWeather);
             // Open the connection //
             URLConnection conn = url.openConnection();
             // Read in the results as a String //
             JsonReader jrd = Json.createReader(new InputStreamReader((conn.getInputStream())));
             JsonObject readOnlineObject = jrd.readObject();
             System.out.println(readOnlineObject);
             jrd.close();
             JsonObject useObject = readOnlineObject;
             JsonArray arrayOfOO = readOnlineObject.getJsonArray("days");
             // Test //
             System.out.println(arrayOfOO);
             // Test //
             // ZoneID: .of(String zoneId, Map<String,String>)
             ZoneId zoneId = ZoneId.of(useObject.getString("timezone"));
             System.out.printf("Weather Data For: %s%n", useObject.getString("resolvedAddress"));
             System.out.printf("Date\tTemp\tHumidity\tPrecip\tPercipProb\tWindSpeed\tSnow\tDescription%n");
               for(int i = 0; i < arrayOfOO.size(); i++) {
                  JsonObject dayValue = arrayOfOO.getJsonObject(i);
                     String dt = dayValue.getString("datetime");
                     JsonNumber tempT = dayValue.getJsonNumber("temp");
                     double t = tempT.doubleValue();
                     JsonNumber tempH = dayValue.getJsonNumber("humidity");
                     double h = tempH.doubleValue();
                     JsonNumber tempPre = dayValue.getJsonNumber("precip");
                     double pre = tempPre.doubleValue();
                     JsonNumber tempPrePro = dayValue.getJsonNumber("precipprob");
                     double prePro = tempPrePro.doubleValue();
                     JsonNumber tempWS = dayValue.getJsonNumber("windspeed");
                     double ws = tempWS.doubleValue();
                     JsonNumber tempSn = dayValue.getJsonNumber("snow");
                     double sn = tempSn.doubleValue();
                     String des = dayValue.getString("description");
                     System.out.println();
                     tempAndPrecip.InsertLast(t);
                     tempAndPrecip.InsertLast(pre);
                     /*This is a way to build the JsonObject and add it to an ArrayList<JsonObject> if I want to remove ceratin variables
                        by removing the .add methods, but I can just add the JsonObject to the ArrayList without this by using ArrayList"name".add(JsonObject)
                     */
                     rebuildObject = factory.createObjectBuilder()
                        .add("datetime", dt)
                        .add("temp", t)
                        .add("humidity", h)
                        .add("precip", pre)
                        .add("precipprob", prePro)
                        .add("windspeed", ws)
                        .add("snow", sn)
                        .add("description", des)
                        .build();
                     // I can also use targetPattern.add(dayValue) if no modifications of data need to be made //
                        targetPattern.add(rebuildObject);
                     System.out.printf("%s\t%.1f\t%.1f\t%.1f\t%.1f\t%.1f\t%.1f\t%s%n",
                        dt, t, h, pre, prePro, ws, sn, des);



               }
         } catch (IOException e) {
            System.out.println(e.getMessage());
         }
         tempAndPrecip.displayForward(tempAndPrecip.head);
         tempAndPrecip.displayBackward(tempAndPrecip.head);

   }
   
   public void CreateEvent_Click() {
   
   }
   
   public void ExitApp_Click() {
      stage.close();
   
   }
   
   public void SaveMatches_Click() {
   
   }
   
   public void SaveEvent_Click() {
   
   }
   
   public void AddToDatabase_Click() {
   
   
   }
         
      
      
   
   
   
   
   
   
   
      
   
}