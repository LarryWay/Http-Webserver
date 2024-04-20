
import java.io.*;

public class HelloWorld{

    public static void main(String[] args) {

        // Create the webserver object. This is essential for every project
        HttpWebServer webserver = new HttpWebServer(8080);



        // Creating a default context
        // There are many different "default" created contexts designed for the server
        DefaultHtmlWebpageDisplayContext dhwdc = new DefaultHtmlWebpageDisplayContext(webserver.createContext());
        webserver.addContext(dhwdc);



        // Add a directory that links to an html file 
        webserver.addDirectory("/", new File("index.html"));



        // Start server - the server will not start until directed to
        webserver.startServer();



        System.out.println("Server is starting!");
        while(true){
            // Stall to make sure the server stays alive
        }


    }

}