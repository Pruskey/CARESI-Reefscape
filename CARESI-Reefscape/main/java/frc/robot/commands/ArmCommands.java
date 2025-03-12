package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ArmSubsystem;
import pabeles.concurrency.ConcurrencyOps.Reset;

public class ArmCommands extends Command {
  private final ArmSubsystem s_Arm;

  public ArmCommands(ArmSubsystem s_Arm) {
    this.s_Arm = s_Arm;
    addRequirements(s_Arm);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
