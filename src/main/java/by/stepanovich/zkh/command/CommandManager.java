package by.stepanovich.zkh.command;

import by.stepanovich.zkh.command.common.*;
import by.stepanovich.zkh.command.employee.*;
import by.stepanovich.zkh.command.manager.*;
import by.stepanovich.zkh.command.user.*;

import java.util.EnumMap;

public class CommandManager {
    private static CommandManager instance;
    private final EnumMap<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    private CommandManager() {
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.SHOW_MAIN_PAGE, new ShowMainPageCommand());
        commands.put(CommandName.UPDATE_PASSWORD, new ChangePasswordCommand());
        commands.put(CommandName.PASSWORD_CHANGE_PAGE, new ShowPasswordPageCommand());
        commands.put(CommandName.PASSWORD_CHANGED, new PasswordChangedCommand());
        commands.put(CommandName.UPDATE_PROFILE_PAGE, new ShowUpdateProfileCommand());
        commands.put(CommandName.UPDATE_PROFILE, new UpdateProfileCommand());
        commands.put(CommandName.SHOW_LOGIN, new ShowUserLoginPageCommand());
        commands.put(CommandName.LOG_IN, new LogInCommand());
        commands.put(CommandName.LOG_OUT, new LogOutCommand());
        commands.put(CommandName.USER_INFO, new ShowProfileCommand());
        commands.put(CommandName.REGISTER_AS_EMPLOYEE_SUCCESS, new RegisterAsEmployeeSuccess());

        commands.put(CommandName.SHOW_REGISTER, new ShowUserRegisterPageCommand());
        commands.put(CommandName.REGISTER, new RegisterCommand());
        commands.put(CommandName.REGISTER_SUCCESS_PAGE, new RegisterSuccessCommand());
        commands.put(CommandName.SHOW_REGISTER_REQUEST_PAGE, new ShowRegisterRequestPageCommand());
        commands.put(CommandName.REGISTER_ORDER, new RegisterOrderCommand());
        commands.put(CommandName.REGISTER_ORDER_SUCCESS, new RequestAcceptedPageCommand());

        commands.put(CommandName.SHOW_ALL_USER_ORDERS, new ShowAllOrderByUserIdCommand());
        commands.put(CommandName.CANCEL_SINGLE_ORDER, new CancelSingleOrderCommand());

        commands.put(CommandName.MANAGER_TASK, new ShowManagerPageCommand());
        commands.put(CommandName.SHOW_ALL_USERS, new ShowAllUsersCommand());
        commands.put(CommandName.SHOW_ALL_ORDERS, new ShowAllOrdersCommand());
        commands.put(CommandName.ASSIGN_PERFORMER_PAGE, new ShowAssignPerformerPageCommand());
        commands.put(CommandName.ASSIGN_PERFORMER, new AssignPerformerCommand());
        commands.put(CommandName.FIND_PERFORMER, new FindPerformerCommand());
        commands.put(CommandName.CHOOSE_PERFORMER, new RegisterRunWorkCommand());
        commands.put(CommandName.SHOW_ADD_ADMIN_PAGE, new ShowAdminPageCommand());
        commands.put(CommandName.ADD_ADMIN, new AddAdminCommand());
        commands.put(CommandName.REGISTER_EMPLOYEE_PAGE, new RegisterEmployeePageCommand());
        commands.put(CommandName.REGISTER_EMPLOYEE, new RegisterEmployeeCommand());
        commands.put(CommandName.ACCEPT_EMPLOYEE_PAGE, new AcceptEmployeePageCommand());
        commands.put(CommandName.MAKE_USER, new AddUserStatusCommand());
        commands.put(CommandName.ADD_EMPLOYEE, new AddEmployeeCommand());
        commands.put(CommandName.REJECT_EMPLOYEE, new RejectEmployeeCommand());
        commands.put(CommandName.SENT_TO_WORK, new SentOrderToWorkCommand());
        commands.put(CommandName.SHOW_ORDERS_AT_WORK, new ShowOrdersAtWork());

        commands.put(CommandName.EMPLOYEE_TASK, new ShowEmployeePageCommand());
        commands.put(CommandName.TAKE_ORDER, new TakeOderCommand());
        commands.put(CommandName.CANCEL_EMPLOYEE_ORDER, new CancelEmployeeOrderCommand());
        commands.put(CommandName.CLOSE_BY_EMPLOYEE_ORDER, new CloseByEmployeeOrderCommand());
        commands.put(CommandName.CLOSE_ORDER, new CloseOrderCommand());
        commands.put(CommandName.CHECK_CLOSED_APPLICATIONS, new CheckClosedOrdersCommand());
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;}

    public void addCommand(CommandName commandName, Command command) {
        commands.put(commandName, command);
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandName.SHOW_MAIN_PAGE);
        }

        CommandName commandType;
        try {
            commandType = CommandName.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandName.SHOW_MAIN_PAGE;
        }
        return commands.get(commandType);
    }
}
