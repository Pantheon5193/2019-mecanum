/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.commands.DriveStick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  
  public WPI_TalonSRX Top = new WPI_TalonSRX(1);
  public WPI_TalonSRX Bottom = new WPI_TalonSRX(3);

  public Timer timer = new Timer();

  public Encoder left = new Encoder(4,1,false,Encoder.EncodingType.k4X);
  public Encoder right = new Encoder(3,2,false,Encoder.EncodingType.k4X);

 // public DifferentialDrive drive = new DifferentialDrive(righhVictorSPX, leftMVictorSPX);

  // here. Call these from Commands.

 // public void manualDrive(double move,double turn) {
  //  drive.arcadeDrive(move, turn);
  


  public void resetEncoders() {
    Top.setSelectedSensorPosition(0,0,0);
    Bottom.setSelectedSensorPosition(0,0,0);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveStick());
    //setDefaultCommand(new CameraFeedBack());
    //setDefaultCommand(new MecanumDriveStick());
  }
}
