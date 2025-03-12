package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.RotSubsystem;

public class RotateIntakeCommand extends Command {
  private final RotSubsystem s_Rot;
  private final double position;

  public RotateIntakeCommand (RotSubsystem s_Rot, double position) {
    this.s_Rot = s_Rot;
    this.position = position;
    addRequirements(s_Rot);
  }


  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    RotSubsystem.setPosition(position);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
