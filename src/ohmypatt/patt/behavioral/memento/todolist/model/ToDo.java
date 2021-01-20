package ohmypatt.patt.behavioral.memento.todolist.model;

public class ToDo implements Cloneable {
    private String content;
    private boolean done;

    public ToDo(String content) {
        this.content = content;
        this.done = false;
    }
    
    private ToDo(String content, boolean done) {
        this.content = content;
        this.done = done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public String getContent() {
        return content;
    }
    
    @Override
    public ToDo clone() {
    	return new ToDo(content, done);
    }
}

