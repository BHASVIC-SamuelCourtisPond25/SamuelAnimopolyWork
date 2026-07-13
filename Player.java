import java.util.Arrays;
import java.util.Scanner;
import java.util.random.RandomGenerator;
import java.util.ArrayList;

public class Player {
    int money;
    int location;
    boolean inGame;
    int playerNumber;
    boolean missTurn = false;

    Player(int pMoney, int pLocation, boolean pInGame, int pPlayerNumber){
        money = pMoney;
        location = pLocation;
        inGame = pInGame;
        playerNumber = pPlayerNumber;
    }

    public void setLocation(int moveSpaces)
    {
        location = location + moveSpaces;
        if(location > 27){
            location = location - 28;
            setMoney(500);
            System.out.println("You gain £500 for passing the start");
        }
        
        if (location == 0) {
            setMoney(1000);
            System.out.println("You gain £1000 for landing on the start");
        }
    }

    public void setMoney(int newMoney)  //a change money
    {
        money = money + newMoney;
        if(money<0){
            System.out.println("\nYou don't have enough money!, YOU LOSE!!");
            resetAnimals();
            inGame = false;
            //CHECK IF PLAYER OWNS AN ANIMAL
            //FORCE THEM TO SELL
            
        }
    }

    public int getMoney() {
        return money;
    }

    public void printMoney() {
        System.out.println("You have £" + money);
    }

    public void pickupTanayCard(Player pPlayer) {


        RandomGenerator generator = RandomGenerator.getDefault();
        int tanayCardNum = generator.nextInt(8);
        int tempMoney;
        switch(tanayCardNum) {
            case 0:
                tempMoney = getRandomNumber(10,200);
                System.out.println("You Got Tanay Card 1! \n ");
                System.out.println("One of your animals contracted the plague. Lose £" + tempMoney);
                setMoney(-tempMoney);
                printMoney();
                break;
            case 1:
                tempMoney = getRandomNumber(100,250);
                System.out.println("You Got Tanay Card 2! \n ");
                System.out.println("One of your animals won a local competition. Gain £" + tempMoney);
                setMoney(tempMoney);
                printMoney();
                break;
            case 2:
                System.out.println("You Got Tanay Card 3! \n ");
                System.out.println("A nuclear bomb landed in one of your habitats. Lose an upgrade on one of your animals!");
                //MAKE THE CODE FOR THIS

                break;
            case 3:
                tempMoney = getRandomNumber(50,150);
                System.out.println("You Got Tanay Card 4! \n ");
                System.out.println("One of your animals pooped in the park. Pay £" + tempMoney + " as a fine");
                setMoney(-tempMoney);
                printMoney();
                break;
            case 4:
                tempMoney = getRandomNumber(250,450);
                System.out.println("You Got Tanay Card 5! \n ");
                System.out.println("it was raining. now its not. There is rainbow. you happy. money is yours.");
                setMoney(tempMoney);
                printMoney();
                break;
            case 5:
                tempMoney = getRandomNumber(1,9);
                System.out.println("You Got Tanay Card 6! \n ");
                System.out.println("You come across a troll. It eats you and your wallet. Lose " + tempMoney*10 + "% of your money");
                setMoney(-money/(tempMoney/10));
                printMoney();
                break;
            case 6:
                tempMoney = getRandomNumber(400,500);
                System.out.println("You Got Tanay Card 7! \n ");
                System.out.println("Your animal bit a random civilian. Lose £" + tempMoney + " to pay for medical bills");
                setMoney(-tempMoney);
                printMoney();
            case 7:
                tempMoney = getRandomNumber(450,550);
                System.out.println("You Got Tanay Card 8! \n ");
                System.out.println("Your pet digs up a diamond from the ground. Gain £" + tempMoney);
                setMoney(tempMoney);
                printMoney();
        }
    }

    public int getRandomNumber(int paramLowerBound, int paramUpperBound) {
        return (int) (Math.random() * (paramUpperBound - paramLowerBound) + paramLowerBound);
    }

