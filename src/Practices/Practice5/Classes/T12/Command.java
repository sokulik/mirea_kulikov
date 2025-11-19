package Practices.Practice5.Classes.T12;

public interface Command {
    void execute();
    void  undo();
    String getDescription();
}
