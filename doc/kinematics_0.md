<head>
    <script src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML" type="text/javascript"></script>
    <script type="text/x-mathjax-config">
        MathJax.Hub.Config({
            tex2jax: {
            skipTags: ['script', 'noscript', 'style', 'textarea', 'pre'],
            inlineMath: [['$','$']]
            }
        });
    </script>
</head>

# 基于WCP的矢量底盘模块动力学分析
## WCP Based Swerve Drive Kinematics Analysis
------

容易得到如下受力分析示意图。
![8ujhtg.png](https://s1.ax1x.com/2020/03/13/8ujhtg.png)

其中 $\beta_r$、$\beta_R$分别代表小齿轮与大齿轮角加速度
$M_r$、$M_R$分别代表小齿轮与大齿轮受外部力矩(非电机提供)
$M$、$m$分别代表小齿轮与大齿轮质量
则有转动惯量
$$I_m=\frac{1}{2}m(r_1-r_2)^2\\
I_M=\frac{1}{2}MR^2$$
依牛顿第二定律及角动量定理有
$$\begin{cases} 
    T_2(r_1-r_2)-M_r=I_m\beta_r\\ 
    T_2-F=ma_r=mr_1\beta_R\\ 
    T_1R-M_R+Fr_1=(I_M+I_m+mr_1^2)\beta_R\\ 
\end{cases} $$
其中F为大齿轮对小齿轮的约束反力（未画出），自行脑补
设法消去F，得
$$\begin{cases}
    T_1=\frac{I_M+I_m+mr_1^2}{R}\beta_R-\frac{I_m r_1}{(r_1-r_2)R}\beta_r-\frac{r_1}{(r_1-r_2)R}M_r+\frac{M_R}{R} \\
    T_2=\frac{I_m}{r_1-r_2}\beta_r+\frac{M_r}{r_1-r_2}
\end{cases} $$
写成矩阵形式
$\begin{bmatrix}
   T_1\\
   T_2
\end{bmatrix}=
\begin{bmatrix}
    \frac{I_M+I_m+mr_1^2}{R} & -\frac{I_m r_1}{(r_1-r_2)R} & -\frac{r_1}{(r_1-r_2)R} \\
    0 & \frac{I_m}{r_1-r_2} & \frac{1}{r_1-r_2}
\end{bmatrix}
\begin{bmatrix}
    \beta_R \\
    \beta_r \\
    M_r
\end{bmatrix}+
\begin{bmatrix}
    \frac{M_R}{R} \\ 0 
\end{bmatrix} $

$\beta_R$、$\beta_r$为闭环控制器输出，$M_r$实际与地面对轮胎摩擦成线性关系，在纯滚动假设下可由期望的单轮牵引力换算得到。
$M_R$可视为常数，由实验测得。如果足够无聊，可建立预测模型得到$M_R$
