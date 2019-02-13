/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro; 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.commands.DriveStick;
import frc.robot.commands.MecanumDriveStick;
import frc.robot.commands.CameraFeedBack;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  
  public WPI_VictorSPX leftMVictorSPX = new WPI_VictorSPX(1);
  public WPI_VictorSPX righhVictorSPX = new WPI_VictorSPX(2);

  public WPI_TalonSRX fL = new WPI_TalonSRX(1);
  public WPI_TalonSRX fR = new WPI_TalonSRX(3);
  public WPI_TalonSRX bL = new WPI_TalonSRX(4);
  public WPI_TalonSRX bR = new WPI_TalonSRX(2);

  public ADXRS450_Gyro gyro = new ADXRS450_Gyro();


  public Timer timer = new Timer();

  public Encoder left = new Encoder(4,1,false,Encoder.EncodingType.k4X);
  public Encoder right = new Encoder(3,2,false,Encoder.EncodingType.k4X);

  public DifferentialDrive drive = new DifferentialDrive(righhVictorSPX, leftMVictorSPX);
  public MecanumDrive mDrive = new MecanumDrive(fL, bR, bL, fR);
  // here. Call these from Commands.

  public void manualDrive(double move,double turn) {
    drive.arcadeDrive(move, turn);
  }

  public void mecanumDrive(double x, double y, double z) {
    mDrive.driveCartesian(y, x, z);
  }

  public void resetEncoders() {
    fL.setSelectedSensorPosition(0,0,0);
    fR.setSelectedSensorPosition(0,0,0);
    bL.setSelectedSensorPosition(0,0,0);
    bR.setSelectedSensorPosition(0,0,0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveStick());
    //setDefaultCommand(new CameraFeedBack());
    //setDefaultCommand(new MecanumDriveStick());
  }
}
