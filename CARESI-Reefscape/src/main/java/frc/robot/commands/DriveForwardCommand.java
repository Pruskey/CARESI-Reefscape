 package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class DriveForwardCommand extends Command {
    private final Drivetrain drivetrain;
    private final double targetDistance;

    public DriveForwardCommand(Drivetrain drivetrain, double targetDistance) {
        this.drivetrain = drivetrain;
        this.targetDistance = targetDistance;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetSensors();
        drivetrain.isBusy = true;
    }

    @Override
    public void execute() {
        drivetrain.driveToDistance(targetDistance);
    }

    @Override
    public boolean isFinished() {
        return !drivetrain.isBusy;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}