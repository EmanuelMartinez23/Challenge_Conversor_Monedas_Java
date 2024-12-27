import Model.Conversion;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String apiBase = "https://v6.exchangerate-api.com/v6/4ebf049c15da0aa105e64bf9/pair";
        List<String> listCoin = new ArrayList<>();
        listCoin.add("MXN");
        listCoin.add("USD");
        listCoin.add("EUR");
        listCoin.add("RUB");
        Scanner scanner =  new Scanner(System.in);
        String baseCode;
        String targetCode;
        double money;
        while(true){
            System.out.println("\n============================================");
            System.out.println("Conversor de monedas");
            System.out.println("Nuestro servicio cuenta con las monedas más usadas: ");
            System.out.println(listCoin);
//            System.out.println("======= Recuerda que cada code es de 3 =========");
            System.out.println("======= Escribe Salir para terminar =========");
            System.out.println("Ingresa la cantidad que deseas convertir : ");
            money = scanner.nextDouble();
            if (money < 0){
                System.out.println("Recuerda que tu cantidad debe ser positiva :)");
                continue;
            }

            System.out.println("Ingresa el tipo de moneda base que tienes: ");
            baseCode = scanner.next();
            if (baseCode.equalsIgnoreCase("salir") || !listCoin.contains(baseCode)){
                if(baseCode.equalsIgnoreCase("salir")){
                    System.out.println("Gracias por su preferencia!!");
                }else{
                    System.out.println("El tipo de moneda de cambio no esta disponible vuelva pronto :)");
                }
                break;
            }
            System.out.println("Ingresa a que tipo de moneda quieres convertir: ");
            targetCode = scanner.next();
            if (targetCode.equalsIgnoreCase("salir") || !listCoin.contains(baseCode)){
                if(targetCode.equalsIgnoreCase("salir")){
                    System.out.println("Gracias por su preferencia!!");
                }else{
                    System.out.println("El tipo de moneda de cambio no esta disponible vuelva pronto :)");
                }
                break;
            }
            String apiRequest = apiBase + "/" + baseCode+ "/" + targetCode + "/";

            HttpResponse<String> response = getResponse(apiRequest);

            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            // Get attributes from a JSON to Class
            Conversion conversion1 = gson.fromJson(response.body(), Conversion.class);
            String conversionRate = conversion1.getConversionRate();
            double total = Double.parseDouble(conversionRate) * money;
            System.out.println("Gracias por usar nuestro servicio de convertir de "
                    + conversion1.getBaseCode() + " a " + conversion1.getTargetCode());
            System.out.println("El valor de tu conversión es: " + total);


        }


    }

    private static HttpResponse<String> getResponse(String apiRequest) throws IOException, InterruptedException {
        // Create client
        HttpClient client = HttpClient.newHttpClient();
        URI url = URI.create(apiRequest);
        // Request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        // Response of req
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    // Example for create JSON
//    Conversion conversion = new Conversion("MXN", "USD", "0.491");
//    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
//    String jsonRepresentation = gson.toJson(conversion);

}
