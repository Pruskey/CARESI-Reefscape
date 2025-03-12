package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class ResetDriveCommand extends Command {
    private final Drivetrain drivetrain;

    public ResetDriveCommand(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetSensors();
        drivetrain.isBusy = true;
    }

    @Override
    public void execute() {
        drivetrain.setBusy();
    }

    @Override
    public boolean isFinished() {
        return false ;
    }

    @Override
    public void end(boolean interrupted) {
    }
}