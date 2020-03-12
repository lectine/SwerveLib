package io.swerve.math;

public class Matrix3{
    protected Vector3[] m_rows=new Vector3[3];
    public Matrix3(Vector3 row1,Vector3 row2,Vector3 row3){
        m_rows[0]=row1;
        m_rows[1]=row2;
        m_rows[2]=row3;
    }
    public Vector3 apply(Vector3 rhs){
        Vector3 comp_x=m_rows[0].mul(rhs.m_x);
        Vector3 comp_y=m_rows[1].mul(rhs.m_y);
        Vector3 comp_z=m_rows[2].mul(rhs.m_z);
        Vector3 result=comp_x.rAdd(comp_y.rAdd(comp_z));
        comp_z.recycle();
        return result;
    }
    public Matrix3 mul(Matrix3 rhs){
        return new Matrix3(apply(rhs.m_rows[0]),apply(rhs.m_rows[1]),apply(rhs.m_rows[2]));
    }
    public void recycle(){
        Vector3.recycle(m_rows);
    }
}