    public void Turn(int playerNumber) {
        
        if (missTurn == true) {
            System.out.println("You missed this turn!");
            missTurn = false;
            return; //exit out of turn early
        }
        
        System.out.println("\n\nPlayer " + playerNumber + " turn: \n Before rolling, would you like to upgrade a habitat? (y/n) ");
        Scanner scanner = new Scanner(System.in);
        String qUPGRADEanswer = scanner.nextLine();

        if (qUPGRADEanswer.equals("y")) {
            checkAnimalOwnerShip();
        }

        int diceResult = Board.rollDice();
        System.out.println(diceResult);
        System.out.println("\nPlayer " + playerNumber + " rolled " + Globals.doubleCount + " doubles, and have earned " + Globals.doubleCount + " Tanay Cards! ");
        for (int i = 0; i < Globals.doubleCount; i++) {
            pickupTanayCard(this);
        }


        setLocation(diceResult);
        System.out.println("\nPlayer " + playerNumber + " is on space " + location + "\n And has £" + money);

        if (location == 14) {
            System.out.println("You landed on the Skip Turn space. L\n");
            missTurn = true;
        }

        Board board = new Board();
        Animals landedAnimal = board.getAnimalFromIndex(location);
        
        if (landedAnimal != null) {
            System.out.println("You landed on a " + landedAnimal.getName() + " in the " + landedAnimal.getType() + " habitat.");
            
            if (landedAnimal.getAnimal_owner() == 0) {
                System.out.println("This habitat is unowned.");
                
                System.out.println("Would you like to buy it for £" + landedAnimal.getPrice() + "? (y/n)");
                String buyAnswer = scanner.nextLine();
                
                if (buyAnswer.equalsIgnoreCase("y")) {
                    
                    if (money >= landedAnimal.getPrice()) {
                        landedAnimal.setAnimal_owner(playerNumber);
                        setMoney(-landedAnimal.getPrice());
                        System.out.println("You bought the habitat!");
                    } else {
                        System.out.println("you're too broke to buy the habitat dummy.");
                    }
                }
            } else {
                int owner = landedAnimal.getAnimal_owner();
                
                System.out.println("This habitat is already owned by Player " + landedAnimal.getAnimal_owner());
                
                if (owner != playerNumber) {

                    int rentCost = landedAnimal.getRent();
                    System.out.println("You must pay £" + rentCost + " in rent.");
                    setMoney(-rentCost);
            
                    if (owner == 1) {
                        Main.player1.setMoney(rentCost);
                    }
                    else if (owner == 2) {
                        Main.player2.setMoney(rentCost);
                    }
                    else if (owner == 3) {
                        Main.player3.setMoney(rentCost);
                    }
            
                }
            }
        } else if (location != 14) {
            System.out.println("You landed on an empty space. nothing happens lol \n");
        }

        Globals.doubleCount = 0;
    }

    public void checkAnimalOwnerShip() {
        ArrayList<String> ownedAnimalsSets = new ArrayList<String>();

        for (int i = 0; i < 8; i++) {
            Animals[] tempAnimalSet = Board.animals[i];
            if (tempAnimalSet[0].getAnimal_owner() == tempAnimalSet[1].getAnimal_owner()
                && tempAnimalSet[1].getAnimal_owner() == tempAnimalSet[2].getAnimal_owner()
                && tempAnimalSet[2].getAnimal_owner() == tempAnimalSet[0].getAnimal_owner()
                && (tempAnimalSet[0].getAnimal_owner() == playerNumber)) {
                ownedAnimalsSets.add(tempAnimalSet[0].getType());
            }
        }

        if (ownedAnimalsSets.isEmpty()) {
            System.out.println("You don't have a full set of habitats to upgrade");
        } else {
            System.out.println("The full sets of habitats you own are: " + ownedAnimalsSets);

            //ask them which one they want to upgrade
            System.out.println("\n Enter the index of the habitat you would like to upgrade");
            int tempIndex;
            Scanner scanner = new Scanner(System.in);
            do {
                tempIndex = scanner.nextInt();
                if (tempIndex < 0 || tempIndex > ownedAnimalsSets.size() - 1) {
                    System.out.println("\n The available indexes are from 0 to " + (ownedAnimalsSets.size() - 1));
                }
            } while (tempIndex < 0 || tempIndex > ownedAnimalsSets.size() - 1);

            upgradeHabitat(ownedAnimalsSets.get(tempIndex));
        }


    }

    //WORK ONT THIS
    public void upgradeHabitat(String pAnimalToUpgrade) {
        int upgradeCost = 0;
        
        for (int i = 0; i < 8; i++) {

            Animals[] tempAnimalSet = Board.animals[i];
    
            if (tempAnimalSet[0].getType().equals(pAnimalToUpgrade)) {
                upgradeCost = tempAnimalSet[0].getPrice();
                
                if (money >= upgradeCost) {
                    setMoney(-upgradeCost);
                    for (int j = 0; j < 3; j++) {
                        Animals animal = tempAnimalSet[j];
                        
                        animal.setLevel(animal.getLevel() + 1);
                        animal.setRent(animal.getRent() * 2);
                    }
                } else {
                    System.out.println("YOURE TOO BROKE TO UPGRADE YOUR HABITAT HAHAHA.");
                }
            }
        }
    }
    
    public void resetAnimals() {
        for (int i = 0; i < 8; i++) {
            
            Animals[] tempAnimalSet = Board.animals[i];
            for (int j = 0; j < 3; j++) {
                if (tempAnimalSet[j].getAnimal_owner() == playerNumber) {
                    tempAnimalSet[j].setAnimal_owner(0);
                }
            }
        }
    }
}
 
