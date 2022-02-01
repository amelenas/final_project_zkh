package by.stepanovich.zkh.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

   ResponseContext execute(HttpServletRequest request);

   static Command of(String name) {
      return CommandManager.getInstance().getCommand(name);
   }

}
