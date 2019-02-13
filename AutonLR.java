/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class AutonLR extends Command {
  private double distance,lr,x;
  private Timer timer = new Timer();
  public AutonLR(double Distance,double Lr) {
    distance = Distance;
    lr = Lr;
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    SmartDashboard.putString("Step","Reset @ init 2");
    Robot.driveTrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(lr==1){
      x=.4;
    }
    if(lr==-1){
      x=-.4;
    }
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putNumber("Distance",distance);
      SmartDashboard.putNumber("Front Left", Robot.driveTrain.fL.getSelectedSensorPosition());
    if(lr==1){
      
    if(Robot.driveTrain.fL.getSelectedSensorPosition() < distance) {
      SmartDashboard.putNumber("Distance",distance);
      Robot.driveTrain.mecanumDrive(-x, 0, 0);
      SmartDashboard.putString("Step", "Right");
      SmartDashboard.putNumber("lr", lr);
      return false;
    }else{
      Robot.driveTrain.mecanumDrive(0, 0, 0);
      SmartDashboard.putString("Step", "End 3");
      //timer.delay(5);
      return true;
    }
  }else if(lr==-1){
    if(Robot.driveTrain.fL.getSelectedSensorPosition() > -distance) {
    Robot.driveTrain.mecanumDrive(-x, 0, 0);
    SmartDashboard.putString("Step", "Left");
    SmartDashboard.putNumber("lr", lr);
    return false;
  }else{
    Robot.driveTrain.mecanumDrive(0, 0, 0);
    SmartDashboard.putString("Step", "End 4");
    //timer.delay(5);
    return true;
  }
}else{return true;}
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Step","Reset @ end 2");
    
    Robot.driveTrain.resetEncoders();
    SmartDashboard.putNumber("Front Left", Robot.driveTrain.fL.getSelectedSensorPosition());
    timer.delay(.1);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
