public class Duckie {

  public String name;
  public int age;

  public Duckie(int age, String name) {
    this.name = name;
    this.age = age;
  }

  public void quack() {
    System.out.println("Quack!");
  }

  public String toString() {
    return this.name + " is " + this.age + " years old";
  }

}