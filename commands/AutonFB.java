/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

//FB for forward or backward
//1 is forward
//-1 is backward
public class AutonFB extends Command {
private double distance,fb,y;
private Timer timer = new Timer();
  public AutonFB(double Distance, double Fb) {
    //distance is the distance we wish to travel
    distance = Distance;
    fb = Fb;
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    SmartDashboard.putString("Step","Reset @ init 1");
    Robot.driveTrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //check direction
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
    //set motor power
    SmartDashboard.putNumber("Distance",distance);
    SmartDashboard.putNumber("Front Left", Robot.driveTrain.fL.getSelectedSensorPosition());
    if(fb==1){
    if(Robot.driveTrain.fL.getSelectedSensorPosition() < distance) {
        Robot.driveTrain.mecanumDrive(0, -y, 0);
        SmartDashboard.putNumber("fb", fb);
        SmartDashboard.putString("Step", "Forward");
        return false;
      }else{
        Robot.driveTrain.mecanumDrive(0, 0, 0);
        SmartDashboard.putString("Step", "End 1");
        //timer.delay(5);
        return true;
      }   
    }else if(fb==-1){if(Robot.driveTrain.fL.getSelectedSensorPosition() > -distance) {
      Robot.driveTrain.mecanumDrive(0, -y, 0);
      SmartDashboard.putNumber("fb", fb);
      SmartDashboard.putString("Step", "Backward");
      return false;
    }else{
      Robot.driveTrain.mecanumDrive(0, 0, 0);
      SmartDashboard.putString("Step", "End 2");
      //timer.delay(5);
      return true;
    }   
  }else{return true;}

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Step","Reset @ end 1");
    
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
