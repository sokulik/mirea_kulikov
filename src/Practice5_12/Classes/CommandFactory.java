package Practice5_12.Classes;

import Practice5_12.Interfaces.Command;
import java.lang.StringBuilder;

public class CommandFactory {
    private StringBuilder stringBuilder;

    public CommandFactory(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public Command createAppendCommand(String str) {
        return new AppendCommand(stringBuilder, str);
    }

    public Command createDeleteCommand(int start, int end) {
        return new DeleteCommand(stringBuilder, start, end);
    }

    public Command createInsertCommand(int offset, String str) {
        return new InsertCommand(stringBuilder, offset, str);
    }

    public Command createReplaceCommand(int start, int end, String str) {
        return new ReplaceCommand(stringBuilder, start, end, str);
    }

    public Command createReverseCommand() {
        return new ReverseCommand(stringBuilder);
    }
}