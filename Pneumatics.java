/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Add your docs here.
 */
public class Pneumatics extends Subsystem {
  DoubleSolenoid pneumaticChad = null;
  public Compressor compressor = new Compressor(0);
  public Pneumatics() {
    DoubleSolenoid pneumaticChad = new DoubleSolenoid(0, 1);
  }
  public void thingUp(){
  pneumaticChad.set(DoubleSolenoid.Value.kForward);}
  public void thingDown(){
  pneumaticChad.set(DoubleSolenoid.Value.kForward);}
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
