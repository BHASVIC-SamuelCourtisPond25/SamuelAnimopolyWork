public class Animals {
    int price;
    int level;
    int rent;
    int animal_owner = 0;
    String type;
    String name;

    Animals(int pPrice, int pLevel, int pRent, int pAnimal_owner, String pType, String pName){
        price = pPrice;
        level = pLevel;
        rent = pRent;
        animal_owner = pAnimal_owner;
        type = pType;
        name = pName;
    }

    public int getPrice() {
        return price;
    }

    public int getLevel() {
        return level;
    }
    
    public void setLevel(int pLevel) {
        level = pLevel;
    }

    public int getRent() {
        return rent;
    }
    
    public void setRent(int pRent) {
        rent = pRent;
    }

    public int getAnimal_owner() {
        return animal_owner;
    }
    
    public void setAnimal_owner(int pOwner) {
        animal_owner = pOwner;
    }

    public String getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
}
