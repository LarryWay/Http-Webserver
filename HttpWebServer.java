import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.net.*;
import java.util.HashMap;
import java.util.ArrayList;


/*  -- Defined in its own public class "DefaultFaviconHandlerContext"
       Can now be accessed by users using the .jar file

class FaviconHandlerContext extends HttpContext{;
    
    public FaviconHandlerContext(HttpContext context){
        super(context);

        ruleset.setUrlRule("/favicon.ico");
        ruleset.setMethodRule(HttpMethod.GET);
        ruleset.setHtmlVersion("HTTP/1.1");
    }

    @Override
    public void run(){

        if (webserver.getFavicon() == null) return;

        byte[] faviconBytes;
        try(BufferedInputStream buff = new BufferedInputStream(new FileInputStream(webserver.getFavicon()))){
            faviconBytes = buff.readAllBytes();
            HTTPResponse newResponse = new HTTPResponse(faviconBytes);
            webpageSocket.getOutputStream().write(newResponse.toBytes(true));
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("Error Issue when writing favicon");
        }

    }

}
*/




/* -- Defined in its own public class "DefaultWebpageDisplayContext"
      Can now be accessed by users using the .jar file
class HtmlWebpageDisplayContext extends HttpContext{

    public HtmlWebpageDisplayContext(HttpContext context){
        super(context);
        ruleset.setMethodRule(HttpMethod.GET);
    }

    @Override 
    public void run(){

        if(directoryMap.containsKey(request.url)){
            try{
                webpageSocket.getOutputStream().write(new HTTPResponse(directoryMap.get(request.url)).toBytes());
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

}
*/





class StartThread extends Thread{

    private Integer port;
    private ArrayList<HttpContext> contextList;


    public StartThread(Integer p, ArrayList<HttpContext> contextList){
        port = p;
        this.contextList = contextList;
    }


    
    public void evaluateContexts(HTTPRequest request, ContextRuleSet basedRuleSet, Socket s){
        for(HttpContext context : contextList){
            if(context.getRuleSet().equals(basedRuleSet)){
                context.setHttpRequest(request);
                context.setSocket(s);
                Thread action = new Thread(context);
                action.start();
                try{
                    action.join();
                }catch(Exception e){
                    e.printStackTrace();
                }   
            }
        }
    }






    @Override
    public void run(){



        boolean listening = true;

        try(ServerSocket serverSocket = new ServerSocket(port)){

            while(listening){

                Socket socket = serverSocket.accept();
                InputStream in = socket.getInputStream();

                while(in.available() <= 50){
                    // Intentional stall to wait for browser to write to stream
                    // If this pause wasn't in place, the thread will accidentally work faster than the browser can keep up
                }
                

                StringBuffer buffer = new StringBuffer();
                while(in.available() != 0){
                    buffer.append((char) in.read());
                }


                HTTPRequest request = new HTTPRequest(buffer.toString());
                ContextRuleSet basedRuleSet = new ContextRuleSet(request);

                evaluateContexts(request, basedRuleSet, socket);
                socket.close();

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}













public class HttpWebServer{

    private Integer port;
    private HashMap<String, File> directoryMap = new HashMap<String, File>();
    private File favicon;

    public volatile Socket pipeline;
    public volatile Socket serverPipeline;
    public ServerSocket serverSocketPipeline;

    private ArrayList<HttpContext> contextList = new ArrayList<>();



    private void keepSocketsAlive(Socket dP, Socket sDP){

        boolean isAlive = true;
        while(isAlive){
            try{
                Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }



    public HttpWebServer(Integer portNumber){
        port = portNumber;
    }
    

    public void addDirectory(String directory, File path){
        directoryMap.put(directory, path);
    }


    public void addContext(HttpContext newContext){
        contextList.add(newContext);
    }


    public HttpContext createContext(){
        return new HttpContext(this, directoryMap, port);
    }




    public void startServer(){

        try{
            serverSocketPipeline = new ServerSocket(port);
            pipeline = new Socket("localhost", port);
            serverPipeline = serverSocketPipeline.accept();
            serverSocketPipeline.close();
        }catch(Exception e){
            e.printStackTrace();
        }


        Thread keepSocketsAlive = new Thread(() -> keepSocketsAlive(pipeline, serverPipeline));
        keepSocketsAlive.setDaemon(true);
        keepSocketsAlive.start();


        StartThread serverStartThread1 = new StartThread(port, contextList);
        serverStartThread1.setDaemon(true);
        serverStartThread1.start();

    }


    public void setFavicon(File file){
        this.favicon = file;
    }



    public synchronized OutputStream getOutputStream(){
        try{
            return this.pipeline.getOutputStream();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return OutputStream.nullOutputStream();
        
    }

    public synchronized InputStream getInputStream(){
        try{
            return this.serverPipeline.getInputStream();
        }catch(Exception e){
            e.printStackTrace();
        }
        return InputStream.nullInputStream();
    }



    public synchronized File getFavicon(){
        return favicon;
    }

    

}
