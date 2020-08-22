package machine;

import java.util.Scanner;

enum State {
    IDLE, BUY, FILL, TAKE, REMAINING, EXIT;
}

public class CoffeeMachine {
    public int getAvailableWater() {
        return availableWater;
    }

    public void setAvailableWater(int availableWater) {
        this.availableWater = availableWater;
    }

    public int getAvailableMilk() {
        return availableMilk;
    }

    public void setAvailableMilk(int availableMilk) {
        this.availableMilk = availableMilk;
    }

    public int getAvailableCoffeeBeans() {
        return availableCoffeeBeans;
    }

    public void setAvailableCoffeeBeans(int availableCoffeeBeans) {
        this.availableCoffeeBeans = availableCoffeeBeans;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAvailableCups() {
        return availableCups;
    }

    public void setAvailableCups(int availableCups) {
        this.availableCups = availableCups;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public CoffeeMachine(int availableWater, int availableMilk, int availableCoffeeBeans, int money, int availableCups, State state) {
        this.availableWater = availableWater;
        this.availableMilk = availableMilk;
        this.availableCoffeeBeans = availableCoffeeBeans;
        this.money = money;
        this.availableCups = availableCups;
        this.state = state;
    }

    private int availableWater, availableMilk, availableCoffeeBeans,
        money, availableCups;
    private State state;

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine =  new CoffeeMachine(400, 540, 120, 550, 9, State.IDLE);
        String action;
        Scanner scanner = new Scanner(System.in);
        do {

            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.next();

            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String coffeeType = scanner.next();
                    if (!coffeeType.equals("back")){
                        coffeeMachine.makeCoffee(coffeeType);
                    }
                    break;
                case "fill":
                    System.out.println("Write how many ml of water do you want to add:");
                    int water = scanner.nextInt();
                    System.out.println("Write how many ml of milk do you want to add:");
                    int milk = scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    int coffeeBeans = scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    int disposableCups = scanner.nextInt();
                    coffeeMachine.fillMachine(water, milk, coffeeBeans, disposableCups);
                    break;
                case "take":
                    coffeeMachine.returnMoney();
                    break;
                case "remaining":
                    coffeeMachine.displayStatus();
                    break;
                default:
                    break;
            }
        }
        while (!action.equals("exit"));
        }



    private void displayStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(getAvailableWater() + " of water");
        System.out.println(getAvailableMilk() + " of milk");
        System.out.println(getAvailableCoffeeBeans() + " of coffee beans");
        System.out.println(getAvailableCups() + " of disposable cups");
        System.out.println(getMoney() + " of money");
    }

    private void returnMoney() {
        System.out.println("I gave you $" + getMoney());
        setMoney(0);
    }

    private void fillMachine(int water, int milk, int coffeeBeans, int disposableCups) {
        setAvailableWater(getAvailableWater() + water);
        setAvailableCoffeeBeans(getAvailableCoffeeBeans() + coffeeBeans);
        setAvailableMilk(getAvailableMilk() + milk);
        setAvailableCups(getAvailableCups() + disposableCups);
    }

    private void makeCoffee(String coffeeType) {

        boolean ifPossibleToMakeCoffee = checkPossibility(coffeeType);

        if (coffeeType.equals("1")  && ifPossibleToMakeCoffee) {
            setAvailableCups(getAvailableCups() - 1);
            setAvailableWater(getAvailableWater() - 250);
            setAvailableCoffeeBeans(getAvailableCoffeeBeans() - 16);
            setMoney(getMoney() + 4);
        }
        if (coffeeType.equals("2") && ifPossibleToMakeCoffee) {
            setAvailableCups(getAvailableCups() - 1);
            setAvailableWater(getAvailableWater() - 350);
            setAvailableMilk(getAvailableMilk() - 75);
            setAvailableCoffeeBeans(getAvailableCoffeeBeans() - 20);
            setMoney(getMoney() + 7);
        }
        if (coffeeType.equals("3") && ifPossibleToMakeCoffee) {
            setAvailableCups(getAvailableCups() - 1);
            setAvailableWater(getAvailableWater() - 200);
            setAvailableMilk(getAvailableMilk() - 100);
            setAvailableCoffeeBeans(getAvailableCoffeeBeans() - 12);
            setMoney(getMoney() + 6);
        }
        if (ifPossibleToMakeCoffee){
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            System.out.println("Sorry, not enough resources");
        }
    }

    private boolean checkPossibility(String coffeeType) {

        boolean result = false;

        if (coffeeType.equals("1")) {
            if (getAvailableCups() >= 1 &&
                    getAvailableWater() >= 250 &&
                    getAvailableCoffeeBeans() >= 16) {
                result = true;
            }
        }
        if (coffeeType.equals("2")) {
            if (getAvailableCups() >= 1 &&
                    getAvailableWater() >= 350 &&
                    getAvailableCoffeeBeans() >= 20 &&
                    getAvailableMilk() >= 75) {
                result = true;
            }
        }
        if (coffeeType.equals("3")) {
            if (getAvailableCups() >= 1 &&
                    getAvailableWater() >= 200 &&
                    getAvailableCoffeeBeans() >= 12 &&
                    getAvailableMilk() >= 100) {
                result = true;
            }
        }

        return result;
    }
}
