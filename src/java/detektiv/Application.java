/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detektiv;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author dusky
 */
@ManagedBean(name="Application")
@ApplicationScoped
public class Application implements Serializable{    
    
    private final Map<Game, Data> gameDatas;
    
    private Game newAutorGame;
    
    public Application() 
    {
        gameDatas = new HashMap<>();
    }

    String getMsgs(Game game) {
        createDataIfNeeded(game);
        StringBuilder msgs = new StringBuilder();
        synchronized(gameDatas)
        {
            Data data = gameDatas.get(game);
            List<String> messages = data.getMessages();
            for (String m : messages)
            {
                msgs.append(m);
                msgs.append("<br />");
            }        
            return msgs.toString();
        }
    }

    void setNewMessage(String msg, Game game) {
        synchronized(gameDatas)
        {
            Data data = gameDatas.get(game);
            data.add(msg, game);
        }  
    }

    void say(Game game) {
        Data data = gameDatas.get(game);
        synchronized(data)
        {
            data.notifyAll();
        }
    }

    void resend(Game game) {
        Data data = gameDatas.get(game);
        try { 
            synchronized(data)
            {
               data.wait(30000);
            }
        } catch (Exception e) { }  
    }

    private void createDataIfNeeded(Game game) {
        synchronized(gameDatas){
            if (gameDatas.containsKey(game)) {
                System.out.println("Data existuju");
            } else {
                if (newAutorGame == null) {
                    newAutorGame = game;
                    Data autorData = new Data(newAutorGame);
                    gameDatas.put(newAutorGame, autorData);
                } else if (!newAutorGame.equals(game)){
                    Data autorData = gameDatas.get(newAutorGame);
                    autorData.setDetective(game);
                    gameDatas.put(game, autorData);
                    newAutorGame = null;
                }
            }
        }
    }
    
}
