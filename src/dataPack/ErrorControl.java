/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASUS
 */
public class ErrorControl {

    public ErrorControl()
    {
    }

    public int validateNumericInputInt(Scanner input, String message)
    {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = Integer.parseInt(input.nextLine());
                if (number < 0) {
                    throw new IllegalArgumentException("Error: El número no puede ser negativo.");

                }
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Error: Por favor ingrese un número válido.\n");
            } catch (IllegalArgumentException e) {
                System.err.println("Error: Por favor ingrese un número válido.\n");
            }
        }
    }

    public String validateIDNumber(Scanner input, String message)
    {
        String idNumber;
        while (true) {
            System.out.print(message);
            idNumber = input.nextLine();
            if (idNumber.matches("\\d{10}")) {
                return idNumber;
            } else {
                System.err.println("Error: La cédula debe tener 10 números.\n");
            }
        }
    }

    public String validateTwoWords(Scanner input, String message, String categoria)
    {
        String names;
        while (true) {
            System.out.print(message);
            names = input.nextLine();

            Pattern pattern = Pattern.compile("^[a-zA-Z]+\\s[a-zA-Z]+$");
            Matcher matcher = pattern.matcher(names);

            if (matcher.matches()) {
                return names;
            } else {
                System.err.println("Error: por favor ingrese dos " + categoria + " separados por un espacio.\n");
            }

        }

    }

    public String validateStrings(Scanner input, String message)
    {
        String word;
        while (true) {
            System.out.print(message);
            word = input.nextLine();

            Pattern pattern = Pattern.compile("^[a-zA-Z0-9,](?:[a-zA-Z0-9, ]*[a-zA-Z0-9,])?$");
            Matcher matcher = pattern.matcher(word.trim());

            if (matcher.matches()) {
                return word;
            } else {
                System.err.println("Error: por favor ingrese un texto valido.\n");
            }
        }

    }

    public LocalDateTime validateLocalDateTime(Scanner input, String message)
    {
        String date;
        while (true) {
            System.out.print(message);
            date = input.nextLine();

            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
            Matcher matcher = pattern.matcher(date);

            if (matcher.matches()) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
                    return dateTime;
                } catch (DateTimeParseException e) {
                    System.err.println("Error: ingrese una fecha y hora v�lidas en el formato (yyyy-MM-dd HH:mm:ss)\n");
                }
            } else {
                System.err.println("Error: ingrese una fecha y hora v�lidas en el formato (yyyy-MM-dd HH:mm:ss)\n");
            }
        }
    }

    public String validateLocalDate(Scanner input, String message)
    {
        String date;
        while (true) {
            System.out.print(message);
            date = input.nextLine();

            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher matcher = pattern.matcher(date);

            if (matcher.matches()) {
                try {
                    LocalDate.parse(date);
                    return date;
                } catch (DateTimeParseException e) {
                    System.err.println("Error: ingrese una fecha v�lida (a�o, mes, d�a)\n");
                }
            } else {
                System.err.println("Error: ingrese una fecha v�lida (a�o, mes, d�a) en formato (yyyy-MM-dd)\n");
            }

        }
    }

//Este metodo se puede utilizar para ingresar años/dias/meses/horas/minutos y segundos

    /*
    Ejemplo de uso
    
          añoNacimiento = validarFecha(lectura, "año de nacimiento", 4, 2024);
                            mesNacimiento = validarFecha(lectura, "mes de nacimiento", 2, 12);
                            diaNacimiento = validarFecha(lectura, "día de nacimiento", 2, 31);
    
     */
    public int validateDate(Scanner input, String message, int length, int maximum)
    {
        int number;
        while (true) {
            try {
                System.out.println("Ingrese el " + message + ":");
                String userInput = input.nextLine();

                if (userInput.length() != length) {
                    throw new IllegalArgumentException("Error: La longitud de " + message + " debe ser de " + length + " dígitos.");
                }

                number = Integer.parseInt(userInput);

                if (number <= 0 || number > maximum) {
                    throw new IllegalArgumentException("Error: " + message + " debe ser un número mayor que cero y menor o igual que " + maximum + ".");
                }

                return number;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido para " + message + ".");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean validateDateTimeRange(LocalDateTime startDate, LocalDateTime endDate)
    {
        // Verificar si la fecha de inicio es antes o igual que la fecha de fin
        return !endDate.isBefore(startDate) || startDate.isEqual(endDate);
    }

}
