/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import java.lang.Math;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class AutonDrive extends Command {
  public double inches, x, y;
  public boolean end = false;
  

  public AutonDrive(double inches,double x, double y) {
    // Use requires() here to declare subsystem dependencies
    this.inches = inches;
    this.x = x;
    this.y = y;
    end = false;
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    end = false;
    inches *= 1;
    Robot.driveTrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putBoolean("State",end);
    SmartDashboard.putNumber("x",x);
    SmartDashboard.putNumber("y",y);
    SmartDashboard.putNumber("Front Left", Robot.driveTrain.fL.getSelectedSensorPosition());
    SmartDashboard.putNumber("Inches", inches);
    

    if(x>0){
      if(Robot.driveTrain.fL.getSelectedSensorPosition() > inches){
        Robot.driveTrain.mecanumDrive(y,x, 0);
      }else{
        //Robot.driveTrain.mecanumDrive(0, 0, 0);
        end = true;
        //SmartDashboard.putBoolean("State",end);
      }
    }else if(y>0) {
      if(Robot.driveTrain.fL.getSelectedSensorPosition() > inches){
        Robot.driveTrain.mecanumDrive(y,x, 0);
      }else{
        //Robot.driveTrain.mecanumDrive(0, 0, 0);
        end = true;
        //SmartDashboard.putBoolean("State",end);
      }
    }else if(x<0) {
      if(Robot.driveTrain.fL.getSelectedSensorPosition() < inches){
        Robot.driveTrain.mecanumDrive(y,x, 0);
      }else{
        //Robot.driveTrain.mecanumDrive(0, 0, 0);
        Timer.delay(1);
        end = true;
        //SmartDashboard.putBoolean("State",end);
      }
    }else if (y<0) {
      
      if(Robot.driveTrain.fL.getSelectedSensorPosition() < inches){
        Robot.driveTrain.mecanumDrive(y,x, 0);
      }else{
        //Robot.driveTrain.mecanumDrive(y, x, 0);
        //Robot.driveTrain.mecanumDrive(0, 0, 0);
        end = true;
        //SmartDashboard.putBoolean("State",end);
      }
    }else{end = true;}
  } 

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putBoolean("State",end);
    return end;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    end = false;
    //SmartDashboard.putBoolean("State", end);
    Robot.driveTrain.mecanumDrive(0, 0, 0);
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
