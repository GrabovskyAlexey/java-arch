package ru.grabovsky;

import ru.grabovsky.adapter.Hunter;
import ru.grabovsky.adapter.WildDog;
import ru.grabovsky.adapter.WildDogAdapter;
import ru.grabovsky.bridge.*;
import ru.grabovsky.composite.Designer;
import ru.grabovsky.composite.Developer;
import ru.grabovsky.composite.Employee;
import ru.grabovsky.composite.Organization;
import ru.grabovsky.decorator.*;
import ru.grabovsky.facade.Computer;
import ru.grabovsky.facade.ComputerFacade;
import ru.grabovsky.flyweight.TeaMaker;
import ru.grabovsky.flyweight.TeaShop;
import ru.grabovsky.proxy.LabDoor;
import ru.grabovsky.proxy.SecuredDoor;

public class Main {
    public static void main(String[] args) {
        // Adapter
        System.out.println("********************* ADAPTER *********************");
        WildDog dog = new WildDog();
        WildDogAdapter adapter = new WildDogAdapter(dog);
        Hunter hunter = new Hunter();
        hunter.hunt(adapter);
        System.out.println("***************************************************\n");

        // Bridge
        System.out.println("********************** BRIDGE *********************");
        Theme darkTheme = new DarkTheme();
        Theme lightTheme = new LightTheme();
        Theme aquaTheme = new AquaTheme();

        WebPage about = new About(darkTheme);
        WebPage career = new Careers(aquaTheme);
        WebPage projects = new Projects(lightTheme);
        System.out.println(about.getContent());
        System.out.println(career.getContent());
        System.out.println(projects.getContent());
        System.out.println("***************************************************\n");

        // Composite
        System.out.println("******************** COMPOSITE ********************");
        Employee john = new Developer("John Doe", 12000.0);
        Employee jane = new Designer("Jane Doe", 15000.0);
        Organization organization = new Organization();
        organization.addEmployee(john);
        organization.addEmployee(jane);
        System.out.println("Net salaries: " + organization.getNetSalaries());
        System.out.println("***************************************************\n");

        // Decorator
        System.out.println("******************** DECORATOR ********************");
        Coffee someCoffee = new SimpleCoffee();
        System.out.printf("Name: %s, Price: %d%n", someCoffee.getDescription(), someCoffee.getCost());
        someCoffee = new MilkCoffee(someCoffee);
        System.out.printf("Name: %s, Price: %d%n", someCoffee.getDescription(), someCoffee.getCost());
        someCoffee = new WhipCoffee(someCoffee);
        System.out.printf("Name: %s, Price: %d%n", someCoffee.getDescription(), someCoffee.getCost());
        someCoffee = new VanillaCoffee(someCoffee);
        System.out.printf("Name: %s, Price: %d%n", someCoffee.getDescription(), someCoffee.getCost());
        System.out.println("***************************************************\n");

        // Facade
        System.out.println("********************* FACADE *********************");
        ComputerFacade computer = new ComputerFacade(new Computer());
        computer.turnOn();
        computer.turnOff();
        System.out.println("***************************************************\n");

        // Flyweight
        System.out.println("******************** FLYWEIGHT ********************");
        TeaMaker teaMaker= new TeaMaker();
        TeaShop shop = new TeaShop(teaMaker);
        shop.takeOrder("less sugar", 1);
        shop.takeOrder("more milk", 2);
        shop.takeOrder("without sugar", 5);
        shop.serve();
        System.out.println("***************************************************\n");

        // Proxy
        System.out.println("********************** PROXY **********************");
        SecuredDoor door = new SecuredDoor(new LabDoor());
        door.open("invalid");
        door.open("$ecr@t");
        door.close();
        System.out.println("***************************************************\n");

    }
}