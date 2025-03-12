package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ArmSubsystem;

public class armUpCommand extends Command {
  private final ArmSubsystem s_Arm;

  public armUpCommand(ArmSubsystem s_Arm) {
    this.s_Arm = s_Arm;
    addRequirements(s_Arm);
  }


  @Override
  public void initialize() {
    ArmSubsystem.setPosition(0);
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean runsWhenDisabled(){
    return true;
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;

}

}
