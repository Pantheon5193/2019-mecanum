/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class MecanumDriveStick extends Command {
  public MecanumDriveStick() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.pneumatics.compressor.setClosedLoopControl(true);
    Robot.driveTrain.gyro.reset();
    Robot.driveTrain.gyro.calibrate();
    Robot.driveTrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    SmartDashboard.putNumber("Gyro", Robot.driveTrain.gyro.getAngle());
    SmartDashboard.putNumber("Timer", Robot.driveTrain.timer.get());
    SmartDashboard.putNumber("Front Left", Robot.driveTrain.fL.getSelectedSensorPosition());
    SmartDashboard.putNumber("Front Right", Robot.driveTrain.fR.getSelectedSensorPosition());
    SmartDashboard.putNumber("Back Left", Robot.driveTrain.bL.getSelectedSensorPosition());
    SmartDashboard.putNumber("Back Right", Robot.driveTrain.bR.getSelectedSensorPosition());

    double x;
    double y;
    double z;
    if(Math.abs(Robot.m_oi.stick.getY()) > .2){
      y = Robot.m_oi.stick.getY();
    }else{y = 0;}
    
     x = Robot.m_oi.stick.getX();
     z = Robot.m_oi.stick.getZ();

    Robot.driveTrain.mecanumDrive(x, y, -z);


  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
