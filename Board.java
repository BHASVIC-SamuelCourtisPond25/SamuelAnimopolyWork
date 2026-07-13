import java.util.Arrays;
import java.util.random.RandomGenerator;


public class Board {
    public int[] monopoloyBoard;

    //Bug
    static Animals centipede = new Animals(1,1,1,0,"Bug", "Centipede");
    static Animals dungBeetle = new Animals(1,1,1,0,"Bug", "Dung Beetle");
    static Animals stickInsect = new Animals(1,1,1,0,"Bug", "Stick Insect");

    //Park
    static Animals pigeon = new Animals(1,1,1,0,"Park", "Pigeon");
    static Animals squirrel = new Animals(1,1,1,0,"Park", "Squirrel");
    static Animals rat = new Animals(1,1,1,0,"Park", "Rat");

    //House
    static Animals cat = new Animals(1,1,1,0,"House", "Cat");
    static Animals dog = new Animals(1,1,1,0,"House", "Dog");
    static Animals guineaPig = new Animals(1,1,1,0,"House", "Guinea Pig");

    //Farm
    static Animals cow = new Animals(1,1,1,0,"Farm", "Cow");
    static Animals pig = new Animals(1,1,1,0,"Farm", "Pig");
    static Animals sheep = new Animals(1,1,1,0,"Farm", "Sheep");

    //Desert
    static Animals camel = new Animals(1,1,1,0,"Desert", "Camel");
    static Animals scorpion = new Animals(1,1,1,0,"Desert", "Scorpion");
    static Animals vulture = new Animals(1,1,1,0,"Desert", "Vulture");

    //Jungle
    static Animals poisonDartFrog = new Animals(1,1,1,0,"Jungle", "Poison Dart Frog");
    static Animals monkey = new Animals(1,1,1,0,"Jungle", "Monkey");
    static Animals jaguar = new Animals(1,1,1,0,"Jungle", "Jaguar");

    //Safari
    static Animals elephant = new Animals(1,1,1,0,"Safari", "Elephant");
    static Animals giraffe = new Animals(1,1,1,0,"Safari", "Giraffe");
    static Animals lion = new Animals(1,1,1,0,"Safari", "Lion");

    //Ocean
    static Animals shark = new Animals(1,1,1,0,"Ocean", "Shark");
    static Animals turtle = new Animals(1,1,1,0,"Ocean", "Turtle");
    static Animals dolphin = new Animals(1,1,1,0,"Ocean", "Dolphin");


    public static Animals[][] animals = {{centipede, dungBeetle, stickInsect}, {pigeon, squirrel, rat}, {cat, dog, guineaPig}, {cow, pig, sheep}, {camel, scorpion, vulture}, {poisonDartFrog, monkey, jaguar}, {elephant, giraffe, lion}, {shark, turtle, dolphin}};


    public Board() {
        this.monopoloyBoard = new int[28]; //first row is position number, second is whats on the board (which animal/miss a turn),
        for (int i = 0; i < 28; i++) {
            this.monopoloyBoard[i] = i + 1; //position spaces, 1, 2, 3, 4, etc
        }

    }

    public void getMonopolyBoard() {
        System.out.println(Arrays.toString(monopoloyBoard));
    }

    public void getPlaceData(int paramIndex) {
        paramIndex -= 1;
        System.out.println("The position space you are on is Position " + monopoloyBoard[paramIndex]);
        System.out.printf("\nThere is a %s on that space", "IDK YET FIGURE IT OUT");
    }

    public Animals getAnimalFromIndex(int paramIndex) {
        switch (paramIndex) {
            case 0:
                return null; //start
            case 1:
                return centipede;
            case 2:
                return dungBeetle;
            case 3:
                return stickInsect;
            case 4:
                return pigeon;
            case 5:
                return squirrel;
            case 6:
                return rat;
            case 7:
                return null;  // empty space
            case 8:
                return cat;
            case 9:
                return dog;
            case 10:
                return guineaPig;
            case 11:
                return cow;
            case 12:
                return pig;
            case 13:
                return sheep;
            case 14:
                return null; //miss a turn
            case 15:
                return camel;
            case 16:
                return scorpion;
            case 17:
                return vulture;
            case 18:
                return poisonDartFrog;
            case 19:
                return monkey;
            case 20:
                return jaguar;
            case 21:
                return null; //empty space
            case 22:
                return elephant;
            case 23:
                return giraffe;
            case 24:
                return lion;
            case 25:
                return shark;
            case 26:
                return turtle;
            case 27:
                return dolphin;
            default:
                return null; //should never happen
        }
    }



    public static int rollDice() {
        RandomGenerator generator = RandomGenerator.getDefault();
        int diceResult1 = generator.nextInt(6) + 1;
        int diceResult2 = generator.nextInt(6) + 1;
        while(diceResult1 == diceResult2) {
            Globals.doubleCount++;
            System.out.println("You have rolled a double " + diceResult1);
            diceResult1 = generator.nextInt(6) + 1;
            diceResult2 = generator.nextInt(6) + 1;
        }
        System.out.println("You have rolled a " + diceResult1 + " and a " + diceResult2);
        return(diceResult1 + diceResult2);
    }


}
