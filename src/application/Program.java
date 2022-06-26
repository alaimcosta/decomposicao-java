package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //para receber o tipo de data

        System.out.print("Enter department's name: ");
        String departmentName = sc.nextLine();
        System.out.println("Enter worker data:");
        System.out.print("Name: ");
        String workerName = sc.nextLine();
        System.out.print("Level: ");
        String workerLevel = sc.nextLine();
        System.out.print("Base salary: ");
        double baseSalary = sc.nextDouble();//recebe o valor do salário base
        //composição de objetos
        //é instanciado um novo objeto do tipo Worker
        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));

        //quantos contratos?
        System.out.print("How many contracts to this worker? ");
        int n = sc.nextInt();

        for (int i=1; i<=n; i++) { //se repete varias vezes
            System.out.println("Enter contract #" + i + " data:");
            System.out.print("Date (DD/MM/YYYY): ");
            Date contractDate = sdf.parse(sc.next());//recebe o formato de data

            System.out.print("Value per hour: ");
            double valuePerHour = sc.nextDouble(); //Recebe o valor por hora

            System.out.print("Duration (hours): ");
            int hours = sc.nextInt();
            //Instancio o contrato
            HourContract contract = new HourContract(contractDate, valuePerHour, hours);
            worker.addContract(contract);//associo o contrato ao trabalhador
        }

        System.out.println();
        System.out.print("Enter month and year to calculate income (MM/YYYY): ");
        String monthAndYear = sc.next();
        int month = Integer.parseInt(monthAndYear.substring(0, 2));//recorto os dois primeiros caracteres e converto para int
        int year = Integer.parseInt(monthAndYear.substring(3));//recorto os quatro últimos, a posição 3 em diante
        System.out.println("Name: " + worker.getName());
        System.out.println("Department: " + worker.getDepartment().getName());
        System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month))); //passando a máscara %.2f

        sc.close();
    }
}