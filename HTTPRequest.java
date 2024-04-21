


public class HTTPRequest {

    protected HttpMethod method = HttpMethod.NULL;
    protected String url = null;
    protected String content = null;
    protected String htmlVersion = null;
    protected String contentType = null;
    protected Integer contentLength = -1;
    protected String cookies = null;
    protected String host = null;
    protected String connection = null;
    
    protected String fullMessage = null;


    public HTTPRequest(){
        // default initialization aka do nothing
    }

    public HTTPRequest(HTTPRequest newRequest){
        method = newRequest.getMethod();
        url = newRequest.getUrl();
        content = newRequest.getContent();
        htmlVersion = newRequest.getHtmlVersion();
        contentType = newRequest.getContentType();
        contentLength = newRequest.getContentLength();
        cookies = newRequest.getCookies();
        host = newRequest.getHost();
        connection = newRequest.getConnection();
        fullMessage = newRequest.fullMessage;
    }


    public HTTPRequest(String fullMessage){


        this.fullMessage = fullMessage;

        String[] split = fullMessage.split("\r\n");
        String[] line = split[0].split(" ");

        if(split.length < 3){
            return;
        }


        for(String s : split){
            
            if(s.contains("Host:")){
                host = s.substring((s.indexOf(": ")) + 2);
            }

            if(s.contains("Cookies: ")){
                cookies = s.substring((s.indexOf(": ")) + 2);
            }

            if(s.contains("Content-Type: ")){
                contentType = s.substring((s.indexOf(": ")) + 2);
            }

            if(s.contains("Content-Length: ")){
                contentLength = Integer.valueOf(s.substring(s.indexOf(": ") + 2));
            }

            if(s.contains("Connection: ")){
                connection = s.substring(s.indexOf(": ") + 2);
            }
        }



        method = HttpMethod.stringToMethod(line[0]);
        System.out.println("PARSED METHOD FOUND: " + HttpMethod.methodToString(method));
        url = line[1];
        htmlVersion = line[2];

        if(method == HttpMethod.GET){
            content = "";
        }else{
            content = split[split.length - 1];
        }

    }



    public HttpMethod getMethod(){
        return this.method;
    }

    public String getUrl(){
        return this.url;
    }

    public String getContent(){
        return this.content;
    }

    public String getHtmlVersion(){
        return this.htmlVersion;
    }

    public String getContentType(){
        return this.contentType;
    }

    public Integer getContentLength(){
        return this.contentLength;
    }

    public String getCookies(){
        return this.cookies;
    }

    public String getHost(){
        return this.host;
    }

    public String getConnection(){
        return this.connection;
    }


    public String toOriginalText(){
        return this.fullMessage;
    }

    public String toParsedText(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(HttpMethod.methodToString(method)).append(" ");
        buffer.append(url).append(" ");
        buffer.append(htmlVersion).append("\r\n");
        buffer.append("Host: ").append(host).append("\r\n");
        buffer.append("Connection: ").append(connection).append("\r\n");

        if(contentLength != -1){

        }

        if(method == HttpMethod.POST){
            buffer.append("Content-Length: ").append(contentLength).append("\r\n");
            buffer.append("Content-Type: ").append(contentType).append("\r\n");
        }

        buffer.append("\r\n").append(content).append("\r\n");



        return buffer.toString();

    }

    public String toString(){
        return fullMessage;
    }


    
}


/* To-do
 * - Make variables constant; they should not be changeable once initialized
 *      Because HTTPRequest objects are shared between contexts by reference, these 
 *      contexts should not be able to modify the object, in turn modifying what other 
 *      contexts will see
 * 
 */