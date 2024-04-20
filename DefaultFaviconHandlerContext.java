import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class DefaultFaviconHandlerContext extends HttpContext{;
    
    public DefaultFaviconHandlerContext(HttpContext context){
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
