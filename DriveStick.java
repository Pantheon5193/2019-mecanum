/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.I2C_Interface;
import frc.robot.subsystems.PixyPacket;
//import frc.robot.subsystems.I2C_Interface.PixyPacket;


/**
 * An example command.  You can replace me with your own command.
 */
public class DriveStick extends Command {
  I2C_Interface i2c = new I2C_Interface();
  PixyPacket pkt = i2c.getPixy();
  public DriveStick() {
     
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
     double move = Robot.m_oi.stick.getY();
     double turn = Robot.m_oi.stick.getX();
     Robot.driveTrain.manualDrive(move, turn);

     pkt = i2c.getPixy();
     SmartDashboard.putNumber("XPos: ", pkt.percentX);



     //Robot.driveTrain.manualDrive(move, turn);
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
