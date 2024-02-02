import java.util.Scanner;

public class CreateDatabaseCats {
    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);

        Cats cats = new Cats();

        while (true) {
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
        while (true) {
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
        while (true) {
            System.out.println("Если вам удалить котика по id (Y/N)");
            String answer1 = str.nextLine();
            if(answer1.equals("N")){
                break;
            }
            else {
                if (answer1.equals("Y")){
                    System.out.println("Введите id котика:");
                    int id = str.nextInt();
                    str.nextLine();
                    cats.delete_cat_id(id);
                }
            }
        }
        while (true) {
            System.out.println("Если вам удалить котика по первой букве имени (Y/N)");
            String answer1 = str.nextLine();
            if(answer1.equals("N")){
                break;
            }
            else {
                if (answer1.equals("Y")){
                    System.out.println("Введите первую букву имени котика:");
                    String where = str.nextLine().toUpperCase();
                    cats.delete_cat_where (where);
                }
            }
        }
        while (true) {
            System.out.println("Если вам изменить котика (Y/N)");
            String answer1 = str.nextLine();
            if(answer1.equals("N")){
                break;
            }
            else {
                if (answer1.equals("Y")){

                    System.out.println("Введите id котика которого надо изменить :");
                    int id = str.nextInt();
                    str.nextLine();
                    System.out.println("Выберите один (set) который надо изменить:");

                    System.out.print("type_id (Y/N) ");
                    String answer2 = str.nextLine();
                    if (answer2.equals("Y")) {
                        String where = "type_id";
                        System.out.print("Введите изменяемый type_id : ");
                        String set  = str.nextLine();
                        cats.update_cat(id, set, where);
                    }
                    else if (answer2.equals("N")){
                        System.out.println("name (Y/N)");
                        String answer3 = str.nextLine();
                        if (answer3.equals("Y")) {
                            String where = "name";
                            System.out.print("Введите изменяемое name : ");
                            String set = str.nextLine();
                            cats.update_cat(id, set, where);
                        }
                        else if (answer3.equals("N")) {
                            System.out.println("age (Y/N)");
                            String answer4 = str.nextLine();
                            if (answer4.equals("Y")){
                                String where = "age";
                                System.out.print("Введите измениямый age : ");
                                String set = str.nextLine();
                                cats.update_cat(id, set, where);
                            }
                            else if (answer4.equals("N")){
                                System.out.println("weight (Y/N)");
                                String answer5 = str.nextLine();
                                if (answer5.equals("Y")){
                                    String where = "weight";
                                    System.out.print("Введите измениямый weight : ");
                                    String set = str.nextLine();
                                    cats.update_cat(id, set, where);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

