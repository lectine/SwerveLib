package io.swerve.electronics;

public interface IMotorController{
    public void setOutput(double value,MotorOutput type);
    public void setOutput(double value,MotorOutput type,double correction); 
    public void setHardwarePidParameters(double kp,double ki,double kd,double kf,double iz);
    public void follow(IMotorController controller,boolean invert);
    public double getOutput();
    public double getOutputCurrent();
    /*
        //use angle system to avoid float calculation and precision loss
        use radian system due to java math lib support
    */
    public double getEncoderValue();
    public double getEncoderVelocity();
    public void resetEncoder();
    public void setMode(MotorMode motorMode);
}