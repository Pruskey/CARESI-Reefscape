// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DriveCommands;
import frc.robot.commands.AutonomousCommandGroup;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.RotSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private Drivetrain drivetrain = new Drivetrain();
  private Joystick m_driverController = new Joystick(0);
  private CommandXboxController controllerS = new CommandXboxController(1);
  private CommandXboxController controller = new CommandXboxController(0);
  private ArmSubsystem arm = new ArmSubsystem();
  private IntakeSubsystem Intake = new IntakeSubsystem();
  private RotSubsystem rot = new RotSubsystem();
  private ClimbSubsystem climb = new ClimbSubsystem();

  private final double MAX_ARM_POSITION = -10 * 300;

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

    
    //Drivetrain
    drivetrain.setDefaultCommand(new DriveCommands(drivetrain, m_driverController));
    
    controller.axisGreaterThan(0, 0);
    controller.button(0);


    //Mecher braço manualmente no analógico
    /*controllerS.axisGreaterThan(1, 0.2).onTrue(new InstantCommand(() -> arm.setPower(0.2), arm))
    .onFalse(new InstantCommand(() -> arm.setPower(0), arm));

    controllerS.axisLessThan(1, -0.2).onTrue(new InstantCommand(() -> arm.setPower(-0.2), arm))
    .onFalse(new InstantCommand(() -> arm.setPower(0), arm));
    */

    controllerS.a().onTrue(new InstantCommand(() -> arm.setPower(0.2), arm))
    .onFalse(new InstantCommand(() -> arm.setPower(0), arm));

    controllerS.y().onTrue(new InstantCommand(() -> arm.setPower(-0.2), arm))
    .onFalse(new InstantCommand(() -> arm.setPower(0), arm));

    /*controllerS.y().onTrue(new InstantCommand(() -> arm.setPower(-0.2), arm))
    .onFalse(new InstantCommand(() -> arm.setPower(0), arm));*/

    //Power Intake
    controllerS.axisGreaterThan(3, 0.2).onTrue(new InstantCommand(() -> Intake.setPowerInt(-0.2), Intake))
    .onFalse(new InstantCommand(() -> Intake.setPowerInt(0), Intake));

    controllerS.axisGreaterThan(2, 0.2).onTrue(new InstantCommand(() -> Intake.setPowerInt(0.5), Intake))
    .onFalse(new InstantCommand(() -> Intake.setPowerInt(0), Intake));


    //Power Climb
    /*controller.x().onTrue(new InstantCommand(() -> climb.setPower(-0.5), climb))
    .onFalse(new InstantCommand(() -> climb.setPower(0), climb));*/

    controller.b().onTrue(new InstantCommand(() -> climb.setPower(0.5), climb))
    .onFalse(new InstantCommand(() -> climb.setPower(0), climb));

    controller.x().onTrue(new InstantCommand(() -> climb.setPower(-0.5), climb))
    .onFalse(new InstantCommand(() -> climb.setPower(0), climb)); 

    controller.y().onTrue(new InstantCommand(() -> arm.setPower(0.04), arm))
    .onFalse(new InstantCommand(() -> climb.setPower(0), arm));

        //PID Braco
    controllerS.povDown().onTrue(new InstantCommand(() -> arm.setPosition(Constants.SetPoints.SPL1))); //l1
    controllerS.povLeft().onTrue(new InstantCommand(() -> arm.setPosition(Constants.SetPoints.SPL2))); //l2
    controllerS.povUp().onTrue(new InstantCommand(() -> arm.setPosition(Constants.SetPoints.SPL3))); //l3
    controllerS.povRight().onTrue(new InstantCommand(() -> arm.setPosition(Constants.SetPoints.SPHP))); //source

    //Girar Intake
    controllerS.rightBumper().onTrue(new InstantCommand(() -> rot.setPosition(Constants.IntakeConstants.rotatePositionH)));
    controllerS.leftBumper().onTrue(new InstantCommand(() -> rot.setPosition(Constants.IntakeConstants.rotatePositionV)));
    controllerS.x().onTrue(new InstantCommand(() -> rot.setPosition(Constants.IntakeConstants.rotatePositionVR)));
    
   

    }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new AutonomousCommandGroup(drivetrain, arm, Intake, rot);
  }
}
