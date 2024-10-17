package organisation;

public class Organisation {
    private String name;
    private Space[] spaces;

    public Organisation(String name,  Space[] spaces) {
        this.name = name;
        this.spaces = spaces;
    }

    public String getName() {
        return name;
    }

    public Space[] getSpaces() {
        return spaces;
    }

    public String toString() {
        String result = "Name: " + name + "\nLocation: "  + "\nSpaces:\n";
        for (Space space : spaces) {
            result += space.toString() + "\n";
        }
        return result;
    }
}
