/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detektiv;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Data {
    
    private List<String> messages;
    private WeakReference<Game> autor;
    private WeakReference<Game> detective;
    private Boolean turn;
    private boolean running;
    
    public Data(Game autor){
        this.autor = new WeakReference<>(autor);
        this.messages = new ArrayList<>();
        this.detective = null;
        this.turn = null;
        this.running = true;
    }

    public List<String> getMessages() {
        return messages;
    }

    public WeakReference<Game> getAutor() {
        return autor;
    }

    public WeakReference<Game> getDetective() {
        return detective;
    }

    public void setDetective(Game detective) {
        this.detective = new WeakReference<>(detective);
    }
    
    
    void add(String msg, Game game) {
        System.out.println("Idem vkladat");
        if (isTurnOf(game) && running){
            System.out.println("Idem vkladat");
            if (isAuthorTurn()){
                if (turn != null) {
                    if (msg.equals("Yes") || msg.equals("No") || msg.equals("No, Comment")) {
                        messages.add("Answer: " + msg);
                        changeTurn();
                    } else if (msg.equals("That's it")) {
                        messages.add("Answer: " + msg);
                        running = false;
                    }
                } else {
                    messages.add("Story: " + msg);
                    changeTurn();
                }
            } else {
                messages.add("Question: " + msg);
                changeTurn();
            }
        }
    }
    
    private boolean isTurnOf(Game game) {
        if (game.equals(autor.get())){
            return isAuthorTurn();
        } else {
            return isDetectiveTurn();
        }
    }
    
    private void changeTurn() {
        if (turn == null){
            turn = false;
        } else {
            turn = !turn;
        }
    }
    
    private boolean isAuthorTurn(){
        return turn == null || turn;
    }
    
    private boolean isDetectiveTurn(){
        return turn != null && !turn;
    }
    
}
