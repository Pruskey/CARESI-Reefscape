// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Represents a differential drive style drivetrain. */
public class Drivetrain extends SubsystemBase {
  SparkMax leftLeader = new SparkMax(Constants.DrivetrainConstants.kLeftLeaderID, MotorType.kBrushless);
  SparkMax leftFollower = new SparkMax(Constants.DrivetrainConstants.kLeftFollowerID, MotorType.kBrushless);
  SparkMax rightLeader = new SparkMax(Constants.DrivetrainConstants.kRightLeaderID, MotorType.kBrushless);
  SparkMax rightFollower = new SparkMax(Constants.DrivetrainConstants.kRightFollowerID, MotorType.kBrushless);
//e
  MotorControllerGroup leftGroup = new MotorControllerGroup(leftLeader, leftFollower);
  MotorControllerGroup rightGroup = new MotorControllerGroup(rightLeader, rightFollower);

  RelativeEncoder leftEncoder = leftLeader.getEncoder();
  RelativeEncoder rightEncoder = rightLeader.getEncoder();

  private Timer timer = new Timer();

  private Pigeon2 gyro = new Pigeon2(9, "rio");

  private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

  Joystick m_driverController = new Joystick(Constants.OperatorConstants.kDriverControllerID);

  private double previousSpeed = 0.0;
  private final double accelerationRate = 0.05; // Adjust this value for smoother acceleration

  public Drivetrain() {
    MotorControllerGroup leftGroup = new MotorControllerGroup(leftLeader, leftFollower);
    MotorControllerGroup rightGroup = new MotorControllerGroup(rightLeader, rightFollower);
  }

  public void drive(Joystick m_driverController) {
    double targetSpeed = -m_driverController.getRawAxis(1); // Get joystick input
    double turn = m_driverController.getRawAxis(4) * 0.8;

    // Gradually increase or decrease speed
    if (targetSpeed > previousSpeed + accelerationRate) {
        targetSpeed = previousSpeed + accelerationRate;
    } else if (targetSpeed < previousSpeed - accelerationRate) {
        targetSpeed = previousSpeed - accelerationRate;
    }

    previousSpeed = targetSpeed; // Store last speed

    drive.arcadeDrive(turn, -targetSpeed);
  }

  public double getAverageDistance() {
    return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
}

public double getYaw() {
    return gyro.getYaw().getValueAsDouble();
}

public void stop() {
    drive.arcadeDrive(0, 0);
}

public void DriveToDistanceCommand(double targetDistance) {
  
}

  /* public void driveForward(double power, double time){
    timer.start();
    leftGroup.set(power);
    rightGroup.set(power);
    if (timer.get() > time){
      leftGroup.set(0);
      rightGroup.set(0);
    }

  } */

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("isbusy", isBusy);
    SmartDashboard.putNumber("encoder Right", rightEncoder.getPosition());
    
    SmartDashboard.putNumber("encoder Left", leftEncoder.getPosition());
    SmartDashboard.putNumber("target", globalTargetDistance);
  }

public void resetSensors() {
  leftEncoder.setPosition(0);
  rightEncoder.setPosition(0);
  gyro.reset();
}
public void setBusy(){
  isBusy = true;
}
public boolean isBusy = false;
double globalTargetDistance = 0;
public void driveToDistance(double targetDistance) {
  isBusy = true;

  globalTargetDistance = targetDistance;
  double kP = 0.007;
  double delta = targetDistance-getAverageDistance();
  double power = delta*kP;
  if (power<-1){
    power = -1;
  } else if (power>1){
    power = 1;
  }
  double kPangle = 0.005;
  double yaw = gyro.getYaw().getValueAsDouble();
  double deltaYaw = 0-yaw;
  double yawCorrection = yaw*kPangle;

  leftGroup.set(power+yawCorrection);
  rightGroup.set(-power+yawCorrection);



  if (Math.abs((leftEncoder.getPosition() + -rightEncoder.getPosition()) / 2) >= Math.abs(targetDistance) - 10) {
    leftGroup.set(0);
    rightGroup.set(0);
    isBusy = false;
}

}

public void turnToAngle(double targetAngle) {
  StatusSignal<Angle> yaw = gyro.getYaw();
  double currentYaw = yaw.getValueAsDouble();
  double deltaYaw = targetAngle-currentYaw;
  double kPyaw = 0.002;
  double power = deltaYaw*kPyaw;
  leftGroup.set(-power);
  rightGroup.set(-power);
  SmartDashboard.putNumber("deltaya", deltaYaw);

  if (Math.abs(deltaYaw)<10){
        isBusy = false;
  }
  
}
}
