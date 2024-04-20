

public class ContextRuleSet {

    public HttpMethod methodRule = HttpMethod.NULL;
    public String urlRule = null;
    public String contentRule = null;
    public String htmlVersion = null;


    public ContextRuleSet(HTTPRequest request){
        this.methodRule = request.method;
        this.urlRule = request.url;
        this.contentRule = request.content;
        this.htmlVersion = request.htmlVersion;
    }


    public ContextRuleSet(){
        // Just nothingness
    }

    public void setMethodRule(HttpMethod newRule){
        this.methodRule = newRule;
    }

    public void setUrlRule(String newRule){
        this.urlRule = newRule;
    }

    public void setContentRule(String newRule){
        this.contentRule = newRule;
    }

    public void setHtmlVersion(String newRule){
        this.htmlVersion = newRule;
    }



    public boolean equals(ContextRuleSet newSet){

        if(methodRule != HttpMethod.NULL && methodRule != newSet.methodRule){
            //System.out.println("http method does not match");
            return false;
        }
        
        if(urlRule != null && !urlRule.equals(newSet.urlRule)){
            return false;
        }

        if(contentRule != null && !contentRule.equals(newSet.contentRule)){
            return false;
        }

        if(htmlVersion != null && !htmlVersion.equals(newSet.htmlVersion)){
            return false;
        }

        //System.out.println("Successful comparison");
        return true;

    }


}
