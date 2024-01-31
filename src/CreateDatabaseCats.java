import java.util.Scanner;

public class CreateDatabaseCats {
    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);

        Cats cats = new Cats();


        while (true){
            System.out.println("Если вам надо добавить котика (Y/N)");
            String answer = str.nextLine();
            if(answer.equals("N")){
                break;
            }
            else  if (answer.equals("Y")){
                System.out.println("Введите name:");
                String name = str.nextLine();
                System.out.println("Введите type:");
                String type = str.nextLine();
                System.out.println("Введите age:");
                int age = str.nextInt();
                str.nextLine();
                System.out.println("Введите weight:");
                String input = str.nextLine();
                if (input.contains(",")) {
                    input = input.replace(",", ".");
                }
                double weight = Double.parseDouble(input);
                cats.insert_cat(name, type, age, weight);
            }
        }
        while (true){
            System.out.println("Хотите добавить рандомных котиков (Y/N)");
            String anser = str.nextLine();
            if (anser.equals("N")){
                break;
            }
            else if (anser.equals("Y")){
                System.out.println("Введите какое количество добавить :");
                int n = str.nextInt();
                str.nextLine();
                cats.add_more_cats(n);
            }
        }
    }
}

