import java.util.Scanner;
import java.net.*;
import java.io.*;

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        // greet the user
        System.out.println("Hello, I'm your chat bot. How can I help you?");

        // loop to read user input and respond
        while (true) {
            // read user input
            input = scanner.nextLine();

            // check user input and respond
            if (input.toLowerCase().contains("hi") || input.toLowerCase().contains("hello")) {
                System.out.println("Hi there!");
            } else if (input.toLowerCase().contains("how are you")) {
                System.out.println("I'm doing well, thanks for asking.");
            } else if (input.toLowerCase().contains("weather in ")) {
                String city = input.toLowerCase().replace("weather in ", "");
                String apiKey = "13a512de5b973fd9ce9bb45fa5810dd0";
                String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

                try {
                    URL weatherUrl = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) weatherUrl.openConnection();
                    conn.setRequestMethod("GET");

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // parse the response to get the weather information
                    String weather = parseWeather(response.toString());

                    System.out.println("The weather in " + city + " is " + weather);
                } catch (IOException e) {
                    System.out.println("An error occurred while making the weather request: " + e.getMessage());
                }
            } else if (input.toLowerCase().contains("bye")) {
                System.out.println("Goodbye!");
                break; // exit loop
            } else {
                System.out.println("I'm sorry, I don't understand.");
            }
        }
    }

    // helper method to parse the weather information from the response
    private static String parseWeather(String response) {
        // you can customize this method to extract the weather information you need
        return response;
    }
}