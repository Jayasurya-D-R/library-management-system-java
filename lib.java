import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Library{
    String Title;
    String Author;
    int Price;
    Library(String Title,String Author,int Price){
        this.Title=Title;
        this.Author=Author;
        this.Price=Price;
    }
    void display(){
        System.out.println("Title:"+Title+" ,Author:"+Author+" ,Price:"+Price);
    }
}
class Main{
    static void addbook(ArrayList<Library> list,Scanner scan){
        System.out.println("Enter the name of the book:");
        String Title=scan.nextLine();
        System.out.println("Enter the author:");
        String Author=scan.nextLine();
        System.out.println("Enter the price");
        int Price=scan.nextInt();
        scan.nextLine();
        list.add(new Library(Title,Author,Price));
        System.out.println("Added Successfully");
    }
    static void viewbooks(ArrayList<Library> list){
        if(list.isEmpty()){
            System.out.println("No books found");
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).display();
            }
        }
    }
    static void searchbook(ArrayList<Library> list,Scanner scan){
        System.out.println("Enter the name of the book:");
        String check=scan.nextLine();
        boolean search=false;
        for(int i=0;i<list.size();i++){
            if(check.equals(list.get(i).Title)){
                list.get(i).display();
                search=true;
            }
        }
        if(!search){
            System.out.println("Book not found");
        }
    }
    static void deletebook(ArrayList<Library> list,Scanner scan){
        System.out.println("Enter the name of the book:");
        String check=scan.nextLine();
        boolean search=false;
        for(int i=0;i<list.size();i++){
            if(check.equals(list.get(i).Title)){
                list.remove(i);
                System.out.println("Book deleted successfully");
                search=true;
                break;
            }
        }
        if(!search){
            System.out.println("Book not found");
        }
    }
    static void updateprice(ArrayList<Library> list,Scanner scan){
        System.out.println("Enter the name of the book:");
        String check=scan.nextLine();
        System.out.println("Entet the price to update:");
        int update= scan.nextInt();
        scan.nextLine();
        boolean search=false;
        for(int i=0;i<list.size();i++){
            if(check.equals(list.get(i).Title)){
                list.get(i).Price=update;
                System.out.println("Book updated successfully");
                search=true;
                break;
            }
        }
        if(!search){
            System.out.println("Book not found");
        }
    }
    static void costlierbook(ArrayList<Library> list){
        if(list.isEmpty()){
            System.out.println("No books found");
        }
        else {
            Library max = list.get(0);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).Price > max.Price) {
                    max = list.get(i);
                }
            }
            System.out.println("The costlier book is:" + max.Title + " ,Price:" + max.Price);
        }
    }
    static void readfile(ArrayList<Library> list){
        try{
            BufferedReader br=new BufferedReader(new FileReader("your path"));
            String line;
            while((line=br.readLine())!=null){
                String[] data=line.split(",");
                String Title=data[0];
                String Author=data[1];
                int Price=Integer.parseInt(data[2]);
                list.add(new Library(Title,Author,Price));
            }
            br.close();
        }
        catch(Exception e){
            System.out.println("File not found");
        }
    }
    static void writefile(ArrayList<Library> list){
        try {
            FileWriter fw=new FileWriter("your path");
            for(int i=0;i<list.size();i++){
                fw.write(list.get(i).Title + "," + list.get(i).Author + "," + list.get(i).Price + "\n");
            }
            fw.close();
        }
        catch (Exception e){
            System.out.println("File not found");
        }
    }
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ArrayList<Library> list=new ArrayList<>();
        boolean add=true;
        readfile(list);
        while(add) {
            System.out.println("Select your menu");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Update Price");
            System.out.println("6. Find Costliest Book");
            System.out.println("7. Exit");
            int choice=scan.nextInt();
            scan.nextLine();
            if (choice == 1) {
                addbook(list,scan);
                writefile(list);
            } else if (choice == 2) {
                viewbooks(list);
            } else if (choice == 3) {
                searchbook(list,scan);
            } else if (choice == 4) {
                deletebook(list,scan);
                writefile(list);
            } else if (choice == 5) {
                updateprice(list,scan);
                writefile(list);
            } else if (choice == 6) {
                costlierbook(list);
            } else if (choice == 7) {
                add=false;
            }
            else{
                System.out.println("Enter proper command");
            }
        }
    }
}
