package io.swerve.drive;

import io.swerve.electronics.IMotorController;

public abstract class SwerveWheel{
    protected IMotorController m_driveMotor;
    protected IMotorController m_rotateMotor;
    protected double m_targetAngle,m_targetVelocity;
    public SwerveWheel(IMotorController driveMotor,IMotorController rotateMotor){
        m_driveMotor=driveMotor;
        m_rotateMotor=rotateMotor;

        calibrateEncoder();
    }
    protected abstract void calibrateEncoder();
    public abstract void run();

    public void setOutput(double angle,double value){
        m_targetAngle=angle;
        m_targetVelocity=value;
    }
}