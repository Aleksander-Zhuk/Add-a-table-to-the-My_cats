public class Cats_info {
    private String type;
    private String name;
    private int age;
    private double weight;
    public Cats_info (String type, String name, int age, double weight) {
        this.type = type;
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    // Геттеры
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    // Сеттеры
    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
