package io.swerve.drive;

import io.swerve.electronics.IMotorController;
import io.swerve.electronics.MotorOutput;
import io.swerve.math.Vector3;

public class WCPSwerveWheel extends SwerveWheel {
    protected double m_angleEps;
    protected double m_motorInvertFactor = 1;

    public WCPSwerveWheel(IMotorController driveMotor, IMotorController rotateMotor, double angleEps) {
        super(driveMotor, rotateMotor);
        m_angleEps = angleEps;
    }

    @Override
    protected void calibrateEncoder() {
        // TODO Add calibrate code
        m_driveMotor.resetEncoder();
        m_rotateMotor.resetEncoder();
    }

    protected double getRotationEncoderTarget() {
        double encoderValue = m_rotateMotor.getEncoderValue();
        Vector3 targetAngleVector = Vector3.create(m_targetAngle);
        Vector3 currentAngleVector = Vector3.create(encoderValue);
        double crossValue = targetAngleVector.cross(currentAngleVector).rGetZ();
        double dotValue = targetAngleVector.dot(currentAngleVector);
        if (Math.abs(crossValue) < m_angleEps) {
            return 0;
        }
        if (dotValue < 0) {
            m_motorInvertFactor = -m_motorInvertFactor;
            dotValue = -dotValue;
            crossValue = -crossValue;
        }
        Vector3.recycle(targetAngleVector, currentAngleVector);
        return encoderValue + Math.copySign(Math.acos(dotValue), crossValue);
    }

    @Override
    public void run() {
        m_driveMotor.setOutput(m_targetVelocity*m_motorInvertFactor,MotorOutput.Velocity);
        m_driveMotor.setOutput(getRotationEncoderTarget(),MotorOutput.Position);
    }

}