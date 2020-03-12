package io.swerve.drive;

import io.swerve.electronics.IMotorController;
import io.swerve.electronics.MotorOutput;
import io.swerve.electronics.SoftwarePID;
import io.swerve.math.Matrix3;
import io.swerve.math.Vector3;

public class WCPSwerveDriveKinematicsBased extends WCPSwerveWheel {
    protected Matrix3 m_torqueDistribution;
    protected SoftwarePID m_rotatePID,m_drivePID;
    public WCPSwerveDriveKinematicsBased(IMotorController driveMotor, IMotorController rotateMotor, double angleEps,Matrix3 torqueDistribution,SoftwarePID rotatePID,SoftwarePID drivePID) {
        super(driveMotor, rotateMotor, angleEps);
        m_torqueDistribution=torqueDistribution;
        m_drivePID=drivePID;
        m_rotatePID=rotatePID;
    }
    public void run(){
        m_rotatePID.setMeasurementValue(m_rotateMotor.getEncoderValue());
        m_rotatePID.setTarget(getRotationEncoderTarget());
        //m_drivePID.setMeasurementValue(m_driveMotor.getEncoderVelocity());
        //m_drivePID.setTarget(m_targetVelocity);
        m_rotatePID.run();//m_drivePID.run();
        Vector3 input=Vector3.create(m_rotatePID.getOutput(), m_targetVelocity, 0);
        Vector3 output=m_torqueDistribution.apply(input);
        m_rotateMotor.setOutput(output.getX(), MotorOutput.Current);
        m_driveMotor.setOutput(output.getY(), MotorOutput.Current);
        Vector3.recycle(input,output);
    }

}