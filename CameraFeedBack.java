/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class CameraFeedBack extends Command {
  public CameraFeedBack() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  private static I2C Wire = new I2C(Port.kOnboard, 4);//uses the i2c port on the RoboRIO
	//uses address 4, must match arduino
    private static final int MAX_BYTES = 32;
    
    public static Double getData() {
    	byte[] data = new byte[MAX_BYTES];//create a byte array to hold the incoming data
		Wire.read(4, MAX_BYTES, data);//use address 4 on i2c and store it in data
		String output = new String(data);//create a string from the byte array
		int pt = output.indexOf((char)255);
		String realOut = (String) output.subSequence(0, pt < 0 ? 0 : pt);
		
		try {
			if (realOut == "none") {
				return 0d;
			} else {
				return Double.parseDouble(realOut);
			}
		} catch (NumberFormatException e) {
			return 0d;
		}
		
    }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //SmartDashboard.putNumber("percent x",getData());
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
