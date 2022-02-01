package by.stepanovich.zkh.command;

import by.stepanovich.zkh.command.common.*;
import by.stepanovich.zkh.command.manager.*;
import by.stepanovich.zkh.command.order.CancelSingleOrderCommand;
import by.stepanovich.zkh.command.order.RegisterOrderCommand;
import by.stepanovich.zkh.command.user.ShowAllOrderByUserIdCommand;
import by.stepanovich.zkh.command.user.ShowRegisterRequestPageCommand;

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
        commands.put(CommandName.SHOW_REGISTER_REQUEST_PAGE, new ShowRegisterRequestPageCommand());
        commands.put(CommandName.SHOW_ALL_USER_ORDERS, new ShowAllOrderByUserIdCommand());
        commands.put(CommandName.CANCEL_SINGLE_ORDER, new CancelSingleOrderCommand());
        commands.put(CommandName.SHOW_ALL_USERS, new ShowAllUsersCommand());
        commands.put(CommandName.SHOW_ALL_ORDERS, new ShowAllOrdersCommand());
        commands.put(CommandName.MANAGER_TASK, new ShowManagerPageCommand());
        commands.put(CommandName.UPDATE_PASSWORD, new ChangePasswordCommand());
        commands.put(CommandName.PASSWORD_CHANGE_PAGE, new ShowPasswordPageCommand());
        commands.put(CommandName.UPDATE_PROFILE_PAGE, new ShowUpdateProfileCommand());
        commands.put(CommandName.UPDATE_PROFILE, new UpdateProfileCommand());
        commands.put(CommandName.ASSIGN_PERFORMER_PAGE, new ShowAssignPerformerPageCommand());
        commands.put(CommandName.ASSIGN_PERFORMER, new AssignPerformerCommand());
        commands.put(CommandName.FIND_PERFORMER, new FindPerformerCommand());

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
