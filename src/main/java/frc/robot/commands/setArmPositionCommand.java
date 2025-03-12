package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ArmSubsystem;

public class setArmPositionCommand extends Command {
  private final ArmSubsystem s_Arm;
  private final double position;

  public setArmPositionCommand(ArmSubsystem s_Arm, double position) {
    this.s_Arm = s_Arm;
    this.position = position;
    addRequirements(s_Arm);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    ArmSubsystem.setPosition(position);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
