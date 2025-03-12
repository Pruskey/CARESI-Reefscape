package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.RotSubsystem;
import frc.robot.Constants;

public class AutonomousCommandGroup extends SequentialCommandGroup {

    public AutonomousCommandGroup(Drivetrain drivetrain, ArmSubsystem s_Arm, IntakeSubsystem s_Int, RotSubsystem s_Rot) {
        
        addCommands(
            new DriveForwardCommand(drivetrain, 50),
            new setArmPositionCommand(s_Arm, Constants.SetPoints.SPL1),
            new RotateIntakeCommand(s_Rot, Constants.IntakeConstants.rotatePositionH),
            new WaitCommand(2),
            new IntakeSpeedCommand(s_Int,-0.5),
            new WaitCommand(1),
            new IntakeSpeedCommand(s_Int, 0),
            new RotateIntakeCommand(s_Rot, Constants.IntakeConstants.rotatePositionV),
            new setArmPositionCommand(s_Arm, Constants.SetPoints.SPL2),
            new WaitCommand(1),
            new DriveForwardCommand(drivetrain, -10),
            new WaitCommand(1),
            new TurnToAngleCommand(drivetrain, -180)
        );
    }
}