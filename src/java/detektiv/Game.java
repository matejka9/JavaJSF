package detektiv;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Game 
{
    
    @ManagedProperty(value="#{Application}")
    private Application application;
    
    public Game() 
    {
    }
        
    public void setNewMessage(String msg)
    {
        application.setNewMessage(msg, this);
    }
    
    public String getNewMessage(){
        return "";
    }
        
    public void say()
    {  
        application.say(this);
    }
    
    public void resend()
    {         
        application.resend(this);  
    }
    
    public String getMsgs()
    {
        return application.getMsgs(this);
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
    
}
