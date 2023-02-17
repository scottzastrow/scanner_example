package com.kenzie.app;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> arrayOfNames = new ArrayList<>();
        String attendees = "";

        //Run Once
        arrayOfNames = getData();
        attendees = formatAttendeeList(arrayOfNames);
        System.out.println(attendees);


    }
    public  static ArrayList getData(){
        Scanner scan = new Scanner(System.in);
        ArrayList<String> arrayOfNames = new ArrayList<>();
        String data = " ";

        System.out.println("Enter the First and Last name of all attendees below.");
        System.out.print("Attendee: ");

        while(!(data = scan.nextLine()).isBlank()) {
            arrayOfNames.add(data);
            System.out.print("Attendee: ");
        }
        return arrayOfNames;
    }
    public static String formatAttendeeList(ArrayList arrayOfNames){
        String text = arrayOfNames.toString();
        StringBuilder attending = new StringBuilder();
        int words = arrayOfNames.size();

        for(int i=0; i<arrayOfNames.size();i++){
            attending.append(arrayOfNames.get(i));
            if(i < arrayOfNames.size()-2){
                attending.append(", ");
            }
            else if(i < arrayOfNames.size()-1 && words > 2){
                attending.append(", and ");
            }
            else if(i < arrayOfNames.size()-1 && words >1){
                attending.append(" and ");
            }
        }
        attending.insert(0,"You have invited: ");
        return attending.toString();
    }

}
