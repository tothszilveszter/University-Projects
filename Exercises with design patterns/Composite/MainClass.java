public class MainClass {
    public static void main(String[] args) {
        Developer dev1=new Developer("Adam Sandler","Ucenic");
        CompanyDirector devDir=new CompanyDirector();
        devDir.addEmployee(dev1);

        Manager man1=new Manager("Kevin Hart","HR");
        CompanyDirector manDir=new CompanyDirector();
        manDir.addEmployee(man1);

        CompanyDirector ceo=new CompanyDirector();
        ceo.addEmployee(devDir);
        ceo.addEmployee(manDir);

        ceo.showEmployeeDetails();
    }
}
