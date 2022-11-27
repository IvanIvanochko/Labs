package commandPattern;

import java.sql.Statement;

/*There can be multiple class Invokers*/
/*For example: RemoteControlInvoker, CarKeysInvoker and etc.*/
public class AccountManager // Invoker
{
    private RegisterCommand registerCommand;
    private InfoRegisterCommand infoRegisterCommand;
    private LogInCommand logInCommand;

    /*There can be multiple parameters for constructor*/
    /*For example: (ICommand lightsOn, ICommand lightsOff, ICommand changeFloorLightColor and etc.))*/
    /*Actual parameters: (TurnLightsCommand, TurnLightsCommand, FloorLightsCommand and etc.)*/
    public AccountManager(Statement statement){
        this.registerCommand = new RegisterCommand(statement);
        this.infoRegisterCommand = new InfoRegisterCommand(statement, registerCommand);
        this.logInCommand = new LogInCommand(statement);
    }
    public void setCommand(ICommand command) {}
    public void createAccount() {
        registerCommand.execute();
        infoRegisterCommand.execute();
    }
    public void logInAccount(){
        logInCommand.execute();
        System.out.println("You are logged in!");
    }
}
