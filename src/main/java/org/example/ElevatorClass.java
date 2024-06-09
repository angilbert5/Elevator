package org.example;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static java.lang.Thread.sleep;

public class ElevatorClass {
    private int _maxFloors;
    private int _currentFloor;
    private String _status;

    public enum STATUS {
        IDLE,
        PENDING_MOVE,
        MOVING_UP,
        MOVING_DOWN;
    }

    public enum ElevatorDirection{
        UP,
        DOWN;
    }
    public ElevatorClass(int maxFloors,STATUS status, int currentFloor) {
        _maxFloors = maxFloors;
        _status = status.name();
        _currentFloor = currentFloor;
    }

    public void moveElevator(List<Integer> requestedFloorsList) {
        List<Integer> ascendingFloors = requestedFloorsList.stream().filter( x -> x > _currentFloor).toList();
        List<Integer> descendingFloors = requestedFloorsList.stream().filter( x -> x < _currentFloor).toList();
        if(!ascendingFloors.isEmpty()){
            moveElevatorUp(ascendingFloors);
        }
        if(!descendingFloors.isEmpty()){
            moveElevatorDown(descendingFloors);
        }
    }

    private void moveElevatorDown(List<Integer> descendingFloors) {
        descendingFloors.forEach(floor -> {
            if(floor <= _maxFloors && floor >= 0) {
                displayElevatorStatus(Optional.of(floor));
                _status = STATUS.MOVING_DOWN.name();
                _currentFloor = floor;
                displayElevatorStatus(Optional.of(floor));
                try {
                    sleep(Duration.ofSeconds(2));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                elevatorStopped();
            }
        });
    }

    private void moveElevatorUp(List<Integer> ascendingFloors) {
        ascendingFloors.forEach(floor -> {
            if(floor <= _maxFloors) {
                displayElevatorStatus(Optional.of(floor));
                _status = STATUS.MOVING_UP.name();
                _currentFloor = floor;
                displayElevatorStatus(Optional.of(floor));
                try {
                    sleep(Duration.ofSeconds(2));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                elevatorPending();
            }
        });
        elevatorStopped();
    }

    private void elevatorPending() {
        _status = STATUS.PENDING_MOVE.name();
        displayElevatorStatus(Optional.empty());
    }

    private void elevatorStopped() {
        _status = STATUS.IDLE.name();
        displayElevatorStatus(Optional.empty());
    }

    public void displayElevatorStatus(Optional<Integer> requestedFloor){
        System.out.print("Current Floor: " + (_currentFloor == 0?"G":_currentFloor) + System.lineSeparator());
        System.out.print("Current Status: " + _status + System.lineSeparator());
        if (requestedFloor.isPresent()) {
            System.out.print("Requested Floor: " + (requestedFloor.get()==0?"G":requestedFloor.get()) + System.lineSeparator());
            System.out.print(System.lineSeparator());
            System.out.print( System.lineSeparator());
        } else {
            System.out.print("Requested Floor: " + System.lineSeparator());
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
        }
    }
}
