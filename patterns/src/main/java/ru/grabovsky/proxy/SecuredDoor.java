package ru.grabovsky.proxy;

public class SecuredDoor implements Door{
    private final Door door;

    public SecuredDoor(Door door) {
        this.door = door;
    }

    @Override
    public void open() {

    }

    public void open(String password) {
        if(authenticate(password)){
            door.open();
        } else {
            System.out.println("Big no! It ain't possible.");
        }
    }

    private boolean authenticate(String password){
        return password.equals("$ecr@t");
    }

    @Override
    public void close() {
        door.close();
    }
}
