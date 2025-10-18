package Practice5_12.Interfaces;

public interface Command {
    void execute();
    void  undo();
    String getDescription();
}
