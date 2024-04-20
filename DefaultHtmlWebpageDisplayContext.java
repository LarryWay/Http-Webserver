public class DefaultHtmlWebpageDisplayContext extends HttpContext{

    public DefaultHtmlWebpageDisplayContext(HttpContext context){
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