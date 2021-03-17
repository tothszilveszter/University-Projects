public class Manager implements Employee{
    private String name;
    private String position;

    public Manager(String name, String position) {
        this.name = name;
        this.position = position;
    }


    @Override
    public void showEmployeeDetails() {
        System.out.println(name + " " + position);
    }
}
