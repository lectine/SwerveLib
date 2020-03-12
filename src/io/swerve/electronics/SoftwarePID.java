package io.swerve.electronics;

public class SoftwarePID{
    protected double m_kp,m_ki,m_kd,m_kf,m_iz,m_dt,m_upper,m_lower;
    protected double m_result=0;
    protected double m_previousError=0;
    protected double m_target;
    protected double m_measure;
    protected double m_intergal;
    public SoftwarePID(double kp,double ki,double kd,double kf,double iz,double dt,double upper,double lower){
        m_kp=kp;
        m_ki=ki;
        m_kd=kd;
        m_kf=kf;
        m_iz=iz;
        m_dt=dt;
        m_upper=upper;
        m_lower=lower;
    }
    public void start(){

    }
    public void stop(){
        
    }
    public void setMeasurementValue(double value){
        m_measure=value;
    }
    public void setTarget(double value){
        m_target=value;
    }
    public double getOutput(){
        return m_result;
    }
    public void run(){
        double error=m_target-m_measure;
        if(Math.abs(error)>m_iz){
            m_intergal=0;
        }else{
            m_intergal+=error*m_dt;
        }
        m_result=error*m_kp+(error-m_previousError)*m_kd/m_dt+m_intergal*m_ki+m_target*m_kf;
        m_result=Math.max(m_lower,Math.min(m_result, m_upper));
    }
}