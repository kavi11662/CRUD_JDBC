import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception{
    CRUD_JDBC student=new CRUD_JDBC();
    Scanner sc =new Scanner(System.in);
    int choice;

    do {
      System.out.println("-------------  Student Management System  --------------");
      System.out.println();
      System.out.println("Enter 1 for to add student in database");
      System.out.println("Enter 2 for to retrive in data");
      System.out.println("Enter 3 for to update student in database");
      System.out.println("Enter 4 for to delete student in database");
      System.out.println("Enter 5 for EXIT");
      choice=sc.nextInt();
      sc.nextLine();
      switch (choice) {
        case 1:
          System.out.println("Enter NAME: ");
          String name=sc.nextLine();
          System.out.println("Enter ID: ");
          int id=sc.nextInt();
          sc.nextLine();
          System.out.println("Enter the GRADE: ");
          String grade=sc.nextLine();
          student.addStudent(new Student(id, name, grade));
          break;

        case 2:
          student.getStudent();  
          break;

        case 3:
          System.out.println("Enter the ID of the student to update the GRADE: ");
          int idupdate=sc.nextInt();
          sc.nextLine();
          System.out.println("Enter the new GRADE: ");
          String updateGrade=sc.nextLine();
          student.updateGrade(idupdate, updateGrade);
          break;

        case 4:
          System.out.println("Enter the ID to delete the student data from database: ");  
          int idDelete=sc.nextInt();
          sc.nextLine();
          student.deleteStudent(idDelete);
          break;

        case 5:
          student.close();
          System.out.println("Thank YOU!!!");  
          break;

        default:
          System.out.println("INVALID Choice: Enter the valid choice From 1 to 5");
          break;
      }
      
    } while (choice!=5);
    sc.close();
  }
}
