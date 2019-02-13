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
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.DriveTrain;

public class Auton extends Command {

  private double distance,lr,fb,x,y;
  private Timer timer = new Timer();
  public Auton(int Distance,int  Lr,int Fb) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
    distance = Distance;
    lr = Lr;
    fb = Fb;
    

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveTrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(lr==1){
      x=.2;
    }
    if(lr==-1){
      x=-.2;
    }
    if(fb==1){
      y=.2;
    }
    if(fb==-1){
      y=-.2;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(fb==1 || lr==1){
      if(Robot.driveTrain.fL.getSelectedSensorPosition() < distance) {
        Robot.driveTrain.mecanumDrive(-y, -x, 0);
        SmartDashboard.putNumber("fb", fb);
        SmartDashboard.putString("Step", "Forward or Right");
        SmartDashboard.putNumber("lr", lr);
        return false;
      }else{
        Robot.driveTrain.mecanumDrive(0, 0, 0);
        SmartDashboard.putString("Step", "End 1");
        timer.delay(1);
        return true;
      }
    }else if(fb==-1 || lr==-1){
      if(Robot.driveTrain.fL.getSelectedSensorPosition() > -distance){
        Robot.driveTrain.mecanumDrive(-y, -x, 0);
        SmartDashboard.putString("Step", "Backward or Left");
        SmartDashboard.putNumber("fb", fb);
        SmartDashboard.putNumber("lr", lr);
        return false;
      }else{
        Robot.driveTrain.mecanumDrive(0, 0, 0);
        SmartDashboard.putString("Step", "End 2");
        return true;
    }
  }else{
    SmartDashboard.putString("Step", "Catch");
    return true;
  }
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
