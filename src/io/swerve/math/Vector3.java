package io.swerve.math;

import java.util.ArrayDeque;
import java.util.Queue;


/*
* 3-dimension vectors
* Use radian system and right-hand coordinate system
*/
public class Vector3 {
    private static final int s_poolSize = 128;
    /*
        Java doesn't support struct like C#. 
        For avoiding frequent garbage collection
    */
    private static final Queue<Vector3> s_vectorPool = new ArrayDeque<Vector3>(s_poolSize);

    public static void init() {
        for (int i = 0; i < s_poolSize; i++)
            s_vectorPool.add(new Vector3(0, 0, 0));
    }

    public static Vector3 create(double x, double y, double z) {
        synchronized (s_vectorPool) {
            if (s_vectorPool.isEmpty())
                throw new OutOfMemoryError("memory pool empty while acquiring vector3");
            Vector3 instance = s_vectorPool.poll();
            instance.m_recycled = false;
            return instance.setCoord(x, y, z);
        }
    }
    public static Vector3 create(double angle){
        return create(Math.cos(angle),Math.sin(angle),0);
    }
    public static void recycle(Vector3... rec) {
        // s_vectorPool.addAll();
        synchronized (s_vectorPool) {
            for (Vector3 i : rec)
                if (!i.m_recycled) {
                    s_vectorPool.add(i);
                    i.m_recycled = true;
                }
        }

    }

    protected double m_x, m_y, m_z;
    protected boolean m_recycled = false;

    private Vector3(double x, double y, double z) {
        m_x = x;
        m_y = y;
        m_z = z;
    }

    public Vector3 setCoord(double x, double y, double z) {
        m_x = x;
        m_y = y;
        m_z = z;
        return this;
    }

    public Vector3 add(Vector3 rhs) {
        return create(m_x + rhs.m_x, m_y + rhs.m_y, m_z + rhs.m_z);
    }

    public Vector3 sub(Vector3 rhs) {
        return create(m_x - rhs.m_x, m_y - rhs.m_y, m_z - rhs.m_z);
    }

    public Vector3 mul(double rhs) {
        return create(m_x * rhs, m_y * rhs, m_z * rhs);
    }

    public double dot(Vector3 rhs) {
        return m_x * rhs.m_x + m_y * rhs.m_y + m_z * rhs.m_z;
    }

    public Vector3 cross(Vector3 rhs) {
        return create(m_y * rhs.m_z - m_z * rhs.m_y, m_z * rhs.m_x - m_x * rhs.m_z, m_x * rhs.m_y - m_y * rhs.m_x);
    }
    public double getX(){
        return m_x;
    }
    public double getY(){
        return m_y;
    }
    public double getZ(){
        return m_z;
    }
    public double rGetZ(){
        recycle(this);
        return m_z;
    }
    public double rGetX(){
        recycle(this);
        return m_x;
    }
    public double rGetY(){
        recycle(this);
        return m_y;
    }
    
    public Vector3 rAdd(Vector3 rhs) {
        recycle(this);
        return create(m_x + rhs.m_x, m_y + rhs.m_y, m_z + rhs.m_z);
    }

    public Vector3 rSub(Vector3 rhs) {
        recycle(this);
        return create(m_x - rhs.m_x, m_y - rhs.m_y, m_z - rhs.m_z);
    }

    public Vector3 rMul(double rhs) {
        recycle(this);
        return create(m_x * rhs, m_y * rhs, m_z * rhs);
    }

    public double rDot(Vector3 rhs) {
        recycle(this);
        return m_x * rhs.m_x + m_y * rhs.m_y + m_z * rhs.m_z;
    }

    public Vector3 rCross(Vector3 rhs) {
        recycle(this);
        return create(m_y * rhs.m_z - m_z * rhs.m_y, m_z * rhs.m_x - m_x * rhs.m_z, m_x * rhs.m_y - m_y * rhs.m_x);
    }

    public double rLength() {
        recycle(this);
        return Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z);
    }

    public double length() {
        return Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z);
    }

    public void recycle() {
        if (!m_recycled)
            recycle(this);
    }

    public String toString() {
        return String.format("[%f %f %f]T", m_x, m_y, m_z);
    }
}