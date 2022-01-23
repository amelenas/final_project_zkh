package by.stepanovich.zkh.command;

public interface Command {

   ResponseContext execute(RequestContent request);

   static Command of(String name) {
      return CommandManager.getInstance().getCommand(name);
   }

}
