package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeSpeedCommand extends Command {
  private final IntakeSubsystem s_Int;
  private final double speed;

  public IntakeSpeedCommand(IntakeSubsystem s_Int, double speed) {
    this.s_Int = s_Int;
    this.speed = speed;
    addRequirements(s_Int);
  }


  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    IntakeSubsystem.setPowerInt(speed);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
