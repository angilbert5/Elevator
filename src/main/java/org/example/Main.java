package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static int maxFloors;
    public static void main(String[] args) {

        System.out.print("Hello and welcome!");
        System.out.print("Please enter max number of floors in your building (minimum floors is 2");
        getUserInput();
        ElevatorClass elevator = new ElevatorClass(maxFloors, ElevatorClass.STATUS.IDLE,0);
        getRequestedFloors(elevator);
    }

    private static void getRequestedFloors(ElevatorClass elevator) {
        System.out.print("Please enter the floors you want to go to. Multiple floors should be in a comma separated list (1,4,5). press Q to quit");
        Scanner in = new Scanner(System.in);
        String requestedFloors = in.nextLine();
        if(!"Q".equals(requestedFloors)){
            List<String> stringOfRequestedFloorsList = List.of(requestedFloors.split(","));
            List<Integer> requestedFloorsList = new ArrayList<>();
            stringOfRequestedFloorsList.forEach(s -> {
                try {
                    Integer floor = Integer.parseInt(s);
                    requestedFloorsList.add(floor);
                }
                catch (NumberFormatException ex){
                    System.out.print(s + " is not a valid number");
                }
            });
            elevator.moveElevator(requestedFloorsList);
        };
    }

    private static void getUserInput() {
        Scanner in = new Scanner(System.in);
        maxFloors = in.nextInt();
        if(maxFloors < 2){
            System.out.print("Number of floors is invalid");
            System.out.print("Please enter max number of floors in your building (minimum floors is 2");
           getUserInput();
        }

    }
}