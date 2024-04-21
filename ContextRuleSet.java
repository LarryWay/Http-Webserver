

public class ContextRuleSet extends HTTPRequest{


    public ContextRuleSet(HTTPRequest request){
        super(request);
    }


    public ContextRuleSet(){
        // Just nothingness
    }

    public void setMethodRule(HttpMethod newRule){
        this.method = newRule;
    }

    public void setUrlRule(String newRule){
        this.url = newRule;
    }

    public void setContentRule(String newRule){
        this.content = newRule;
    }

    public void setHtmlVersionRule(String newRule){
        this.htmlVersion = newRule;
    }

    public void setContentTypeRule(String newRule){
        this.contentType = newRule;
    }

    public void setContentLengthRule(Integer newRule){
        this.contentLength = newRule;
    }

    public void setHostRule(String newRule){
        this.host = newRule;
    }

    public void setCookiesRule(String newRule){
        this.cookies = newRule;
    }

    public void setConnectionRule(String newRule){
        this.connection = newRule;
    }



    public boolean equals(ContextRuleSet newSet){

        if(method != HttpMethod.NULL && method != newSet.getMethod()){
            return false;
        }
        
        if(url != null && !url.equals(newSet.getUrl())){
            return false;
        }

        if(content != null && !content.equals(newSet.getContent())){
            return false;
        }

        if(htmlVersion != null && !htmlVersion.equals(newSet.getHtmlVersion())){
            return false;
        }

        if(contentType != null && !contentType.equals(newSet.getContentType())){
            return false;
        }

        if(contentLength != -1 && !(contentLength == newSet.getContentLength())){
            return false;
        }

        if(host != null && !host.equals(newSet.getHost())){
            return false;
        }

        if(cookies != null && !cookies.equals(newSet.getCookies())){
            return false;
        }

        if(connection != null && !connection.equals(newSet.getConnection())){
            return false;
        }

        return true;

    }


}
