import java.util.HashMap;
import java.net.Socket;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;


class HttpContext implements Runnable{

    protected HashMap<String, File> directoryMap;
    protected Integer port;
    protected HTTPRequest request = null;
    protected HttpWebServer webserver;
    protected ContextRuleSet ruleset;
    protected Socket webpageSocket;



    public HttpContext(HttpContext context){
        this.directoryMap = context.directoryMap;
        this.port = context.port;
        this.webserver = context.webserver;
        this.ruleset = context.ruleset;
    }


    public HttpContext(HttpWebServer w, HashMap<String, File> dirMap, Integer p){
        this.webserver = w;
        this.directoryMap = dirMap;
        this.port = p;
        this.ruleset = new ContextRuleSet();
    }



    
    public InputStream getInputStream_expr1(){
        return webserver.getInputStream();
    }


    public OutputStream getOutputStream_expr1(){
        return webserver.getOutputStream();
    }



    public ContextRuleSet getRuleSet(){
        return ruleset;
    }


    public void setHttpRequest(HTTPRequest newRequest){
        this.request = newRequest;
    }

    public void setSocket(Socket socket){
        this.webpageSocket = socket;
    }


    @Override
    public void run(){

    }

    

}
