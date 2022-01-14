package by.stepanovich.zkh.command;


import by.stepanovich.zkh.command.order.RegisterOrder;
import by.stepanovich.zkh.command.page.ShowMainPageCommand;
import by.stepanovich.zkh.command.page.ShowProfileCommand;
import by.stepanovich.zkh.command.page.ShowUserLoginPageCommand;
import by.stepanovich.zkh.command.page.ShowUserRegisterPageCommand;
import by.stepanovich.zkh.command.user.LogInCommand;
import by.stepanovich.zkh.command.user.LogOutCommand;
import by.stepanovich.zkh.command.user.RegisterCommand;

public enum CommandManager {
    LOG_IN(new LogInCommand()),
    LOG_OUT(new LogOutCommand()),
    REGISTER(new RegisterCommand()),
    DEFAULT(new ShowMainPageCommand()),
    SHOW_REGISTER(new ShowUserRegisterPageCommand()),
    SHOW_LOGIN(new ShowUserLoginPageCommand()),
    USER_INFO(new ShowProfileCommand()),
    REGISTER_ORDER(new RegisterOrder());

    private final Command command;

    CommandManager(Command command) {
        this.command = command;
    }

    public static Command of(String name) {
        for (CommandManager value : CommandManager.values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value.command;
            }
        }
        return DEFAULT.command;
    }
}
