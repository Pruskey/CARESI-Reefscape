package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class TurnToAngleCommand extends Command {
    private final Drivetrain drivetrain;
    private final double targetAngle;

    public TurnToAngleCommand(Drivetrain drivetrain, double targetAngle) {
        this.drivetrain = drivetrain;
        this.targetAngle = targetAngle;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.isBusy = true;
        drivetrain.resetSensors();
    }

    @Override
    public void execute() {
        drivetrain.turnToAngle(targetAngle);
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
