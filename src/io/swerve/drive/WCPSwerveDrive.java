package io.swerve.drive;

import io.swerve.math.Vector3;

public class WCPSwerveDrive extends SwerveDrive {
    @Override
    public void addWheel(SwerveWheel wheel,Vector3 radial){
        if(!(wheel instanceof WCPSwerveWheel)){
            throw new IllegalArgumentException(String.format("Attempt to add a '%s' wheel to WCPSwerveDrive",wheel.getClass().getSimpleName()));
        }
        super.addWheel(wheel,radial);
    }
    @Override
    public void run() {
        for(int i=0;i<m_wheels.size();i++){
            Vector3 veloc=m_wheelRadialVectors.get(i).cross(m_rotation).rAdd(m_translation);
            m_wheels.get(i).setOutput(Math.atan2(veloc.getY(), veloc.getX()), veloc.length());
            Vector3.recycle(veloc);
        }
    }

}