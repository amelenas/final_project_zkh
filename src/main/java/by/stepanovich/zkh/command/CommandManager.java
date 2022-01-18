package by.stepanovich.zkh.command;


import by.stepanovich.zkh.command.order.RegisterOrder;
import by.stepanovich.zkh.command.order.SavePhoto;
import by.stepanovich.zkh.command.page.*;
import by.stepanovich.zkh.command.common.LogInCommand;
import by.stepanovich.zkh.command.common.LogOutCommand;
import by.stepanovich.zkh.command.common.RegisterCommand;
import by.stepanovich.zkh.command.user.ShowRegisterRequestPage;

import java.util.EnumMap;

public class CommandManager {
    private static CommandManager instance;
    private final EnumMap<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    private CommandManager() {
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.LOG_IN, new LogInCommand());
        commands.put(CommandName.LOG_OUT, new LogOutCommand());
        commands.put(CommandName.REGISTER, new RegisterCommand());
        commands.put(CommandName.DEFAULT, (new ShowMainPageCommand()));
        commands.put(CommandName.SHOW_REGISTER, new ShowUserRegisterPageCommand());
        commands.put(CommandName.SHOW_LOGIN, new ShowUserLoginPageCommand());
        commands.put(CommandName.USER_INFO, new ShowProfileCommand());
        commands.put(CommandName.REGISTER_ORDER, new RegisterOrder());
        commands.put(CommandName.SHOW_MAIN_PAGE, new ShowMainPageCommand());
        commands.put(CommandName.SAVE_PHOTO, new SavePhoto());
        commands.put(CommandName.SHOW_REGISTER_REQUEST_PAGE, new ShowRegisterRequestPage());
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    public void addCommand(CommandName commandName, Command command) {
        commands.put(commandName, command);
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandName.DEFAULT);
        }

        CommandName commandType;
        try {
            commandType = CommandName.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandName.DEFAULT;
        }

        return commands.get(commandType);
    }
}
