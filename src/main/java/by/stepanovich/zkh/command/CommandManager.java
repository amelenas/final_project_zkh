package by.stepanovich.zkh.command;

import by.stepanovich.zkh.command.order.CancelSingleOrderCommand;
import by.stepanovich.zkh.command.order.RegisterOrderCommand;
import by.stepanovich.zkh.command.order.SavePhotoCommand;
import by.stepanovich.zkh.command.page.*;
import by.stepanovich.zkh.command.common.LogInCommand;
import by.stepanovich.zkh.command.common.LogOutCommand;
import by.stepanovich.zkh.command.common.RegisterCommand;
import by.stepanovich.zkh.command.user.ShowAllOrderByUserId;
import by.stepanovich.zkh.command.user.ShowRegisterRequestPage;

import java.util.EnumMap;

public class CommandManager {
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
        commands.put(CommandName.REGISTER_ORDER, new RegisterOrderCommand());
        commands.put(CommandName.SHOW_MAIN_PAGE, new ShowMainPageCommand());
        commands.put(CommandName.SAVE_PHOTO, new SavePhotoCommand());
        commands.put(CommandName.SHOW_REGISTER_REQUEST_PAGE, new ShowRegisterRequestPage());
        commands.put(CommandName.SHOW_ALL_USER_ORDERS, new ShowAllOrderByUserId());
        commands.put(CommandName.CANCEL_SINGLE_ORDER, new CancelSingleOrderCommand());
    }

    public static class CommandManagerHolder {
        public static final CommandManager HOLDER_INSTANCE = new CommandManager();
    }

    public static CommandManager getInstance() {
        return CommandManagerHolder.HOLDER_INSTANCE;
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
