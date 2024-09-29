package madhukar;


package madhukar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;  // Make sure this library is added to your project

public class CurrencyConverter {

    // Function to fetch exchange rate between base currency and target currency
    public static double getExchangeRate(String baseCurrency, String targetCurrency) {
        String apiKey = "your_api_key";  // Replace with your API key
        String apiUrl = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", apiKey, baseCurrency, targetCurrency);

        try {
            // Create a URL object with the API endpoint
            URL url = new URL(apiUrl);

            // Open a connection to the API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check for successful response code (HTTP 200 OK)
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Read the API response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Convert the response string to JSON object
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Extract exchange rate from the JSON response
                double exchangeRate = jsonResponse.getDouble("conversion_rate");
                return exchangeRate;
            } else {
                System.out.println("Error: API response code: " + responseCode);
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Function to convert currency
    public static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    public static void main(String[] args) {
        // Define base and target currencies
        String baseCurrency = "USD";
        String targetCurrency = "INR";

        // Get exchange rate from base currency to target currency
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

        if (exchangeRate != -1) {
            // Convert amount from base currency to target currency
            double amountInBaseCurrency = 100.0;  // Example: 100 USD
            double amountInTargetCurrency = convertCurrency(amountInBaseCurrency, exchangeRate);

            // Output the conversion result
            System.out.println(amountInBaseCurrency + " " + baseCurrency + " is equal to " + amountInTargetCurrency + " " + targetCurrency);
        } else {
            System.out.println("Currency conversion failed.");
        }
    }
}

