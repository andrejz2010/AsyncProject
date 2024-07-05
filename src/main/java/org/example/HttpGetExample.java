import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetExample {

    private static String getWebpage(String url) {
        StringBuilder result = new StringBuilder();
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                String url = " https://www.baeldung.com/java-http-request";
                String webpage = getWebpage(url);
                System.out.println(webpage);
            }
        };

        Thread thread = new Thread(task);
        thread.start();
        System.out.println("Thread has been started");

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread has been completed"); //
    }
}
