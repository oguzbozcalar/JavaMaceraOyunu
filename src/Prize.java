public class Prize {

    private String name;

    public Prize(String name) {
        this.name = name;
    }

    public static Prize[] prizes() {
        Prize [] prizeList = new Prize[3];
        prizeList[0] = new Prize("Yemek");
        prizeList[1] = new Prize("Odun");
        prizeList[2] = new Prize("Su");

        return prizeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
