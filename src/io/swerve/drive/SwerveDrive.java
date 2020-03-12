package io.swerve.drive;

import java.util.ArrayList;
import java.util.List;

import io.swerve.math.Vector3;

public abstract class SwerveDrive {
    protected List<SwerveWheel> m_wheels=new ArrayList<SwerveWheel>();
    protected List<Vector3> m_wheelRadialVectors=new ArrayList<Vector3>();
    protected Vector3 m_translation,m_rotation;
    public void addWheel(SwerveWheel wheel,Vector3 radial){
        m_wheels.add(wheel);
        m_wheelRadialVectors.add(radial);
    }

    public void setVelocity(Vector3 v){
        m_translation.setCoord(v.getX(), v.getY(), 0);
        m_rotation.setCoord(0,0,v.getZ());
    }
    public abstract void run();
}